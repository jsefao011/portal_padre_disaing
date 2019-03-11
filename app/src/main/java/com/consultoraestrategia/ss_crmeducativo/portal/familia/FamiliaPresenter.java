package com.consultoraestrategia.ss_crmeducativo.portal.familia;


import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.ui.FamiliaView;

import java.util.List;

public interface FamiliaPresenter extends BaseFragmentPresenter<FamiliaView> {

    void onResumeFragment();

    void onSaveEditPerson(List<Object> objectList);
}
