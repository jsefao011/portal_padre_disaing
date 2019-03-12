package com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareaUiCount;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.List;

import butterknife.BindView;

public class CabeceraAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<TareaUiCount> tareaUiCountList;

    public CabeceraAdapter(List<TareaUiCount> tareaUiCountList) {
        this.tareaUiCountList = tareaUiCountList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CabeceraHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cabecera_lista_tarea, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TareaUiCount tareaUiCount= tareaUiCountList.get(position);
        ((CabeceraHolder)holder).bind(tareaUiCount);
    }

    @Override
    public int getItemCount() {
        return tareaUiCountList.size();
    }

    public void setObjectList(List<TareaUiCount> tareasUiscount) {
        this.tareaUiCountList.clear();
        this.tareaUiCountList.addAll(tareasUiscount);
        notifyDataSetChanged();
    }
}
