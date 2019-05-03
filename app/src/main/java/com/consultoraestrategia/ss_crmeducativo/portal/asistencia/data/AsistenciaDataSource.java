package com.consultoraestrategia.ss_crmeducativo.portal.asistencia.data;

import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.entities.PeriodoUi;

import java.util.List;

public interface AsistenciaDataSource {

    interface SucessCallback<T> {
        void onLoad(boolean success, T item);
    }

    List<PeriodoUi> getPeriodoList(int programaEducativoId);
    void getValoresAsistencia(int alumnoId, SucessCallback<List<Object>>sucessCallback);

}
