package com.consultoraestrategia.ss_crmeducativo.portal.tareas.data.source;

import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CursoTareasUi;

import java.util.List;

public interface TareasDataSource {

    interface CallbackTareasCursosAlumno{
        void onListeTareasCursos(List<CursoTareasUi> TareasCursosUiList, int status);
    }

     void getTareasCursosList(CallbackTareasCursosAlumno callbackTareasCursosAlumno);




}
