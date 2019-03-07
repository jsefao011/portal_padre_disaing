package com.consultoraestrategia.ss_crmeducativo.repositorio.data;

import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.DownloadCancelUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

import java.util.List;

public interface RepositorioDataSource {

    interface Callback<T>{
        void onLoad(boolean success, T item);
    }
    interface CallbackProgress<T>  {
        void onProgress(int count);
        void onLoad(boolean success, T item);
        void onPreLoad(DownloadCancelUi isCancel);
    }
    void dowloadImage(String url, String nombre, CallbackProgress<String> repositorioFileUiCallback);
    void getArchivosConArchivosTareas(int tareaId, Callback<List<RepositorioFileUi>> callback);
    void getArchivosJustificacion(String asistenciaId, Callback<List<RepositorioFileUi>> callback);
    void uploadFileJustificacion(String urlServidor, String path, final CallbackProgress<String> callback);
    void getUrlRepositorioArchivo(Callback<String> stringCallback);
    void uploadFileArchivo(String urlServidor, String path, CallbackProgress<String> callbackProgress);
    void updateSucessDowload(String archivoId, String path, Callback<Boolean> callback);
    void updateSucessDowloadAsistenica(String archivoId, String path, Callback<Boolean> booleanCallback);
    void updateSucessDowloaComportamiento(String archivoId, String path, Callback<Boolean> booleanCallback);
    void uploadFileCaso(String urlServidor, String path, CallbackProgress<String> callbackProgress);
}

