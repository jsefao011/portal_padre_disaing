package com.consultoraestrategia.ss_crmeducativo.portal.demo.estudianteTarea;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class EstudianteTareaFragment extends Fragment {

    @BindView(R.id.tab_host)
    TabHost tabHost;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_estudiante_tarea, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(getClass().getSimpleName(), "onViewCreated");
        setupTabHost();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setupTabHost() {

        tabHost.setup();
        TabHost.TabSpec spec = tabHost.newTabSpec("Tarea por curso");
        spec.setContent(R.id.tab_one_container);
        spec.setIndicator("Tarea por curso");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Lista de tareas");
        spec.setContent(R.id.tab_two_container);
        spec.setIndicator("Lista de tareas");
        tabHost.addTab(spec);

        tabHost.getTabWidget().getChildAt(0).getBackground().setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
        tabHost.getTabWidget().getChildAt(1).getBackground().setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(getClass().getSimpleName(), "onDestroyView");
        unbinder.unbind();
    }

}
