package com.consultoraestrategia.ss_crmeducativo.portal.main;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.HijoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.ItemMenuUI;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.ProgramaEducativoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.main.view.MainView;

public interface MainPresenter extends BasePresenter<MainView> {
    void onClickBtnInfoColegio();

    void onClickBtnInfoEstudiante();

    void onClickBtnInfoFamilia();

    void onMenuSelected(ItemMenuUI itemMenuUI);

    void onClickHijo();

    void onClickSelectedHijo(HijoUi hijoUi);

    void onClickedProgramaEducativo();

    void onSelectedProgramaEduca(ProgramaEducativoUi programaEducativoUi);
}
