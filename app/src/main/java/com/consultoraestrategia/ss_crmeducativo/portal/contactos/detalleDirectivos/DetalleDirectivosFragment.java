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
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetalleDirectivosFragment extends Fragment implements DetalleDirectivosView, ViewPagerItemListener<ContactosPresenter> {

    private String TAG= DetalleDirectivosFragment.class.getSimpleName();
    private ContactosPresenter contactosPresenter;

    private Unbinder unbinder;
    @BindView(R.id.rv_directivos)
    RecyclerView rc_Directivos;
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
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rc_Directivos.setLayoutManager(linearLayoutManager);
        contactosAdapter = new ContactosAdapter(new ArrayList<Object>());
        contactosAdapter.setRecyclerView(rc_Directivos);
        rc_Directivos.setAdapter(contactosAdapter);
        rc_Directivos.setHasFixedSize(true);
    }

    @Override
    public void onAttach(ContactosPresenter presenter) {
        this.contactosPresenter=presenter;
    }


    @Override
    public void showListDirectivos(List<Object> objectList) {
        contactosAdapter.setObjetList(objectList);
    }
}
