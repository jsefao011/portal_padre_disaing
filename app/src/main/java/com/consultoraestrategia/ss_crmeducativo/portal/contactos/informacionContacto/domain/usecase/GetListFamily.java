package com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.domain.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.data.source.InformacionContactoDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.data.source.InformacionContactoRepository;

import java.util.List;

public class GetListFamily extends UseCase<GetListFamily.RequestValues, GetListFamily.ResponseValues> {

    public static final String TAG = GetListFamily.class.getSimpleName();
    private InformacionContactoRepository informacionContactoRepository;

    public GetListFamily(InformacionContactoRepository informacionContactoRepository) {
        this.informacionContactoRepository = informacionContactoRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        informacionContactoRepository.getInformacionALumno(requestValues.getIdAlumnoPersona(), new InformacionContactoDataSource.SucessCallback<List<Object>>() {
            @Override
            public void onLoad(boolean success, List<Object> item) {
                getUseCaseCallback().onSuccess(new ResponseValues(item));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{

        private int idAlumnoPersona;

        public RequestValues(int idAlumnoPersona) {
            this.idAlumnoPersona = idAlumnoPersona;
        }

        public int getIdAlumnoPersona() {
            return idAlumnoPersona;
        }

        public void setIdAlumnoPersona(int idAlumnoPersona) {
            this.idAlumnoPersona = idAlumnoPersona;
        }

    }

    public static class ResponseValues implements UseCase.ResponseValue{
        private List<Object> list;

        public ResponseValues(List<Object> list) {
            this.list = list;
        }

        public List<Object> getList() {
            return list;
        }
    }
}
