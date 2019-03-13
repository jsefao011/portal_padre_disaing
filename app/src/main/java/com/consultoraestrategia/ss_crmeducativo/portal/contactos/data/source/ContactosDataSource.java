package com.consultoraestrategia.ss_crmeducativo.portal.contactos.data.source;


import java.util.List;

public interface ContactosDataSource {

    interface SucessCallback<T> {
        void onLoad(boolean success, T item);
    }

    void getCompaneros(int idAlumno, int idProgramaEducativo, SucessCallback<List<Object>> callback);

    void getDocentes(int idAlumno, int idProgramaEducativo, SucessCallback<List<Object>> callback);

    void getDirectivos(int idAlumno, int idProgramaEducativo, SucessCallback<List<Object>> callback);
}
