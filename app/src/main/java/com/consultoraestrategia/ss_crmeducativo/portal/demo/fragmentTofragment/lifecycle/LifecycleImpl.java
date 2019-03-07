package com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.lifecycle;

import com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.cabecera.CabeceraPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.cabecera.CabeceraPresenterImpl;

public class LifecycleImpl implements Lifecycle {

 private CabeceraPresenter cabeceraPresenter;


    @Override
    public void onCreate() {

        cabeceraPresenter.onCreate();

    }

    @Override
    public void onViewCreated() {
cabeceraPresenter.onViewCreated();
    }

    @Override
    public void onDestroy() {
cabeceraPresenter.onDestroy();
    }

    @Override
    public void onResumen() {
cabeceraPresenter.onResumen();
    }

    public void setCabeceraPresenter(CabeceraPresenter cabeceraPresenter) {
        this.cabeceraPresenter = cabeceraPresenter;
    }
}
