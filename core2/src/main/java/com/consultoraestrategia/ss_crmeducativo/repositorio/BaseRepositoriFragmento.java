package com.consultoraestrategia.ss_crmeducativo.repositorio;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragment;
import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.RepositorioAdapter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.RepositorioColumnCountProvider;
import com.consultoraestrategia.ss_crmeducativo.repositorio.bundle.RepositorioTBunble;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioRepository;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.local.RepositorioLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.preferents.RepositorioPreferentsDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.remote.RepositorioRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.listener.RepositorioItemListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.listener.RepositorioItemUpdateListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.listener.RepositorioListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.ConvertirPathRepositorioUpload;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.DowloadImageUseCase;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.GetUrlRepositorioArchivo;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.UpdateRepositorioDowload;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.UploadRepositorio;
import com.consultoraestrategia.ss_crmeducativo.util.OpenIntents;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import droidninja.filepicker.models.sort.SortingTypes;
import droidninja.filepicker.utils.Orientation;

public class BaseRepositoriFragmento extends BaseFragment<RepositorioView, RepositorioPresenter, RepositorioListener> implements RepositorioView, RepositorioItemListener, RepositorioItemUpdateListener, View.OnClickListener {

    protected static final int CUSTOM_REQUEST_CODE = 532;
    protected RecyclerView rcRepositorio;
    protected RepositorioAdapter repositorioAdapter;
    protected ProgressBar progressBar;
    protected ConstraintLayout root;
    protected FloatingActionButton floatingActionButton;
    private AutoColumnGridLayoutManager autoColumnGridLayoutManager;
    private FloatingActionButton floatingActionButton2;
    private FloatingActionButton floatingActionButton3;


    public static BaseRepositoriFragmento newInstance(RepositorioTBunble repositorioTBunble) {
        BaseRepositoriFragmento fragment = new BaseRepositoriFragmento();
        fragment.setArguments(repositorioTBunble.getBundle());
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return BaseRepositoriFragmento.class.getSimpleName();
    }

    @Override
    protected RepositorioPresenter getPresenter() {
        RepositorioRepository repository = new RepositorioRepository(new RepositorioLocalDataSource(),
                new RepositorioPreferentsDataSource(),
                new RepositorioRemoteDataSource(ApiRetrofit.getInstance()));
        return new BaseRepositorioPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new DowloadImageUseCase(repository),
                new ConvertirPathRepositorioUpload(),
                new UploadRepositorio(repository),
                new GetUrlRepositorioArchivo(repository),
                new UpdateRepositorioDowload(repository));
    }

    @Override
    protected RepositorioView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_repositorio, container, false);
        rcRepositorio = (RecyclerView)view.findViewById(R.id.rc_repositorio);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        root = (ConstraintLayout)view.findViewById(R.id.root);
        floatingActionButton = (FloatingActionButton)view.findViewById(R.id.floatingActionButton);
        floatingActionButton2 = (FloatingActionButton)view.findViewById(R.id.floatingActionButton2);
        floatingActionButton3 = (FloatingActionButton)view.findViewById(R.id.floatingActionButton3);
        floatingActionButton.setOnClickListener(this);
        floatingActionButton2.setOnClickListener(this);
        floatingActionButton3.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setupAdapter();
        super.onViewCreated(view, savedInstanceState);
    }

    private void setupAdapter() {
        repositorioAdapter = new RepositorioAdapter(this,this ,rcRepositorio);
        autoColumnGridLayoutManager = new AutoColumnGridLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        RepositorioColumnCountProvider columnCountProvider = new RepositorioColumnCountProvider(getContext());
        autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider);
        rcRepositorio.setLayoutManager(autoColumnGridLayoutManager);
        rcRepositorio.setAdapter(repositorioAdapter);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return root;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }


    @Override
    public void showListArchivos(List<RepositorioFileUi> repositorioFileUiList) {
        if(repositorioAdapter!=null)repositorioAdapter.setList(repositorioFileUiList);
    }

    @Override
    public void updateList(RepositorioFileUi repositorioFileUi) {
        repositorioAdapter.update(repositorioFileUi);
    }

    @Override
    public synchronized void setUpdateProgress(RepositorioFileUi repositorioFileUi, int count) {
        repositorioAdapter.updateProgress(repositorioFileUi, count);
    }

    @Override
    public void setUpdate(RepositorioFileUi repositorioEstadoFileU) {
        repositorioAdapter.update(repositorioEstadoFileU);
    }

    @Override
    public void leerArchivo(String path) {
        Log.d(getClass().getSimpleName(), path);
        OpenIntents.openFile(FileProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".provider", new File(path)), getContext());
    }

    @Override
    public void onClickDownload(RepositorioFileUi repositorioFileUi) {
        presenter.onClickDownload(repositorioFileUi);
    }

    @Override
    public void onClickClose(RepositorioFileUi repositorioFileUi) {
        presenter.onClickClose(repositorioFileUi);
    }

    @Override
    public void onClickCheck(RepositorioFileUi repositorioFileUi) {
        presenter.onClickCheck(repositorioFileUi);
    }

    @Override
    public void onClickArchivo(RepositorioFileUi repositorioFileUi) {
        presenter.onClickArchivo(repositorioFileUi);
    }

    @Override
    public void onClickUpload(UpdateRepositorioFileUi updateRepositorioFileUi) {
        presenter.onClickUpload(updateRepositorioFileUi);
    }

    @Override
    public void onClickRemover(UpdateRepositorioFileUi updateRepositorioFileUi) {
        presenter.onClickRemover(updateRepositorioFileUi);
    }

    @Override
    public void onClickClose(UpdateRepositorioFileUi updateRepositorioFileUi) {
        presenter.onClickClose(updateRepositorioFileUi);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.floatingActionButton){
            presenter.onClickAddFile();
        }else if(id == R.id.floatingActionButton2){
            presenter.onClickAddMultimedia();
        }else if(id == R.id.floatingActionButton3){
            presenter.onClickAddVinculo();
        }
    }

    public void showDialog(Context context, final RepositorioFileUi repositorioFileUi){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_add_vinculo);

        final EditText txtTituloVideo = (EditText) dialog.findViewById(R.id.txtTituloVideo);
        final EditText txtUrlVideo = (EditText) dialog.findViewById(R.id.txtUrlVideo);
        txtTituloVideo.setText(repositorioFileUi.getNombreArchivo());
        txtUrlVideo.setText(repositorioFileUi.getUrl());

        Button btnCancelarVideo = (Button) dialog.findViewById(R.id.btnCancelarVideo);
        Button btnAceptar = (Button) dialog.findViewById(R.id.btnAceptar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repositorioFileUi.setNombreArchivo(txtUrlVideo.getText().toString());
                repositorioFileUi.setNombreRecurso(txtTituloVideo.getText().toString());
                repositorioFileUi.setUrl(txtUrlVideo.getText().toString());
                presenter.onClickAceptarDialogVinculo(repositorioFileUi);
                dialog.dismiss();
            }
        });
        btnCancelarVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ArrayList<String> photoPaths = new ArrayList<>();;
        switch (requestCode) {
            case CUSTOM_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    photoPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA));
                }
                break;

            case FilePickerConst.REQUEST_CODE_DOC:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    photoPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS));
                }
                break;
        }

        presenter.onSalirSelectPiket(photoPaths);

    }

    @Override
    public void showPickPhoto(boolean enableVideo, int maxCount, List<UpdateRepositorioFileUi> photoPaths) {
        ArrayList<String> stringList = new ArrayList<>();
        //for (UpdateRepositorioFileUi recursoUploadFile : photoPaths)stringList.add(recursoUploadFile.getPath());
        FilePickerBuilder filePickerBuilder = FilePickerBuilder.getInstance()
                //.setSelectedFiles(stringList)
                .setActivityTheme(R.style.LibAppThemeLibrary)
                //.setActivityTitle("Selección de multimedia")
                .enableVideoPicker(enableVideo)
                .enableCameraSupport(true)
                .showGifs(true)
                .showFolderView(true)
                //.enableSelectAll(false)
                .enableImagePicker(true)
                .setMaxCount(1)
                //.setCameraPlaceholder(R.drawable.custom_camera)
                .withOrientation(Orientation.UNSPECIFIED);
            filePickerBuilder.pickPhoto(this, CUSTOM_REQUEST_CODE);
    }


    /* DOC_ESCRITOS(),
     DOC_PRESENTACIONES(new String[]{".ppt", ".pptx"}),
     PDF(new String[]{".pdf"}),
     DOC_TABLAS(new String[]{".xls", ".xlsx",".ods"}),
     MUSICA(new String[]{".mp3", ".ogg",".wav"}),
     VIDEOS(new String[]{".mpg",".3gp",".mpg4",".wmv",".mov",".ogv"}),
     IMAGENES(new String[]{".gif",".jpeg",".jpg",".png"}),
     COMPRESION(new String[]{".gz",".gzip",".rar",".zip"});*/
    @Override
    public void onShowPickDoc(int maxCount, List<UpdateRepositorioFileUi> docPaths) {
        ArrayList<String> stringList = new ArrayList<>();
        for (UpdateRepositorioFileUi recursoUploadFile : docPaths)stringList.add(recursoUploadFile.getPath());
        FilePickerBuilder filePickerBuilder = FilePickerBuilder.getInstance()
                //.setMaxCount(maxCount)
                //.setSelectedFiles(stringList)
                .setActivityTheme(R.style.LibAppThemeLibrary)
                //.setActivityTitle("Selección de documento");
                .addFileSupport("DOCUMENTO", new String[]{".doc", ".docx", ".txt"},R.drawable.ext_doc)
                .addFileSupport("HOJA DE CALCULO", new String[]{".xls", ".xlsx",".ods"},R.drawable.ext_xls)
                .addFileSupport("PDF", new String[]{".pdf"},R.drawable.ext_pdf)
                .addFileSupport("PRESENTACION", new String[]{".ppt", ".pptx"},R.drawable.ext_ppt)
                .addFileSupport("MUSICA", new String[]{".mp3", ".ogg",".wav"},R.drawable.ext_aud)
        //filePickerBuilder.addFileSupport("COMPRESION", new String[]{".gz",".gzip",".rar",".zip"});
                //.enableDocSupport(false)
                //.enableSelectAll(true)
                //.showFolderView(true)
                .sortDocumentsBy(SortingTypes.name)
                .withOrientation(Orientation.UNSPECIFIED);
        filePickerBuilder.pickFile(this);
    }


    @Override
    public void callbackChange(List<RepositorioFileUi> repositorioFileUiList) {
       if(listener!=null)listener.onChangeList(repositorioFileUiList);
    }

    @Override
    public void showFloadButtonAddMultimedia() {
        floatingActionButton2.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFloadButtonAddFile() {
        floatingActionButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFloadButtonAddFile() {
        floatingActionButton.setVisibility(View.GONE);
    }

    @Override
    public void showFloadButtonAddVinculo() {
        floatingActionButton3.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFloadButtonAddVinculo() {
        floatingActionButton3.setVisibility(View.GONE);
    }

    @Override
    public void showDialogaddVinculo(RepositorioFileUi repositorioFileUi) {
        showDialog(getContext(), repositorioFileUi);
    }

    @Override
    public void onClickArchivo(UpdateRepositorioFileUi updateRepositorioFileUi) {
        presenter.onClickArchivo(updateRepositorioFileUi);
    }

    public void changeList(List<RepositorioFileUi> repositorioFileUiList){
       presenter.changeList(repositorioFileUiList);
    }

    public List<RepositorioFileUi> getListFiles() {
        return presenter.getListFiles();
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }
}
