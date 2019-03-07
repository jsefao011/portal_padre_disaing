package com.consultoraestrategia.ss_crmeducativo.repositorio.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.holder.RepositorioDownloadHolder;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.holder.RepositorioUpdateHolder;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.listener.RepositorioItemListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.listener.RepositorioItemUpdateListener;

import java.util.ArrayList;
import java.util.List;


public class RepositorioAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected int UPDATE = 0, DOWNLOAD = 1;
    protected List<RepositorioFileUi> repositorioFileUiList = new ArrayList<>();
    protected RepositorioItemListener repositorioItemListener;
    protected RepositorioItemUpdateListener repositorioItemUpdateListener;
    protected RecyclerView recyclerView;

    public RepositorioAdapter(RepositorioItemListener repositorioItemListener, RepositorioItemUpdateListener repositorioItemUpdateListener, RecyclerView recyclerView) {
        this.repositorioItemListener = repositorioItemListener;
        this.repositorioItemUpdateListener = repositorioItemUpdateListener;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == UPDATE) {
            return new RepositorioUpdateHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repositorio_update, parent, false));
        } else {
            return new RepositorioDownloadHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repositorio, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == UPDATE) {
            ((RepositorioUpdateHolder) holder).bind((UpdateRepositorioFileUi) repositorioFileUiList.get(position), repositorioItemUpdateListener);
        } else {
            ((RepositorioDownloadHolder) holder).bind(repositorioFileUiList.get(position), repositorioItemListener);
        }

    }

    @Override
    public int getItemCount() {
        return repositorioFileUiList.size();
    }

    @Override
    public int getItemViewType(int position) {
        RepositorioFileUi repositorioFileUi = repositorioFileUiList.get(position);
        if (repositorioFileUi instanceof UpdateRepositorioFileUi) {
            return UPDATE;
        } else {
            return DOWNLOAD;
        }
    }

    public void setList(List<RepositorioFileUi> repositorioFileUiList) {
        Log.d(getClass().getSimpleName(),"setList :" +repositorioFileUiList.size());
        this.repositorioFileUiList.clear();
        this.repositorioFileUiList.addAll(repositorioFileUiList);
        notifyDataSetChanged();
    }

    public void update(RepositorioFileUi repositorioFileUi) {
        int position = repositorioFileUiList.indexOf(repositorioFileUi);
        if (position != -1) {
            repositorioFileUiList.set(position, repositorioFileUi);
            notifyItemChanged(position);
        }
    }

    public synchronized void updateProgress(RepositorioFileUi repositorioFileUi, int count) {
        RepositorioDownloadHolder repositorioHolder = getRepositorioHolder(repositorioFileUi);
        if (repositorioHolder != null) repositorioHolder.count(count);
    }

    private synchronized RepositorioDownloadHolder getRepositorioHolder(RepositorioFileUi repositorioFileUi) {
        RepositorioDownloadHolder repositorioDownloadHolder = null;
        int posicion = this.repositorioFileUiList.indexOf(repositorioFileUi);
        Log.d(getClass().getSimpleName(), "RepositorioHolder posicion: " + posicion + "repositorioFileUi: " + repositorioFileUi.getNombreArchivo());
        if (posicion != -1) {
            repositorioDownloadHolder = (RepositorioDownloadHolder) recyclerView.findViewHolderForLayoutPosition(posicion);
        }
        return repositorioDownloadHolder;
    }

    public void addPostionInitList(List<UpdateRepositorioFileUi> updateRepositorioFileUiList) {
        this.repositorioFileUiList.addAll(0, updateRepositorioFileUiList);
        notifyDataSetChanged();
    }

    public void removeListSinNotificationChange(List<UpdateRepositorioFileUi> updateRepositorioFileUis) {
        this.repositorioFileUiList.removeAll(updateRepositorioFileUis);
    }
}
