package com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.detalle;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.lifecycle.Lifecycle;

public class FragmentDetalle extends Fragment implements FragmentDetalleView {

    Lifecycle lifecycle;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.lifecycle= (Lifecycle) getTargetFragment();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(lifecycle!=null)lifecycle.onCreate();
    }
}
