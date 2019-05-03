package com.consultoraestrategia.ss_crmeducativo.portal.asistencia.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.data.AsistenciaRepository;

import java.util.List;

public class GetValoresAsistencia  extends UseCase<GetValoresAsistencia.RequestValues, GetValoresAsistencia.ResponseValue> {


    AsistenciaRepository asistenciaRepository;

    public GetValoresAsistencia(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {

    }
    public static class RequestValues implements UseCase.RequestValues{
        private int alumnoId;

        public RequestValues(int alumnoId) {
            this.alumnoId = alumnoId;
        }

        public int getAlumnoId() {
            return alumnoId;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue{
        private List<Object>objects;

        public ResponseValue(List<Object> objects) {
            this.objects = objects;
        }

        public List<Object> getObjects() {
            return objects;
        }
    }
}
