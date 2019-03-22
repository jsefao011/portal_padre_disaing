package com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters.holders;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters.TareaAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters.TareaColumnCountProvider;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareaUiCount;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.presenter.TareaListener;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CursoHolder   extends RecyclerView.ViewHolder implements View. OnClickListener{

    @BindView(R.id.curso)
    TextView curso;
    @BindView(R.id.recyclerTareas)
    RecyclerView recyclerTareas;

    TareaAdapter tareaAdapter;
    @BindView(R.id.docente)
    TextView docente;
    @BindView(R.id.content)
    ConstraintLayout content;
    @BindView(R.id.textView83)
    TextView linea;
    boolean click;
    @BindView(R.id.cantidadA)
    TextView cantidadA;
    @BindView(R.id.asignado)
    TextView asignado;
    @BindView(R.id.cantidade)
    TextView cantidade;
    @BindView(R.id.entregar)
    TextView entregar;
    @BindView(R.id.cantidadc)
    TextView cantidadc;
    @BindView(R.id.calificado)
    TextView calificado;
    TareaListener tareaListener;


    public CursoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        content.setOnClickListener(this);
    }
    public void bind(CursoUi cursoUi, TareaListener tareaListener){
        this.tareaListener=tareaListener;
        recyclerTareas.setVisibility(View.GONE);
        curso.setText(cursoUi.getCurso());
        docente.setText(cursoUi.getDocente());
        try { content.setBackgroundColor(Color.parseColor(cursoUi.getColoCurso()));
            linea.setBackgroundColor(Color.parseColor(cursoUi.getColoCurso()));
        }catch (Exception e){e.getStackTrace();}

        initAdapter();
        tareaAdapter.setObjectList(cursoUi.getTareasUiList());
        tareaAdapter.setparametroDisenio(cursoUi.getColoCurso());

        //cabecera
        List<TareaUiCount> tareaUiCountList= cursoUi.getTareaUiCountList();
        TareaUiCount TareaAsignada= tareaUiCountList.get(0);
        cantidadA.setText(String.valueOf(TareaAsignada.getCantidad()));
        asignado.setText("Asignada");
        cantidadA.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_black_1000));

        TareaUiCount TareaPorEntregar= tareaUiCountList.get(1);
        cantidade.setText(String.valueOf(TareaPorEntregar.getCantidad()));
        entregar.setText("Por entregar");
        cantidade.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_red_600));

        TareaUiCount TareaCalificada= tareaUiCountList.get(2);
        cantidadc.setText(String.valueOf(TareaCalificada.getCantidad()));
        calificado.setText("Calificadas");
        cantidadc.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_black_1000));
    }


    private void initAdapter() {
        AutoColumnGridLayoutManager autoColumnGridLayoutManager = new AutoColumnGridLayoutManager(itemView.getContext(), OrientationHelper.VERTICAL, false);
        TareaColumnCountProvider columnCountProvider = new TareaColumnCountProvider(itemView.getContext());
        autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider);
        recyclerTareas.setLayoutManager(autoColumnGridLayoutManager);
        tareaAdapter = new TareaAdapter(new ArrayList<TareasUi>(), "", tareaListener);
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
