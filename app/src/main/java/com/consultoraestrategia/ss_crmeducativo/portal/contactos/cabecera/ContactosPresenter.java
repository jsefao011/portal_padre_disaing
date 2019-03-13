package com.consultoraestrategia.ss_crmeducativo.portal.contactos.cabecera;

import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.detalleCompaneros.DetalleCompanerosFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.detalleDirectivos.DetalleDirectivosFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.detalleProfesores.DetalleProfesoresFragment;

public interface ContactosPresenter extends BaseFragmentPresenter<ContactosView> {

    void onAttach(ContactosView contactosView, DetalleProfesoresFragment detalleProfesoresFragment,DetalleDirectivosFragment detalleDirectivosFragment, DetalleCompanerosFragment detalleCompanerosFragment);
    void onCreate();
    void onViewCreated();
    void onDestroy();
    void onResumen();
    void onChildsFragmentViewCreated();
}
