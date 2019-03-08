package com.consultoraestrategia.ss_crmeducativo.portal.caso.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.data.CasoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.data.CasoRepository;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.domain.useCase.GetAlumnoCasos;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.CasoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.TipoPadreUi;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.presenter.CasoPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.presenter.CasoPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.ui.adapter.CasosAdapter;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CasoFragment extends BaseFragment<CasoView, CasoPresenter, CasoListener> implements CasoView {

    String TAG = CasoFragment.class.getSimpleName();
    @BindView(R.id.recyclerCaso)
    RecyclerView recyclerCaso;
    @BindView(R.id.cant1)
    TextView cant1;
    @BindView(R.id.porcentaje1)
    TextView porcentaje1;
    @BindView(R.id.cant2)
    TextView cant2;
    @BindView(R.id.porcentaje2)
    TextView porcentaje2;
    Unbinder unbinder;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    CasosAdapter casosAdapter;


    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected CasoPresenter getPresenter() {
        CasoRepository casoRepository = new CasoRepository(new CasoLocalDataSource(InjectorUtils.provideCursoDao()));
        presenter = new CasoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(), new GetAlumnoCasos(casoRepository));
        return presenter;
    }

    @Override
    protected CasoView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.caso_fragment, container, false);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return null;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerCaso.setLayoutManager(layoutManager);
        casosAdapter = new CasosAdapter(new ArrayList<CasoUi>());
        recyclerCaso.setAdapter(casosAdapter);
        recyclerCaso.setHasFixedSize(true);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showListTipos(TipoPadreUi tipoMerito, TipoPadreUi tipoDemerito) {
        cant1.setText(String.valueOf(tipoMerito.getCantidad()));
        porcentaje1.setText(tipoMerito.getPorcentaje()+"%");
        cant2.setText(String.valueOf(tipoDemerito.getCantidad()));
        porcentaje2.setText(tipoDemerito.getPorcentaje()+"%");

    }

    @Override
    public void showListCasos(List<CasoUi> objectList) {
        casosAdapter.setObjectList(objectList);
    }

    @Override
    public void showTextEmpty() {

    }
}
