package com.consultoraestrategia.ss_crmeducativo.services.usecase.login;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin.SEDatosCompletosLoginDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin.SEDatosCompletosLoginRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.SEDatosCompletosLogin;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosCargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioAsistencia;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioGrupo;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioHorario;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioMensajeria;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioTipoNota;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEvaluacionResultado;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosSilaboEventoEnvio;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEObtenerDatosLogin;

/**
 * Created by SCIEV on 18/05/2018.
 */

public class SaveDatosCompletosLogin extends UseCaseSincrono<SaveDatosCompletosLogin.RequestValues, SaveDatosCompletosLogin.ResponseValue> {
    private SEDatosCompletosLoginRepository repository;

    public SaveDatosCompletosLogin(SEDatosCompletosLoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(RequestValues request, final Callback<ResponseValue> callback) {
        repository.save(request.getSeDatosCompletosLogin(), new SEDatosCompletosLoginDataSource.SuccessCallBack() {
            @Override
            public void onResponse(boolean success) {
                if(success){
                    callback.onResponse(true, new ResponseValue(success));
                }else {
                    callback.onResponse(false, new ResponseValue(success));
                }

            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        private SEDatosCompletosLogin seDatosCompletosLogin;

        public RequestValues() {
        }

        public SEDatosCompletosLogin getSeDatosCompletosLogin() {
            return seDatosCompletosLogin;
        }

        public void setSeDatosCompletosLogin(Object o) {
            if(this.seDatosCompletosLogin == null){
                this.seDatosCompletosLogin= new SEDatosCompletosLogin();
            }
           if(o instanceof BEDatosCargaAcademica){
               seDatosCompletosLogin.setBeDatosCargaAcademica((BEDatosCargaAcademica)o);
           }else if(o instanceof BEDatosEnvioAsistencia){
               seDatosCompletosLogin.setBeDatosEnvioAsistencia((BEDatosEnvioAsistencia)o);
           }else if(o instanceof BEDatosEnvioGrupo){
               seDatosCompletosLogin.setBeDatosEnvioGrupo((BEDatosEnvioGrupo)o);
           }else if(o instanceof BEDatosEnvioHorario){
               seDatosCompletosLogin.setBeDatosEnvioHorario((BEDatosEnvioHorario)o);
           }else if(o instanceof BEDatosEnvioMensajeria){
               seDatosCompletosLogin.setBeDatosEnvioMensajeria((BEDatosEnvioMensajeria)o);
           }else if(o instanceof BEDatosEnvioTipoNota){
               seDatosCompletosLogin.setBeDatosEnvioTipoNota((BEDatosEnvioTipoNota)o);
           }else if(o instanceof BEDatosEvaluacionResultado){
               seDatosCompletosLogin.setBeDatosEvaluacionResultado((BEDatosEvaluacionResultado)o);
           }else if(o instanceof BEDatosRubroEvaluacionProceso){
               seDatosCompletosLogin.setBeDatosRubroEvaluacionProceso((BEDatosRubroEvaluacionProceso)o);
           }else if(o instanceof GEDatosSilaboEventoEnvio){
               seDatosCompletosLogin.setBeDatosSilaboEventoEnvio((GEDatosSilaboEventoEnvio)o);
           }else if(o instanceof BEObtenerDatosLogin){
               seDatosCompletosLogin.setBeObtenerDatosLogin((BEObtenerDatosLogin)o);
           }
        }

        public Boolean isfullRequest(){
            if(seDatosCompletosLogin.getBeDatosCargaAcademica() == null ||
                    seDatosCompletosLogin.getBeDatosEnvioAsistencia() == null ||
                    seDatosCompletosLogin.getBeDatosEnvioGrupo() == null ||
                    seDatosCompletosLogin.getBeDatosEnvioHorario() == null||
                    seDatosCompletosLogin.getBeDatosEnvioMensajeria() == null||
                    seDatosCompletosLogin.getBeDatosEnvioTipoNota() == null||
                    //seDatosCompletosLogin.getBeDatosEvaluacionResultado() == null||
                    seDatosCompletosLogin.getBeDatosRubroEvaluacionProceso() == null||
                    seDatosCompletosLogin.getBeDatosSilaboEventoEnvio() == null||
                    seDatosCompletosLogin.getBeObtenerDatosLogin() == null
                    )return false;
            return true;
        }
        public Boolean isfullRequestImportacion(){
            if(seDatosCompletosLogin.getBeDatosEnvioAsistencia() == null ||
                    seDatosCompletosLogin.getBeDatosEnvioGrupo() == null ||
                    seDatosCompletosLogin.getBeDatosEnvioMensajeria() == null||
                    seDatosCompletosLogin.getBeDatosEnvioTipoNota() == null||
                    seDatosCompletosLogin.getBeDatosRubroEvaluacionProceso() == null||
                    seDatosCompletosLogin.getBeDatosSilaboEventoEnvio() == null
                    )return false;
            return true;
        }

        @Override
        public String toString() {
            return "RequestValues{" +
                    seDatosCompletosLogin.toString() +
                    '}';
        }
    }

    public class ResponseValue implements UseCase.ResponseValue{
        private boolean sucesses;

        public ResponseValue(boolean sucesses) {
            this.sucesses = sucesses;
        }

        public boolean isSucesses() {
            return sucesses;
        }


    }

}
