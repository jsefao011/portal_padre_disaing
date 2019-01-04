package com.consultoraestrategia.ss_crmeducativo.portal.tareas.useCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.data.source.TareasDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.data.source.TareasRepository;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CursoTareasUi;

import java.util.List;

public class GetTareasCursoAlumno  extends UseCase<GetTareasCursoAlumno.RequestValues,GetTareasCursoAlumno.ResponseValue> {

    TareasRepository tareasRepository;

    public GetTareasCursoAlumno(TareasRepository tareasRepository) {
        this.tareasRepository = tareasRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        tareasRepository.getTareasCursosList(new TareasDataSource.CallbackTareasCursosAlumno() {
            @Override
            public void onListeTareasCursos(List<CursoTareasUi> TareasCursosUiList, int status) {
                getUseCaseCallback().onSuccess(new ResponseValue(TareasCursosUiList));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{

    }

    public static class ResponseValue implements UseCase.ResponseValue{
     private List<CursoTareasUi>cursoTareasUiList;

        public ResponseValue(List<CursoTareasUi> cursoTareasUiList) {
            this.cursoTareasUiList = cursoTareasUiList;
        }

        public List<CursoTareasUi> getCursoTareasUiList() {
            return cursoTareasUiList;
        }
    }
}
