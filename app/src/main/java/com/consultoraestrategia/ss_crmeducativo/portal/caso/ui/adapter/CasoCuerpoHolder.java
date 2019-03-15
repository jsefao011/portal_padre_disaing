package com.consultoraestrategia.ss_crmeducativo.portal.caso.ui.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.CasoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.TipoHijoUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.DownloadAdapter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.DownloadItemListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.recylerarchivos)
    RecyclerView recylerarchivos;
    @BindView(R.id.textEmptyArchivo)
    TextView textEmptyArchivo;
    private  DownloadAdapter recursosCasosAdapter;
    private DownloadItemListener downloadItemListener;
    private List<RepositorioFileUi>recursosUIList;

    public CasoCuerpoHolder(View itemView)
    {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public  void bind(CasoUi casoUi,int  position,  DownloadItemListener downloadItemListener){
        this.downloadItemListener=downloadItemListener;
        diaSemana.setText(casoUi.getFechaUi().getDiaSemana());
        dia.setText(String.valueOf(casoUi.getFechaUi().getDia()));
        mes.setText(casoUi.getFechaUi().getMes());
        curso.setText(casoUi.getCursoUi().getNombre());
        descripcion.setText(casoUi.getDescripcion());
        subtipo.setText(casoUi.getTipoHijoUi().getNombre());
        int url=0;
        if(casoUi.getTipoHijoUi().getTipoPadre()== TipoHijoUi.TipoPadre.MERITO){
            linea.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.md_blue_900));
            url= R.drawable.medal;
        }

        else {
            linea.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.md_red_600));
            url= R.drawable.policeman;
        }
        imgTipo.setImageResource(url);
        recylerarchivos.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        recursosCasosAdapter = new DownloadAdapter(downloadItemListener, recylerarchivos);
        recylerarchivos.setAdapter(recursosCasosAdapter);
        recylerarchivos.setHasFixedSize(false);
        recylerarchivos.setNestedScrollingEnabled(false);
        recursosUIList= casoUi.getRepositorioFileUiList();
        if (recursosUIList != null)recursosCasosAdapter.setList(new ArrayList<RepositorioFileUi>(recursosUIList));
        recylerarchivos.setHasFixedSize(true);

        if(casoUi.getRepositorioFileUiList()==null||
                casoUi.getRepositorioFileUiList().isEmpty()){
            recylerarchivos.setVisibility(View.GONE);
            textEmptyArchivo.setVisibility(View.VISIBLE);
        }else {
            recylerarchivos.setVisibility(View.VISIBLE);
            textEmptyArchivo.setVisibility(View.GONE);
        }

    }

    public List<RepositorioFileUi> getRecursosUIList() {
        return recursosUIList;}
    public DownloadAdapter getRecursosCasosAdapter() {
        return recursosCasosAdapter;
    }

}

