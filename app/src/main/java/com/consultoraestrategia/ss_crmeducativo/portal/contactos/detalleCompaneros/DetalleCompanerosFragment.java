package com.consultoraestrategia.ss_crmeducativo.portal.contactos.detalleCompaneros;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.jay.widget.StickyHeadersLinearLayoutManager;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fastscroll.app.fastscrollalphabetindex.AlphabetIndexFastScrollRecyclerView;

public class DetalleCompanerosFragment extends Fragment implements DetalleCompanerosView, ViewPagerItemListener<ContactosPresenter>, ContactoListener {

    private String TAG= DetalleCompanerosFragment.class.getSimpleName();
    private ContactosPresenter contactosPresenter;

    private Unbinder unbinder;
    @BindView(R.id.rv_companeros)
    AlphabetIndexFastScrollRecyclerView rv_companeros;
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
        LinearLayoutManager layoutManager = new StickyHeadersLinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_companeros.setLayoutManager(layoutManager);
        contactosAdapter = new ContactosAdapter(new ArrayList<Object>(), this);
        contactosAdapter.setRecyclerView(rv_companeros);
        rv_companeros.setAdapter(contactosAdapter);
        rv_companeros.setIndexBarTextColor(R.color.md_blue_grey_20);
        rv_companeros.setIndexBarColor(R.color.md_blue_grey_05);
        rv_companeros.setPreviewVisibility(true);
    }

    @Override
    public void onAttach(ContactosPresenter presenter) {
        this.contactosPresenter=presenter;
    }


    @Override
    public void showListCompaneros(List<Object> objectList) {
        contactosAdapter.setObjetList(objectList);
    }

    @Override
    public void callPerson(String number) {
//        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+number));
//        if (ActivityCompat.checkSelfPermission(DetalleCompanerosFragment.this, Manifest.permission.CALL_PHONE)!=
//                PackageManager.PERMISSION_GRANTED)
//            return;
//        startActivity(intent);

        Intent i = new Intent(android.content.Intent.ACTION_CALL,
                Uri.parse("tel:992715857"));
        startActivity(i);
    }


    @Override
    public void sendMessage(String number) {

    }

    @Override
    public void settings(PersonaUi personaUi) {
        InformacionContactoFragment informacionContactoFragment = InformacionContactoFragment.newInstance(personaUi.getPersonaId(), 0);
        informacionContactoFragment.setTargetFragment(DetalleCompanerosFragment.this, 200);
        informacionContactoFragment.show(getFragmentManager(), InformacionContactoFragment.class.getSimpleName());
    }
}
