package com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasGenerales;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.base.viewpager.ViewPagerItemListener;
import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters.CabeceraAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters.TareaAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters.TareaColumnCountProvider;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareaUiCount;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.presenter.TareasPresenter;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentTareasGenerales extends Fragment implements TareaGeneralesView, ViewPagerItemListener<TareasPresenter> {


    Unbinder unbinder;
    TareasPresenter tareasPresenter;
    @BindView(R.id.recyclerCount)
    RecyclerView recyclerCount;
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
    private TareaAdapter tareasAdapter;
    private CabeceraAdapter cabeceraAdapter;

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
        initAdapterCabecera();
    }

    private void initAdapterCabecera() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerCount.setLayoutManager(linearLayoutManager);
        cabeceraAdapter = new CabeceraAdapter(new ArrayList<TareaUiCount>());
        recyclerCount.setAdapter(cabeceraAdapter);
        recyclerCount.setHasFixedSize(true);

    }

    private void initAdapter() {
        AutoColumnGridLayoutManager autoColumnGridLayoutManager = new AutoColumnGridLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        TareaColumnCountProvider columnCountProvider = new TareaColumnCountProvider(getContext());
        autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider);
        recyclerTareas.setLayoutManager(autoColumnGridLayoutManager);
        tareasAdapter = new TareaAdapter(new ArrayList<TareasUi>(), "");
        recyclerTareas.setAdapter(tareasAdapter);
        recyclerCount.setHasFixedSize(true);

    }

    @Override
    public void onAttach(TareasPresenter presenter) {
        this.tareasPresenter = tareasPresenter;
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
        cabeceraAdapter.setObjectList(tareasCountUis);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
