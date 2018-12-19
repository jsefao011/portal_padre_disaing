package com.consultoraestrategia.ss_crmeducativo.portal.tareas.view.TabsTareaLista;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TabsTareasLista extends Fragment {
    static  String TAG = TabsTareasLista.class.getSimpleName();
    private View view;
    private Unbinder unbinder;

    public static TabsTareasLista newInstance( Bundle bundle) {
        TabsTareasLista fragment = new TabsTareasLista();
        Bundle args = new Bundle();
        Log.d(TAG, "tipo tarea " + args.getInt("tipotarea") );
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
     //   Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tabs_tarea_lista, container, false);
        unbinder = ButterKnife.bind(this, view);
       // setupPresenter();

        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //presenter = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onDetach() {
        super.onDetach();

    }
}
