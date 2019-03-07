package com.consultoraestrategia.ss_crmeducativo.services.syncIntentService;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.dao.sessionUser.SessionUserDao;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.services.data.local.ServiceLocalDataRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.remote.ServiceRemoteDataRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.util.RepositoryInjector;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEGuardarEntidadesGlobal;
import com.consultoraestrategia.ss_crmeducativo.services.util.ImportarCountDownTimer;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

/**
 * Created by SCIEV on 12/06/2018.
 */

public abstract class  SyncIntenService extends IntentService  {

    protected static final Long SYNCTIME = 800L;//Valorpor defecto 800L
    protected static final String LASTTIMESYNC = "DATE";
    SharedPreferences sharedPreferences;
    ImportarCountDownTimer importarCountDownTimer;

    public static final String ACTION_PROGRESO ="net.sgoliver.intent.action.PROGRESO";
    public static final String ACTION_FIN ="net.sgoliver.intent.action.FIN";
    public final static String TAG = SyncIntenService.class.getSimpleName();
    protected ServiceLocalDataRepository serviceLocalDataRepository;
    protected ServiceRemoteDataRepository serviceRemoteDataRepository;
    protected static int value = 0;
    protected Notification.InboxStyle inboxStyle;
    protected Bitmap bitmap;

    public SyncIntenService() {
        super("SyncIntenService");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "Servicio creado...");
        value = 0;
        super.onCreate();
        setupRepository();
        setupNotificacion();
    }

    private void setupNotificacion() {
        inboxStyle = new Notification.InboxStyle();
       bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.base_academico);
    }

    private void setupRepository() {
        serviceLocalDataRepository = RepositoryInjector.getServiceLocalDataRepository();
        serviceRemoteDataRepository = RepositoryInjector.getServiceRemoteDataRepository();
    }

    @SuppressLint("ApplySharedPref")
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        SessionUserDao sessionUserDao = InjectorUtils.provideSessionUserDao();
        SessionUser sessionUser = sessionUserDao.getCurrentUser();
        if(sessionUser==null|| !sessionUser.isDataImported()){
            Log.d(TAG, "Cancelar service ");
            return;
        }
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        if(System.currentTimeMillis()-sharedPreferences.getLong(LASTTIMESYNC, 0)>=SYNCTIME)
        {
            onHandle(intent);
            sharedPreferences.edit().putLong(LASTTIMESYNC, System.currentTimeMillis()).commit();
        }
    }

    protected abstract void onHandle(Intent intent);


    @Override
    public void onDestroy() {

        //sendBroadcast(bcIntent);
        super.onDestroy();
        Log.d(TAG, "Servicio destruido...");
    }

    private void tareaLarga()
    {
        try {
            Thread.sleep(10000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }



    protected final int NOTIFICATION_IDCONECTIVIDAD = 237;
    protected static final int NOTIFICATION_ID = 238;
    protected static final int NOTIFICATION_IDSAVE = 239;
    protected static final int NOTIFICATION_IDESTADO = 240;

    public void showNotificacionConectividad(String mensaje)
    {
        value ++;
        NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("CRMEducativo");
        builder.setContentText(value + " errores de conectividad");
        builder.setSmallIcon(R.drawable.base_academico);
        builder.setLargeIcon(bitmap);
        builder.setAutoCancel(true);
        inboxStyle.setBigContentTitle("Error en los datos");
        inboxStyle.addLine(value + ". " + mensaje +".");
        builder.setStyle(inboxStyle);
        if(nManager!=null) nManager.notify("App Name",NOTIFICATION_IDCONECTIVIDAD,builder.build());
    }

    public void showNotificacion(Context context, int id, String titulo, String mensaje)
    {
        Notification noti =
                new NotificationCompat.Builder(context, "App Name")
                        .setSmallIcon(R.drawable.base_academico)
                        .setContentTitle(titulo)
                        .setContentText(mensaje)
                        .setColor(Color.parseColor("#2980b9"))
                        .build();

        noti.flags |= Notification.FLAG_AUTO_CANCEL;
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(mNotificationManager!=null) mNotificationManager.notify(id, noti);
    }


}

