package com.consultoraestrategia.ss_crmeducativo.portal.eventos.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.eventos.EventosPresent;
import com.consultoraestrategia.ss_crmeducativo.portal.eventos.EventosPresentImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.eventos.adapter.EventosAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.eventos.buscarEventos.ui.BuscarEventosDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.eventos.entities.EventosUi;
import com.consultoraestrategia.ss_crmeducativo.portal.eventos.listener.EventosListener;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class EventosFragment extends BaseFragment<EventosView, EventosPresent, EventosListener> implements EventosView, EventosListener{

    @BindView(R.id.eventosRv)
    RecyclerView eventosRv;
    EventosAdapter eventosAdapter;
    List<Object> objectList = new ArrayList<>();


    @Override
    protected String getLogTag() {
        return EventosFragment.class.getSimpleName();
    }

    @Override
    protected EventosPresent getPresenter() {
        presenter = new EventosPresentImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
        return presenter;
    }

    @Override
    protected EventosView getBaseView() {
        return this;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setData();
        setUpAdapter();
        setHasOptionsMenu(true);
        showListEventos(new ArrayList<Object>());
    }


    private void setUpAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        eventosRv.setLayoutManager(layoutManager);
        eventosAdapter = new EventosAdapter(new ArrayList<Object>(), this);
        eventosAdapter.setRecyclerView(eventosRv);
        eventosRv.setAdapter(eventosAdapter);
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_colegio_eventos, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }



    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected ViewGroup getRootLayout() {
        return null;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }

    @Override
    public void showListEventos(List<Object> objects) {
        EventosUi eventosUi = new EventosUi("Guillero Mamani", "Primer Concurso de Gimnasia Artistica, Fecha de Inscripcion 24/04 9:00 am, costo S/. 20", R.drawable.imagen2, EventosUi.Tipo.PUBLIC, "02 de Abril, 2019");
        EventosUi eventosUi1 = new EventosUi("Daniel Rosas", "Desfile intitucional por grados academicos, comenzara la convocatoria el dia Jueves 29 de Marzo", R.drawable.imagen3, EventosUi.Tipo.PRIVATE, "02 de Abril, 2019");
        EventosUi eventosUi2 = new EventosUi("Luisa Valdivia", "Examen de Recuperacion, para los alumnos que desaprobaron el 03 Examen Fisica, se realizara un examen que sustuira la nota del anterios examen, tendran un tiempo maximo de 1 hora", 0, EventosUi.Tipo.PRIVATE, "02 de Abril, 2019");
        EventosUi eventosUi3 = new EventosUi("Basilio Quispe", "Comunicado a los padres de Familia, la segunda armada se vence en dos dias, para los padres que necesitan un prorogra acercarse a caja del colegio.", 0, EventosUi.Tipo.PUBLIC, "02 de Abril, 2019");
        EventosUi eventosUi4 = new EventosUi("Guillero Mamani", "Aniversario del Colegio Union, Se comunica que los alumnos que pertenecen a la orquesta de banda sinfonica preveer el almuerzo debido que ese dia estaran todo el dia en el colegio practicando para el aniversario", R.drawable.imagen1, EventosUi.Tipo.PUBLIC, "02 de Abril, 2019");
        objectList.add(eventosUi);
        objectList.add(eventosUi1);
        objectList.add(eventosUi2);
        objectList.add(eventosUi3);
        objectList.add(eventosUi4);
        eventosAdapter.setObjetList(objectList);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_eventos, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_buscar:
                    initBuscar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initBuscar() {
        BuscarEventosDialogFragment buscarEventosDialogFragment = BuscarEventosDialogFragment.newInstance();
        buscarEventosDialogFragment.setTargetFragment(EventosFragment.this, 200);
        buscarEventosDialogFragment.show(getFragmentManager(), BuscarEventosDialogFragment.class.getSimpleName());
    }


}
