package com.consultoraestrategia.ss_crmeducativo.dao.dimensionObservada;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionObservada;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionObservada_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoEvaluacion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoObservado;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoObservado_Table;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.Property;

import java.util.List;

/**
 * Created by SCIEV on 16/08/2018.
 */

public class DimensionObservadaDaoImpl extends BaseDaoImpl<DimensionObservada,DimensionObservada_Table> implements DimensionObservadaDao {

    private static DimensionObservadaDaoImpl mInstance;

    private DimensionObservadaDaoImpl() {
    }

    public static DimensionObservadaDao getInstance() {
        if (mInstance == null) {
            mInstance = new DimensionObservadaDaoImpl();
        }
        return mInstance;
    }

    @Override
    public DimensionObservada get(String id) {
        return getWithQuery(getDefaultSqlOperator().eq(id));
    }


    private Property<String> getDefaultSqlOperator() {
        return new Property<>(getTableclass(), "dimensionObservadaId");
    }

    @Override
    protected Class<DimensionObservada> getEntityClass() {
        return DimensionObservada.class;
    }

    @Override
    protected Class<DimensionObservada_Table> getTableclass() {
        return DimensionObservada_Table.class;
    }

    @Override
    public List<DimensionObservada> getTwoMaxDimensionesAlumno(int personaId, int entidadId) {
        return SQLite.select(Utils.f_allcolumnTable(DimensionObservada_Table.ALL_COLUMN_PROPERTIES))
                .from(DimensionObservada.class)
                .innerJoin(InstrumentoObservado.class)
                .on(DimensionObservada_Table.instrumentoObservadaId.withTable()
                        .eq(InstrumentoObservado_Table.instrumentoObservadoId.withTable()))
                .innerJoin(InstrumentoEvaluacion.class)
                .on(InstrumentoObservado_Table.instrumentoEvalId.withTable()
                        .eq(InstrumentoEvaluacion_Table.instrumentoevalid.withTable()))
                .where(InstrumentoObservado_Table.personaId.eq(personaId))
                //.and(InstrumentoEvaluacion_Table.entidadid.withTable().eq(entidadId))
                .orderBy(DimensionObservada_Table.valor.withTable().desc())
                .limit(2)
                .queryList();
    }

    @Override
    public List<DimensionObservada> getDimensionesAlumno(int alumnoId, int entidadId) {
        return SQLite.select(Utils.f_allcolumnTable(DimensionObservada_Table.ALL_COLUMN_PROPERTIES))
                .from(DimensionObservada.class)
                .innerJoin(InstrumentoObservado.class)
                .on(DimensionObservada_Table.instrumentoObservadaId.withTable()
                        .eq(InstrumentoObservado_Table.instrumentoObservadoId.withTable()))
                .innerJoin(InstrumentoEvaluacion.class)
                .on(InstrumentoObservado_Table.instrumentoEvalId.withTable()
                        .eq(InstrumentoEvaluacion_Table.instrumentoevalid.withTable()))
                .where(InstrumentoObservado_Table.personaId.eq(alumnoId))
                //.and(InstrumentoEvaluacion_Table.entidadid.withTable().eq(entidadId))
                .orderBy(DimensionObservada_Table.valor.withTable().desc())
                .queryList();
    }

    @Override
    public List<DimensionObservada> getDimensionesAlumnoInstrumento(int alumnoId, int entidadId, String instrumentoId) {
        return SQLite.select(Utils.f_allcolumnTable(DimensionObservada_Table.ALL_COLUMN_PROPERTIES))
                .from(DimensionObservada.class)
                .innerJoin(InstrumentoObservado.class)
                .on(DimensionObservada_Table.instrumentoObservadaId.withTable()
                        .eq(InstrumentoObservado_Table.instrumentoObservadoId.withTable()))
                .innerJoin(InstrumentoEvaluacion.class)
                .on(InstrumentoObservado_Table.instrumentoEvalId.withTable()
                        .eq(InstrumentoEvaluacion_Table.instrumentoevalid.withTable()))
                .where(InstrumentoObservado_Table.personaId.eq(alumnoId))
                .and(InstrumentoObservado_Table.instrumentoObservadoId.withTable().eq(instrumentoId))
                //.and(InstrumentoEvaluacion_Table.entidadid.withTable().eq(entidadId))
                //.orderBy(DimensionObservada_Table.valor.withTable().desc())
                .queryList();
    }
}
