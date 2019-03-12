package com.consultoraestrategia.ss_crmeducativo.portal.tareas.data.source;

import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareaUiCount;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;

import java.util.List;

public class TareasRepository implements  TareasDataSource  {


    TareasLocalDataSource tareasLocalDataSource;

    public TareasRepository(TareasLocalDataSource tareasLocalDataSource) {
        this.tareasLocalDataSource = tareasLocalDataSource;
    }


    @Override
    public void getTareasGenerales(int alumnoId, SucessCallback<List<Object>>listSucessCallback) {
        tareasLocalDataSource.getTareasGenerales(alumnoId, listSucessCallback);
    }

    @Override
    public void getTareasPorCurso(int alumnoId, SucessCallback<List<CursoUi>> listSucessCallback) {
        tareasLocalDataSource.getTareasPorCurso(alumnoId, listSucessCallback);
    }
}
