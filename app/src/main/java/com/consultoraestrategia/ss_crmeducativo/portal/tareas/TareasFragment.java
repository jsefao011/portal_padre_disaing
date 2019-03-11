package com.consultoraestrategia.ss_crmeducativo.portal.tareas;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragment;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.LifecycleImpl;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.ViewpagerAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.data.source.TareasLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.data.source.TareasRepository;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.presenter.TareaListener;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.presenter.TareasPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.presenter.TareasPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasCurso.FragmentTareasCurso;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasGenerales.FragmentTareasGenerales;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.useCase.GetTareasGeneralesAlumno;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.BindView;
import butterknife.Unbinder;


public class TareasFragment extends BaseFragment<TareaView,TareasPresenter, TareaListener> implements TareaView, LifecycleImpl.LifecycleListener {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

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

    @Override
    protected String getLogTag() {
        return null;
    }

    @Override
    protected TareasPresenter getPresenter() {
        TareasRepository tareasRepository= new TareasRepository(new TareasLocalDataSource(InjectorUtils.provideCursoDao()));
        presenter= new TareasPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
        new GetTareasGeneralesAlumno(tareasRepository));
        return presenter;
    }

    @Override
    protected TareaView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_tareas, container, false);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return null;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapterViewPager();
        //presenter.onCreate();
    }


    private void setupAdapterViewPager() {
        FragmentTareasCurso fragmentTareasCurso= new FragmentTareasCurso();
        FragmentTareasGenerales fragmentTareasGenerales= new FragmentTareasGenerales();
        ViewpagerAdapter fragmentAdapter = new ViewpagerAdapter(getChildFragmentManager(),1  ,this);
        fragmentAdapter.addFragment(fragmentTareasCurso, "Curso");
        fragmentAdapter.addFragment(fragmentTareasGenerales, "General");
        vpTareas.setOffscreenPageLimit(5);
        vpTareas.setAdapter(fragmentAdapter);
        tabTareas.setupWithViewPager(vpTareas);
        tabTareas.getTabAt(0).setIcon(R.drawable.booksstack);
        tabTareas.getTabAt(1).setIcon(R.drawable.listonwindow);
        presenter.onAttach(this, fragmentTareasCurso,fragmentTareasGenerales);
       // presenter.onViewCreated();

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

    @Override
    public void onChildsFragmentViewCreated() {
        presenter.onChildsFragmentViewCreated();
    }

    @Override
    public void onFragmentViewCreated(Fragment f, View view, Bundle savedInstanceState) {

    }

    @Override
    public void onFragmentResumed(Fragment f) {

    }

    @Override
    public void onFragmentViewDestroyed(Fragment f) {

    }

    @Override
    public void onFragmentActivityCreated(Fragment f, Bundle savedInstanceState) {

    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }
}
