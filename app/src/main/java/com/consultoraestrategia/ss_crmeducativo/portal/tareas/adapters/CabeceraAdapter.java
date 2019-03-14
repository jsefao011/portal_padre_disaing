package com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters.holders.CabeceraHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareaUiCount;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.List;

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
