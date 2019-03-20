package com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.data.source;

import java.util.List;

public interface InformacionContactoDataSource {

    interface SucessCallback<T> {
        void onLoad(boolean success, T item);
    }

    void getInformacionALumno(int idAlumno, SucessCallback<List<Object>> sucessCallback);

    void getInformacionDocente(int idDocentePerson, SucessCallback<List<Object>> sucessCallback);
}
