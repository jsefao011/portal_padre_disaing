package com.consultoraestrategia.ss_crmeducativo.core2.application;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.core2.listener.Core2Listener;
import com.consultoraestrategia.ss_crmeducativo.services.firebase.FireBaseNotificacion;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SyncIntenService;
import com.google.firebase.FirebaseApp;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.ArrayList;
import java.util.List;

public class Core2 extends Application implements ActivityLifecycleHandler.LifecycleListener, Application.ActivityLifecycleCallbacks {
    final static String CONNECTIVITY_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private static final String TAG = Core2.class.getSimpleName();
    private List<Activity> activityList = new ArrayList<>();
    //private Core2BroadcastReceiver core2BroadcastReceiver;
    private List<Core2Listener> core2ListenerList = new ArrayList<>();
    private MultiplePermissionsListener dialogMultiplePermissionsListener;
    private List<Integer> actividades = new ArrayList<>();
    private ProgressReceiver progressReceiver;

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(this).build());
        FirebaseApp.initializeApp(this);
        registerActivityLifecycleCallbacks(new ActivityLifecycleHandler(this));
        FireBaseNotificacion.getInstance();
        //SyncIntenService.start(this);
        Log.d(TAG,"Application onCreate ");
    }

    private void registerProgressReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(SyncIntenService.ACTION_PROGRESO);
        filter.addAction(SyncIntenService.ACTION_FIN);
        LocalBroadcastManager.getInstance(this).registerReceiver(getProgressReceiver(), filter);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(Core2.this);
        Log.d(TAG,"Application attachBaseContext ");
    }

    @Override
    public void onApplicationStarted() {
        FireBaseNotificacion.onDestroy();
        FireBaseNotificacion.onStarted();
        registerActivityLifecycleCallbacks(this);
        registerProgressReceiver();
        //registerConnectivityReceiver();
        Log.d(TAG,"Application onApplicationStarted ");
    }

    @Override
    public void onApplicationPaused() {
        Log.d(TAG,"Application onApplicationPaused ");
    }

    @Override
    public void onApplicationResumed() {
        Log.d(TAG,"Application onApplicationResumed ");
    }

    @Override
    public void onApplicationStopped() {
        Log.d(TAG,"Application onApplicationStopped ");
        unregisterActivityLifecycleCallbacks(this);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(getProgressReceiver());
        //LocalBroadcastManager.getInstance(this).unregisterReceiver(getConnectivityReceiver());
    }


    @Override
    public void onTerminate() {
        Log.d(TAG,"onTerminate ");
        super.onTerminate();
    }
/*
    private void registerConnectivityReceiver() {
        try {
            // if (android.os.Build.VERSION.SDK_INT >= 26) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            //filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
            //filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
            filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
            LocalBroadcastManager.getInstance(this).registerReceiver(getConnectivityReceiver(), filter);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }*/

    /*private Core2BroadcastReceiver getConnectivityReceiver() {
        if (core2BroadcastReceiver == null)
            core2BroadcastReceiver = new Core2BroadcastReceiver();

        return core2BroadcastReceiver;
    }*/

    private ProgressReceiver getProgressReceiver() {
        if (progressReceiver == null)
            progressReceiver = new ProgressReceiver();

        return progressReceiver;
    }

    private void setupValidatePermisos(Activity activity) {
        if(dialogMultiplePermissionsListener==null)dialogMultiplePermissionsListener =
                DialogOnAnyDeniedMultiplePermissionsListener.Builder
                        .withContext(activity)
                        .withTitle("Permisos de internet, contactos, lectura y escritura y cámara.")
                        .withMessage("Tanto los permisos contactos como el permiso de Internet y el permiso de lectura y escritura son necesarios para el correcto funcionamiento del aplicativo móvil.")
                        .withButtonText(android.R.string.ok)
                        .withIcon(R.drawable.base_academico)
                        .build();

        Dexter.withActivity(activity)
                .withPermissions(
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_WIFI_STATE
                )
                .withListener(dialogMultiplePermissionsListener)
                .check();
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        setupValidatePermisos(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.d(TAG,"onActivityStarted " + activity.getClass());
        for (Core2Listener listener : core2ListenerList)listener.onStart(activity.getClass());
    }

    @Override
    public void onActivityResumed(Activity activity) {

        Log.d(TAG,"onActivityResumed " + activity.getClass());
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.d(TAG,"onActivityPaused " + activity.getClass());
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.d(TAG,"onActivityStopped " + activity.getClass());
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        Log.d(TAG,"onActivitySaveInstanceState " + activity.getClass());
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.d(TAG,"onActivityDestroyed " + activity.getClass());

    }


    public class ProgressReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(SyncIntenService.ACTION_PROGRESO)) {
                int prog = intent.getIntExtra("progreso", 0);
                for (Core2Listener listener : core2ListenerList)listener.onLoad(prog);
                Log.d(TAG,"progreso " + prog );
            }
            else if(intent.getAction().equals(SyncIntenService.ACTION_FIN)) {
                for (Core2Listener listener : core2ListenerList)listener.onFinish();
                //Toast.makeText(context, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void addCore2Listener(Core2Listener core2Listener) {
        core2ListenerList.remove(core2Listener);
        core2ListenerList.add(core2Listener);
    }

    public void removeCore2Listener(Core2Listener core2Listener){
        core2ListenerList.remove(core2Listener);
    }

    public static Core2 getCore2(Activity activity){
        return (Core2)activity.getApplication();
    }
}
