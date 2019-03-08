package com.consultoraestrategia.ss_crmeducativo.portal.familia.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.FamiliaPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.FamiliaPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.adaper.FamiliaAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.data.source.FamiliaRepository;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.data.source.local.FamiliaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.domain.usecase.GetPersonFamilia;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.listener.FamiliaListener;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FamiliaFragment extends BaseFragment<FamiliaView, FamiliaPresenter, FamiliaListener> implements FamiliaView, FamiliaListener{


    @BindView(R.id.familiaRv)
    RecyclerView familiaRv;
    FamiliaAdapter familiaAdapter;
    List<Object> objectList= new ArrayList<>();
    private GetPersonFamilia getPersonFamilia;

    @Override
    protected String getLogTag() {
        return FamiliaFragment.class.getSimpleName();
    }

    @Override
    protected FamiliaPresenter getPresenter() {
        FamiliaRepository familiaRepository= new FamiliaRepository(
                new FamiliaLocalDataSource(InjectorUtils.providePersonaDao()));
        presenter = new FamiliaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(), new GetPersonFamilia(familiaRepository));
        return presenter;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpAdapter();
    }

    @Override
    protected FamiliaView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_familia, container, false);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    private void setUpAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        familiaRv.setLayoutManager(layoutManager);
        familiaAdapter = new FamiliaAdapter(new ArrayList<Object>());
        familiaRv.setAdapter(familiaAdapter);
        familiaRv.setHasFixedSize(true);
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }

    @Override
    public void showListComentarios(List<Object> objects) {
            familiaAdapter.setObjetList(objects);
    }
}
