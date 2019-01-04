package com.consultoraestrategia.ss_crmeducativo.portal.tareas.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters.FragmentAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.presenter.TareasPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.presenter.TareasPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasCurso.view.FragmentTareasCurso;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasGenerales.view.FragmentTareasGenerales;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class TareasFragment extends Fragment implements TareasView{


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int TAREA_POR_CURSO=0;
    private int TAREA_GENERALES=1;
    private static final String TAG = TareasFragment.class.getSimpleName();
    Unbinder unbinder;
    TareasPresenter presenter;
    @BindView(R.id.tab_tareas)
    TabLayout tabTareas;
    @BindView(R.id.vp_Tareas)
    ViewPager vpTareas;


    public static TareasFragment newInstance(String param1, String param2) {
        TareasFragment fragment = new TareasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        //fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_tareas, container, false);
        unbinder = ButterKnife.bind(this, view);

        initPresenter();
        return view;
    }



    private void initPresenter() {

        presenter= new TareasPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()));
        setPresenter(presenter);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapterViewPager();
        //presenter.onCreate();
    }


    private void setupAdapterViewPager() {

        final FragmentAdapter fragmentAdapter = new FragmentAdapter(getFragmentManager());
        fragmentAdapter.addFragment(FragmentTareasCurso.newInstance(), "Tareas por curso");
        fragmentAdapter.addFragment(FragmentTareasGenerales.newInstance(), "Tareas General");
        vpTareas.setOffscreenPageLimit(5);
        vpTareas.setAdapter(fragmentAdapter);
        tabTareas.setupWithViewPager(vpTareas);
        vpTareas.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (presenter != null)presenter.onPageChanged(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    private <T extends Fragment> T getFragment(Class<T> tClass) {
        List<Fragment> fragments = getFragments();
        for (Fragment fragment :
                fragments) {
            if (tClass.isInstance(fragment)) {
                return (T) fragment;
            }
        }
        return null;
    }
    private List<Fragment> getFragments() {
        return getFragmentManager().getFragments();
    }

    @Override
    public void setPresenter(TareasPresenter presenter) {
        presenter.attachView(this);
      presenter.onCreate();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    /*@Override
    public void setTipoTareaCurso(int tipoTarea) {
        TabsTareasLista tabsTareasLista= getFragment(TabsTareasLista.class);
        if (tabsTareasLista == null) return;
        //tabsTareasLista.setTipoNota(tipoTarea);
    }*/
}
