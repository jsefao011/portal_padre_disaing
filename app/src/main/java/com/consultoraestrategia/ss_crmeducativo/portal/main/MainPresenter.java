package com.consultoraestrategia.ss_crmeducativo.portal.main;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.main.view.MainView;

public interface MainPresenter extends BasePresenter<MainView> {
    void onClickBtnInfoColegio();

    void onClickBtnInfoEstudiante();

    void onClickBtnInfoFamilia();

    void onMenuSelected(Object o);
}
