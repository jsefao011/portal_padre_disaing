package com.consultoraestrategia.ss_crmeducativo.portal.familia.adaper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.adaper.holder.FamiliaHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.List;

public class FamiliaAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> objectList;


    public FamiliaAdapter(List<Object> contactosList) {
        this.objectList = contactosList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        view = layoutInflater.inflate(R.layout.item_familia, parent, false);
        viewHolder = new FamiliaHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PersonaUi personaUi = (PersonaUi) objectList.get(position);
        FamiliaHolder itemFamiliaHolder = (FamiliaHolder) holder;
        itemFamiliaHolder.bind(personaUi);
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    public void setObjetList(List<Object> objects) {
        this.objectList.clear();
        this.objectList.addAll(objects);
        notifyDataSetChanged();
    }
}