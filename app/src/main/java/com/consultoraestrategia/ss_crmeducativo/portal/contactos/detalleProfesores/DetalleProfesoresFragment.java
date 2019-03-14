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
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetalleProfesoresFragment extends Fragment implements DetalleProfesoresView, ViewPagerItemListener<ContactosPresenter> {

    private String TAG= DetalleProfesoresFragment.class.getSimpleName();
    private ContactosPresenter contactosPresenter;

    private Unbinder unbinder;
    @BindView(R.id.rv_profesores)
    FastScrollRecyclerView rv_profesores;
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
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_profesores.setLayoutManager(linearLayoutManager);
        contactosAdapter = new ContactosAdapter(new ArrayList<Object>());
        contactosAdapter.setRecyclerView(rv_profesores);
        rv_profesores.setAdapter(contactosAdapter);
        rv_profesores.setHasFixedSize(true);
    }

    @Override
    public void onAttach(ContactosPresenter presenter) {
        this.contactosPresenter=presenter;
    }

    @Override
    public void showListProfesores(List<Object> objectList) {
        contactosAdapter.setObjetList(objectList);
    }
}
