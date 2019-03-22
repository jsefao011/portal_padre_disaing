package com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.ui;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.InformacionContactoPresenter;

import java.util.List;

public interface InformacionContactoView extends BaseView<InformacionContactoPresenter> {

    void showListPersonInformacion(List<Object> list);
    void showListFamilyInformacion(List<Object> list);
}
