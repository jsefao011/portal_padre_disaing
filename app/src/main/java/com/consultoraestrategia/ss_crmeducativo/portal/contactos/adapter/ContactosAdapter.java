package com.consultoraestrategia.ss_crmeducativo.portal.contactos.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.adapter.holder.ContactosHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements FastScrollRecyclerView.SectionedAdapter {

    private List<Object> objectList;
    private RecyclerView recyclerView;
    private ArrayList<Integer> mSectionPositions;

    public ContactosAdapter(List<Object> objectList) {
        this.objectList = objectList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactosHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contactos, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
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

    @NonNull
    @Override
    public String getSectionName(int position) {
        PersonaUi personaUi = (PersonaUi)objectList.get(position);
        return String.valueOf(personaUi.getNombres().charAt(0));
    }

//    @Override
//    public Object[] getSections() {
//        List<String> sections = new ArrayList<>();
//        mSectionPositions = new ArrayList<>();
//        for (int i = 0, size = objectList.size(); i < size; i++) {
//            PersonaUi personaUi = (PersonaUi) objectList.get(i);
//
//            String section = String.valueOf(personaUi.getNombres().charAt(0)).toUpperCase();
//            if (!sections.contains(section)) {
//                sections.add(section);
//                mSectionPositions.add(i);
//            }
//        }
//        return sections.toArray(new String[0]);
//    }
//
//    @Override
//    public int getPositionForSection(int i) {
//        return mSectionPositions.get(i);    }
//
//    @Override
//    public int getSectionForPosition(int i) {
//        return 0;
//    }


}