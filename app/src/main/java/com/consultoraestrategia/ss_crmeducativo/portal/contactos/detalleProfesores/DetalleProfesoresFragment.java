package com.consultoraestrategia.ss_crmeducativo.portal.contactos.detalleProfesores;

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
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.ui.InformacionContactoFragment;
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

public class DetalleProfesoresFragment extends Fragment implements DetalleProfesoresView, ViewPagerItemListener<ContactosPresenter>, ContactoListener {

    private String TAG= DetalleProfesoresFragment.class.getSimpleName();
    private ContactosPresenter contactosPresenter;

    private Unbinder unbinder;
    @BindView(R.id.rv_profesores)
    AlphabetIndexFastScrollRecyclerView rv_profesores;
    ContactosAdapter contactosAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_profesores, container, false);
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
        rv_profesores.setLayoutManager(layoutManager);
        contactosAdapter = new ContactosAdapter(new ArrayList<Object>(), this);
        contactosAdapter.setRecyclerView(rv_profesores);
        rv_profesores.setAdapter(contactosAdapter);
        rv_profesores.setHasFixedSize(true);
        rv_profesores.setIndexBarTextColor(R.color.md_blue_grey_20);
        rv_profesores.setIndexBarColor(R.color.md_blue_grey_05);
        rv_profesores.setPreviewVisibility(true);
    }

    @Override
    public void onAttach(ContactosPresenter presenter) {
        this.contactosPresenter=presenter;
    }

    @Override
    public void showListProfesores(List<Object> objectList) {
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
        InformacionContactoFragment informacionContactoFragment = InformacionContactoFragment.newInstance(personaUi.getPersonaId(), 1);
        informacionContactoFragment.setTargetFragment(DetalleProfesoresFragment.this, 200);
        informacionContactoFragment.show(getFragmentManager(), InformacionContactoFragment.class.getSimpleName());

    }
}
