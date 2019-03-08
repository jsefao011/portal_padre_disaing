package com.consultoraestrategia.ss_crmeducativo.portal.caso.data;

import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.AlumnoUi;

public class CasoRepository implements CasoDataSource {

    CasoLocalDataSource casoLocalDataSource;

    public CasoRepository(CasoLocalDataSource casoLocalDataSource) {
        this.casoLocalDataSource = casoLocalDataSource;
    }

    @Override
    public void getAlumoCaso(int alumnoId, SucessCallback<AlumnoUi> alumnoUiSucessCallback) {
        casoLocalDataSource.getAlumoCaso(alumnoId, alumnoUiSucessCallback);
    }
}
