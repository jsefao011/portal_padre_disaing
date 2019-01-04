package com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.adapters.holder;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CursoTareasUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColumnaTareaCursoHolder extends AbstractViewHolder {

    @BindView(R.id.txtcurso)
    TextView txtcurso;
    @BindView(R.id.txtdocente)
    TextView txtdocente;
    @BindView(R.id.fondo)
    View fondo;
    @BindView(R.id.txtlistaTareas)
    TextView txtlistaTareas;

    public ColumnaTareaCursoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(CursoTareasUi cursosUi){
        if(cursosUi!=null){
            txtcurso.setVisibility(View.VISIBLE);
            txtdocente.setVisibility(View.VISIBLE);
            txtlistaTareas.setVisibility(View.GONE);
            txtcurso.setText(String.valueOf(cursosUi.getCurso()));
            txtdocente.setText(String.valueOf(cursosUi.getDocente()));
            fondo.setBackgroundColor(Color.parseColor(cursosUi.getColorfondo()));
        }
        else {
            txtcurso.setVisibility(View.GONE);
            txtdocente.setVisibility(View.GONE);
            txtlistaTareas.setVisibility(View.VISIBLE);
            fondo.setBackgroundColor(Color.parseColor("#40747c"));
        }

    }

}
