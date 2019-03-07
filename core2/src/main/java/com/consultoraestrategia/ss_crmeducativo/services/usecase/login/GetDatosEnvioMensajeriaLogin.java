package com.consultoraestrategia.ss_crmeducativo.services.usecase.login;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioMensajeria.BEDatosEnvioMensajeriaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioMensajeria;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class GetDatosEnvioMensajeriaLogin extends UseCase<GetDatosEnvioMensajeriaLogin.RequestValues, GetDatosEnvioMensajeriaLogin.ResponseValue>  {

    private BEDatosEnvioMensajeriaRepository repository;

    public GetDatosEnvioMensajeriaLogin(BEDatosEnvioMensajeriaRepository cargaAcademicaLoginRepository) {
        this.repository = cargaAcademicaLoginRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {

        repository.getDatosLogin(requestValues.usuarioId,
                new ServiceDataSource.ObjectCallBack<BEDatosEnvioMensajeria>() {
                    @Override
                    public void onResponse(boolean success, BEDatosEnvioMensajeria item) {
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
        private BEDatosEnvioMensajeria item;

        public ResponseValue(BEDatosEnvioMensajeria item) {
            this.item = item;
        }

        public BEDatosEnvioMensajeria getItem() {
            return item;
        }
    }
}
