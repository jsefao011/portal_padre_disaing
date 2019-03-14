package com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters.holders.TareaHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.List;

import butterknife.BindView;

public class TareaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<TareasUi> tareasUiList;
    String parametroDisenio;

    public TareaAdapter(List<TareasUi> tareasUiList, String parametroDisenio) {
        this.tareasUiList = tareasUiList;
        this.parametroDisenio = parametroDisenio;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TareaHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarea, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d(getClass().getSimpleName(), "item tarea ");
        TareasUi tareasUi = tareasUiList.get(position);
        ((TareaHolder) holder).bind(tareasUi, position, parametroDisenio);

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
