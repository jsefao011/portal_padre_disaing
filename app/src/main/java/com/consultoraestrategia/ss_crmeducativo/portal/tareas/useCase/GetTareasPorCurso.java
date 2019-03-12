package com.consultoraestrategia.ss_crmeducativo.portal.tareas.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.data.source.TareasDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.data.source.TareasRepository;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CursoUi;

import java.util.List;

public class GetTareasPorCurso extends UseCase<GetTareasPorCurso.RequestValues, GetTareasPorCurso.ResponseValue> {

   TareasRepository tareasRepository;

    public GetTareasPorCurso(TareasRepository tareasRepository) {
        this.tareasRepository = tareasRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        tareasRepository.getTareasPorCurso(requestValues.getIdAlumno(), new TareasDataSource.SucessCallback<List<CursoUi>>() {
            @Override
            public void onLoad(boolean success, List<CursoUi> item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
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
        private List<CursoUi>cursoUiList;

        public ResponseValue(List<CursoUi> cursoUiList) {
            this.cursoUiList = cursoUiList;
        }

        public List<CursoUi> getCursoUiList() {
            return cursoUiList;
        }
    }
}
