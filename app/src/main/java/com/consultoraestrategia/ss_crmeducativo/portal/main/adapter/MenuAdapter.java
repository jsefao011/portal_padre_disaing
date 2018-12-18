package com.consultoraestrategia.ss_crmeducativo.portal.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.consultoraestrategia.ss_crmeducativo.portal.main.adapter.holder.ProgramaViewHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.ItemMenuUI;
import com.consultoraestrategia.ss_crmeducativo.portal.main.listener.MenuListener;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.List;

/**
 * Created by irvinmarin on 02/10/2017.
 */

public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int PROGRAMAEDUCATIVO = 1, ACCESOS = 2, CONFIGURACION = 3;
    private List<Object> objectList;
    private MenuListener listener;

    public MenuAdapter(List<Object> objectList, MenuListener listener) {
        this.objectList = objectList;
        this.listener = listener;

    }

    @NonNull
    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_accesos, parent, false);
        return new ProgramaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProgramaViewHolder programaViewHolder = (ProgramaViewHolder)holder;
        ItemMenuUI itemMenuUI = (ItemMenuUI)objectList.get(position);
        programaViewHolder.bind(itemMenuUI, listener);
    }


    @Override
    public int getItemCount() {
        return objectList.size();
    }


    public void setLista(List<Object> objectList) {
        this.objectList.clear();
        this.objectList.addAll(objectList);
        notifyDataSetChanged();
    }

}
