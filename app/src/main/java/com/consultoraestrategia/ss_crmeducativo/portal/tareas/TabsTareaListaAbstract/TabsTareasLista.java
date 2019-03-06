package com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

public  abstract  class TabsTareasLista extends Fragment implements  View.OnClickListener, TabsTareasListaView{
    static  String TAG = TabsTareasLista.class.getSimpleName();
    private View view;
    protected TabsTareasListPresenter presenter;


    protected abstract View getViews(LayoutInflater inflater, ViewGroup container);
    protected abstract TabsTareasListPresenter  getPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
       Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        FlowManager.init(new FlowConfig.Builder(getContext()).build());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = getViews(inflater,container);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter=getPresenter();
        setPresenter(presenter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void setPresenter(TabsTareasListPresenter presenter) {
        presenter.attachView(this);
        presenter.onCreate();
    }







}
