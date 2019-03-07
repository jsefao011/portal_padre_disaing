package com.consultoraestrategia.ss_crmeducativo.services.usecase.login;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosRubroEvaluacionProceso.BEDatosRubroEvaluacionProcesoRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosRubroEvaluacionProceso;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class GetDatosRubroEvaluacionProceso extends UseCase<GetDatosRubroEvaluacionProceso.RequestValues, GetDatosRubroEvaluacionProceso.ResponseValue>  {

    private BEDatosRubroEvaluacionProcesoRepository repository;

    public GetDatosRubroEvaluacionProceso(BEDatosRubroEvaluacionProcesoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {

        repository.getDatosLogin(requestValues.usuarioId,
                new ServiceDataSource.ObjectCallBack<BEDatosRubroEvaluacionProceso>() {
                    @Override
                    public void onResponse(boolean success, BEDatosRubroEvaluacionProceso item) {
                        if(success){
                            getUseCaseCallback().onSuccess(new ResponseValue(item));
                        }else {
                            getUseCaseCallback().onError();
                        }
                    }
                });

    }

    public static class RequestValues implements UseCase.RequestValues{
        private String usuarioId;

        public RequestValues(String usuarioId) {
            this.usuarioId = usuarioId;
        }

        public String getUsuarioId() {
            return usuarioId;
        }
    }

    public class ResponseValue implements UseCase.ResponseValue{
        private BEDatosRubroEvaluacionProceso item;

        public ResponseValue(BEDatosRubroEvaluacionProceso item) {
            this.item = item;
        }

        public BEDatosRubroEvaluacionProceso getItem() {
            return item;
        }
    }
}
