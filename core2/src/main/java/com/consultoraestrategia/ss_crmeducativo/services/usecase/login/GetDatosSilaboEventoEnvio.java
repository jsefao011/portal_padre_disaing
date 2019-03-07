package com.consultoraestrategia.ss_crmeducativo.services.usecase.login;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSilaboEventoEnvio.BEDatosSilaboEventoEnvioRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosSilaboEventoEnvio;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class GetDatosSilaboEventoEnvio extends UseCase<GetDatosSilaboEventoEnvio.RequestValues, GetDatosSilaboEventoEnvio.ResponseValue>  {

    private BEDatosSilaboEventoEnvioRepository repository;

    public GetDatosSilaboEventoEnvio(BEDatosSilaboEventoEnvioRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {

        repository.getDatosLogin(requestValues.usuarioId,
                new ServiceDataSource.ObjectCallBack<GEDatosSilaboEventoEnvio>() {
                    @Override
                    public void onResponse(boolean success, GEDatosSilaboEventoEnvio item) {
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
        private GEDatosSilaboEventoEnvio item;

        public ResponseValue(GEDatosSilaboEventoEnvio item) {
            this.item = item;
        }

        public GEDatosSilaboEventoEnvio getItem() {
            return item;
        }
    }
}
