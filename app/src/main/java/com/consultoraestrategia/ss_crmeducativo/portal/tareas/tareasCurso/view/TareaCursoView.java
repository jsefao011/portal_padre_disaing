package com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasCurso.view;

import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CursoTareasUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.TabsTareasListaView;

import java.util.List;

public interface TareaCursoView extends TabsTareasListaView {
    void showTabletareas(List<CursoTareasUi> listarTareasUis);
}
