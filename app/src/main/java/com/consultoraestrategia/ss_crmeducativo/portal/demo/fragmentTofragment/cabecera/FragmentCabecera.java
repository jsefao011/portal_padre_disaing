package com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.cabecera;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.base.viewpager.LifecycleImpl;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.ViewPagerItemListener;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.ViewpagerAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.detalle.FragmentDetalle;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.detalle2.FragmentDetalle2;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentCabecera extends Fragment implements FragmentCabeceraView, LifecycleImpl.LifecycleListener {

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
    CabeceraPresenter presenter;


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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"onActivityCreated "+ (presenter==null));
    }

    private void setupAdapterViewPager() {
        FragmentDetalle fragment1 = new FragmentDetalle();
        FragmentDetalle2 fragment2 = new FragmentDetalle2();
        ViewpagerAdapter fragmentAdapter = new ViewpagerAdapter(getChildFragmentManager(),1  ,this);
        fragmentAdapter.addFragment(fragment1, "fragment1");
        fragmentAdapter.addFragment(fragment2, "fragment2");
        vpTareas.setOffscreenPageLimit(1);
        vpTareas.setAdapter(fragmentAdapter);
        tabTareas.setupWithViewPager(vpTareas);
        presenter = new CabeceraPresenterImpl();
        presenter.onAttach(this, fragment1,fragment2);
        presenter.onViewCreated();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onChildsFragmentViewCreated() {
        Log.d(TAG,"onChildsFragmentViewCreated");
        presenter.onChildsFragmentViewCreated();
    }

    @Override
    public void onFragmentViewCreated(Fragment f, View view, Bundle savedInstanceState) {
        Log.d(TAG,"onChildsFragmentViewCreated");
    }


    @Override
    public void onFragmentResumed(Fragment f) {
        Log.d(TAG,"onFragmentResumed: " + f.getClass());
    }

    @Override
    public void onFragmentViewDestroyed(Fragment f) {
        Log.d(TAG,"onChildsFragmentViewCreated"+ f);
    }

    @Override
    public void onFragmentActivityCreated(Fragment f, Bundle savedInstanceState) {
        if(f instanceof ViewPagerItemListener){
            ViewPagerItemListener<CabeceraPresenter> viewPagerItemFragment = (ViewPagerItemListener<CabeceraPresenter>)f;
            viewPagerItemFragment.onAttach(presenter);
        }
    }
}
