package com.consultoraestrategia.ss_crmeducativo.portal.tareas.presenter;

import android.support.v4.app.Fragment;

import com.consultoraestrategia.ss_crmeducativo.base.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.view.TareasView;

public interface TareasPresenter extends BasePresenter <TareasView> {
    void onPageChanged(int position);
}
