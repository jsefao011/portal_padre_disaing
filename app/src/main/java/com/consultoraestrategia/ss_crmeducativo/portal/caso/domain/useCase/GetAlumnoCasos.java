package com.consultoraestrategia.ss_crmeducativo.portal.caso.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.data.CasoDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.data.CasoRepository;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.AlumnoUi;

public class GetAlumnoCasos extends UseCase<GetAlumnoCasos.RequestValues, GetAlumnoCasos.ResponseValue> {

    CasoRepository casoRepository;

    public GetAlumnoCasos(CasoRepository casoRepository) {
        this.casoRepository = casoRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        casoRepository.getAlumoCaso(requestValues.getAlumnoId(), new CasoDataSource.SucessCallback<AlumnoUi>() {
            @Override
            public void onLoad(boolean success, AlumnoUi item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        int alumnoId;

        public RequestValues(int alumnoId) {
            this.alumnoId = alumnoId;
        }

        public int getAlumnoId() {
            return alumnoId;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue{

        AlumnoUi alumnoUi;

        public ResponseValue(AlumnoUi alumnoUi) {
            this.alumnoUi = alumnoUi;
        }

        public AlumnoUi getAlumnoUi() {
            return alumnoUi;
        }
    }
}
