package com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.data;

import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.InfoRubroUi;

public class InfoRubroRepository implements InfoRubroDataSource {

    InfoRubroDataLocalSource infoRubroDataLocalSource;

    public InfoRubroRepository(InfoRubroDataLocalSource infoRubroDataLocalSource) {
        this.infoRubroDataLocalSource = infoRubroDataLocalSource;
    }

    @Override
    public void getInfoRubro(String idEvaluacionProceso, int cargaCurso, SucessCallback<InfoRubroUi> infoRubroUiSucessCallback) {
        infoRubroDataLocalSource.getInfoRubro(idEvaluacionProceso, cargaCurso, infoRubroUiSucessCallback);
    }
}
