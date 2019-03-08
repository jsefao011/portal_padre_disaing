package com.consultoraestrategia.ss_crmeducativo.portal.caso.ui;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.CasoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.TipoPadreUi;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.presenter.CasoPresenter;

import java.util.List;

public interface CasoView  extends  BaseView<CasoPresenter> {

    void showListTipos(TipoPadreUi tipoMerito, TipoPadreUi tipoDemerito);
    void showListCasos(List<CasoUi>objectList);

    void showTextEmpty();
}
