package com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters.holders.TareaHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.presenter.TareaListener;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.List;

public class TareaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<TareasUi> tareasUiList;
    String parametroDisenio;
    TareaListener tareaListener;

    public TareaAdapter(List<TareasUi> tareasUiList, String parametroDisenio, TareaListener tareaListener) {
        this.tareasUiList = tareasUiList;
        this.parametroDisenio = parametroDisenio;
        this.tareaListener=tareaListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     return new TareaHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarea, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
      TareasUi tareasUi = tareasUiList.get(position);
      ((TareaHolder) holder).bind(tareasUi, position, parametroDisenio, tareaListener);

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

    public void setparametroDisenio(String parametroDisenio) {
        this.parametroDisenio = parametroDisenio;
    }


}
