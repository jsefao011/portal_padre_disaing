package com.consultoraestrategia.ss_crmeducativo.portal.caso.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragment;
import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.data.CasoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.data.CasoRepository;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.domain.useCase.GetAlumnoCasos;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.domain.useCase.UpdateSuccesDowloadCasoArchivo;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.CasoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.TipoPadreUi;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.presenter.CasoPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.presenter.CasoPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.ui.adapter.CasoColumnGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.ui.adapter.CasosAdapter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.DownloadItemListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioRepository;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.local.RepositorioLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.preferents.RepositorioPreferentsDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.remote.RepositorioRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.DowloadImageUseCase;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.OpenIntents;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CasoFragment extends BaseFragment<CasoView, CasoPresenter, CasoListener> implements CasoView,  DownloadItemListener {

    String TAG = CasoFragment.class.getSimpleName();
    @BindView(R.id.recyclerCaso)
    RecyclerView recyclerCaso;
    @BindView(R.id.cant1)
    TextView cant1;
    @BindView(R.id.porcentaje1)
    TextView porcentaje1;
    @BindView(R.id.cant2)
    TextView cant2;
    @BindView(R.id.porcentaje2)
    TextView porcentaje2;
    Unbinder unbinder;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    CasosAdapter casosAdapter;
    @BindView(R.id.txtemptyproEd)
    TextView txtemptyproEd;


    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected CasoPresenter getPresenter() {
        CasoRepository casoRepository = new CasoRepository(new CasoLocalDataSource(InjectorUtils.provideCursoDao()));
        presenter = new CasoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(), new GetAlumnoCasos(casoRepository)
        , new DowloadImageUseCase(new RepositorioRepository(
                new RepositorioLocalDataSource(),
                new RepositorioPreferentsDataSource(),
                new RepositorioRemoteDataSource(ApiRetrofit.getInstance())
        )), new UpdateSuccesDowloadCasoArchivo(casoRepository));
        return presenter;
    }

    @Override
    protected CasoView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.caso_fragment, container, false);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return null;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    private void initAdapter() {
        AutoColumnGridLayoutManager autoColumnGridLayoutManager = new AutoColumnGridLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        CasoColumnGridLayoutManager columnCountProvider = new CasoColumnGridLayoutManager(getContext());
        autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider);
        recyclerCaso.setLayoutManager(autoColumnGridLayoutManager);
        casosAdapter = new CasosAdapter(new ArrayList<CasoUi>(), this, recyclerCaso);
        recyclerCaso.setAdapter(casosAdapter);
        recyclerCaso.setHasFixedSize(true);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showListTipos(TipoPadreUi tipoMerito, TipoPadreUi tipoDemerito) {
        cant1.setText(String.valueOf(tipoMerito.getCantidad()));
        porcentaje1.setText(tipoMerito.getPorcentaje() + "%");
        cant2.setText(String.valueOf(tipoDemerito.getCantidad()));
        porcentaje2.setText(tipoDemerito.getPorcentaje() + "%");

    }

    @Override
    public void showListCasos(List<CasoUi> objectList) {

        txtemptyproEd.setVisibility(View.GONE);
        casosAdapter.setObjectList(objectList);
    }

    @Override
    public void showTextEmpty(String string) {
        txtemptyproEd.setText(string);
        txtemptyproEd.setVisibility(View.VISIBLE);
    }

    @Override
    public void leerArchivo(String path) {
        if(!TextUtils.isEmpty(path)){
            OpenIntents.openFile(FileProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".provider", new File(path)), getContext());
        }
    }

    @Override
    public void setUpdateProgress(RepositorioFileUi repositorioFileUi, int count) {
        casosAdapter.updateProgress(repositorioFileUi, count);
    }

    @Override
    public void setUpdate(RepositorioFileUi repositorioFileUi) {
        casosAdapter.update(repositorioFileUi);
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
    public void onClickArchivo(RepositorioFileUi repositorioFileUi) {
        presenter.onClickArchivo(repositorioFileUi);
    }
}
