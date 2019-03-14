package com.consultoraestrategia.ss_crmeducativo.portal.caso.ui.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.CasoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.TipoHijoUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CasoCuerpoHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.diaSemana)
    TextView diaSemana;
    @BindView(R.id.dia)
    TextView dia;
    @BindView(R.id.mes)
    TextView mes;
    @BindView(R.id.img_tipo)
    ImageView imgTipo;
    @BindView(R.id.subtipo)
    TextView subtipo;
    @BindView(R.id.descripcion)
    TextView descripcion;
    @BindView(R.id.curso)
    TextView curso;
    @BindView(R.id.linea)
    TextView linea;

    public CasoCuerpoHolder(View itemView)
    {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public  void bind(CasoUi casoUi,int  position){

        diaSemana.setText(casoUi.getFechaUi().getDiaSemana());
        dia.setText(String.valueOf(casoUi.getFechaUi().getDia()));
        mes.setText(casoUi.getFechaUi().getMes());
        curso.setText(casoUi.getCursoUi().getNombre());
        descripcion.setText(casoUi.getDescripcion());
        subtipo.setText(casoUi.getTipoHijoUi().getNombre());
        int url=0;
        if(casoUi.getTipoHijoUi().getTipoPadre()== TipoHijoUi.TipoPadre.MERITO){
            linea.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.md_blue_900));
          //  subtipo.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorPrimaryDark));
            url= R.drawable.medal;
        }

        else {
            linea.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.md_red_600));
           // subtipo.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_red_600));
            url= R.drawable.policeman;
        }
        imgTipo.setImageResource(url);

    }


}
