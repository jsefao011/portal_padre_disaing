package com.consultoraestrategia.ss_crmeducativo.portal.asistencia.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragment;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.LifecycleImpl;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.ViewPagerItemListener;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.ViewpagerAdapter;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.adapters.CabeceraAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.adapters.PeriodoAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.asistenciaColegio.AsistenciaColegioFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.asistenciaCurso.AsistenciaCursoFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.data.AsistenciaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.data.AsistenciaRepository;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.listener.AsistenciaListener;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.listener.PeriodoListener;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.presenter.AsistenciaPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.presenter.AsistenciaPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.useCase.GetPeriodoList;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.useCase.GetValoresAsistencia;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AsistenciaFragment extends BaseFragment<AsistenciaView, AsistenciaPresenter, AsistenciaListener> implements AsistenciaView, LifecycleImpl.LifecycleListener, PeriodoListener {

    String TAG = AsistenciaFragment.class.getSimpleName();
    @BindView(R.id.tab_asistencia)
    BottomNavigationViewEx tabAsistencia;
    @BindView(R.id.vp_asistencia)
    ViewPager vpAsistencia;
    @BindView(R.id.tab_container)
    ScrollView tabContainer;
    Unbinder unbinder;
    PeriodoAdapter periodoAdapter;
    @BindView(R.id.recyclerValorAsistencia)
    RecyclerView recyclerValorAsistencia;
    @BindView(R.id.recyclerPeriodo)
    RecyclerView recyclerPeriodo;
    CabeceraAdapter cabeceraAdapter;

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected AsistenciaPresenter getPresenter() {
        AsistenciaRepository asistenciaRepository= new AsistenciaRepository(new AsistenciaLocalDataSource());
        presenter = new AsistenciaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetPeriodoList(asistenciaRepository), new GetValoresAsistencia(asistenciaRepository));
        return presenter;
    }

    @Override
    protected AsistenciaView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.asistencia_fragment, container, false);
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
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapterViewPager();
        initAdapterPeriodo();
        initAdapterCabecera();
    }

    private void initAdapterCabecera() {
        recyclerValorAsistencia.setVisibility(View.VISIBLE);
        cabeceraAdapter = new CabeceraAdapter(new ArrayList<Object>());
        recyclerValorAsistencia.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerValorAsistencia.setHasFixedSize(true);
        recyclerValorAsistencia.setAdapter(cabeceraAdapter);
    }

    private void initAdapterPeriodo() {
        recyclerPeriodo.setVisibility(View.VISIBLE);
        periodoAdapter = new PeriodoAdapter(new ArrayList<PeriodoUi>(), this);
        recyclerPeriodo.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerPeriodo.setHasFixedSize(true);
        recyclerPeriodo.setAdapter(periodoAdapter);

    }
    private void setupAdapterViewPager() {
        AsistenciaCursoFragment asistenciaCursoFragment = new AsistenciaCursoFragment();
        AsistenciaColegioFragment asistenciaColegioFragment = new AsistenciaColegioFragment();
        ViewpagerAdapter fragmentAdapter = new ViewpagerAdapter(getChildFragmentManager(), 1, this);
        fragmentAdapter.addFragment(asistenciaColegioFragment, "Colegio");
        fragmentAdapter.addFragment(asistenciaCursoFragment, "Curso");
        vpAsistencia.setOffscreenPageLimit(2);
        vpAsistencia.setAdapter(fragmentAdapter);
        tabAsistencia.setupWithViewPager(vpAsistencia);
        presenter.onAttach(this, asistenciaCursoFragment, asistenciaColegioFragment);
    }

    @Override
    public void onChildsFragmentViewCreated() {
         presenter.onChildsFragmentViewCreated();
    }

    @Override
    public void onFragmentViewCreated(Fragment f, View view, Bundle savedInstanceState) {
        if(f instanceof AsistenciaCursoFragment ) presenter.onDestroyTabAsistenciaCurso();
        else if(f instanceof  AsistenciaColegioFragment)presenter.onDestroyTabAsistenciaColegio();
    }

    @Override
    public void onFragmentResumed(Fragment f) {

    }

    @Override
    public void onFragmentViewDestroyed(Fragment f) {

    }

    @Override
    public void onFragmentActivityCreated(Fragment f, Bundle savedInstanceState) {

        if(f instanceof ViewPagerItemListener){
            ViewPagerItemListener<AsistenciaPresenter> viewPagerItemFragment = (ViewPagerItemListener<AsistenciaPresenter>)f;
            viewPagerItemFragment.onAttach(presenter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onPeriodoSelected(PeriodoUi periodoUi) {
       presenter.onPeriodoSelected(periodoUi);
    }

    @Override
    public void showListPeriodos(List<PeriodoUi> periodoUiList) {
        periodoAdapter.setPeriodoList(periodoUiList);
    }

    @Override
    public void changePeriodo(PeriodoUi oldSelected, PeriodoUi periodoSelected) {
       periodoAdapter.togglePeriodo(oldSelected, periodoSelected);
    }

    @Override
    public void showListValorAsistencia(List<Object> objectList) {
        cabeceraAdapter.setObjectList(objectList);
    }
}
