package com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.cabecera;

import com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.detalle.FragmentDetalleView;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.detalle2.FragmentDetalleView2;

public interface CabeceraPresenter {
    void onAttach(FragmentCabeceraView fragmentCabeceraView, FragmentDetalleView fragmentDetalleView, FragmentDetalleView2 fragmentDetalleView2);
    void onCreate();
    void onViewCreated();
    void onDestroy();
    void onResumen();

}
