package com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.cabecera;

import com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.detalle.FragmentDetalle;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.detalle.FragmentDetalleView;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.detalle2.FragmentDetalleView2;

public class CabeceraPresenterImpl implements CabeceraPresenter {

 FragmentDetalleView view1;
 FragmentDetalleView2 view2;
 FragmentCabeceraView fragmentCabeceraView;

    @Override
    public void onAttach(FragmentCabeceraView fragmentCabeceraView, FragmentDetalleView fragmentDetalleView, FragmentDetalleView2 fragmentDetalleView2) {
        this.fragmentCabeceraView=fragmentCabeceraView;
        this.view1= fragmentDetalleView;
        this.view2=fragmentDetalleView2;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onResumen() {

    }
}
