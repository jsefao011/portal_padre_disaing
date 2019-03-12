package com.consultoraestrategia.ss_crmeducativo.portal.tareas.data.source;

import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CursoUi;

import java.util.List;

public interface TareasDataSource {

    interface SucessCallback<T> {
        void onLoad(boolean success, T item);
    }
    void getTareasGenerales(int alumnoId, SucessCallback<List<Object>>listSucessCallback);

    void getTareasPorCurso(int alumnoId, SucessCallback<List<CursoUi>>listSucessCallback);
}
