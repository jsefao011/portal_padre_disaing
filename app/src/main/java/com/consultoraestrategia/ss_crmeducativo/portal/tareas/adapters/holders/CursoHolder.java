package com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters.holders;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters.CabeceraAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters.TareaAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters.TareaColumnCountProvider;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareaUiCount;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CursoHolder   extends RecyclerView.ViewHolder implements View. OnClickListener{

    @BindView(R.id.curso)
    TextView curso;
    @BindView(R.id.recyclercabecera)
    RecyclerView recyclercabecera;
    @BindView(R.id.recyclerTareas)
    RecyclerView recyclerTareas;
    CabeceraAdapter cabeceraAdapter;
    TareaAdapter tareaAdapter;
    @BindView(R.id.docente)
    TextView docente;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.textView83)
    TextView linea;
    boolean click;


    public CursoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        content.setOnClickListener(this);
    }
    public void bind(CursoUi cursoUi){
        recyclerTareas.setVisibility(View.GONE);
        curso.setText(cursoUi.getCurso());
        docente.setText("Profesor: "+cursoUi.getDocente());
        try { content.setBackgroundColor(Color.parseColor(cursoUi.getColoCurso()));
            linea.setBackgroundColor(Color.parseColor(cursoUi.getColoCurso()));
        }catch (Exception e){e.getStackTrace();}
        initAdapterCabecera();
        initAdapter();
        cabeceraAdapter.setObjectList(cursoUi.getTareaUiCountList());
        tareaAdapter.setObjectList(cursoUi.getTareasUiList());
        tareaAdapter.setparametroDisenio(cursoUi.getColoCurso());
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
        tareaAdapter = new TareaAdapter(new ArrayList<TareasUi>(), "");
        recyclerTareas.setAdapter(tareaAdapter);
        recyclerTareas.setHasFixedSize(true);

    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.content:
                 onclick();
         }
    }

    private void onclick() {
        if(click){
            recyclerTareas.setVisibility(View.GONE);
            click=false;
        }else {
            recyclerTareas.setVisibility(View.VISIBLE);
            click=true;
        }
    }
}
