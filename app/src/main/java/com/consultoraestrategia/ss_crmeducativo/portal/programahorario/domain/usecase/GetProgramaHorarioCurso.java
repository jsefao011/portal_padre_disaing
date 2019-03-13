package com.consultoraestrategia.ss_crmeducativo.portal.programahorario.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.data.source.ProgramaHorarioDatSource;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.data.source.ProgramaHorarioRepository;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.ProgramaHorarioUi;

import java.util.List;

public class GetProgramaHorarioCurso extends UseCase<GetProgramaHorarioCurso.RequestValue, GetProgramaHorarioCurso.ResponseValue> {

    private static GetProgramaHorarioCurso mInstance;
    private ProgramaHorarioRepository horarioRepository;

    private GetProgramaHorarioCurso(ProgramaHorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    public static GetProgramaHorarioCurso getInstance(ProgramaHorarioRepository horarioRepository) {
        if (mInstance == null) {
            mInstance = new GetProgramaHorarioCurso(horarioRepository);
        }
        return mInstance;
    }

    @Override
    protected void executeUseCase(RequestValue requestValues) {
        horarioRepository.listarProgramasHorarioCargaCurso(requestValues.getCargaCursoId(), new ProgramaHorarioDatSource.Callback<List<ProgramaHorarioUi>>() {
            @Override
            public void load(boolean success, List<ProgramaHorarioUi> item) {
                if (success){
                    getUseCaseCallback().onSuccess(new ResponseValue(item));
                }else {
                    getUseCaseCallback().onError();
                }
            }
        });
    }

    public static class RequestValue implements UseCase.RequestValues{
        private int cargaCursoId;

        public RequestValue(int cargaCursoId) {
            this.cargaCursoId = cargaCursoId;
        }

        public int getCargaCursoId() {
            return cargaCursoId;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
        private List<ProgramaHorarioUi> programaHorarioUiList;

        public ResponseValue(List<ProgramaHorarioUi> programaHorarioUiList) {
            this.programaHorarioUiList = programaHorarioUiList;
        }

        public List<ProgramaHorarioUi> getProgramaHorarioUiList() {
            return programaHorarioUiList;
        }
    }
}
