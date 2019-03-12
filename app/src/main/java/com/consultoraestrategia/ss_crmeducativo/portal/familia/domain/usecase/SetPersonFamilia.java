package com.consultoraestrategia.ss_crmeducativo.portal.familia.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.data.source.FamiliaDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.data.source.FamiliaRepository;

import java.util.List;

public class SetPersonFamilia extends UseCase<SetPersonFamilia.RequestValues, SetPersonFamilia.ResponseValues> {

    private FamiliaRepository familiaRepository;

    public SetPersonFamilia(FamiliaRepository familiaRepository) {
        this.familiaRepository = familiaRepository;
    }

    @Override
    protected void executeUseCase(SetPersonFamilia.RequestValues requestValues) {
        familiaRepository.setListFamilia(requestValues.getList(), requestValues.getIdPerson(), new FamiliaDataSource.SucessCallback<List<Object>>() {
            @Override
            public void onLoad(boolean success, List<Object> item) {
                getUseCaseCallback().onSuccess(new SetPersonFamilia.ResponseValues(item));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues {
        private List<Object> list;
        private int idPerson;

        public RequestValues(List<Object> list, int idPerson) {
            this.list = list;
            this.idPerson = idPerson;
        }

        public List<Object> getList() {
            return list;
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