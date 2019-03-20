package com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.data.source;

import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.data.source.local.InformacionContactoLocalDataSource;

import java.util.List;

public class InformacionContactoRepository implements InformacionContactoDataSource{

    private InformacionContactoLocalDataSource informacionContactoLocalDataSource;

    public InformacionContactoRepository(InformacionContactoLocalDataSource informacionContactoLocalDataSource) {
        this.informacionContactoLocalDataSource = informacionContactoLocalDataSource;
    }

    @Override
    public void getInformacionALumno(int idAlumno, SucessCallback<List<Object>> sucessCallback) {
        informacionContactoLocalDataSource.getInformacionALumno(idAlumno, sucessCallback);
    }

    @Override
    public void getInformacionDocente(int idDocentePerson, SucessCallback<List<Object>> sucessCallback) {
        informacionContactoLocalDataSource.getInformacionDocente(idDocentePerson, sucessCallback);
    }
}
