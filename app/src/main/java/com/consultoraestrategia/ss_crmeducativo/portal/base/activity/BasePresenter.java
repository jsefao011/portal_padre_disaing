package com.consultoraestrategia.ss_crmeducativo.portal.base.activity;

import android.os.Bundle;
import android.util.SparseBooleanArray;

/**
 * Created by @stevecampos on 26/01/2018.
 */

public interface BasePresenter<T extends BaseView> extends com.consultoraestrategia.ss_crmeducativo.portal.base.BasePresenter<T> {
    void setExtras(Bundle extras);
    void onSingleItemSelected(Object singleItem, int selectedPosition);
    void onMultiItemsSelected(SparseBooleanArray booleanArray);
    void onMultiItemsSelectedFilter(Object o);
}
