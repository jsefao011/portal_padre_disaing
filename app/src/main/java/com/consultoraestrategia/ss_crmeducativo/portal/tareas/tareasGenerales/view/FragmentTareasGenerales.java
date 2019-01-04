package com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasGenerales.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.adapters.TareasTableAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.data.source.TareasLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.data.source.TareasRepository;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CursoTareasUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TipoTareaUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasGenerales.presenter.TareaGeneralesPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasGenerales.presenter.TareaGeneralesPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.useCase.GetTareasCursoAlumno;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.TabsTareasListPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.TabsTareasLista;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.evrencoskun.tableview.TableView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentTareasGenerales extends TabsTareasLista  implements TareaGeneralesView{

    private View view;
    private Unbinder unbinder;
    @BindView(R.id.table)
    TableView tableTgenerales;
    TareaGeneralesPresenter tareaGeneralesPresenter;

    private TareasTableAdapter tareasTableAdapter;

    public static FragmentTareasGenerales newInstance() {
        FragmentTareasGenerales fragment = new FragmentTareasGenerales();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected View getViews(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.item_tabla_tarea, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected TabsTareasListPresenter getPresenter() {
        TareasRepository tareasRepository= new TareasRepository(new TareasLocalDataSource());
        tareaGeneralesPresenter = new TareaGeneralesPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), new GetTareasCursoAlumno(tareasRepository));
        return  tareaGeneralesPresenter;
    }

    @Override
    public void showTabletareasGenerales(List<TareasUi> listaTRows, List<TipoTareaUi> listaTipoTColums, List<List<Object>> celdaUiList) {
        CursoTareasUi cursoTareasUi= null;
        tareasTableAdapter = new TareasTableAdapter(getContext());
        tableTgenerales.setAdapter(tareasTableAdapter);
        tableTgenerales.setIgnoreSelectionColors(false);
        tableTgenerales.setHasFixedWidth(true);
        tableTgenerales.setIgnoreSelectionColors(true);
        tareasTableAdapter.setAllItems(listaTipoTColums,listaTRows,celdaUiList);
        int row_header_width = (int) getContext().getResources().getDimension(R.dimen.table_corner_tareas_width);
        int row_header_height = (int) getContext().getResources().getDimension(R.dimen.table_corner_tareas_height);
        tareasTableAdapter.setCornerView(cursoTareasUi,row_header_width,row_header_height);
    }
}
