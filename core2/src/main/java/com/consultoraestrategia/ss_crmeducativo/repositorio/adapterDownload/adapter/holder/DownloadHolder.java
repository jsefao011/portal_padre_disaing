package com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.DownloadItemListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioTipoFileU;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class DownloadHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final ProgressBar progressBar2;
    private final ImageView imgFondoprogres;
    private final ImageView imgClose;
    private final CircularProgressBar progressSucces;
    private final ImageView imgDownload;
    private final TextView txtNombreRecurso;
    private final ImageView imgRecurso;
    private final TextView txtdescripcion;
    private RepositorioFileUi repositorioFileUi;
    private DownloadItemListener listener;

    public DownloadHolder(View itemView) {
        super(itemView);
        txtNombreRecurso = (TextView)itemView.findViewById(R.id.txtNombreRecurso);
        imgRecurso = (ImageView)itemView.findViewById(R.id.imgRecurso);
        progressBar2 = (ProgressBar)itemView.findViewById(R.id.progressBar2);
        imgFondoprogres = (ImageView)itemView.findViewById(R.id.img_fondo_progres);
        imgClose = (ImageView)itemView.findViewById(R.id.img_close);
        progressSucces = (CircularProgressBar)itemView.findViewById(R.id.progress_succes);
        imgDownload = (ImageView)itemView.findViewById(R.id.img_download);
        txtdescripcion = (TextView)itemView.findViewById(R.id.txtdescripcion);
        imgDownload.setOnClickListener(this);
        imgClose.setOnClickListener(this);
        itemView.setOnClickListener(this);
    }

    public void bind(RepositorioFileUi repositorioFileUi, DownloadItemListener repositorioItemListener) {
        this.repositorioFileUi = repositorioFileUi;
        this.listener = repositorioItemListener;
        txtNombreRecurso.setText(repositorioFileUi.getNombreRecurso());
        txtdescripcion.setText(repositorioFileUi.getNombreArchivo());
        setupEstado(repositorioFileUi.getEstadoFileU());
        setupIcono(repositorioFileUi.getTipoFileU());
    }


    private void setupEstado(RepositorioEstadoFileU estadoFileU) {
        hideDowload();
        hiProgress();
        hiProgressSuccess();
        switch (estadoFileU){
            case SIN_DESCARGAR:
                showDowload();
                break;
            case CONECTANDO_SERVER:
                showProgress();
                break;
            case ENPROCESO_DESCARGA:
                showProgressSuccess();
                break;
            case ERROR_DESCARGA:
                showDowload();
                break;
            case DESCARGA_COMPLETA:

                break;
        }
    }

    private void setupIcono(RepositorioTipoFileU tipoFileU) {
        switch (tipoFileU){
            case PDF:
                imgRecurso.setImageResource(R.drawable.ext_pdf);
                break;
            case AUDIO:
                imgRecurso.setImageResource(R.drawable.ext_aud);
                break;
            case YOUTUBE:
                imgRecurso.setImageResource(R.drawable.youtube);
                break;
            case VIDEO:
                imgRecurso.setImageResource(R.drawable.ext_vid);
                break;
            case IMAGEN:
                imgRecurso.setImageResource(R.drawable.ext_img);
                break;
            case VINCULO:
                imgRecurso.setImageResource(R.drawable.ext_link);
                break;
            case DOCUMENTO:
                imgRecurso.setImageResource(R.drawable.ext_doc);
                break;
            case DIAPOSITIVA:
                imgRecurso.setImageResource(R.drawable.ext_ppt);
                break;
            case HOJA_CALCULO:
                imgRecurso.setImageResource(R.drawable.ext_xls);
                break;
            case MATERIALES:
                imgRecurso.setImageResource(R.drawable.ext_other);
                break;
        }

    }

    private void showProgress(){
        progressBar2.setVisibility(View.VISIBLE);
        imgClose.setVisibility(View.VISIBLE);
        imgFondoprogres.setVisibility(View.VISIBLE);
    }

    private void hiProgress(){
        progressBar2.setVisibility(View.GONE);
        imgClose.setVisibility(View.GONE);
        imgFondoprogres.setVisibility(View.GONE);
    }

    private void showProgressSuccess(){
        progressSucces.setVisibility(View.VISIBLE);
        imgClose.setVisibility(View.VISIBLE);
        imgFondoprogres.setVisibility(View.VISIBLE);
    }

    private void hiProgressSuccess(){
        progressSucces.setVisibility(View.GONE);
        imgClose.setVisibility(View.GONE);
        imgFondoprogres.setVisibility(View.GONE);
    }

    private void showDowload(){
        imgFondoprogres.setVisibility(View.VISIBLE);
        imgDownload.setVisibility(View.VISIBLE);
    }

    private void hideDowload(){
        imgFondoprogres.setVisibility(View.GONE);
        imgDownload.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
       if(i == R.id.img_close){
            onClickClose();
        }else if(i == R.id.img_download){
            onClickDownload();
        }else{
            onClickArchivo();
        }
    }

    private void onClickArchivo() {
        listener.onClickArchivo(repositorioFileUi);
    }

    private void onClickDownload() {
        repositorioFileUi.setEstadoFileU(RepositorioEstadoFileU.CONECTANDO_SERVER);
        listener.onClickDownload(repositorioFileUi);
        setupEstado(this.repositorioFileUi.getEstadoFileU());
    }

    private void onClickClose() {
        hiProgress();
        showDowload();
        listener.onClickClose(repositorioFileUi);
    }

    public void count(int count) {
        if (progressSucces.getVisibility() == View.GONE) {
            progressSucces.setVisibility(View.VISIBLE);
        }
        if (progressBar2.getVisibility() == View.VISIBLE) {
            progressBar2.setVisibility(View.GONE);
        }
        if (imgFondoprogres.getVisibility() == View.GONE) {
            imgFondoprogres.setVisibility(View.VISIBLE);
        }
        if (imgClose.getVisibility() == View.GONE) {
            imgClose.setVisibility(View.VISIBLE);
        }
        if (imgDownload.getVisibility() == View.VISIBLE) {
            imgDownload.setVisibility(View.GONE);
        }
        progressSucces.setProgress(count);

    }

    public RepositorioFileUi getRepositorioFileUi() {
        return repositorioFileUi;
    }
}