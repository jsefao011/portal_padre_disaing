package com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasCurso;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.base.viewpager.ViewPagerItemListener;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters.CursoAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters.CursoHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.presenter.TareasPresenter;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentTareasCurso extends Fragment implements TareaCursoView , ViewPagerItemListener<TareasPresenter> {

    private String TAG= FragmentTareasCurso.class.getSimpleName();
    private TareasPresenter tareasPresenter;

    private Unbinder unbinder;
    @BindView(R.id.rc_tablesTareas)
    RecyclerView rc_tablesTareas;
    CursoAdapter cursoAdapter;


    public static FragmentTareasCurso newInstance() {
        FragmentTareasCurso fragment = new FragmentTareasCurso();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tarea_curso, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    private void initAdapter() {
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rc_tablesTareas.setLayoutManager(linearLayoutManager);
        cursoAdapter = new CursoAdapter(new ArrayList<CursoUi>());
        rc_tablesTareas.setAdapter(cursoAdapter);
        rc_tablesTareas.setHasFixedSize(true);


    }

    @Override
    public void onAttach(TareasPresenter presenter) {
    this.tareasPresenter=tareasPresenter;
    }

    @Override
    public void setTareasCurso(List<CursoUi> cursoUiList) {
        cursoAdapter.setObjectList(cursoUiList);
    }
}