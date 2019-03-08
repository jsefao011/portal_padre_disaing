package com.consultoraestrategia.ss_crmeducativo.portal.caso.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.CasoUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.List;

import butterknife.BindView;

public class CasosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<CasoUi> casoUiList;


    public CasosAdapter(List<CasoUi> casoUiList) {
        this.casoUiList = casoUiList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CasoCuerpoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cuerpo_caso, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CasoUi casoUi = casoUiList.get(position);
        ((CasoCuerpoHolder) holder).bind(casoUi, position);

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
}
