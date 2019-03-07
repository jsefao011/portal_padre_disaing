package com.consultoraestrategia.ss_crmeducativo.services.data.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEGuardarEntidadesGlobal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jse on 30/12/2018.
 */

public class ServiceRemoteDataRepositoryImpl implements ServiceRemoteDataRepository {

    private static final  String TAG = ServiceRemoteDataRepositoryImpl.class.getSimpleName();
    private UtilServidor utilServidor;

    public ServiceRemoteDataRepositoryImpl(UtilServidor utilServidor) {
        this.utilServidor = utilServidor;
    }

    @Override
    public void SendDatosGlobal(final BEGuardarEntidadesGlobal item, final RespuestaCallBack<BEGuardarEntidadesGlobal, BERespuesta> callBack) {
        ApiRetrofit apiRetrofit = utilServidor.getApiRetrofit();
        Call<RestApiResponse<BERespuesta>> responseCall = apiRetrofit.fins_GuardarEntidades_Global(item);
        try {

            Response<RestApiResponse<BERespuesta>> response = responseCall.execute();
            if (!response.isSuccessful()){
                callBack.onResponse(false,item,null);
                Log.d(TAG, "SendDatos Response: false");
            }else {
                RestApiResponse<BERespuesta> body = response.body();
                if(body == null){
                    callBack.onResponse(false,item,null);
                    Log.d(TAG, "SendDatos Successful body null ");
                } else if(body.isSuccessful()){
                    callBack.onResponse(true,item,body.getValue());
                    Log.d(TAG, "SendDatos Successful : true");
                }else {
                    callBack.onResponse(false,item, body.getValue());
                    Log.d(TAG, "SendDatos Successful : false");
                }
            }


        }catch (Throwable t){
            t.printStackTrace();
            Log.d(TAG, "getDatosLogin Throwable : false - "+t.getMessage());
            callBack.onResponse(false,item,null);
        }
    }

    @Override
    public void SendDatosGlobalSimple(BEGuardarEntidadesGlobal item, RespuestaCallBack<BEGuardarEntidadesGlobal, BERespuesta> callBack) {
        ApiRetrofit apiRetrofit = utilServidor.getApiRetrofit();
        Call<RestApiResponse<BERespuesta>> responseCall = apiRetrofit.fins_GuardarEntidades_GlobalSimple(item);
        try {

            Response<RestApiResponse<BERespuesta>> response = responseCall.execute();
            if (!response.isSuccessful()){
                callBack.onResponse(false,item,null);
                Log.d(TAG, "SendDatos Response: false");
            }else {
                RestApiResponse<BERespuesta> body = response.body();
                if(body == null){
                    callBack.onResponse(false,item,null);
                    Log.d(TAG, "SendDatos Successful body null ");
                } else if(body.isSuccessful()){
                    callBack.onResponse(true,item,body.getValue());
                    Log.d(TAG, "SendDatos Successful : true");
                }else {
                    callBack.onResponse(false,item, body.getValue());
                    Log.d(TAG, "SendDatos Successful : false");
                }
            }


        }catch (Throwable t){
            t.printStackTrace();
            Log.d(TAG, "getDatosLogin Throwable : false - "+t.getMessage());
            callBack.onResponse(false,item,null);
        }
    }
}
