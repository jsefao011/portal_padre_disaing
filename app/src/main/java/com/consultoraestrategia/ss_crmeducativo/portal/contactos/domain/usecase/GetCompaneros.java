package com.consultoraestrategia.ss_crmeducativo.portal.contactos.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.data.source.ContactosDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.data.source.ContactosRepository;

import java.util.List;

public class GetCompaneros extends UseCase<GetCompaneros.RequestValues, GetCompaneros.ResponseValues> {

    private ContactosRepository contactosRepository;

    public GetCompaneros(ContactosRepository contactosRepository) {
        this.contactosRepository = contactosRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        contactosRepository.getCompaneros(requestValues.getIdAlumno(), requestValues.getIdProgramaEducativo(), new ContactosDataSource.SucessCallback<List<Object>>() {
            @Override
            public void onLoad(boolean success, List<Object> item) {
                getUseCaseCallback().onSuccess(new ResponseValues(item));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues {
        private int idAlumno;
        private int idProgramaEducativo;

        public RequestValues(int idAlumno, int idProgramaEducativo) {
            this.idAlumno = idAlumno;
            this.idProgramaEducativo = idProgramaEducativo;
        }

        public int getIdAlumno() {
            return idAlumno;
        }

        public int getIdProgramaEducativo() {
            return idProgramaEducativo;
        }
    }

    public static class ResponseValues implements UseCase.ResponseValue {

        private List<Object> list;

        public ResponseValues(List<Object> list) {
            this.list = list;
        }

        public List<Object> getList() {
            return list;
        }

        public void setList(List<Object> list) {
            this.list = list;
        }
    }
}