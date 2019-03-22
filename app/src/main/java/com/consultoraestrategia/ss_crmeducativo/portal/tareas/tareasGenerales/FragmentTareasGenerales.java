package com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasGenerales;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.ViewPagerItemListener;
import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters.TareaAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters.TareaColumnCountProvider;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareaUiCount;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.presenter.TareaListener;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.presenter.TareasPresenter;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentTareasGenerales extends Fragment implements TareaGeneralesView, ViewPagerItemListener<TareasPresenter>, TareaListener {


    Unbinder unbinder;
    TareasPresenter tareasPresenter;
    String TAG= FragmentTareasGenerales.class.getSimpleName();

    @BindView(R.id.recyclerTareas)
    RecyclerView recyclerTareas;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.txtempty)
    TextView txtempty;
    @BindView(R.id.linea)
    TextView linea;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.cantidadA)
    TextView cantidadA;
    @BindView(R.id.asignado)
    TextView asignado;
    @BindView(R.id.cantidade)
    TextView cantidade;
    @BindView(R.id.entregar)
    TextView entregar;
    @BindView(R.id.cantidadc)
    TextView cantidadc;
    @BindView(R.id.calificado)
    TextView calificado;
    private TareaAdapter tareasAdapter;

    public static FragmentTareasGenerales newInstance() {
        FragmentTareasGenerales fragment = new FragmentTareasGenerales();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tarea_general, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();

    }

    private void initAdapter() {
        AutoColumnGridLayoutManager autoColumnGridLayoutManager = new AutoColumnGridLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        TareaColumnCountProvider columnCountProvider = new TareaColumnCountProvider(getContext());
        autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider);
        recyclerTareas.setLayoutManager(autoColumnGridLayoutManager);
        tareasAdapter = new TareaAdapter(new ArrayList<TareasUi>(), "", this);
        recyclerTareas.setAdapter(tareasAdapter);
        recyclerTareas.setHasFixedSize(true);

    }

    @Override
    public void onAttach(TareasPresenter presenter) {
        this.tareasPresenter = presenter;
    }

    @Override
    public void showText() {
        txtempty.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void setListTareasList(List<TareasUi> tareasUiList) {
        tareasAdapter.setObjectList(tareasUiList);
    }

    @Override
    public void setTareaCountList(List<TareaUiCount> tareasCountUis) {
        content.setVisibility(View.VISIBLE);
        linea.setVisibility(View.VISIBLE);
        txtempty.setVisibility(View.GONE);

        //cabecera
        TareaUiCount TareaAsignada= tareasCountUis.get(0);
        cantidadA.setText(String.valueOf(TareaAsignada.getCantidad()));
        asignado.setText("Asignada");
        cantidadA.setTextColor(ContextCompat.getColor(getContext(), R.color.md_black_1000));

        TareaUiCount TareaPorEntregar= tareasCountUis.get(1);
        cantidade.setText(String.valueOf(TareaPorEntregar.getCantidad()));
        entregar.setText("Por entregar");
        cantidade.setTextColor(ContextCompat.getColor(getContext(), R.color.md_red_600));

        TareaUiCount TareaCalificada= tareasCountUis.get(2);
        cantidadc.setText(String.valueOf(TareaCalificada.getCantidad()));
        calificado.setText("Calificadas");
        cantidadc.setTextColor(ContextCompat.getColor(getContext(), R.color.md_black_1000));

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onclickInfoRubro(TareasUi tareasUi) {
        tareasPresenter.onClickRubroInformacion(tareasUi);
    }
}
