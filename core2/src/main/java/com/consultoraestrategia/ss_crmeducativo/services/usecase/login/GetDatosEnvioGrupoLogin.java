package com.consultoraestrategia.ss_crmeducativo.services.usecase.login;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioGrupo.BEDatosEnvioGrupoRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioGrupo;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class GetDatosEnvioGrupoLogin extends UseCase<GetDatosEnvioGrupoLogin.RequestValues, GetDatosEnvioGrupoLogin.ResponseValue>  {

    private BEDatosEnvioGrupoRepository repository;

    public GetDatosEnvioGrupoLogin(BEDatosEnvioGrupoRepository cargaAcademicaLoginRepository) {
        this.repository = cargaAcademicaLoginRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {

        repository.getDatosLogin(requestValues.usuarioId,
                new ServiceDataSource.ObjectCallBack<BEDatosEnvioGrupo>() {
                    @Override
                    public void onResponse(boolean success, BEDatosEnvioGrupo item) {
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
        private BEDatosEnvioGrupo item;

        public ResponseValue(BEDatosEnvioGrupo item) {
            this.item = item;
        }

        public BEDatosEnvioGrupo getItem() {
            return item;
        }
    }
}
