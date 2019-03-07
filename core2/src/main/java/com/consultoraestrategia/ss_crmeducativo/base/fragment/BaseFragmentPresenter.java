package com.consultoraestrategia.ss_crmeducativo.base.fragment;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;

/**
 * Created by @stevecampos on 14/02/2018.
 */

public interface BaseFragmentPresenter<T extends BaseView> extends BasePresenter<T> {
    void onAttach();
    void onCreateView();
    void onViewCreated();
    void onActivityCreated();
    void onDestroyView();
    void onDetach();
}
