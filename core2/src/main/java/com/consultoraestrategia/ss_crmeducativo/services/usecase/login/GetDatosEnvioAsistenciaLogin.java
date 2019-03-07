package com.consultoraestrategia.ss_crmeducativo.services.usecase.login;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioAsistencia.BEDatosEnvioAsistenciaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioAsistencia;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class GetDatosEnvioAsistenciaLogin extends UseCase<GetDatosEnvioAsistenciaLogin.RequestValues, GetDatosEnvioAsistenciaLogin.ResponseValue>  {

    private BEDatosEnvioAsistenciaRepository cargaAcademicaLoginRepository;

    public GetDatosEnvioAsistenciaLogin(BEDatosEnvioAsistenciaRepository cargaAcademicaLoginRepository) {
        this.cargaAcademicaLoginRepository = cargaAcademicaLoginRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {

        cargaAcademicaLoginRepository.getDatosLogin(requestValues.usuarioId,
                new ServiceDataSource.ObjectCallBack<BEDatosEnvioAsistencia>() {
                    @Override
                    public void onResponse(boolean success, BEDatosEnvioAsistencia item) {
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

    public class ResponseValue implements UseCase.ResponseValue {
        private BEDatosEnvioAsistencia item;

        public ResponseValue(BEDatosEnvioAsistencia item) {
            this.item = item;
        }

        public BEDatosEnvioAsistencia getItem() {
            return item;
        }
    }
}
