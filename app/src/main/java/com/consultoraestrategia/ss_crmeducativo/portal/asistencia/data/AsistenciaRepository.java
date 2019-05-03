package com.consultoraestrategia.ss_crmeducativo.portal.asistencia.data;

import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.entities.PeriodoUi;

import java.util.List;

public class AsistenciaRepository  implements AsistenciaDataSource {

    AsistenciaLocalDataSource asistenciaLocalDataSource;

    public AsistenciaRepository(AsistenciaLocalDataSource asistenciaLocalDataSource) {
        this.asistenciaLocalDataSource = asistenciaLocalDataSource;
    }

    @Override
    public  List<PeriodoUi> getPeriodoList(int programaEducativoId) {
        return  asistenciaLocalDataSource.getPeriodoList(programaEducativoId);
    }

    @Override
    public void getValoresAsistencia(int alumnoId, SucessCallback<List<Object>> sucessCallback) {
        asistenciaLocalDataSource.getValoresAsistencia(alumnoId, sucessCallback);
    }
}
