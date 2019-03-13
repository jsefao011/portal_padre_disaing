package com.consultoraestrategia.ss_crmeducativo.portal.contactos.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.portal.contactos.adapter.holder.ContactosHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.List;

public class ContactosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> objectList;
    private RecyclerView recyclerView;

    public ContactosAdapter(List<Object> objectList) {
        this.objectList = objectList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("asfgdsgsdf", "true" + objectList.size() + " " + viewType);
        return new ContactosHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contactos, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("asfgdsgsdf", "true" + objectList.size() + " " + position);
        PersonaUi personaUi = (PersonaUi) objectList.get(position);
        ContactosHolder contactosCompanerosHolder = (ContactosHolder) holder;
        contactosCompanerosHolder.bind(personaUi);
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

    public void updateItem(PersonaUi personaUi) {
        int position = this.objectList.indexOf(personaUi);
        if (position != -1) {
            this.objectList.set(position, personaUi);
            //contactoListener.setListFamilia(objectList);
        }
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

}