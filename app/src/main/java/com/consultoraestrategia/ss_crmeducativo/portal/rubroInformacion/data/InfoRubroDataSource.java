package com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.data;

import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.InfoRubroUi;

public interface InfoRubroDataSource {

    interface SucessCallback<T> {
        void onLoad(boolean success, T item);
    }

    void getInfoRubro(String idEvaluacionProceso, int cargaCurso, SucessCallback<InfoRubroUi>infoRubroUiSucessCallback);
}
