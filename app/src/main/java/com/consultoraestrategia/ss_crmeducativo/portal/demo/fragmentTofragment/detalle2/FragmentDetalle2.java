package com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.detalle2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.consultoraestrategia.ss_crmeducativo.base.viewpager.ViewPagerItemListener;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.cabecera.CabeceraPresenter;


public class FragmentDetalle2 extends Fragment implements FragmentDetalleView2, ViewPagerItemListener<CabeceraPresenter> {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(CabeceraPresenter presenter) {

    }
}
