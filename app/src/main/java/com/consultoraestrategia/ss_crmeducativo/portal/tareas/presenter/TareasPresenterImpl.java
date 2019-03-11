package com.consultoraestrategia.ss_crmeducativo.portal.tareas.presenter;




import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.TareaView;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.TareasFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareaUiCount;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasCurso.FragmentTareasCurso;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasCurso.TareaCursoView;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasGenerales.FragmentTareasGenerales;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasGenerales.TareaGeneralesView;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.useCase.GetTareasGeneralesAlumno;
import com.consultoraestrategia.ss_crmeducativo.portal.wrapper.MainParametrosGlobales;

import java.util.ArrayList;
import java.util.List;


public class TareasPresenterImpl extends BaseFragmentPresenterImpl<TareaView > implements TareasPresenter {
    
    TareaView tareasView;
    TareaCursoView tareaCursoView;
    TareaGeneralesView tareaGeneralesView;
    GetTareasGeneralesAlumno getTareasGeneralesAlumno;
    private int idAlumno;

    private String TAG=TareasPresenterImpl.class.getSimpleName();

    public TareasPresenterImpl(UseCaseHandler handler, Resources res, GetTareasGeneralesAlumno getTareasGeneralesAlumno) {
        super(handler, res);
        this.getTareasGeneralesAlumno=getTareasGeneralesAlumno;
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
         getTareasGenerales();
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
            tareaGeneralesView.setListTareasList(tareasUis);
            tareaGeneralesView.setTareaCountList(tareasCountUis);
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
