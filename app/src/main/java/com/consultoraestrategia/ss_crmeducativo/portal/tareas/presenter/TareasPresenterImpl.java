package com.consultoraestrategia.ss_crmeducativo.portal.tareas.presenter;




import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.TareaView;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.TareasFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareaUiCount;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasCurso.FragmentTareasCurso;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasCurso.TareaCursoView;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasGenerales.FragmentTareasGenerales;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasGenerales.TareaGeneralesView;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.useCase.GetTareasGeneralesAlumno;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.useCase.GetTareasPorCurso;
import com.consultoraestrategia.ss_crmeducativo.portal.wrapper.MainParametrosGlobales;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.ArrayList;
import java.util.List;


public class TareasPresenterImpl extends BaseFragmentPresenterImpl<TareaView > implements TareasPresenter {
    
    TareaView tareasView;
    TareaCursoView tareaCursoView;
    TareaGeneralesView tareaGeneralesView;
    GetTareasGeneralesAlumno getTareasGeneralesAlumno;
    GetTareasPorCurso getTareasPorCurso;
    private int idAlumno;

    private String TAG=TareasPresenterImpl.class.getSimpleName();

    public TareasPresenterImpl(UseCaseHandler handler, Resources res, GetTareasGeneralesAlumno getTareasGeneralesAlumno, GetTareasPorCurso getTareasPorCurso) {
        super(handler, res);
        this.getTareasGeneralesAlumno=getTareasGeneralesAlumno;
        this.getTareasPorCurso=getTareasPorCurso;
    }



    @Override
    public void onPageChanged(int position) {


    }

    @Override
    public void onAttach(TareasFragment tareasFragment, FragmentTareasCurso fragmentTareasCurso, FragmentTareasGenerales fragmentTareasGenerales) {
        tareasView= tareasFragment;
        tareaCursoView=fragmentTareasCurso;
        tareaGeneralesView=fragmentTareasGenerales;
    }

    @Override
    public void onChildsFragmentViewCreated() {
        getcursos();
        getTareasGenerales();
    }

    @Override
    public void onDestroyTabTareaCurso() {
       tareaCursoView=null;
    }

    @Override
    public void onDestroyTabTareaGeneral() {
        tareaGeneralesView=null;
    }

    private void getcursos() {

        handler.execute(getTareasPorCurso, new GetTareasPorCurso.RequestValues(idAlumno), new UseCase.UseCaseCallback<GetTareasPorCurso.ResponseValue>() {
            @Override
            public void onSuccess(GetTareasPorCurso.ResponseValue response) {
                Log.d(TAG, "getTareasPorCurso onSuccess"+ response.getCursoUiList().size());
                showListCursoTareas(response.getCursoUiList());
            }

            @Override
            public void onError() {
                Log.d(TAG, "getTareasGenerales onError");
            }
        });
    }

    private void showListCursoTareas(List<CursoUi> cursoUiList) {
         if(tareaCursoView!=null){
             tareaCursoView.hideProgressBar();
             if(cursoUiList.size()>0)tareaCursoView.setTareasCurso(cursoUiList);
             else tareaCursoView.showTextEmpty();
         }

    }

    private void getTareasGenerales() {

        handler.execute(getTareasGeneralesAlumno, new GetTareasGeneralesAlumno.RequestValues(idAlumno), new UseCase.UseCaseCallback<GetTareasGeneralesAlumno.ResponseValue>() {
            @Override
            public void onSuccess(GetTareasGeneralesAlumno.ResponseValue response) {
                Log.d(TAG, "getTareasGenerales onSuccess"+ response.getObjectList().size());
                showListGenerales( response.getObjectList());
            }

            @Override
            public void onError() {
                Log.d(TAG, "getTareasGenerales onError");
            }
        });

    }

    private void showListGenerales(List<Object> objectList) {
        List<TareasUi> tareasUis = new ArrayList<>();
        List<TareaUiCount> tareasCountUis = new ArrayList<>();
        for(Object object:objectList){
            if(object instanceof TareasUi) tareasUis.add((TareasUi)object);
            else tareasCountUis.add((TareaUiCount)object);
        }
        if(tareaGeneralesView!=null){
            tareaGeneralesView.hideProgressBar();
            if(tareasUis.size()>0){
                tareaGeneralesView.setListTareasList(tareasUis);
                tareaGeneralesView.setTareaCountList(tareasCountUis);
            } else tareaGeneralesView.showText();
        }
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
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        MainParametrosGlobales mainParametrosGlobales = MainParametrosGlobales.clone(extras);
        if(mainParametrosGlobales!=null)this.idAlumno=mainParametrosGlobales.getHijo_selected_personaId();

    }
}
