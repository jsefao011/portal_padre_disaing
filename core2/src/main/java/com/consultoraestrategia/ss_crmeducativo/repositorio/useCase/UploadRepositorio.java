package com.consultoraestrategia.ss_crmeducativo.repositorio.useCase;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioRepository;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.DownloadCancelUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioUploadEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;

public class UploadRepositorio extends UseCaseSincrono<UploadRepositorio.Request, UpdateRepositorioFileUi> {

    RepositorioRepository repositorioRepository;
    private  String TAG = UploadRepositorio.class.getSimpleName();

    public UploadRepositorio(RepositorioRepository repositorioRepository) {
        this.repositorioRepository = repositorioRepository;
    }

    @Override
    public void execute(final UploadRepositorio.Request request, final Callback<UpdateRepositorioFileUi> callback) {
        final UpdateRepositorioFileUi updateRepositorioFileUi = request.getUpdateRepositorioFileUi();
        Log.d(TAG,"getUrlServidor: "+ request.getUrlServidor() +"getPath: " + updateRepositorioFileUi.getPath());
        if(TextUtils.isEmpty(request.getUrlServidor())){
            updateRepositorioFileUi.setUploadEstadoFileU(RepositorioUploadEstadoFileU.ERROR_SUBIDA);
            callback.onResponse(false, updateRepositorioFileUi);
            return;
        }
        if(TextUtils.isEmpty(updateRepositorioFileUi.getPath())){
            updateRepositorioFileUi.setUploadEstadoFileU(RepositorioUploadEstadoFileU.ERROR_SUBIDA);
            callback.onResponse(false, updateRepositorioFileUi);
            return;
        }

        RepositorioDataSource.CallbackProgress<String> callbackProgress = new RepositorioDataSource.CallbackProgress<String>() {
            @Override
            public void onProgress(int count) {
                //not found
            }

            @Override
            public void onLoad(boolean success, String item) {
                Log.d(TAG, "success : " + success + "item: " + item);
                if (success) {
                    updateRepositorioFileUi.setUrl(item);
                    updateRepositorioFileUi.setUploadEstadoFileU(RepositorioUploadEstadoFileU.SUDIDA_COMPLETA);
                    callback.onResponse(true, updateRepositorioFileUi);
                } else {
                    updateRepositorioFileUi.setUploadEstadoFileU(RepositorioUploadEstadoFileU.ERROR_SUBIDA);
                    callback.onResponse(false, updateRepositorioFileUi);
                }
            }

            @Override
            public void onPreLoad(DownloadCancelUi isCancel) {
                updateRepositorioFileUi.setDownloadCancelUi(isCancel);
                updateRepositorioFileUi.setUploadEstadoFileU(RepositorioUploadEstadoFileU.ENPROCESO_SUBIDA);
                callback.onResponse(false, updateRepositorioFileUi);
            }
        };

        Log.d(TAG, request.getRepositorio().toString());
        switch (request.getRepositorio()){
            case ARCHIVO:
                repositorioRepository.uploadFileArchivo(request.getUrlServidor(), updateRepositorioFileUi.getPath(),callbackProgress);
                break;
            case ARCHIVO_ASISTENICA:
                repositorioRepository.uploadFileJustificacion(request.getUrlServidor(), updateRepositorioFileUi.getPath(),callbackProgress);
                break;
            case ARCHIVO_COMPORTAMIENTO:
                repositorioRepository.uploadFileCaso(request.getUrlServidor(), updateRepositorioFileUi.getPath(),callbackProgress);
                break;
        }

    }

    public static class Request {
        private String urlServidor;
        private UpdateRepositorioFileUi updateRepositorioFileUi;
        private RepositorioUi repositorio;

        public Request(String urlServidor, RepositorioUi repositorio, UpdateRepositorioFileUi updateRepositorioFileUi) {
            this.urlServidor = urlServidor;
            this.updateRepositorioFileUi = updateRepositorioFileUi;
            this.repositorio = repositorio;
        }

        public String getUrlServidor() {
            return urlServidor;
        }

        public UpdateRepositorioFileUi getUpdateRepositorioFileUi() {
            return updateRepositorioFileUi;
        }

        public RepositorioUi getRepositorio() {
            return repositorio;
        }
    }
}
