package com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareaUiCount;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CabeceraHolder  extends RecyclerView.ViewHolder {

    @BindView(R.id.cantidad)
    TextView cantidad;
    @BindView(R.id.tipo)
    TextView tipo;

    public CabeceraHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(TareaUiCount tareaUiCount){

        Log.d(getClass().getSimpleName(), " tarea "+ tareaUiCount.getTipoTarea());
        cantidad.setText(String.valueOf(tareaUiCount.getCantidad()));
        switch (tareaUiCount.getTipoTarea()){
            case ASIGANADAS:
                tipo.setText("Asignadas");
                break;
            case CALIFICADAS:
                tipo.setText("Calificadas");
                break;
             default:
                 tipo.setText("Por entregar");
                 break;
        }

    }

}
