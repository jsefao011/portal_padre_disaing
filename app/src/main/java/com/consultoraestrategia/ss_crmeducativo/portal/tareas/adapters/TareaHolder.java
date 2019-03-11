package com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TareaHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.count)
    TextView count;
    @BindView(R.id.titulo)
    TextView titulo;
    @BindView(R.id.curso)
    TextView curso;
    @BindView(R.id.docente)
    TextView docente;
    @BindView(R.id.diasemana)
    TextView diasemana;
    @BindView(R.id.dia)
    TextView dia;
    @BindView(R.id.mes)
    TextView mes;
    @BindView(R.id.img_tiponota)
    ImageView imgTiponota;
    @BindView(R.id.tiponota)
    TextView tiponota;
    @BindView(R.id.diasemanafin)
    TextView diasemanafin;
    @BindView(R.id.diafin)
    TextView diafin;
    @BindView(R.id.mesfin)
    TextView mesfin;

    public TareaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(TareasUi tareasUi, int contador){
        count.setText(String.valueOf(contador+1));
        titulo.setText(tareasUi.getNombre());
        curso.setText(tareasUi.getCurso());
        docente.setText(tareasUi.getDocente());
        diasemana.setText(tareasUi.getFechaUiInicio().getDia());
        dia.setText(tareasUi.getFechaUiInicio().getFecha());
        mes.setText(tareasUi.getFechaUiInicio().getMes());
        diasemanafin.setText(tareasUi.getFechaUiFin().getDia());
        diafin.setText(tareasUi.getFechaUiFin().getFecha());
        mesfin.setText(tareasUi.getFechaUiFin().getMes());
        if(tareasUi.getValorTipoNotaUi()!=null)tiponota.setText(tareasUi.getValorTipoNotaUi().getAlias());

          String url = tareasUi.getValorTipoNotaUi().getUrl();
            if (!TextUtils.isEmpty(url)) {
                Glide.with(itemView.getContext())
                        .load(url)
                        .apply(Utils.getGlideRequestOptions())
                        .into(imgTiponota);
            }





    }
}
