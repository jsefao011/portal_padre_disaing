package com.consultoraestrategia.ss_crmeducativo.dao.horarioPrograma;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioPrograma;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioPrograma_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo_Table;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.Property;

import java.util.List;

public class HorarioProgramaDaoImpl extends BaseIntegerDaoImpl<HorarioPrograma, HorarioPrograma_Table> implements HorarioProgramaDao {

    private static HorarioProgramaDaoImpl mInstance;
    private String TAG = HorarioProgramaDaoImpl.class.getSimpleName();

    private HorarioProgramaDaoImpl() {
    }

    public static HorarioProgramaDaoImpl getInstance() {
        if (mInstance == null) {
            mInstance = new HorarioProgramaDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Property<Integer> getPrimaryKeyProperty() {
        return HorarioPrograma_Table.idHorarioPrograma;
    }

    @Override
    protected Class<HorarioPrograma> getEntityClass() {
        return HorarioPrograma.class;
    }

    @Override
    protected Class<HorarioPrograma_Table> getTableclass() {
        return HorarioPrograma_Table.class;
    }

    @Override
    public List<HorarioPrograma> obtenerPorCargacurso(int cargacursoId) {
        return SQLite.select(Utils.f_allcolumnTable(HorarioPrograma_Table.ALL_COLUMN_PROPERTIES))
                .from(HorarioPrograma.class)
                .innerJoin(ProgramasEducativo.class)
                .on(HorarioPrograma_Table.idProgramaEducativo.withTable()
                        .eq(ProgramasEducativo_Table.programaEduId.withTable()))
                .innerJoin(PlanEstudios.class)
                .on(ProgramasEducativo_Table.programaEduId.withTable()
                        .eq(PlanEstudios_Table.programaEduId.withTable()))
                .innerJoin(CargaCursos.class)
                .on(PlanEstudios_Table.planEstudiosId.withTable()
                        .eq(CargaCursos_Table.idPlanEstudio.withTable()))
                .where(CargaCursos_Table.cargaCursoId.withTable()
                        .eq(cargacursoId))
                .and(HorarioPrograma_Table.activo.withTable().eq(1))
                .queryList();
    }

    @Override
    public List<HorarioPrograma> obtenerPorProgramaEducativo(int programaEducativoId) {
        return SQLite.select(Utils.f_allcolumnTable(HorarioPrograma_Table.ALL_COLUMN_PROPERTIES))
                .from(HorarioPrograma.class)
                .innerJoin(ProgramasEducativo.class)
                .on(HorarioPrograma_Table.idProgramaEducativo.withTable()
                        .eq(ProgramasEducativo_Table.programaEduId.withTable()))
                .where(ProgramasEducativo_Table.programaEduId.withTable()
                        .eq(programaEducativoId))
                .and(HorarioPrograma_Table.activo.withTable().eq(1))
                .queryList();
    }
}
