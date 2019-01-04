package com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.adapters.holder;

import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CellUI2;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CeldaTareaRetrasoHolder  extends AbstractViewHolder {
    @BindView(R.id.cantidad)
    TextView cantidad;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.vacioitem)
    TextView vacioitem;


    public CeldaTareaRetrasoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void bind(CellUI2 cellUI2){
        if(cellUI2.getCantidad()!=null){
            vacioitem.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            cantidad.setVisibility(View.VISIBLE);
            cantidad.setText(String.valueOf(cellUI2.getCantidad()));
        }
        else {

            vacioitem.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
            cantidad.setVisibility(View.GONE);
        }
    }
}
