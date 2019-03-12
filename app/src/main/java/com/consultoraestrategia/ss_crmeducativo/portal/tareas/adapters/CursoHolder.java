package com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareaUiCount;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CursoHolder   extends RecyclerView.ViewHolder {

    @BindView(R.id.curso)
    TextView curso;
    @BindView(R.id.recyclercabecera)
    RecyclerView recyclercabecera;
    @BindView(R.id.recyclerTareas)
    RecyclerView recyclerTareas;
    CabeceraAdapter cabeceraAdapter;
    TareaAdapter tareaAdapter;

    public CursoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void bind(CursoUi cursoUi){
        curso.setText(cursoUi.getCurso());
        initAdapterCabecera();
        initAdapter();
        cabeceraAdapter.setObjectList(cursoUi.getTareaUiCountList());
        tareaAdapter.setObjectList(cursoUi.getTareasUiList());
    }

    private void initAdapterCabecera() {
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(itemView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclercabecera.setLayoutManager(linearLayoutManager);
        cabeceraAdapter = new CabeceraAdapter(new ArrayList<TareaUiCount>());
        recyclercabecera.setAdapter(cabeceraAdapter);
        recyclercabecera.setHasFixedSize(true);

    }

    private void initAdapter() {
        AutoColumnGridLayoutManager autoColumnGridLayoutManager = new AutoColumnGridLayoutManager(itemView.getContext(), OrientationHelper.VERTICAL, false);
        TareaColumnCountProvider columnCountProvider = new TareaColumnCountProvider(itemView.getContext());
        autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider);
        recyclerTareas.setLayoutManager(autoColumnGridLayoutManager);
        tareaAdapter = new TareaAdapter(new ArrayList<TareasUi>());
        recyclerTareas.setAdapter(tareaAdapter);
        recyclerTareas.setHasFixedSize(true);

    }

}
