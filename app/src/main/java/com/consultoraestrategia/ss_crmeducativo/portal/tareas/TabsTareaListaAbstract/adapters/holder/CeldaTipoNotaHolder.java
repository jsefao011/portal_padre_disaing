package com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.adapters.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CellUI4;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CeldaTipoNotaHolder extends AbstractViewHolder {


    @BindView(R.id.icono)
    ImageView icono;
    @BindView(R.id.tiponota)
    TextView tiponota;
    @BindView(R.id.vacioitem)
    TextView vacioitem;


    public CeldaTipoNotaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void bind(CellUI4 cellUI4, int position){
        if(cellUI4.getTipo()!=null){
            tiponota.setVisibility(View.VISIBLE);
            icono.setVisibility(View.VISIBLE);
            vacioitem.setVisibility(View.GONE);
            tiponota.setText(cellUI4.getTipo());
        }else{
            vacioitem.setVisibility(View.VISIBLE);
            tiponota.setVisibility(View.GONE);
            icono.setVisibility(View.GONE);
        }


    }
}
