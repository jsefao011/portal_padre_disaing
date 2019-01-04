package com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.adapters.holder;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TipoTareaUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColumnaTareasHolder extends AbstractViewHolder {

    @BindView(R.id.txtcantidad)
    TextView txtcantidad;
    @BindView(R.id.txttipotarea)
    TextView txttipotarea;


    public ColumnaTareasHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(TipoTareaUi numeroTareasUI) {

        txttipotarea.setTextColor(Color.parseColor(numeroTareasUI.getColortext()));
        txtcantidad.setTextColor(Color.parseColor(numeroTareasUI.getColortext()));
        txtcantidad.setText(String.valueOf(numeroTareasUI.getCantidad()));
        txttipotarea.setText(String.valueOf(numeroTareasUI.getTipo()));


    }


}
