package com.consultoraestrategia.ss_crmeducativo.portal.caso.data;

import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.AlumnoUi;

public class CasoRepository implements CasoDataSource {

    CasoLocalDataSource casoLocalDataSource;

    public CasoRepository(CasoLocalDataSource casoLocalDataSource) {
        this.casoLocalDataSource = casoLocalDataSource;
    }

    @Override
    public void getAlumoCaso(int alumnoId, int programaEducativoId,  SucessCallback<AlumnoUi> alumnoUiSucessCallback) {
        casoLocalDataSource.getAlumoCaso(alumnoId, programaEducativoId,alumnoUiSucessCallback);
    }

    @Override
    public void updateSucessDowload(String archivoId, String path, CallbackSuccess callback) {
         casoLocalDataSource.updateSucessDowload(archivoId, path, callback);
    }
}
