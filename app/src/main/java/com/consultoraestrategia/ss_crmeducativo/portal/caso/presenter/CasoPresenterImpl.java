package com.consultoraestrategia.ss_crmeducativo.portal.caso.presenter;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.domain.useCase.GetAlumnoCasos;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.domain.useCase.UpdateSuccesDowloadCasoArchivo;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.CasoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.TipoPadreUi;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.ui.CasoView;
import com.consultoraestrategia.ss_crmeducativo.portal.wrapper.MainParametrosGlobales;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.DowloadImageUseCase;
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
    private DowloadImageUseCase dowloadImageUseCase;
    private UpdateSuccesDowloadCasoArchivo updateSuccesDowloadCasoArchivo;

    public CasoPresenterImpl(UseCaseHandler handler, Resources res,  GetAlumnoCasos getAlumnoCasos,DowloadImageUseCase dowloadImageUseCase, UpdateSuccesDowloadCasoArchivo updateSuccesDowloadCasoArchivo) {
        super(handler, res);
        this.getAlumnoCasos=getAlumnoCasos;
        this.dowloadImageUseCase=dowloadImageUseCase;
        this.updateSuccesDowloadCasoArchivo=updateSuccesDowloadCasoArchivo;
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

    @Override
    public void onClickDownload(final RepositorioFileUi repositorioFileUi) {
        handler.execute(dowloadImageUseCase, new DowloadImageUseCase.RequestValues(repositorioFileUi),
                new UseCase.UseCaseCallback<UseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(UseCase.ResponseValue response) {
                        if(response instanceof DowloadImageUseCase.ResponseProgressValue){
                            DowloadImageUseCase.ResponseProgressValue responseProgressValue = (DowloadImageUseCase.ResponseProgressValue) response;
                            view.setUpdateProgress(responseProgressValue.getRepositorioFileUi(), responseProgressValue.getCount());
                            Log.d(TAG,":( :" + repositorioFileUi.getNombreArchivo() +" = " + responseProgressValue.getRepositorioFileUi().getNombreArchivo());
                        }
                        if(response instanceof DowloadImageUseCase.ResponseSuccessValue){
                            final DowloadImageUseCase.ResponseSuccessValue responseValue = (DowloadImageUseCase.ResponseSuccessValue) response;
                            saveRegistorRecursos(repositorioFileUi, new UseCaseSincrono.Callback<Boolean>() {
                                @Override
                                public void onResponse(boolean success, Boolean value) {
                                    if(!success){
                                        responseValue.getRepositorioFileUi().setEstadoFileU(RepositorioEstadoFileU.ERROR_DESCARGA);
                                        Log.d(TAG,"error al actualizar archivoId: " + repositorioFileUi.getArchivoId()+ " con el pathLocal:" + responseValue.getRepositorioFileUi().getPath());
                                    }
                                    view.setUpdate(responseValue.getRepositorioFileUi());
                                }
                            });
                            Log.d(TAG,"pathLocal:" + responseValue.getRepositorioFileUi().getPath());
                        }
                        if(response instanceof DowloadImageUseCase.ResponseErrorValue){
                            DowloadImageUseCase.ResponseErrorValue responseErrorValue = (DowloadImageUseCase.ResponseErrorValue) response;
                            view.setUpdate(responseErrorValue.getRepositorioFileUi());
                        }
                    }

                    @Override
                    public void onError() {

                    }
                }
        );
    }

    private void saveRegistorRecursos(RepositorioFileUi repositorioFileUi, UseCaseSincrono.Callback<Boolean> booleanCallback) {
        updateSuccesDowloadCasoArchivo.execute(new UpdateSuccesDowloadCasoArchivo.Request(repositorioFileUi.getArchivoId(), repositorioFileUi.getPath()), booleanCallback);
    }

    @Override
    public void onClickClose(RepositorioFileUi repositorioFileUi) {
        repositorioFileUi.setCancel(true);
    }

    @Override
    public void onClickArchivo(RepositorioFileUi repositorioFileUi) {
        if(repositorioFileUi.getEstadoFileU()== RepositorioEstadoFileU.DESCARGA_COMPLETA){
            if(view!=null)view.leerArchivo(repositorioFileUi.getPath());
        }
    }
}
