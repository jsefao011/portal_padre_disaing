package com.consultoraestrategia.ss_crmeducativo.services.usecase.login;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioHorario.BEDatosEnvioHorarioRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioHorario;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class GetDatosEnvioHorarioLogin extends UseCase<GetDatosEnvioHorarioLogin.RequestValues, GetDatosEnvioHorarioLogin.ResponseValue>  {

    private BEDatosEnvioHorarioRepository repository;

    public GetDatosEnvioHorarioLogin(BEDatosEnvioHorarioRepository cargaAcademicaLoginRepository) {
        this.repository = cargaAcademicaLoginRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {

        repository.getDatosLogin(requestValues.usuarioId,
                new ServiceDataSource.ObjectCallBack<BEDatosEnvioHorario>() {
                    @Override
                    public void onResponse(boolean success, BEDatosEnvioHorario item) {
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
        private BEDatosEnvioHorario item;

        public ResponseValue(BEDatosEnvioHorario item) {
            this.item = item;
        }

        public BEDatosEnvioHorario getItem() {
            return item;
        }
    }
}
