package com.consultoraestrategia.ss_crmeducativo.portal.asistencia.data;

import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Periodo;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.entities.PeriodoUi;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

public class AsistenciaLocalDataSource implements AsistenciaDataSource{



    @Override
    public  List<PeriodoUi> getPeriodoList(int programaEducativoId) {
        List<CalendarioPeriodo> calendarioPeriodoList= SQLite.select().from(CalendarioPeriodo.class).
                innerJoin(CalendarioAcademico.class).
                on(CalendarioPeriodo_Table.calendarioAcademicoId.withTable()
                        .eq(CalendarioAcademico_Table.calendarioAcademicoId.withTable()))
                .where(CalendarioAcademico_Table.programaEduId.withTable()
                        .eq(programaEducativoId)).queryList();

        List<PeriodoUi>periodoUis= new ArrayList<>();
        for(CalendarioPeriodo calendarioPeriodo:calendarioPeriodoList){
            PeriodoUi periodoUi= new PeriodoUi();
            Tipos tipo = SQLite.select()
                    .from(Tipos.class)
                    .where(Tipos_Table.tipoId.eq(calendarioPeriodo.getTipoId()))
                    .querySingle();
            if(tipo!=null){
                periodoUi.setTipoName(tipo.getNombre());
            }
            periodoUi.setIdCalendarioPeriodo(String.valueOf(calendarioPeriodo.getCalendarioPeriodoId()));

            if(calendarioPeriodo.getEstadoId()==CalendarioPeriodo.CALENDARIO_PERIODO_VIGENTE)
                periodoUi.setVigente(true);
            else periodoUi.setVigente(false);
            periodoUis.add(periodoUi);
        }
        if(!periodoUis.isEmpty())periodoUis.get(0).setStatus(true);
        return periodoUis;
    }

    @Override
    public void getValoresAsistencia(int alumnoId, SucessCallback<List<Object>> sucessCallback) {

    }
}
