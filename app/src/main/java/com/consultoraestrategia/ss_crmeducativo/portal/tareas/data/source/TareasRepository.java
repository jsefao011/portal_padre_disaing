package com.consultoraestrategia.ss_crmeducativo.portal.tareas.data.source;

public class TareasRepository implements  TareasDataSource  {


    TareasLocalDataSource tareasLocalDataSource;

    public TareasRepository(TareasLocalDataSource tareasLocalDataSource) {
        this.tareasLocalDataSource = tareasLocalDataSource;
    }

    @Override
    public void getTareasCursosList(CallbackTareasCursosAlumno callbackTareasCursosAlumno) {
        tareasLocalDataSource.getTareasCursosList(callbackTareasCursosAlumno);
    }
}
