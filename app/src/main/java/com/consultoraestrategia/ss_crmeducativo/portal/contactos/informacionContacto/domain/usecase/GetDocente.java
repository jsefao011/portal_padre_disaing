package com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.domain.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.data.source.InformacionContactoDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.data.source.InformacionContactoRepository;

import java.util.List;

public class GetDocente extends UseCase<GetDocente.RequestValues, GetDocente.ResponseValues> {

    private InformacionContactoRepository informacionContactoRepository;

    public GetDocente(InformacionContactoRepository informacionContactoRepository) {
        this.informacionContactoRepository = informacionContactoRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
            informacionContactoRepository.getInformacionDocente(requestValues.getIdPersonaDocente(),
                    new InformacionContactoDataSource.SucessCallback<List<Object>>() {
                        @Override
                        public void onLoad(boolean success, List<Object> item) {
                            getUseCaseCallback().onSuccess(new ResponseValues(item));
                        }
                    });
    }

    public static class RequestValues implements UseCase.RequestValues{
        private int idPersonaDocente;

        public RequestValues(int idPersonaDocente) {
            this.idPersonaDocente = idPersonaDocente;
        }

        public int getIdPersonaDocente() {
            return idPersonaDocente;
        }
    }

    public static class ResponseValues implements UseCase.ResponseValue{
        private List<Object> objectList;

        public ResponseValues(List<Object> objectList) {
            this.objectList = objectList;
        }

        public List<Object> getObjectList() {
            return objectList;
        }
    }
}
