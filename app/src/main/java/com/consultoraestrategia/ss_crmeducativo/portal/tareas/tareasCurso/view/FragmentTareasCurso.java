package com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasCurso.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.adapters.TareasAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.data.source.TareasLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.data.source.TareasRepository;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CursoTareasUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasCurso.presenter.TareaCursoPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasCurso.presenter.TareaCursoPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.useCase.GetTareasCursoAlumno;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.TabsTareasListPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.TabsTareasLista;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentTareasCurso extends TabsTareasLista  implements TareaCursoView{

    private String TAG= FragmentTareasCurso.class.getSimpleName();
    private TareaCursoPresenter tareaCursoPresenter;
    private View view;
    private Unbinder unbinder;
    @BindView(R.id.rc_tablesTareas)
    RecyclerView rc_tablesTareas;
    private TareasAdapter tareasAdapter;


    public static FragmentTareasCurso newInstance() {
        FragmentTareasCurso fragment = new FragmentTareasCurso();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected View getViews(LayoutInflater inflater, @Nullable ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_tarea_curso, container, false);
        unbinder = ButterKnife.bind(this, view);
        initAdapter();
        return view;
    }

    private void initAdapter() {
        rc_tablesTareas.setLayoutManager(new LinearLayoutManager(getContext()));
        tareasAdapter = new TareasAdapter(new ArrayList<CursoTareasUi>(), getContext());
        rc_tablesTareas.setAdapter(tareasAdapter);
        rc_tablesTareas.setHasFixedSize(true);


    }

    @Override
    protected TabsTareasListPresenter getPresenter() {
        TareasRepository tareasRepository= new TareasRepository(new TareasLocalDataSource());
        tareaCursoPresenter = new TareaCursoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), new GetTareasCursoAlumno(tareasRepository));
        return  tareaCursoPresenter;
    }

    @Override
    public void showTabletareas(List<CursoTareasUi> listarTareasUis) {
        Log.d(TAG, "showTabletareas");
        tareasAdapter.setCursosList(listarTareasUis);
    }

}
