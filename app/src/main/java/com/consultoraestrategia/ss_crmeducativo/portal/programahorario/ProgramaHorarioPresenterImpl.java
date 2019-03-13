package com.consultoraestrategia.ss_crmeducativo.portal.programahorario;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.domain.usecase.GetProgramaHorarioCompleto;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.domain.usecase.GetReporteHorario;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.DiaUi;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.HoraUi;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.ProgramaHorarioUi;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.ui.ProgramaHorarioView;
import com.consultoraestrategia.ss_crmeducativo.portal.wrapper.MainParametrosGlobales;

import java.util.ArrayList;
import java.util.List;

public abstract class ProgramaHorarioPresenterImpl extends BaseFragmentPresenterImpl<ProgramaHorarioView> implements ProgramaHorarioPresenter {
    private GetProgramaHorarioCompleto getProgramaHorarioCompleto;
    private GetReporteHorario getReporteHorario;
    protected int hijoPersonaId;
    protected int programaEducativoId;
    protected List<ProgramaHorarioUi> programaHorarioUiList = new ArrayList<>();
    protected List<CursoUi> cursoUiList = new ArrayList<>();
    protected ProgramaHorarioUi programaHorarioUi;

    public interface Callback<T>{
        void onLoad(boolean success, T item);
    }

    public ProgramaHorarioPresenterImpl(UseCaseHandler handler, Resources res, GetProgramaHorarioCompleto getProgramaHorarioCompleto, GetReporteHorario getReporteHorario) {
        super(handler, res);
        this.getProgramaHorarioCompleto = getProgramaHorarioCompleto;
        this.getReporteHorario = getReporteHorario;
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        MainParametrosGlobales crmBundle = MainParametrosGlobales.clone(extras);
        if(crmBundle!=null){
            programaEducativoId = crmBundle.getHijo_programa_educativo_Id();
            hijoPersonaId = crmBundle.getHijo_selected_personaId();
        }
    }

    @Override
    protected String getTag() {
        return ProgramaHorarioPresenterImpl.class.getSimpleName();
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }


    @Override
    public void onResume() {
        super.onResume();
        listarCurso(new Callback<List<CursoUi>>() {
            @Override
            public void onLoad(boolean success, List<CursoUi> item) {
                if(success){
                    cursoUiList.clear();
                    cursoUiList.addAll(item);
                    if(view!=null)view.showCurso(cursoUiList);
                    listarProgramaHorario();
                }else {
                    showMessage("Error en los cursos");
                }

            }
        });


        showProgress();
    }

    protected abstract void listarCurso(Callback<List<CursoUi>> callback);


    private void listarProgramaHorario() {
        Log.d(getTag(), "programaEducativoId: " + programaEducativoId);
        handler.execute(getProgramaHorarioCompleto, new GetProgramaHorarioCompleto.RequestValue(programaEducativoId),
                new UseCase.UseCaseCallback<GetProgramaHorarioCompleto.ResponseValue>() {
                    @Override
                    public void onSuccess(GetProgramaHorarioCompleto.ResponseValue response) {
                        programaHorarioUiList.clear();
                        programaHorarioUiList.addAll(response.getProgramaHorarioUiList());
                        if(!programaHorarioUiList.isEmpty()){
                            programaHorarioUi = programaHorarioUiList.get(0);
                            programaHorarioUi.setSelect(true);
                        }
                        if(view!=null)view.showListaProgramaEducativo(programaHorarioUiList);
                        listarHoraio();
                    }

                    @Override
                    public void onError() {
                        showMessage("Error obtener horario programa");
                    }
                });


    }

    private void listarHoraio() {
        handler.execute(getReporteHorario,
                new GetReporteHorario.RequestValues(programaHorarioUiList, programaHorarioUi, cursoUiList),
                new UseCase.UseCaseCallback<GetReporteHorario.ResponseValue>() {
                    @Override
                    public void onSuccess(GetReporteHorario.ResponseValue response) {
                        List<HoraUi> fila = new ArrayList<>(response.getHoraUiList());
                        List<DiaUi> diaUiList = new ArrayList<>(response.getDiaUiList());
                        List<List<Object>> lists = new ArrayList<>(response.getLists());
                        if(view!=null)view.showHorario(diaUiList, fila, lists);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public void ProgramaHorarioUi(ProgramaHorarioUi programaHorarioUi) {
        showProgress();
        if(this.programaHorarioUi!=null)this.programaHorarioUi.setSelect(false);
        programaHorarioUi.setSelect(true);
        this.programaHorarioUi = programaHorarioUi;
        if(view!=null)view.showListaProgramaEducativo(programaHorarioUiList);
        listarHoraio();
    }

    @Override
    public void postShowHorario() {
        hideProgress();
    }
}
