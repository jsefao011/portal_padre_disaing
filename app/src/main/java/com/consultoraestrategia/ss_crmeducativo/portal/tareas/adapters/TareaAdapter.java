package com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.List;

import butterknife.BindView;

public class TareaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<TareasUi> tareasUiList;


    public TareaAdapter(List<TareasUi> tareasUiList) {
        this.tareasUiList = tareasUiList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TareaHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarea, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TareasUi tareasUi = tareasUiList.get(position);
        ((TareaHolder) holder).bind(tareasUi, position);

    }

    @Override
    public int getItemCount() {
        return tareasUiList.size();

    }

    public void setObjectList(List<TareasUi> tareasUis) {
        this.tareasUiList.clear();
        this.tareasUiList.addAll(tareasUis);
        notifyDataSetChanged();
    }
}
