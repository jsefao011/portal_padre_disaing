package com.consultoraestrategia.ss_crmeducativo.portal.caso.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.CasoUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.DownloadAdapter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.DownloadItemListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CasosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<CasoUi> casoUiList;
    DownloadItemListener downloadItemListener;
    RecyclerView recyclerView;


    public CasosAdapter(List<CasoUi> casoUiList, DownloadItemListener downloadItemListener, RecyclerView recyclerView) {
        this.casoUiList = casoUiList;
        this.downloadItemListener=downloadItemListener;
        this.recyclerView=recyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CasoCuerpoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cuerpo_caso, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CasoUi casoUi = casoUiList.get(position);
        ((CasoCuerpoHolder) holder).bind(casoUi, position, downloadItemListener);

    }

    @Override
    public int getItemCount() {
        return casoUiList.size();
    }

    public void setObjectList(List<CasoUi> casoUiList) {
        this.casoUiList.clear();
        this.casoUiList.addAll(casoUiList);
        notifyDataSetChanged();
    }
    public List<DownloadAdapter> getListDownloadAdapter(RepositorioFileUi repositorioFileUi) {
        List<DownloadAdapter> downloadAdapters = new ArrayList<>();
        try {
            for (int childCount = recyclerView.getChildCount(), i = 0; i < childCount; ++i) {
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(recyclerView.getChildAt(i));
                if(holder instanceof  CasoCuerpoHolder){
                    CasoCuerpoHolder casoCuerpoHolder = (CasoCuerpoHolder) holder;
                    List<RepositorioFileUi> repositorioFileUiList = casoCuerpoHolder.getRecursosUIList();
                    int posicion = repositorioFileUiList.indexOf(repositorioFileUi);
                    if(posicion!=-1){
                        DownloadAdapter downloadAdapter = casoCuerpoHolder.getRecursosCasosAdapter();
                        downloadAdapters.add(downloadAdapter);
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return downloadAdapters;
    }

    public void update(RepositorioFileUi repositorioFileUi){
        for (DownloadAdapter downloadAdapter : getListDownloadAdapter(repositorioFileUi)){
            downloadAdapter.update(repositorioFileUi);
        }
    }

    public void updateProgress(RepositorioFileUi repositorioFileUi, int count) {
        for (DownloadAdapter downloadAdapter : getListDownloadAdapter(repositorioFileUi)){
            downloadAdapter.updateProgress(repositorioFileUi, count);
        }
    }
}
