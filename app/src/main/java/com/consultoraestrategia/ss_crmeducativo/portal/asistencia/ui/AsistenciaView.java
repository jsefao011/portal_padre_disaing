package com.consultoraestrategia.ss_crmeducativo.portal.asistencia.ui;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.presenter.AsistenciaPresenter;

import java.util.List;

public interface AsistenciaView extends BaseView<AsistenciaPresenter> {

    void showListPeriodos(List<PeriodoUi> periodoUiList);

    void changePeriodo(PeriodoUi oldSelected, PeriodoUi periodoSelected);
    void showListValorAsistencia(List<Object>objectList);

}
