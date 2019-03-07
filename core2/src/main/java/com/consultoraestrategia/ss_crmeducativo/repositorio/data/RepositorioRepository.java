package com.consultoraestrategia.ss_crmeducativo.repositorio.data;

import com.consultoraestrategia.ss_crmeducativo.repositorio.data.remote.RepositorioRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.preferents.RepositorioPreferentsDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.local.RepositorioLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

import java.util.List;

public class RepositorioRepository implements RepositorioDataSource {
    private RepositorioLocalDataSource localDataSource;
    private RepositorioPreferentsDataSource preferentsDataSource;
    private RepositorioRemoteDataSource remoteDataSource;

    public RepositorioRepository(RepositorioLocalDataSource localDataSource, RepositorioPreferentsDataSource preferentsDataSource, RepositorioRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.preferentsDataSource = preferentsDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void dowloadImage(String url, String nombre, CallbackProgress<String> repositorioFileUiCallback) {
        remoteDataSource.dowloadImage(url,nombre, repositorioFileUiCallback);
    }

    @Override
    public void getArchivosConArchivosTareas(int tareaId, Callback<List<RepositorioFileUi>> callback) {
        localDataSource.getArchivosConArchivosTareas(tareaId, callback);
    }

    @Override
    public void getArchivosJustificacion(String asistenciaId, Callback<List<RepositorioFileUi>> callback) {
        localDataSource.getArchivosJustificacion(asistenciaId, callback);
    }

    @Override
    public void uploadFileJustificacion(String urlServidor, String path, final CallbackProgress<String> callback) {
        remoteDataSource.uploadFileJustificacion(urlServidor, path, callback);
    }

    @Override
    public void getUrlRepositorioArchivo(Callback<String> stringCallback) {
        localDataSource.getUrlRepositorioArchivo(stringCallback);
    }

    @Override
    public void uploadFileArchivo(String urlServidor, String path, CallbackProgress<String> callbackProgress) {
        remoteDataSource.uploadFileArchivo(urlServidor, path, callbackProgress);
    }

    @Override
    public void updateSucessDowload(String archivoId, String path, Callback<Boolean> callback) {
        localDataSource.updateSucessDowload(archivoId, path, callback);
    }

    @Override
    public void updateSucessDowloadAsistenica(String archivoId, String path, Callback<Boolean> booleanCallback) {
        localDataSource.updateSucessDowloadAsistenica(archivoId,path, booleanCallback);
    }

    @Override
    public void updateSucessDowloaComportamiento(String archivoId, String path, Callback<Boolean> booleanCallback) {
        localDataSource.updateSucessDowloadAsistenica(archivoId, path, booleanCallback);
    }

    @Override
    public void uploadFileCaso(String urlServidor, String path, CallbackProgress<String> callbackProgress) {
        remoteDataSource.uploadFileCaso(urlServidor, path, callbackProgress);
    }

}
