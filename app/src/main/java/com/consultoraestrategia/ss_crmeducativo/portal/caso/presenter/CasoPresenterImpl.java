package com.consultoraestrategia.ss_crmeducativo.portal.caso.presenter;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.domain.useCase.GetAlumnoCasos;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.CasoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.TipoPadreUi;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.ui.CasoView;
import com.consultoraestrategia.ss_crmeducativo.portal.wrapper.MainParametrosGlobales;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.List;

public class CasoPresenterImpl extends BaseFragmentPresenterImpl<CasoView>  implements CasoPresenter{

    GetAlumnoCasos getAlumnoCasos;
    private int alumnoId;
    private int programaEducativoId;
    List<CasoUi>casoUiList;
    List<Object>objectList;
    List<TipoPadreUi>tipoPadreUiList;
    String TAG= CasoPresenterImpl.class.getSimpleName();

    public CasoPresenterImpl(UseCaseHandler handler, Resources res,  GetAlumnoCasos getAlumnoCasos) {
        super(handler, res);
        this.getAlumnoCasos=getAlumnoCasos;
    }



    @Override
    protected String getTag() {
        return null;
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
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        MainParametrosGlobales mainParametrosGlobales = MainParametrosGlobales.clone(extras);
        if(mainParametrosGlobales!=null){
            this.alumnoId=mainParametrosGlobales.getHijo_selected_personaId();
            this.programaEducativoId= mainParametrosGlobales.getHijo_programa_educativo_Id();
        }
        getAlumnoCaso();

    }

    private void getAlumnoCaso() {
        if(view!=null)view.showProgress();
        if(programaEducativoId!=0){
            handler.execute(getAlumnoCasos, new GetAlumnoCasos.RequestValues(alumnoId, programaEducativoId), new UseCase.UseCaseCallback<GetAlumnoCasos.ResponseValue>() {
                @Override
                public void onSuccess(GetAlumnoCasos.ResponseValue response) {
                    Log.d(TAG, "casos  "+ response.getAlumnoUi().getCasoUiList().size());
                    Log.d(TAG, "tipos padres  "+  response.getAlumnoUi().getTipoPadreUiList().size());
                    casoUiList=response.getAlumnoUi().getCasoUiList();
                    tipoPadreUiList= response.getAlumnoUi().getTipoPadreUiList();
                    showList();
                }

                @Override
                public void onError() {
                    Log.d(TAG, "Error al listar casos del alumno ");
                }
            });
        }else {if(view!=null){
            view.showTextEmpty(res.getString(R.string.mensaje_programaEduEmpty));
            view.hideProgress();
        }}


    }

    private void showList() {
        if(view!=null)view.hideProgress();
        if(tipoPadreUiList.size()>0){
            if(view!=null) view.showListTipos(tipoPadreUiList.get(0), tipoPadreUiList.get(1));
        }
        if(casoUiList.size()>0) {if(view!=null)view.showListCasos(casoUiList);}
        else {if(view!=null)view.showTextEmpty(res.getString(R.string.mensaje_casosEmpty));}

    }
}
