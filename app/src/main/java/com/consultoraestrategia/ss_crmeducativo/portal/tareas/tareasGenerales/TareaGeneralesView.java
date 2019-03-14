package com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasGenerales;

import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareaUiCount;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;

import java.util.List;

public interface TareaGeneralesView{

    void showText();
    void hideProgressBar();
    void setListTareasList(List<TareasUi> tareasUiList);

    void setTareaCountList(List<TareaUiCount> tareasCountUis);
}
