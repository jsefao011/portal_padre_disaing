package com.consultoraestrategia.ss_crmeducativo.services.usecase.login;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioTipoNota.BEDatosEnvioTipoNotaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioTipoNota;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class GetDatosEnvioTipoNotaLogin extends UseCase<GetDatosEnvioTipoNotaLogin.RequestValues, GetDatosEnvioTipoNotaLogin.ResponseValue>  {

    private BEDatosEnvioTipoNotaRepository repository;

    public GetDatosEnvioTipoNotaLogin(BEDatosEnvioTipoNotaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {

        repository.getDatosLogin(requestValues.usuarioId,
                new ServiceDataSource.ObjectCallBack<BEDatosEnvioTipoNota>() {
                    @Override
                    public void onResponse(boolean success, BEDatosEnvioTipoNota item) {
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
        private BEDatosEnvioTipoNota item;

        public ResponseValue(BEDatosEnvioTipoNota item) {
            this.item = item;
        }

        public BEDatosEnvioTipoNota getItem() {
            return item;
        }
    }
}
