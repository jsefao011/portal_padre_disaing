package com.consultoraestrategia.ss_crmeducativo.portal.contactos.detalleDirectivos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.base.viewpager.ViewPagerItemListener;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.adapter.ContactosAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.cabecera.ContactosPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.listener.ContactoListener;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.PadreMentorUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.jay.widget.StickyHeadersLinearLayoutManager;
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fastscroll.app.fastscrollalphabetindex.AlphabetIndexFastScrollRecyclerView;

public class DetalleDirectivosFragment extends Fragment implements DetalleDirectivosView, ViewPagerItemListener<ContactosPresenter>, ContactoListener {

    private String TAG= DetalleDirectivosFragment.class.getSimpleName();
    private ContactosPresenter contactosPresenter;

    private Unbinder unbinder;
    @BindView(R.id.rv_directivos)
    AlphabetIndexFastScrollRecyclerView rc_Directivos;
    ContactosAdapter contactosAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_directivos, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new StickyHeadersLinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rc_Directivos.setLayoutManager(layoutManager);
        contactosAdapter = new ContactosAdapter(new ArrayList<Object>(), this);
        contactosAdapter.setRecyclerView(rc_Directivos);
        rc_Directivos.setAdapter(contactosAdapter);
        rc_Directivos.setHasFixedSize(true);
        rc_Directivos.setIndexBarTextColor(R.color.md_blue_grey_20);
        rc_Directivos.setIndexBarColor(R.color.md_blue_grey_05);
        rc_Directivos.setPreviewVisibility(true);
    }

    @Override
    public void onAttach(ContactosPresenter presenter) {
        this.contactosPresenter=presenter;
    }


    @Override
    public void showListDirectivos(List<Object> objectList) {
        contactosAdapter.setObjetList(objectList);
    }

    @Override
    public void callPerson(String number) {

    }


    @Override
    public void sendMessage(String number) {

    }

    @Override
    public void settings(PersonaUi personaUi) {

    }
}
