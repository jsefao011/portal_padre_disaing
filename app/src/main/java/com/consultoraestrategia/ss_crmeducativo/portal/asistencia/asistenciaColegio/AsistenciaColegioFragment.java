package com.consultoraestrategia.ss_crmeducativo.portal.asistencia.asistenciaColegio;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.base.viewpager.ViewPagerItemListener;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.presenter.AsistenciaPresenter;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AsistenciaColegioFragment extends Fragment implements ViewPagerItemListener<AsistenciaPresenter>, AsistenciaColegioView {

    AsistenciaPresenter presenter;
    private Unbinder unbinder;
    
    public static AsistenciaColegioFragment newInstance() {
        AsistenciaColegioFragment fragment = new AsistenciaColegioFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(AsistenciaPresenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    private void initAdapter() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_asistencia_colegio, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
