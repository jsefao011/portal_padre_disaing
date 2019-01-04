package com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.adapters.holder;

import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CellUI3;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CeldaTareaFecha2Holder extends AbstractViewHolder {

    @BindView(R.id.txtfecha2)
    TextView txtfecha2;
    @BindView(R.id.vacioitem)
    TextView vacioitem;

    public CeldaTareaFecha2Holder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void bind(CellUI3 cellUI3){
        if(cellUI3.getFecha()!=null){
            vacioitem.setVisibility(View.GONE);
            txtfecha2.setText(String.valueOf(cellUI3.getFecha()));
        }else{
            vacioitem.setVisibility(View.VISIBLE);
            txtfecha2.setVisibility(View.GONE);
        }

    }

}
