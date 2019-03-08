package com.consultoraestrategia.ss_crmeducativo.portal.caso.ui.adapter;

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
        if(casoUi.getTipoHijoUi().getTipoPadre()== TipoHijoUi.TipoPadre.MERITO)
            url= R.drawable.medal;
        else url= R.drawable.policeman;
        imgTipo.setImageResource(url);

    }


}
