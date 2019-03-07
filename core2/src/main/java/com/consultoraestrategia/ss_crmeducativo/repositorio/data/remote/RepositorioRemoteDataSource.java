package com.consultoraestrategia.ss_crmeducativo.repositorio.data.remote;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.DownloadCancelUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class RepositorioRemoteDataSource implements RepositorioDataSource {

    private final ApiRetrofit apiRetrofit;
    private String TAG = RepositorioRemoteDataSource.class.getSimpleName();

    public RepositorioRemoteDataSource(ApiRetrofit apiRetrofit) {
        this.apiRetrofit = apiRetrofit;
    }

    @Override
    public  void dowloadImage(final String url, final String nombre, final CallbackProgress<String> repositorioFileUiCallback) {
        new Thread(){
            @Override
            public void run() {
                try {
                    ApiRetrofit apiRetrofit1 = ApiRetrofit.getInstance();
                    Log.d(TAG,"url " + url);
                    ResponseBody body = apiRetrofit1.downloadFileByUrl(url);
                    int count;
                    byte data[] = new byte[1024 * 4];
                    long fileSize = body.contentLength();
                    InputStream inputStream = new BufferedInputStream(body.byteStream(), 1024 * 8);
                    File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), nombre);
                    OutputStream outputStream = new FileOutputStream(outputFile);
                    long total = 0;
                    boolean downloadComplete = false;
                    //int totalFileSize = (int) (fileSiz
                    // e / (Math.pow(1024, 2)));
                    int gloabalcount = 0;
                    DownloadCancelUi downloadCancelUi = new DownloadCancelUi();
                    repositorioFileUiCallback.onPreLoad(downloadCancelUi);
                    while ((count = inputStream.read(data)) != -1) {
                        if(downloadCancelUi.isCancel()){
                            repositorioFileUiCallback.onLoad(false, null);
                            break;
                        }
                        total += count;
                        int progress = (int) ((double) (total * 100) / (double) fileSize);
                        if(gloabalcount==progress)repositorioFileUiCallback.onProgress(progress);
                        gloabalcount = progress;
                        Log.d(TAG,"progress" + progress + " %");
                        outputStream.write(data, 0, count);
                        downloadComplete = true;
                    }
                    Log.d(TAG,"downloadComplete" + downloadComplete );
                    if(!downloadCancelUi.isCancel()){
                        repositorioFileUiCallback.onLoad(true, outputFile.getPath());
                        outputStream.flush();
                    }
                    outputStream.close();
                    inputStream.close();

                } catch (IOException e) {
                    repositorioFileUiCallback.onLoad(false, null);
                    e.printStackTrace();
                } catch (Exception e){
                    repositorioFileUiCallback.onLoad(false, null);
                    e.printStackTrace();
                }
            }
        }.start();

    }

    @Override
    public void getArchivosConArchivosTareas(int tareaId, Callback<List<RepositorioFileUi>> callback) {

    }

    @Override
    public void getArchivosJustificacion(String asistenciaId, Callback<List<RepositorioFileUi>> callback) {

    }

    @Override
    public void uploadFileJustificacion(String urlServidor, String path, final CallbackProgress<String> callback) {
        Log.d(TAG,"urlServidor "+ urlServidor);
        ApiRetrofit apiRetrofit1 = ApiRetrofit.getInstance();
        final Call<String> call = apiRetrofit1.uploadMultiFileJustificacion(urlServidor, path);
        uploadArchivo(call, callback);
    }

    @Override
    public void getUrlRepositorioArchivo(Callback<String> stringCallback) {

    }

    @Override
    public void uploadFileArchivo(String urlServidor, String path, CallbackProgress<String> callbackProgress) {
        Log.d(TAG,"urlServidor "+ urlServidor);
        ApiRetrofit apiRetrofit1 = ApiRetrofit.getInstance();
        final Call<String> call = apiRetrofit1.uploadMultiFileArchivo(urlServidor, path);
        uploadArchivo(call, callbackProgress);
    }

    @Override
    public void updateSucessDowload(String archivoId, String path, Callback<Boolean> callback) {

    }

    @Override
    public void updateSucessDowloadAsistenica(String archivoId, String path, Callback<Boolean> booleanCallback) {

    }

    @Override
    public void updateSucessDowloaComportamiento(String archivoId, String path, Callback<Boolean> booleanCallback) {

    }

    @Override
    public void uploadFileCaso(String urlServidor, String path, CallbackProgress<String> callbackProgress) {
        Log.d(TAG,"urlServidor "+ urlServidor);
        ApiRetrofit apiRetrofit1 = ApiRetrofit.getInstance();
        final Call<String> call = apiRetrofit1.uploadMultiFileCaso(urlServidor, path);
        uploadArchivo(call, callbackProgress);
    }

    private void uploadArchivo(final Call<String> call, final CallbackProgress<String> callback){

        final DownloadCancelUi downloadCancelUi = new DownloadCancelUi();
        callback.onPreLoad(downloadCancelUi);
        call.enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String serverResponse = response.body();

                if (serverResponse != null) {
                    if(!TextUtils.isEmpty(serverResponse)
                            &&!serverResponse.equals("null")){
                        Log.d(TAG, serverResponse);
                        callback.onLoad(true, serverResponse);
                    }else {
                        Log.d(TAG, "vacio");
                        callback.onLoad(false, null);
                    }

                } else {
                    Log.d(TAG, "vacio");
                    callback.onLoad(false, null);
                }
               // callback.onLoad(true, "pruebas");
                //downloadCancelUi.setCancel(true);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                downloadCancelUi.setCancel(true);
                callback.onLoad(false, null);
                Log.d(TAG, "onFailure ");
                t.printStackTrace();
            }
        });

        new Thread(){
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    if(downloadCancelUi.isCancel()){
                        if(call.isExecuted()){
                            call.cancel();
                            Log.d(TAG, "cancel");
                        }
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };
    }



}
