package com.consultoraestrategia.ss_crmeducativo.portal.contactos.detalleCompaneros;

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

public class DetalleCompanerosFragment extends Fragment implements DetalleCompanerosView, ViewPagerItemListener<ContactosPresenter> {

    private String TAG= DetalleCompanerosFragment.class.getSimpleName();
    private ContactosPresenter contactosPresenter;

    private Unbinder unbinder;
    @BindView(R.id.rv_companeros)
    RecyclerView rv_companeros;
    ContactosAdapter contactosAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_companeros, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_companeros.setLayoutManager(layoutManager);
        contactosAdapter = new ContactosAdapter(new ArrayList<Object>());
        contactosAdapter.setRecyclerView(rv_companeros);
        rv_companeros.setAdapter(contactosAdapter);
    }

    @Override
    public void onAttach(ContactosPresenter presenter) {
        this.contactosPresenter=presenter;
    }


    @Override
    public void showListCompaneros(List<Object> objectList) {
        contactosAdapter.setObjetList(objectList);
    }

}
