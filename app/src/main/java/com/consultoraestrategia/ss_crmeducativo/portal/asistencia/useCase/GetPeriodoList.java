package com.consultoraestrategia.ss_crmeducativo.portal.asistencia.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.data.AsistenciaDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.data.AsistenciaRepository;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.entities.PeriodoUi;

import java.util.List;

/**
 * Created by @stevecampos on 20/02/2018.
 */

public class GetPeriodoList  {
    private static final String TAG = GetPeriodoList.class.getSimpleName();
    private AsistenciaRepository repository;

    public GetPeriodoList(AsistenciaRepository repository) {
        this.repository = repository;
    }


    public ResponseValue execute(RequestsValue requestsValue){
        return  new ResponseValue(repository.getPeriodoList(requestsValue.getProgramaEducativoId()));
    }

    public static final class RequestsValue  {
        private int programaEducativoId;

        public RequestsValue(int programaEducativoId) {
            this.programaEducativoId = programaEducativoId;
        }

        public int getProgramaEducativoId() {
            return programaEducativoId;
        }
    }
    public static final class ResponseValue  {
        private final List<PeriodoUi> periodoUiList;


        public ResponseValue(List<PeriodoUi> periodoUiList) {
            this.periodoUiList = periodoUiList;
        }
        public List<PeriodoUi> getPeriodoUiList() {
            return periodoUiList;
        }

    }
}
