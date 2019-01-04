package com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.adapters.holder;

import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilaTareasHolder extends AbstractViewHolder {

    @BindView(R.id.txtcount)
    TextView count;
    @BindView(R.id.txttareanombre)
    TextView tareanombre;
    @BindView(R.id.txttareadescrpcion)
    TextView tareadescrpcion;

    public FilaTareasHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind (TareasUi tareasUi){
        count.setText(String.valueOf(tareasUi.getId()));
        tareanombre.setText(String.valueOf(tareasUi.getNombre()));
        tareadescrpcion.setText(String.valueOf(tareasUi.getDescripcion()));
    }

}
