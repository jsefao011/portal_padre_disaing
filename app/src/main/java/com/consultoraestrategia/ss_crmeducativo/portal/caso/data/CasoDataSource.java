package com.consultoraestrategia.ss_crmeducativo.portal.caso.data;

import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.AlumnoUi;

public interface CasoDataSource {

    interface SucessCallback<T> {
        void onLoad(boolean success, T item);
    }
    interface CallbackSuccess {
        void onLoad(boolean success);
    }

    void getAlumoCaso(int alumnoId, int programaEducativoId, SucessCallback<AlumnoUi>alumnoUiSucessCallback);
    void updateSucessDowload(String archivoId, String path, CallbackSuccess callback);
}
