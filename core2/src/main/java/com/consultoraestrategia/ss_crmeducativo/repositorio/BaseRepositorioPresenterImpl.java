package com.consultoraestrategia.ss_crmeducativo.repositorio;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.repositorio.bundle.RepositorioTBunble;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.DownloadCancelUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.FragmentoTipo;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioTipoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.ConvertirPathRepositorioUpload;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.DowloadImageUseCase;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.GetUrlRepositorioArchivo;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.UpdateRepositorioDowload;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.UploadRepositorio;
import com.consultoraestrategia.ss_crmeducativo.util.IdGenerator;
import com.consultoraestrategia.ss_crmeducativo.util.YouTubeHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BaseRepositorioPresenterImpl extends BaseFragmentPresenterImpl<RepositorioView> implements RepositorioPresenter {
    private String TAG = BaseRepositorioPresenterImpl.class.getSimpleName();
    private List<UpdateRepositorioFileUi> updateRepositorioFileUiList = new ArrayList<>();
    private List<RepositorioFileUi> repositorioFileUiList = new ArrayList<>();
    private DowloadImageUseCase dowloadImageUseCase;
    private ConvertirPathRepositorioUpload convertirPathRepositorioUpload;
    private UploadRepositorio uploadRepositorio;
    private GetUrlRepositorioArchivo getUrlRepositorioArchivo;
    private UpdateRepositorioDowload updateRepositorioDowload;
    private int cantidadMaxima = 5;
    private RepositorioUi repositorio = RepositorioUi.ARCHIVO;
    private String urlServidor;
    private FragmentoTipo fragmentoTipo = FragmentoTipo.SUBIDA_DESCARGA_IMAGEN;


    public BaseRepositorioPresenterImpl(UseCaseHandler handler, Resources res,
                                        DowloadImageUseCase dowloadImageUseCase,
                                        ConvertirPathRepositorioUpload convertirPathRepositorioUpload,
                                        UploadRepositorio uploadRepositorioJustificacion,
                                        GetUrlRepositorioArchivo getUrlRepositorioArchivo,
                                        UpdateRepositorioDowload updateRepositorioDowload) {
        super(handler, res);
        this.dowloadImageUseCase = dowloadImageUseCase;
        this.convertirPathRepositorioUpload = convertirPathRepositorioUpload;
        this.uploadRepositorio = uploadRepositorioJustificacion;
        this.getUrlRepositorioArchivo = getUrlRepositorioArchivo;
        this.updateRepositorioDowload = updateRepositorioDowload;
    }

    @Override
    protected String getTag() {
        return getClass().getSimpleName();
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {
    }

    @Override
    public void onCreateView() {
        super.onCreateView();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        showListArchivos(repositorioFileUiList);
    }

    private void showListArchivos(List<RepositorioFileUi> repositorioFileUiList) {
        try {
            Collections.sort(repositorioFileUiList, new Comparator<RepositorioFileUi>() {
                @Override
                public int compare(RepositorioFileUi o1, RepositorioFileUi o2) {
                    Long obj1 =  o1.getFechaCreacionArchivo();
                    Long obj2 =  o2.getFechaCreacionArchivo();

                    int compare =obj2.compareTo(obj1);
                    if(compare > 0){
                        if(!o1.isSelect()&&o2.isSelect()){
                            return -1;
                        }else {
                            return 1;
                        }
                    }else if(compare == 0){
                        if(!o2.isSelect()&&o1.isSelect()){
                            return -1;
                        }else if(o2.isSelect()&&o1.isSelect()){
                            return 0;
                        }else {
                            return 1;
                        }
                    }else {
                        if(o2.isSelect()&&!o1.isSelect()){
                            return 1;
                        }else {
                            return -1;
                        }
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        if(view!=null)view.showListArchivos(repositorioFileUiList);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClickDownload(final RepositorioFileUi repositorioFileUi) {

        handler.execute(dowloadImageUseCase, new DowloadImageUseCase.RequestValues(repositorioFileUi),
                new UseCase.UseCaseCallback<UseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(UseCase.ResponseValue response) {
                        if(response instanceof DowloadImageUseCase.ResponseProgressValue){
                            DowloadImageUseCase.ResponseProgressValue responseProgressValue = (DowloadImageUseCase.ResponseProgressValue) response;
                           if(view!=null) view.setUpdateProgress(responseProgressValue.getRepositorioFileUi(), responseProgressValue.getCount());
                            Log.d(TAG,":( :" + repositorioFileUi.getNombreArchivo() +" = " + responseProgressValue.getRepositorioFileUi().getNombreArchivo());
                        }
                        if(response instanceof DowloadImageUseCase.ResponseSuccessValue){
                            final DowloadImageUseCase.ResponseSuccessValue responseValue = (DowloadImageUseCase.ResponseSuccessValue) response;
                            final RepositorioFileUi repositorioFileUi = responseValue.getRepositorioFileUi();
                            updateRepositorioDowload.execute(new UpdateRepositorioDowload.Request(repositorioFileUi.getArchivoId(), repositorioFileUi.getPath(), repositorio), new UseCaseSincrono.Callback<Boolean>() {
                                @Override
                                public void onResponse(boolean success, Boolean value) {
                                    if(success){
                                       if(view!=null) view.setUpdate(responseValue.getRepositorioFileUi());
                                    }else {
                                        repositorioFileUi.setEstadoFileU(RepositorioEstadoFileU.ERROR_DESCARGA);
                                        if(view!=null) view.setUpdate(responseValue.getRepositorioFileUi());
                                    }
                                }
                            });

                            Log.d(TAG,"pathLocal:" + responseValue.getRepositorioFileUi().getPath());
                        }
                        if(response instanceof DowloadImageUseCase.ResponseErrorValue){
                            DowloadImageUseCase.ResponseErrorValue responseErrorValue = (DowloadImageUseCase.ResponseErrorValue) response;
                            if(view!=null) view.setUpdate(responseErrorValue.getRepositorioFileUi());
                        }
                    }

                    @Override
                    public void onError() {

                    }
                }
        );
    }


    @Override
    public void onClickArchivo(RepositorioFileUi repositorioFileUi) {
        if(repositorioFileUi instanceof UpdateRepositorioFileUi){
            if(view!=null)view.leerArchivo(repositorioFileUi.getPath());
        }else if(repositorioFileUi.getEstadoFileU()==RepositorioEstadoFileU.DESCARGA_COMPLETA){
            if(view!=null)view.leerArchivo(repositorioFileUi.getPath());
        }
    }

    @Override
    public void onClickClose(RepositorioFileUi repositorioFileUi) {
        repositorioFileUi.setCancel(true);
    }

    @Override
    public void onClickAddFile() {
        if(view!=null)view.onShowPickDoc(cantidadMaxima, new ArrayList<UpdateRepositorioFileUi>());
    }

    @Override
    public void onSalirSelectPiket(ArrayList<String> photoPaths) {

        convertirPathRepositorioUpload.execute(photoPaths, new UseCaseSincrono.Callback<List<UpdateRepositorioFileUi>>() {
            @Override
            public void onResponse(boolean succes, List<UpdateRepositorioFileUi> value) {
                if(succes){
                    updateRepositorioFileUiList.addAll(value);
                    repositorioFileUiList.addAll(0, value);
                    List<UpdateRepositorioFileUi> updateRepositorioFileUis = new ArrayList<>(updateRepositorioFileUiList);
                    for (UpdateRepositorioFileUi updateRepositorioFileUi : updateRepositorioFileUis) {
                        onClickUpload(updateRepositorioFileUi);
                    }
                    showListArchivos(repositorioFileUiList);
                }
            }
        });

    }

    @Override
    public void onClickUpload(final UpdateRepositorioFileUi updateRepositorioFileUi) {
        uploadRepositorio.execute(new UploadRepositorio.Request(urlServidor, repositorio ,updateRepositorioFileUi), new UseCaseSincrono.Callback<UpdateRepositorioFileUi>() {
            @Override
            public void onResponse(boolean succes, UpdateRepositorioFileUi value) {
                Log.d(TAG, "success : " +succes + "item: " + value);
                if(succes){
                    int position = repositorioFileUiList.indexOf(updateRepositorioFileUi);
                    if(position!=-1){
                        repositorioFileUiList.remove(position);
                        updateRepositorioFileUiList.remove(updateRepositorioFileUi);
                        RepositorioFileUi repositorioFileUi = new RepositorioFileUi();
                        repositorioFileUi.setArchivoId(updateRepositorioFileUi.getArchivoId());
                        repositorioFileUi.setPath(updateRepositorioFileUi.getPath());
                        repositorioFileUi.setNombreArchivo(updateRepositorioFileUi.getNombreArchivo());
                        repositorioFileUi.setNombreRecurso(updateRepositorioFileUi.getNombreRecurso());
                        repositorioFileUi.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
                        repositorioFileUi.setUrl(updateRepositorioFileUi.getUrl());
                        repositorioFileUi.setTipoFileU(updateRepositorioFileUi.getTipoFileU());
                        repositorioFileUi.setSelect(true);
                        repositorioFileUiList.add(position,repositorioFileUi);
                        showListArchivos(repositorioFileUiList);
                    }

                }else {
                    Log.d(TAG, "updateList");
                    if(view!=null)view.updateList(value);
                }
            }
        });
    }

    @Override
    public void onClickRemover(UpdateRepositorioFileUi updateRepositorioFileUi) {
        DownloadCancelUi downloadCancelUi = updateRepositorioFileUi.getDownloadCancelUi();
        if(downloadCancelUi!=null)downloadCancelUi.setCancel(true);
        updateRepositorioFileUiList.remove(updateRepositorioFileUi);
        repositorioFileUiList.remove(updateRepositorioFileUi);
        showListArchivos(repositorioFileUiList);
    }

    @Override
    public void onClickClose(UpdateRepositorioFileUi updateRepositorioFileUi) {
        DownloadCancelUi downloadCancelUi = updateRepositorioFileUi.getDownloadCancelUi();
        if(downloadCancelUi!=null)downloadCancelUi.setCancel(true);
    }

    @Override
    public void changeList(List<RepositorioFileUi> repositorioFileUiList) {
        Log.d(TAG, "repositorioFileUiList " + repositorioFileUiList.size());
        this.repositorioFileUiList.clear();
        this.updateRepositorioFileUiList.clear();
        this.repositorioFileUiList.addAll(repositorioFileUiList);
        showListArchivos(repositorioFileUiList);
    }

    @Override
    public void onClickCheck(RepositorioFileUi repositorioFileUi) {
        List<RepositorioFileUi> repositorioFileUiList = new ArrayList<>();
        for(RepositorioFileUi itemRepositorioFileUi : this.repositorioFileUiList){
            if(!(itemRepositorioFileUi instanceof UpdateRepositorioFileUi)){
                if(itemRepositorioFileUi.isSelect()){
                    repositorioFileUiList.add(itemRepositorioFileUi);
                }
            }
        }
       // if(view!=null)view.callbackChange(repositorioFileUiList);
    }

    @Override
    public List<RepositorioFileUi> getListFiles() {
        List<RepositorioFileUi> repositorioFileUiList = new ArrayList<>();
        for(RepositorioFileUi itemRepositorioFileUi : this.repositorioFileUiList){
            if(!(itemRepositorioFileUi instanceof UpdateRepositorioFileUi)){
                if(itemRepositorioFileUi.isSelect()){
                    repositorioFileUiList.add(itemRepositorioFileUi);
                }
            }
        }
        return repositorioFileUiList;
    }

    @Override
    public void onClickAddMultimedia() {
        switch (fragmentoTipo){
            case SUBIDA_DESCARGA_IMAGEN:
                if(view!=null)view.showPickPhoto(false, cantidadMaxima, new ArrayList<UpdateRepositorioFileUi>());
                break;
            case SUBIDA_DESCARGA_ARCHIVOS_VINCULOS:
                if(view!=null)view.showPickPhoto(true, cantidadMaxima, new ArrayList<UpdateRepositorioFileUi>());
                break;
            case SUBIDA_DESCARGA_IMAGEN_VIDEOS:
                if(view!=null)view.showPickPhoto(true, cantidadMaxima, new ArrayList<UpdateRepositorioFileUi>());
                break;
            case SUBIDA_DESCARGA__ARCHIVOS:
                if(view!=null)view.showPickPhoto(true, cantidadMaxima, new ArrayList<UpdateRepositorioFileUi>());
                break;
        }
    }

    @Override
    public void onClickAddVinculo() {
        RepositorioFileUi repositorioFileUi = new RepositorioFileUi();
        repositorioFileUi.setArchivoId(IdGenerator.generateId());
        if(view!=null)view.showDialogaddVinculo(repositorioFileUi);
    }

    @Override
    public void onClickAceptarDialogVinculo(RepositorioFileUi repositorioFileUi) {
        if(TextUtils.isEmpty(repositorioFileUi.getUrl()))return;
        String idVideo = YouTubeHelper.extractVideoIdFromUrl(repositorioFileUi.getUrl());
        Log.d(TAG, "dvideo: " + idVideo);
        if(!TextUtils.isEmpty(idVideo)){
            repositorioFileUi.setTipoFileU(RepositorioTipoFileU.YOUTUBE);
            Log.d(TAG,"Video");
        }else{
            repositorioFileUi.setTipoFileU(RepositorioTipoFileU.VINCULO);
            Log.d(TAG,"vinculo");
        }
        repositorioFileUi.setSelect(true);
        repositorioFileUi.setDescripcion(repositorioFileUi.getUrl());
        repositorioFileUi.setUrl(repositorioFileUi.getUrl());
        repositorioFileUi.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
        repositorioFileUiList.add(repositorioFileUi);
        showListArchivos(repositorioFileUiList);
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        RepositorioTBunble tBunble = RepositorioTBunble.clone(extras);
        if(tBunble!=null){
            repositorio = tBunble.getRepositorio();
            fragmentoTipo = tBunble.getFragmentoTipo();
        }
        getUrl();
        initFragment();

    }

    private void initFragment() {
        if(fragmentoTipo!=null){
            switch (fragmentoTipo){
                case SUBIDA_DESCARGA_ARCHIVOS_VINCULOS:
                    if(view!=null)view.showFloadButtonAddFile();
                    if(view!=null)view.showFloadButtonAddMultimedia();
                    if(view!=null)view.showFloadButtonAddVinculo();
                    break;
                case SUBIDA_DESCARGA_IMAGEN_VIDEOS:
                    if(view!=null)view.hideFloadButtonAddFile();
                    if(view!=null)view.showFloadButtonAddMultimedia();
                    if(view!=null)view.hideFloadButtonAddVinculo();
                    break;
                case SUBIDA_DESCARGA_IMAGEN:
                    if(view!=null)view.hideFloadButtonAddFile();
                    if(view!=null)view.showFloadButtonAddMultimedia();
                    if(view!=null)view.hideFloadButtonAddVinculo();
                    break;
                case SUBIDA_DESCARGA__ARCHIVOS:
                    if(view!=null)view.showFloadButtonAddFile();
                    if(view!=null)view.showFloadButtonAddMultimedia();
                    if(view!=null)view.hideFloadButtonAddVinculo();
                    break;
            }
        }
    }


    private void getUrl() {
        getUrlRepositorioArchivo.execute(new GetUrlRepositorioArchivo.Request(), new UseCaseSincrono.Callback<String>() {
            @Override
            public void onResponse(boolean success, String value) {
                if (success) {
                    urlServidor = value;
                } else {
                    showMessage("error en la dirección web del repositorio");
                }
            }
        });
    }


}
