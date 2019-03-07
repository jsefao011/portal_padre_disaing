package com.consultoraestrategia.ss_crmeducativo.services.usecase.login;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.obtenerDatosLogin.BEObtenerDatosLoginRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEObtenerDatosLogin;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class GetObtenerDatosLogin extends UseCase<GetObtenerDatosLogin.RequestValues, GetObtenerDatosLogin.ResponseValue>  {

    private BEObtenerDatosLoginRepository repository;

    public GetObtenerDatosLogin(BEObtenerDatosLoginRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {

        repository.getDatosLogin(requestValues.usuarioId,
                new ServiceDataSource.ObjectCallBack<BEObtenerDatosLogin>() {
                    @Override
                    public void onResponse(boolean success, BEObtenerDatosLogin item) {
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
        private BEObtenerDatosLogin item;

        public ResponseValue(BEObtenerDatosLogin item) {
            this.item = item;
        }

        public BEObtenerDatosLogin getItem() {
            return item;
        }
    }
}
