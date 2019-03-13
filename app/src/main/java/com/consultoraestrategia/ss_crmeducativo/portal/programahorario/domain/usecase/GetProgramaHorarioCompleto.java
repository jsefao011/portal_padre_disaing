package com.consultoraestrategia.ss_crmeducativo.portal.programahorario.domain.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.data.source.ProgramaHorarioDatSource;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.data.source.ProgramaHorarioRepository;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.ProgramaHorarioUi;

import java.util.List;

public class GetProgramaHorarioCompleto extends UseCase<GetProgramaHorarioCompleto.RequestValue, GetProgramaHorarioCompleto.ResponseValue> {

    private ProgramaHorarioRepository horarioRepository;
    private String TAG = GetProgramaHorarioCompleto.class.getSimpleName();

    public GetProgramaHorarioCompleto(ProgramaHorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    @Override
    protected void executeUseCase(RequestValue requestValues) {
        Log.d(TAG, "programaEducativoId: " + requestValues.getProgramaEducativoId());
        horarioRepository.listarProgramasHorarioProgramaEducativo(requestValues.getProgramaEducativoId(), new ProgramaHorarioDatSource.Callback<List<ProgramaHorarioUi>>() {
            @Override
            public void load(boolean success, List<ProgramaHorarioUi> item) {
                if (success){
                    Log.d(TAG, "items: " + item.size());
                    getUseCaseCallback().onSuccess(new ResponseValue(item));
                }else {
                    getUseCaseCallback().onError();
                }
            }
        });
    }

    public static class RequestValue implements UseCase.RequestValues{
        private int programaEducativoId;

        public RequestValue(int programaEducativoId) {
            this.programaEducativoId = programaEducativoId;
        }

        public int getProgramaEducativoId() {
            return programaEducativoId;
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
