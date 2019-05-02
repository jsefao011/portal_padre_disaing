package com.consultoraestrategia.ss_crmeducativo.portal.eventos.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.portal.eventos.adapter.holder.EventosHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.eventos.entities.EventosUi;
import com.consultoraestrategia.ss_crmeducativo.portal.eventos.listener.EventosListener;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.ArrayList;
import java.util.List;

public class EventosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Object> objectList;
    private EventosListener eventosListener;


    public EventosAdapter(List<Object> objects, EventosListener eventosListener) {
        this.objectList = objects;
        this.eventosListener = eventosListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_colegio_eventos, parent, false);
        viewHolder = new EventosHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EventosUi eventosUi = (EventosUi) objectList.get(position);
        EventosHolder eventosHolder = (EventosHolder) holder;
        eventosHolder.bind(eventosUi);
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    public void setRecyclerView(RecyclerView eventosRv) {
    }

    public void setObjetList(List<Object> objects) {
        this.objectList.clear();
        this.objectList.addAll(objects);
        notifyDataSetChanged();
    }
}
