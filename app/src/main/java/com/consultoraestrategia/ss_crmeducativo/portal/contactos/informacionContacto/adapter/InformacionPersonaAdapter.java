package com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.adapter.holder.InformacionLetraHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.adapter.holder.InformacionPersonaHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.jay.widget.StickyHeaders;

import java.util.List;

public class InformacionPersonaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyHeaders {


    private final int PERSONA = 1, LETRA= 2;
    private List<Object> listObject;

    public InformacionPersonaAdapter(List<Object> listObject) {
        this.listObject = listObject;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType) {
            case PERSONA:
                view = layoutInflater.inflate(R.layout.item_informacion_contactos, parent, false);
                viewHolder = new InformacionPersonaHolder(view);
                break;
            case LETRA:
                view = layoutInflater.inflate(R.layout.item_contacto_letra, parent, false);
                viewHolder = new InformacionLetraHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case PERSONA:
                PersonaUi personaUi = (PersonaUi) listObject.get(position);
                InformacionPersonaHolder contactosCompanerosHolder = (InformacionPersonaHolder) holder;
                contactosCompanerosHolder.bind(personaUi);
                break;
            case LETRA:
                PersonaUi personaUi1 = (PersonaUi) listObject.get(position);
                InformacionLetraHolder letraHolder = (InformacionLetraHolder) holder;
                letraHolder.bind(personaUi1.getNombres());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listObject.size();
    }

    public void setObjetList(List<Object> objects) {
        this.listObject.clear();
        this.listObject.addAll(objects);
        notifyDataSetChanged();
    }


    @Override
    public boolean isStickyHeader(int i) {
        return listObject.get(i) instanceof String;
    }

    @Override
    public int getItemViewType(int position) {
        Object item = listObject.get(position);
        if (item instanceof PersonaUi) {
            PersonaUi personaUi = (PersonaUi) item;
            switch (personaUi.getTipoObjeto()){
                case PERSONA:
                    return PERSONA;
                case LETRA:
                    return LETRA;
                default:
                    return -1;
            }
        }
        else {
            return -1;
        }
    }
}
