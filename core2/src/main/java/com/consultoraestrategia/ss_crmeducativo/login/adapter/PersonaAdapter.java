package com.consultoraestrategia.ss_crmeducativo.login.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.login.adapter.viewHolder.PersonaViewHolder;
import com.consultoraestrategia.ss_crmeducativo.login.entity.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.login.listener.PersonaListener;

import java.util.List;

/**
 * Created by Jse on 25/09/2018.
 */

public class PersonaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PersonaUi> personaUiList;
    private PersonaListener usuarioListener;
    private boolean elimiarUsuario = false;

    public PersonaAdapter(List<PersonaUi> personaUiList, PersonaListener usuarioListener) {
        this.personaUiList = personaUiList;
        this.usuarioListener = usuarioListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_login_usuario, parent, false);
        return new PersonaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PersonaViewHolder personaViewHolder = (PersonaViewHolder)holder;
        PersonaUi personaUi = personaUiList.get(position);
        personaViewHolder.bind(personaUi, elimiarUsuario,usuarioListener);
    }

    @Override
    public int getItemCount() {
        return personaUiList.size();
    }

    public void setUsuarios(List<PersonaUi> usuarios, boolean elimiarUsuario) {
        this.elimiarUsuario = elimiarUsuario;
        this.personaUiList.clear();
        this.personaUiList.addAll(usuarios);
        notifyDataSetChanged();
    }

    public void removerUsuario(PersonaUi usuarioUi) {
        int posicion = personaUiList.indexOf(usuarioUi);
        if(posicion!=-1){
            personaUiList.remove(usuarioUi);
            notifyDataSetChanged();
        }
    }
}