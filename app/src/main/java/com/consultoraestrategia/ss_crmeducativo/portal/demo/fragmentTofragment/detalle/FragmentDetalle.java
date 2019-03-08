package com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.detalle;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.consultoraestrategia.ss_crmeducativo.base.viewpager.ViewPagerItemListener;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.cabecera.CabeceraPresenter;


public class FragmentDetalle extends Fragment implements FragmentDetalleView, ViewPagerItemListener<CabeceraPresenter> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onAttach(CabeceraPresenter presenter) {

    }
}
