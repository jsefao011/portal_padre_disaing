package com.consultoraestrategia.ss_crmeducativo.portal.tareas.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.LifecycleImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.data.source.TareasDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.data.source.TareasRepository;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareaUiCount;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;

import java.util.List;

public class GetTareasGeneralesAlumno extends UseCase<GetTareasGeneralesAlumno.RequestValues,GetTareasGeneralesAlumno.ResponseValue> {

    public GetTareasGeneralesAlumno(TareasRepository tareasRepository) {
        this.tareasRepository = tareasRepository;
    }

    TareasRepository tareasRepository;

    @Override
    protected void executeUseCase(GetTareasGeneralesAlumno.RequestValues requestValues) {
        tareasRepository.getTareasGenerales(requestValues.getIdAlumno(), new TareasDataSource.SucessCallback<List<Object>>() {
            @Override
            public void onLoad(boolean success, List<Object> item) {
                getUseCaseCallback().onSuccess(new GetTareasGeneralesAlumno.ResponseValue(item));
            }
        });
    }
    public static class RequestValues implements UseCase.RequestValues{
        private int idAlumno;

        public RequestValues(int idAlumno) {
            this.idAlumno = idAlumno;
        }

        public int getIdAlumno() {
            return idAlumno;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
        List<Object> objectList;

        public ResponseValue(List<Object> objectList) {
            this.objectList = objectList;
        }

        public List<Object> getObjectList() {
            return objectList;
        }
    }
}
