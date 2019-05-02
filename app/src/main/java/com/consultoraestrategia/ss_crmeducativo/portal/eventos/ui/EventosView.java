package com.consultoraestrategia.ss_crmeducativo.portal.eventos.ui;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.portal.eventos.EventosPresent;

import java.util.List;

public interface EventosView extends BaseView<EventosPresent> {
    void showListEventos(List<Object> objects);
}
