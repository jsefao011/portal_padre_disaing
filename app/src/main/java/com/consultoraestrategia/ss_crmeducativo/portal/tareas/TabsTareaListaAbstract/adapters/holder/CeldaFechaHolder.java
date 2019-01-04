package com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.adapters.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CeldaFechaHolder extends AbstractViewHolder {

    @BindView(R.id.txtdia)
    TextView txtdia;
    @BindView(R.id.txtfecha)
    TextView txtfecha;
    @BindView(R.id.txtmes)
    TextView txtmes;
    @BindView(R.id.linear)
    LinearLayout linearfechatarea;
    @BindView(R.id.vacioitem)
    TextView vacioitem;
    @BindView(R.id.txtretraso)
    TextView txtretraso;
    @BindView(R.id.txtcantidad)
    TextView txtcantidad;
    @BindView(R.id.linear1)
    LinearLayout linearretraso;

    public CeldaFechaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void bind(TareasUi tareasUi){
        if(tareasUi.getDia()!=null){
            vacioitem.setVisibility(View.GONE);
            linearfechatarea.setVisibility(View.VISIBLE);
            txtdia.setText(String.valueOf(tareasUi.getDia()));
            txtfecha.setText(String.valueOf(tareasUi.getFecha()));
            txtmes.setText(String.valueOf((tareasUi.getMes())));


        }else{
            vacioitem.setVisibility(View.VISIBLE);
            linearfechatarea.setVisibility(View.GONE);
            linearretraso.setVisibility(View.GONE);
        }

    }



}
