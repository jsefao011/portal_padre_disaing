package com.consultoraestrategia.ss_crmeducativo.portal.programahorario.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.ProgramaHorarioPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.adapter.CursoHorarioAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.adapter.ProgramaHorarioAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.adapter.ProgramaHorarioTableAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.DiaUi;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.HoraUi;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.ProgramaHorarioUi;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.listener.ProgramaHorarioListener;
import com.consultoraestrategia.ss_crmeducativo.util.linePagerIndicatorDecoration.LinePagerIndicatorDecoration;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.evrencoskun.tableview.TableView;

import java.util.ArrayList;
import java.util.List;

public  abstract class ProgramaHorarioActivity extends BaseFragment<ProgramaHorarioView, ProgramaHorarioPresenter, ProgramaHorarioListener> implements ProgramaHorarioView, View.OnClickListener, ProgramaHorarioAdapter.ProgramaHorarioListener {


    private ProgramaHorarioTableAdapter adapter;
    private ProgramaHorarioAdapter programaHorAdapter;
    private CursoHorarioAdapter cursoHorarioAdapter;

    protected abstract RecyclerView getRcCurso();
    protected abstract RecyclerView getRcProgramaHorario();
    protected abstract TableView getHorario();
    protected abstract ProgressBar getProgress();



    @Override
    protected String getLogTag() {
        return  ProgramaHorarioActivity.class.getSimpleName();
    }


    @Override
    protected ProgramaHorarioView getBaseView() {
        return this;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapter();
    }

    private void setupAdapter() {
        programaHorAdapter = new ProgramaHorarioAdapter(new ArrayList<ProgramaHorarioUi>(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        getRcProgramaHorario().setLayoutManager(linearLayoutManager);
        getRcProgramaHorario().setAdapter(programaHorAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4, GridLayoutManager.HORIZONTAL, false);
        getRcCurso().setLayoutManager(gridLayoutManager);
        cursoHorarioAdapter = new CursoHorarioAdapter(new ArrayList<CursoUi>());
        getRcCurso().setAdapter(cursoHorarioAdapter);

        // add pager behavior
        /*PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(getRcCurso());*/
// add a background color to the recyclerview
        LinePagerIndicatorDecoration linePagerIndicatorDecoration = new LinePagerIndicatorDecoration();
        linePagerIndicatorDecoration.setColorActive(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        linePagerIndicatorDecoration.setColorInactive(ContextCompat.getColor(getContext(), R.color.md_blue_grey_20));
        getRcCurso().addItemDecoration(linePagerIndicatorDecoration);

        adapter = new ProgramaHorarioTableAdapter(getContext(), this);
        getHorario().setAdapter(adapter);
        getHorario().setIgnoreSelectionColors(false);
        getHorario().setHasFixedWidth(true);
        getHorario().setIgnoreSelectionColors(true);




    }

    @Override
    protected ProgressBar getProgressBar() {
        return getProgress();
    }

    @Override
    public void showHorario(List<DiaUi> columna, List<HoraUi> fila, List<List<Object>> celdaHorarioUiListList) {
        Log.d(getTag(), "showTableview");

        adapter.setAllItems(columna, fila, celdaHorarioUiListList);

        getHorario().postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.postShowHorario();
            }
        },1000);

    }

    @Override
    public void showListaProgramaEducativo(List<ProgramaHorarioUi> programaHorarioUiList) {
        programaHorAdapter.setList(programaHorarioUiList);
    }

    @Override
    public void showCurso(List<CursoUi> cursoUiList) {
        cursoHorarioAdapter.setList(cursoUiList);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onClick(ProgramaHorarioUi programaHorarioUi) {
        presenter.ProgramaHorarioUi(programaHorarioUi);
    }


    @Override
    public void onDestroyView() {
        getHorario().removeAllViews();
        super.onDestroyView();
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }
}
