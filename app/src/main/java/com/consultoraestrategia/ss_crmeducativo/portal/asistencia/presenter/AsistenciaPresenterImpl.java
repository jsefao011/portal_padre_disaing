package com.consultoraestrategia.ss_crmeducativo.portal.asistencia.presenter;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.Periodo;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.asistenciaColegio.AsistenciaColegioFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.asistenciaColegio.AsistenciaColegioView;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.asistenciaCurso.AsistenciaCursoFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.asistenciaCurso.AsistenciaCursoView;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.ui.AsistenciaFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.ui.AsistenciaView;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.useCase.GetPeriodoList;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.useCase.GetValoresAsistencia;
import com.consultoraestrategia.ss_crmeducativo.portal.wrapper.MainParametrosGlobales;

import java.util.List;

public class AsistenciaPresenterImpl extends BaseFragmentPresenterImpl<AsistenciaView> implements AsistenciaPresenter {

    String TAG=AsistenciaPresenterImpl.class.getSimpleName();
    AsistenciaColegioView asistenciaColegioView;
    AsistenciaCursoView asistenciaCursoView;
    AsistenciaView asistenciaView;
    GetPeriodoList getPeriodoList;
    GetValoresAsistencia getValoresAsistencia;
    private List<PeriodoUi>periodoUiList;
    private List<Object>objectListValores;
    private PeriodoUi periodoUiSelected;
    private int alumnoId;
    private int programaEducativoId;

    public AsistenciaPresenterImpl(UseCaseHandler handler, Resources res, GetPeriodoList getPeriodoList, GetValoresAsistencia getValoresAsistencia) {
        super(handler, res);
        this.getPeriodoList=getPeriodoList;
        this.getValoresAsistencia=getValoresAsistencia;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }
    @Override
    public void onAttach(AsistenciaFragment asistenciaFragment, AsistenciaCursoFragment asistenciaCursoFragment, AsistenciaColegioFragment asistenciaColegioFragment) {
         this.asistenciaView=asistenciaFragment;
         this.asistenciaCursoView=asistenciaCursoFragment;
         this.asistenciaColegioView=asistenciaColegioFragment;
    }

    @Override
    public void onChildsFragmentViewCreated() {
        getValoresAsistencia();
        getPeriodos();


    }
    private void getPeriodos() {
        GetPeriodoList.ResponseValue response= getPeriodoList.execute(new GetPeriodoList.RequestsValue(programaEducativoId));
        periodoUiList=response.getPeriodoUiList();
        Log.d(TAG, "periodoUiList size"+ periodoUiList.size());
        for(PeriodoUi periodoUi: periodoUiList){
            if(periodoUi.isStatus())periodoUiSelected= periodoUi;
        }
        if(asistenciaView!=null)asistenciaView.showListPeriodos(periodoUiList);

    }

    private void getValoresAsistencia() {
     handler.execute(getValoresAsistencia, new GetValoresAsistencia.RequestValues(alumnoId), new UseCase.UseCaseCallback<GetValoresAsistencia.ResponseValue>() {
         @Override
         public void onSuccess(GetValoresAsistencia.ResponseValue response) {
             objectListValores= response.getObjects();
             if(!objectListValores.isEmpty())
                 if(asistenciaView!=null)asistenciaView.showListValorAsistencia(objectListValores);
         }

         @Override
         public void onError() {
         Log.d(TAG, "getValoresAsistencia onError");
         }
     });

    }

    @Override
    public void onDestroyTabAsistenciaCurso() {
        asistenciaCursoView=null;
    }

    @Override
    public void onDestroyTabAsistenciaColegio() {
        asistenciaColegioView=null;
    }

    @Override
    public void onPeriodoSelected(PeriodoUi periodoUi) {
        if(periodoUiSelected!=null)periodoUiSelected.setStatus(false);
        periodoUi.setStatus(true);
        view.changePeriodo(periodoUiSelected, periodoUi);
        periodoUiSelected=periodoUi;
        Log.d(TAG,  "onPeriodoSelected vigente "+ periodoUiSelected.isVigente());

    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        MainParametrosGlobales mainParametrosGlobales = MainParametrosGlobales.clone(extras);
        if(mainParametrosGlobales!=null){
            this.alumnoId=mainParametrosGlobales.getHijo_selected_personaId();
            this.programaEducativoId= mainParametrosGlobales.getHijo_programa_educativo_Id();
            Log.d(TAG, "aLUMNOID "+ alumnoId + "programaEducatvo "+ programaEducativoId);
        }
    }
}
