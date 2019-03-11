package com.consultoraestrategia.ss_crmeducativo.portal.caso.data;

import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.AlumnoUi;

public interface CasoDataSource {

    interface SucessCallback<T> {
        void onLoad(boolean success, T item);
    }

    void getAlumoCaso(int alumnoId, int programaEducativoId, SucessCallback<AlumnoUi>alumnoUiSucessCallback);
}
