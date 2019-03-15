package com.consultoraestrategia.ss_crmeducativo.portal.caso.domain.useCase;


import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.data.CasoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.data.CasoRepository;

public class UpdateSuccesDowloadCasoArchivo extends UseCaseSincrono<UpdateSuccesDowloadCasoArchivo.Request, Boolean> {
    private CasoRepository casoRepository;

    public UpdateSuccesDowloadCasoArchivo(CasoRepository casoRepository) {
        this.casoRepository = casoRepository;
    }

    @Override
    public void execute(Request request, final Callback<Boolean> callback) {
        casoRepository.updateSucessDowload(request.getArchivoId(), request.getPath(), new CasoLocalDataSource.CallbackSuccess() {
            @Override
            public void onLoad(boolean success) {
                callback.onResponse(success, success);
            }
        });
    }

    public static class Request {
        String archivoId;
        String path;

        public Request(String archivoId, String path) {
            this.archivoId = archivoId;
            this.path = path;
        }

        public String getArchivoId() {
            return archivoId;
        }

        public void setArchivoId(String archivoId) {
            this.archivoId = archivoId;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
