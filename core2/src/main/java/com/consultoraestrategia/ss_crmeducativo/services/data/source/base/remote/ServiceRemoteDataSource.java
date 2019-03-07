package com.consultoraestrategia.ss_crmeducativo.services.data.source.base.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SCIEV on 18/05/2018.
 */

public abstract class ServiceRemoteDataSource<T> implements ServiceDataSource<T> {
    protected UtilServidor utilServidor;
    protected final  static String TAG = ServiceRemoteDataSource.class.getSimpleName();

    public ServiceRemoteDataSource(UtilServidor utilServidor) {
        this.utilServidor = utilServidor;
    }

    protected abstract Class<T> getTableclass();

    protected abstract Call<RestApiResponse<T>> _getDatosLogin(ApiRetrofit apiRetrofit, String usuarioId) throws Exception;

    @Override
    public void getDatosLogin(String usuarioId, final ObjectCallBack<T> callBack) {
        ApiRetrofit apiRetrofit = utilServidor.getApiRetrofit();
        Log.d(TAG, "getDatosLogin table: "+getTableclass()+" Usuario: "+ usuarioId);
        try {
            Call<RestApiResponse<T>> responseCall = _getDatosLogin(apiRetrofit,usuarioId);
            Response<RestApiResponse<T>> response = responseCall.execute();
            if (!response.isSuccessful()){
                callBack.onResponse(false,null);
                Log.d(TAG, "getDatosLogin Response" + getTableclass() + ": false");
            }else {
                RestApiResponse<T> body = response.body();
                if(body == null){
                    callBack.onResponse(false,null);
                    Log.d(TAG, "getDatosLogin Successful body null " + getTableclass() + ": false");
                } else if(body.isSuccessful()){
                    callBack.onResponse(true,body.getValue());
                    Log.d(TAG, "getDatosLogin Successful " + getTableclass() + ": true");
                }else {
                    callBack.onResponse(false, body.getValue());
                    Log.d(TAG, "getDatosLogin Successful " + getTableclass() + ": false");
                }
            }


        }catch (Throwable t){
            t.printStackTrace();

            Log.d(TAG, "getDatosLogin Throwable " + getTableclass() + ": false - "+t.getMessage());
            Log.d(TAG, "getDatosLogin Throwable " + getTableclass() + ": false - "+ t.getLocalizedMessage());
            callBack.onResponse(false,null);
        }

    }

    @Override
    public void getDatosExportar(ObjectCallBack<T> callBack) {

    }

    @Override
    public void SendDatos(final T item, Class<BERespuesta> respuestaClass, final DosObjectCallBack<T,BERespuesta> callBack)  {

        ApiRetrofit apiRetrofit = utilServidor.getApiRetrofit();
        Log.d(TAG, "SendDatos table: "+getTableclass());
        try {

            Call<RestApiResponse<BERespuesta>> responseCall = _sendDatos(apiRetrofit, item);

            responseCall.enqueue(new Callback<RestApiResponse<BERespuesta>>() {
                @Override
                public void onResponse(@NonNull Call<RestApiResponse<BERespuesta>> call, @NonNull Response<RestApiResponse<BERespuesta>> response) {
                    if (!response.isSuccessful()){
                        callBack.onResponse(false,item,null);
                        Log.d(TAG, "SendDatos Response" + getTableclass() + ": false");
                    }else {
                        RestApiResponse<BERespuesta> body = response.body();
                        if(body == null){
                            callBack.onResponse(false,item,null);
                            Log.d(TAG, "SendDatos Successful body null " + getTableclass() + ": false");
                        } else if(body.isSuccessful()){
                            callBack.onResponse(true,item,body.getValue());
                            Log.d(TAG, "SendDatos Successful " + getTableclass() + ": true");
                        }else {
                            callBack.onResponse(false,item, body.getValue());
                            Log.d(TAG, "SendDatos Successful " + getTableclass() + ": false");
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<RestApiResponse<BERespuesta>> call, @NonNull Throwable t) {
                    t.printStackTrace();

                    Log.d(TAG, "getDatosLogin Throwable " + getTableclass() + ": false - "+t.getMessage());
                    Log.d(TAG, "getDatosLogin Throwable " + getTableclass() + ": false - "+ t.getLocalizedMessage());
                    callBack.onResponse(false,item,null);
                }
            });
        } catch (Exception e) {
            callBack.onResponse(false,item,null);
            e.printStackTrace();
        }

    }


    protected abstract Call<RestApiResponse<BERespuesta>> _sendDatos(ApiRetrofit restAPI, T item) throws Exception;

    @Override
    public void changeEstado(T item, int syncFlag, SuccessCallBack callBack) {

    }

    @Override
    public void saveDatos(T item, SuccessCallBack callBack) {

    }

    @Override
    public <V extends BEVariables> void getDatosImportar(V importar, final ObjectCallBack<T> callBack) {

        ApiRetrofit apiRetrofit = utilServidor.getApiRetrofit();
        Log.d(TAG, "getDatosImportar table: "+getTableclass()+" importar: "+ importar.toString());
        try {

            Call<RestApiResponse<T>> responseCall = _getDatosImportar(apiRetrofit, importar);

            responseCall.enqueue(new Callback<RestApiResponse<T>>() {
                @Override
                public void onResponse(@NonNull Call<RestApiResponse<T>> call, @NonNull Response<RestApiResponse<T>> response) {
                    if (!response.isSuccessful()){
                        callBack.onResponse(false,null);
                        Log.d(TAG, "getDatosLogin Response" + getTableclass() + ": false");
                    }else {
                        RestApiResponse<T> body = response.body();
                        if(body == null){
                            callBack.onResponse(false,null);
                            Log.d(TAG, "getDatosLogin Successful body null " + getTableclass() + ": false");
                        } else if(body.isSuccessful()){
                            callBack.onResponse(true,body.getValue());
                            Log.d(TAG, "getDatosLogin Successful " + getTableclass() + ": true");
                        }else {
                            callBack.onResponse(false, body.getValue());
                            Log.d(TAG, "getDatosLogin Successful " + getTableclass() + ": false");
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<RestApiResponse<T>> call, @NonNull Throwable t) {
                    t.printStackTrace();

                    Log.d(TAG, "getDatosLogin Throwable " + getTableclass() + ": false - "+t.getMessage());
                    Log.d(TAG, "getDatosLogin Throwable " + getTableclass() + ": false - "+ t.getLocalizedMessage());
                    callBack.onResponse(false,null);
                }
            });
        } catch (Exception e) {
            callBack.onResponse(false,null);
            e.printStackTrace();
        }

    }

    protected abstract <V extends BEVariables> Call<RestApiResponse<T>> _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception;
}
