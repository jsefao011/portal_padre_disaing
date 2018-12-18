package com.consultoraestrategia.ss_crmeducativo.portal.demo.estudianteCursos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.portal.demo.graficos.DesempenoActivity;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.graficos.OthersActivity;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Intent.ACTION_SEARCH;

public class EstudianteCursos extends Fragment {


    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_estudiante_curos, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(getClass().getSimpleName(), "onViewCreated");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(getClass().getSimpleName(), "onDestroyView");
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @OnClick({R.id.cardviewCurso, R.id.cardviewCurso2, R.id.cardviewCurso3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cardviewCurso:
                showDesempenio();
                break;
            case R.id.cardviewCurso2:
                showDesempenio();
                break;
            case R.id.cardviewCurso3:
                showOtros();
                break;
        }
    }

    private void showOtros() {
        Intent intent = new Intent(ACTION_SEARCH, null, getContext(), OthersActivity.class);
        startActivity(intent);
    }

    private void showDesempenio(){
        Intent intent = new Intent(ACTION_SEARCH, null, getContext(), DesempenoActivity.class);
        startActivity(intent);
    }
}
