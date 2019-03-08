package com.consultoraestrategia.ss_crmeducativo.portal.familia.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.data.source.FamiliaDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.data.source.FamiliaRepository;

import java.util.List;

public class GetPersonFamilia extends UseCase<GetPersonFamilia.RequestValues, GetPersonFamilia.ResponseValues> {

    private FamiliaRepository familiaRepository;

    public GetPersonFamilia(FamiliaRepository familiaRepository) {
        this.familiaRepository = familiaRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        familiaRepository.getListFamilia(requestValues.getIdPerson(), new FamiliaDataSource.SucessCallback<List<Object>>() {
            @Override
            public void onLoad(boolean success, List<Object> item) {
                getUseCaseCallback().onSuccess(new ResponseValues(item));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues {
        private int idPerson;

        public RequestValues(int idPerson) {
            this.idPerson = idPerson;
        }

        public int getIdPerson() {
            return idPerson;
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
