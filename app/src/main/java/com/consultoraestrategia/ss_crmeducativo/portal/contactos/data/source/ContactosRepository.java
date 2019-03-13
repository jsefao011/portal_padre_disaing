package com.consultoraestrategia.ss_crmeducativo.portal.contactos.data.source;

import com.consultoraestrategia.ss_crmeducativo.portal.contactos.data.source.local.ContactosLocalDataSource;

import java.util.List;

public class ContactosRepository implements ContactosDataSource{

    private ContactosLocalDataSource contactosLocalDataSource;


    public ContactosRepository(ContactosLocalDataSource contactosLocalDataSource) {
        this.contactosLocalDataSource = contactosLocalDataSource;
    }

    @Override
    public void getCompaneros(int idAlumno, int idProgramaEducativo, SucessCallback<List<Object>> callback) {
        contactosLocalDataSource.getCompaneros(idAlumno, idProgramaEducativo, callback);
    }

    @Override
    public void getDocentes(int idAlumno, int idProgramaEducativo, SucessCallback<List<Object>> callback) {
        contactosLocalDataSource.getDocentes(idAlumno, idProgramaEducativo, callback);
    }

    @Override
    public void getDirectivos(int idAlumno, int idProgramaEducativo, SucessCallback<List<Object>> callback) {
        contactosLocalDataSource.getDirectivos(idAlumno, idProgramaEducativo, callback);

    }
}
