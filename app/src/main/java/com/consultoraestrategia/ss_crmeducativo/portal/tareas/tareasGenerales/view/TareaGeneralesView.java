package com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasGenerales.view;

import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TipoTareaUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.TabsTareasListaView;

import java.util.List;

public interface TareaGeneralesView extends TabsTareasListaView {

    void showTabletareasGenerales(List<TareasUi> listaTRows, List<TipoTareaUi>listaTipoTColums, List<List<Object>> celdaUiList);

}
