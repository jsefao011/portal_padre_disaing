package com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.data.InfoRubroDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.data.InfoRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.InfoRubroUi;

public class GetInfoRubroProceso extends UseCase<GetInfoRubroProceso.RequestValues, GetInfoRubroProceso.ResponseValue> {


    InfoRubroRepository infoRubroRepository;

    public GetInfoRubroProceso(InfoRubroRepository infoRubroRepository) {
        this.infoRubroRepository = infoRubroRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        infoRubroRepository.getInfoRubro(requestValues.getIdEvaluacionProceso(), requestValues.getCargaCursoId(), new InfoRubroDataSource.SucessCallback<InfoRubroUi>() {
            @Override
            public void onLoad(boolean success, InfoRubroUi item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
            }
        });
    }
    public static class RequestValues implements UseCase.RequestValues{
        private String idEvaluacionProceso;
        private int cargaCursoId;

        public RequestValues(String idEvaluacionProceso, int cargaCursoId) {
            this.idEvaluacionProceso = idEvaluacionProceso;
            this.cargaCursoId = cargaCursoId;
        }

        public String getIdEvaluacionProceso() {
            return idEvaluacionProceso;
        }

        public int getCargaCursoId() {
            return cargaCursoId;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue{
        private InfoRubroUi infoRubroUi;

        public ResponseValue(InfoRubroUi infoRubroUi) {
            this.infoRubroUi = infoRubroUi;
        }

        public InfoRubroUi getInfoRubroUi() {
            return infoRubroUi;
        }
    }
}
