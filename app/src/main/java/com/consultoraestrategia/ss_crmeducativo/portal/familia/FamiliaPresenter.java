package com.consultoraestrategia.ss_crmeducativo.portal.familia;


import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.ui.FamiliaView;

public interface FamiliaPresenter extends BaseFragmentPresenter<FamiliaView> {

    void setExtras(int personaId);

    void onResumeFragment();
}
