package com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.cabecera;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.detalle.FragmentDetalle;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.detalle.FragmentDetalleView;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.detalle2.FragmentDetalle2;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.lifecycle.Lifecycle;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.lifecycle.LifecycleImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters.FragmentAdapter;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentCabecera extends Fragment implements FragmentCabeceraView, Lifecycle {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int TAREA_POR_CURSO=0;
    private int TAREA_GENERALES=1;
    private static final String TAG = FragmentCabecera.class.getSimpleName();
    Unbinder unbinder;
    @BindView(R.id.tab_tareas)
    TabLayout tabTareas;
    @BindView(R.id.vp_Tareas)
    ViewPager vpTareas;
    private FragmentDetalle fragment1;
    private FragmentDetalle2 fragment2;
    CabeceraPresenter presenter;
    private LifecycleImpl lifecycle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_tareas, container, false);
        unbinder = ButterKnife.bind(this, view);

        initPresenter();
        return view;
    }



    private void initPresenter() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapterViewPager();
        //presenter.onCreate();
    }


    private void setupAdapterViewPager() {



        lifecycle = new LifecycleImpl();

        fragment1= new FragmentDetalle();

        fragment2= new FragmentDetalle2();


        final FragmentAdapter fragmentAdapter = new FragmentAdapter(getFragmentManager());
        fragmentAdapter.addFragment(fragment1, "Tareas por curso");
        fragmentAdapter.addFragment(fragment2, "Tareas General");



        vpTareas.setOffscreenPageLimit(5);
        vpTareas.setAdapter(fragmentAdapter);
        tabTareas.setupWithViewPager(vpTareas);

        presenter = new CabeceraPresenterImpl();
        presenter.onAttach(this, fragment1, fragment2);
        lifecycle.setCabeceraPresenter(presenter);
    }


    @Override
    public void onCreate() {
        lifecycle.onCreate();
    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onResumen() {

    }
}
