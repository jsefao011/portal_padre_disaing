package com.consultoraestrategia.ss_crmeducativo.portal.contactos.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SectionIndexer;

import com.consultoraestrategia.ss_crmeducativo.portal.contactos.adapter.holder.ContactosHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.listener.ContactoListener;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.jay.widget.StickyHeaders;

import java.util.ArrayList;
import java.util.List;

public class ContactosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements SectionIndexer, StickyHeaders {

    private final int PERSONA = 1, LETRA= 2;

    private List<Object> objectList;
    private RecyclerView recyclerView;
    private ArrayList<Integer> mSectionPositions;
    private ContactoListener contactoListener;

    public ContactosAdapter(List<Object> objectList, ContactoListener contactoListener) {
        this.objectList = objectList;
        this.contactoListener = contactoListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType) {
            case PERSONA:
                view = layoutInflater.inflate(R.layout.item_contactos, parent, false);
                viewHolder = new ContactosHolder(view);
                break;
            case LETRA:
                view = layoutInflater.inflate(R.layout.item_contacto_letra, parent, false);
                viewHolder = new LetraHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case PERSONA:
                PersonaUi personaUi = (PersonaUi) objectList.get(position);
                ContactosHolder contactosCompanerosHolder = (ContactosHolder) holder;
                contactosCompanerosHolder.bind(personaUi, contactoListener);
                break;
            case LETRA:
                PersonaUi personaUi1 = (PersonaUi) objectList.get(position);
                LetraHolder letraHolder = (LetraHolder) holder;
                letraHolder.bind(personaUi1.getNombres());
                break;
        }
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

//    @NonNull
//    @Override
//    public String getSectionName(int position) {
//        PersonaUi personaUi = (PersonaUi)objectList.get(position);
//        return String.valueOf(personaUi.getNombres().charAt(0));
//    }



    @Override
    public Object[] getSections() {
        List<String> sections = new ArrayList<>();
            mSectionPositions = new ArrayList<>();
            for (int i = 0, size = objectList.size(); i < size; i++) {
                PersonaUi personaUi = (PersonaUi) objectList.get(i);
                //String letra = (String) objectLista.get(i);

                String section = String.valueOf(personaUi.getNombres().charAt(0)).toUpperCase();
                if (!sections.contains(section)) {
                    sections.add(section);
                    mSectionPositions.add(i);
                }
            }
        return sections.toArray(new String[0]);

    }

    @Override
    public int getPositionForSection(int i) {
        return mSectionPositions.get(i);    }

    @Override
    public int getSectionForPosition(int i) {
        return 0;
    }


    @Override
    public boolean isStickyHeader(int i) {
        return objectList.get(i) instanceof String;
    }

    @Override
    public int getItemViewType(int position) {
        Object item = objectList.get(position);
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