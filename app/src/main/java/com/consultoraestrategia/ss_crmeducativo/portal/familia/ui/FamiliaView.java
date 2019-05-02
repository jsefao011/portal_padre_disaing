package com.consultoraestrategia.ss_crmeducativo.portal.familia.ui;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.FamiliaPresenter;

import java.util.List;

public interface FamiliaView extends BaseView<FamiliaPresenter> {

    void showListFamilia(List<Object> objects);
    void onCLickSave();
}
