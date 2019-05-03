package com.consultoraestrategia.ss_crmeducativo.portal.asistencia.asistenciaCurso;

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
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.adapters.PeriodoAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.listener.PeriodoListener;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.presenter.AsistenciaPresenter;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AsistenciaCursoFragment extends Fragment implements ViewPagerItemListener<AsistenciaPresenter>, AsistenciaCursoView {


    AsistenciaPresenter presenter;

    private Unbinder unbinder;


    public static AsistenciaCursoFragment newInstance() {
        AsistenciaCursoFragment fragment = new AsistenciaCursoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(AsistenciaPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_asistencia_curso, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(getClass().getSimpleName(), "ONRESUME ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
