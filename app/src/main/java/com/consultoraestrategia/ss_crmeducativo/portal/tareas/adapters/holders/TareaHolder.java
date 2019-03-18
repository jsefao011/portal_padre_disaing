package com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters.holders;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.UpdateRepositorioDowload;
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
    @BindView(R.id.linea)
    TextView linea;
    @BindView(R.id.nota)
    TextView nota;

    public TareaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(TareasUi tareasUi, int contador, String parametroDisenio){
        count.setText(String.valueOf(tareasUi.getCount()));
        titulo.setText(tareasUi.getNombre());
        if(tareasUi.getFechaUiInicio()!=null){
            diasemana.setText(tareasUi.getFechaUiInicio().getDia());
            dia.setText(String.valueOf(tareasUi.getFechaUiInicio().getFecha()));
            mes.setText(tareasUi.getFechaUiInicio().getMes());
            diasemanafin.setText(tareasUi.getFechaUiFin().getDia());
            diafin.setText(String.valueOf(tareasUi.getFechaUiFin().getFecha()));
            mesfin.setText(tareasUi.getFechaUiFin().getMes());
        }
        try { linea.setBackgroundColor(Color.parseColor(parametroDisenio));
        }catch (Exception e){e.getStackTrace();}

        if(tareasUi.getTipo().equals(TareasUi.Tipo.RUBRO)){
            TipoNotaUi tipoNotaUi= tareasUi.getTipoNotaUi();
            if(tipoNotaUi.getValorTipoNotaUi()!=null){
                ValorTipoNotaUi valorTipoNotaUi= tareasUi.getTipoNotaUi().getValorTipoNotaUi();
                switch (tareasUi.getTipoNotaUi().getTipo()){
                    case SELECTOR_ICONOS:
                        String url = valorTipoNotaUi.getUrl();
                        Glide.with(itemView.getContext())
                                .load(url)
                                .apply(Utils.getGlideRequestOptions())
                                .into(imgTiponota);
                        nota.setVisibility(View.GONE);
                        tiponota.setVisibility(View.VISIBLE);
                        imgTiponota.setVisibility(View.VISIBLE);
                        tiponota.setText(String.valueOf(valorTipoNotaUi.getAlias()));
                        break;
                    case SELECTOR_VALORES:
                        nota.setVisibility(View.VISIBLE);
                        tiponota.setVisibility(View.VISIBLE);
                        imgTiponota.setVisibility(View.GONE);
                        nota.setText(valorTipoNotaUi.getValor());
                        tiponota.setText(valorTipoNotaUi.getAlias());
                        break;
                }
            }
            if(tipoNotaUi.getValorTipoNotaUi()==null&& tipoNotaUi.getNota()==0){
                tiponota.setVisibility(View.GONE);
                imgTiponota.setVisibility(View.GONE);
                nota.setVisibility(View.VISIBLE);
                nota.setText("-");
            }
            if(tipoNotaUi.getValorTipoNotaUi()==null&&tipoNotaUi.getNota()>0){
                tiponota.setVisibility(View.GONE);
                imgTiponota.setVisibility(View.GONE);
                nota.setVisibility(View.VISIBLE);
                nota.setText(String.valueOf(tipoNotaUi.getNota()));
            }

        }else {
            tiponota.setVisibility(View.GONE);
            imgTiponota.setVisibility(View.GONE);
            nota.setVisibility(View.VISIBLE);
            nota.setText("-");
        }

            curso.setText(tareasUi.getCurso());
            docente.setText(tareasUi.getDocente());




    }
}
