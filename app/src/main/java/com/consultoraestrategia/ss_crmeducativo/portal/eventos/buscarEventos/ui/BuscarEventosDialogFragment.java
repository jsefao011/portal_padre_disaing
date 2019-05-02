package com.consultoraestrategia.ss_crmeducativo.portal.eventos.buscarEventos.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.dialogFragment.BaseDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.eventos.buscarEventos.BuscarEventosPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.eventos.buscarEventos.BuscarEventosPresenterImplement;
import com.consultoraestrategia.ss_crmeducativo.portal.eventos.buscarEventos.listener.BuscarEventosListener;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BuscarEventosDialogFragment extends BaseDialogFragment<BuscarEventosView, BuscarEventosPresenter, BuscarEventosListener> implements BuscarEventosView, View.OnClickListener {

    private Unbinder unbinder;
    @BindView(R.id.rv_filtro_eventos)
    RecyclerView filtroEventosRv;
    @BindView(R.id.backPress)
    ImageView backPress;
    private BuscarEventosListener buscarEventosListener;

    public static BuscarEventosDialogFragment newInstance() {
        Bundle args = new Bundle();
        BuscarEventosDialogFragment fragment = new BuscarEventosDialogFragment();
        fragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return BuscarEventosDialogFragment.class.getSimpleName();
    }

    @Override
    protected BuscarEventosPresenter getPresenter() {
        presenter = new BuscarEventosPresenterImplement(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
        return presenter;
    }

    @Override
    protected BuscarEventosView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_colegio_buscar_eventos, container, false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        unbinder = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.getDialog().getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        this.getDialog().getWindow().
                setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (presenter != null) presenter.onStart();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BuscarEventosListener) {
            listener = (BuscarEventosListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement BuscarEventosListener");

        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        backPress.setOnClickListener(this);
        backPress.setRotation(360);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return null;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backPress:
                roration();
                getDialog().dismiss();
                break;
        }
    }

    private void roration() {
        backPress.setRotation(360);
    }
}
