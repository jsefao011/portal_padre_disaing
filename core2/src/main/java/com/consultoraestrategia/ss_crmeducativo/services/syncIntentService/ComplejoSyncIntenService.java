package com.consultoraestrategia.ss_crmeducativo.services.syncIntentService;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.services.data.local.ServiceLocalDataRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.remote.ServiceRemoteDataRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEGuardarEntidadesGlobal;
import com.consultoraestrategia.ss_crmeducativo.services.util.ImportarCountDownTimer;

public class ComplejoSyncIntenService extends SyncIntenService implements ImportarCountDownTimer.ImportarCountDownTimerCallback {

    @Override
    public void onCreate() {
        final int maxSecondsInMillis = 360000;
        final int countInterval = 1000;
        if(importarCountDownTimer != null)importarCountDownTimer.onDestroy();
        importarCountDownTimer = new ImportarCountDownTimer(maxSecondsInMillis, countInterval, this);
        importarCountDownTimer.start();
        super.onCreate();
    }

    @Override
    public void onHandle(Intent intent) {
        Log.d(TAG, "ImportarCountDownTimer service ");


        serviceLocalDataRepository.getDatosExportarGlobal(new ServiceLocalDataRepository.CallBack<BEGuardarEntidadesGlobal>() {
            @Override
            public void onResponse(boolean success, BEGuardarEntidadesGlobal item) {
                if(success){
                    serviceRemoteDataRepository.SendDatosGlobal(item, new ServiceRemoteDataRepository.RespuestaCallBack<BEGuardarEntidadesGlobal, BERespuesta>() {
                        @Override
                        public void onResponse(boolean success, BEGuardarEntidadesGlobal item, BERespuesta respuesta) {
                            if(success){

                                if(respuesta == null){
                                    showNotificacion(getApplicationContext(), NOTIFICATION_ID, "Error de conectividad","Los servidores han dectado un problema con los datos del CRMEducativo");
                                    return;
                                }
                                if(!respuesta.isCommit_Asistencia()){
                                    showNotificacionConectividad("De los registros de asistencia.");
                                }

                                if(!respuesta.isCommit_Grupo()){
                                    showNotificacionConectividad("De los registros de grupos.");
                                }

                                if(!respuesta.isCommit_Mensajes()){
                                    showNotificacionConectividad("De los registros de mensajes.");
                                }

                                if(!respuesta.isCommit_RubroEvaluacionProceso()){
                                    showNotificacionConectividad("De los registros de rubros.");
                                }

                                if(!respuesta.isCommit_Sesion()){
                                    showNotificacionConectividad("De los registros de sesión.");
                                }

                                if(!respuesta.isCommit_TareaRecurso()){
                                    showNotificacionConectividad("De los registros de tareas.");
                                }


                                serviceLocalDataRepository.changeEstadoGlobal(item,respuesta, BaseEntity.FLAG_UPDATED, new ServiceLocalDataRepository.SuccessCallBack() {
                                    @Override
                                    public void onResponse(boolean success) {
                                        if(!success){showNotificacion(getApplicationContext(),NOTIFICATION_IDESTADO,"Error interno","codigo del error E5t4Ad0");

                                        }
                                    }
                                });

                                serviceLocalDataRepository.saveDatosGlobal(respuesta, new ServiceDataSource.SuccessCallBack() {
                                    @Override
                                    public void onResponse(boolean success) {
                                        if(!success){
                                            showNotificacion(getApplicationContext(), NOTIFICATION_IDSAVE, "Error interno","codigo del error S4v3");
                                        }
                                    }
                                });

                            }else {
                                showNotificacion(getApplicationContext(), NOTIFICATION_ID, "Error de conectividad","CRMEducativo ha dectado un problema con los servidores");
                            }
                        }
                    });
                }else {
                    showNotificacion(getApplicationContext(), NOTIFICATION_ID, "Error de Conectividad","CRMEducativo ha dectado un problema con la red");
                }
            }
        });

        /*int iter = intent.getIntExtra("iteraciones", 0);
        Log.d(TAG, "Emprocreso...");
        for(int i=1; i<=iter; i++) {
            tareaLarga();

            //Comunicamos el progreso
            Intent bcIntent = new Intent();
            bcIntent.setAction(ACTION_PROGRESO);
            bcIntent.putExtra("progreso", i*10);
            Log.d(TAG, "progreso service : "+ i*10);
            sendBroadcast(bcIntent);
        }*/
        if(importarCountDownTimer != null)importarCountDownTimer.onFinish();
    }

    @Override
    public void onImportarCountDownTimerCount(int counter) {

    }

    @Override
    public void onImportarCountDownTimerFinish() {

    }

    @Override
    public void onImportarCountDownProgress(int progress) {
        //Comunicamos el progreso
        Intent bcIntent = new Intent();
        bcIntent.setAction(ACTION_PROGRESO);
        bcIntent.putExtra("progreso", progress);
        Log.d(TAG, "progreso service : "+ progress);
        //sendBroadcast(bcIntent);
        LocalBroadcastManager.getInstance(this).sendBroadcast(bcIntent);
    }

    void destruirTimer(){
        if(importarCountDownTimer != null)importarCountDownTimer.onDestroy();
        importarCountDownTimer = null;
    }

    @Override
    public void onDestroy() {
        Intent bcIntent = new Intent();
        bcIntent.setAction(ACTION_FIN);
        LocalBroadcastManager.getInstance(this).sendBroadcast(bcIntent);
        destruirTimer();
        super.onDestroy();
    }

    public static void start(Context context){
        Intent msgIntent = new Intent(context, ComplejoSyncIntenService.class);
        msgIntent.putExtra("iteraciones", 10);
        context.startService(msgIntent);
    }
}
