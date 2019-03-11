package com.consultoraestrategia.ss_crmeducativo.portal.familia.listener;


import android.widget.EditText;

import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentListener;

import java.util.List;

public interface FamiliaListener extends BaseFragmentListener {

    void setListFamilia(List<Object> listFamilia);

    void onHideKeyBoardTrue(EditText textTelefono, EditText gmail, EditText direccion);
}
