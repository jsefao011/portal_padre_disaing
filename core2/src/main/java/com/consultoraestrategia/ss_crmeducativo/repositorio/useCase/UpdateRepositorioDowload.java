package com.consultoraestrategia.ss_crmeducativo.repositorio.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioRepository;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioUi;

import java.util.List;

public class UpdateRepositorioDowload extends UseCaseSincrono<UpdateRepositorioDowload.Request, Boolean> {

    private RepositorioRepository repository;

    public UpdateRepositorioDowload(RepositorioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Request request, final Callback<Boolean> callback) {
        RepositorioDataSource.Callback<Boolean> booleanCallback = new RepositorioRepository.Callback<Boolean>() {
            @Override
            public void onLoad(boolean success, Boolean item) {
                callback.onResponse(success, item);
            }
        };
        switch (request.getRepositorioUi()){
            case ARCHIVO_COMPORTAMIENTO:
                repository.updateSucessDowloaComportamiento(request.getArchivoId(), request.getPath(), booleanCallback);
                break;
            case ARCHIVO_ASISTENICA:
                repository.updateSucessDowloadAsistenica(request.getArchivoId(), request.getPath(), booleanCallback);
                break;
            case ARCHIVO:
                repository.updateSucessDowload(request.getArchivoId(), request.getPath(), booleanCallback);
                break;
        }

    }

    public static class Request{
        String archivoId;
        String path;
        RepositorioUi repositorioUi;

        public Request(String archivoId, String path, RepositorioUi repositorioUi) {
            this.archivoId = archivoId;
            this.path = path;
            this.repositorioUi = repositorioUi;
        }

        public String getArchivoId() {
            return archivoId;
        }

        public String getPath() {
            return path;
        }

        public RepositorioUi getRepositorioUi() {
            return repositorioUi;
        }
    }
}
