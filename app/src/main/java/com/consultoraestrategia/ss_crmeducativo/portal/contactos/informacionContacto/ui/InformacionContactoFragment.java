package com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.ui;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.dialogFragment.BaseDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.InformacionContactoPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.InformacionContactoPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.adapter.InformacionPersonaAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.data.source.InformacionContactoRepository;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.data.source.local.InformacionContactoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.domain.usecase.GetDocente;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.domain.usecase.GetListFamily;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import org.parceler.Parcels;
import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class InformacionContactoFragment extends BaseDialogFragment<InformacionContactoView, InformacionContactoPresenter, InformacionContactoView> implements InformacionContactoView, View.OnClickListener {


    public static final String PERSONA_ID = "InformacionContactoFragment.personaId";
    public static final String TIPO_CONTACTO = "InformacionContactoFragment.tipo";
    public static final String TAG = InformacionContactoFragment.class.getSimpleName();
    private Unbinder unbinder;

    @BindView(R.id.rv_informacion_contactos)
    RecyclerView informacionRV;
    @BindView(R.id.backPress)
    ImageView backPress;
    InformacionPersonaAdapter informacionPersonaAdapter;

    public static InformacionContactoFragment newInstance(int personaId, int tipo) {
        Bundle args = new Bundle();
        args.putInt(PERSONA_ID, personaId);
        args.putInt(TIPO_CONTACTO, tipo);
        InformacionContactoFragment fragment = new InformacionContactoFragment();
        fragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return InformacionContactoFragment.class.getSimpleName();
    }

    @Override
    protected InformacionContactoPresenter getPresenter() {
        InformacionContactoRepository informacionContactoRepository = new InformacionContactoRepository(new InformacionContactoLocalDataSource(InjectorUtils.providePersonaDao()));
        return new InformacionContactoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(), new GetListFamily(informacionContactoRepository), new GetDocente(informacionContactoRepository));
    }

    @Override
    protected InformacionContactoView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_informacion_contacto, container, false);
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setupAdapterInformacionPersona();
        super.onViewCreated(view, savedInstanceState);
        backPress.setOnClickListener(this);
    }

    private void setupAdapterInformacionPersona() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        informacionRV.setLayoutManager(layoutManager);
        informacionPersonaAdapter = new InformacionPersonaAdapter(new ArrayList<Object>());
        informacionRV.setAdapter(informacionPersonaAdapter);
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
    public void showListPersonInformacion(List<Object> list) {
        informacionPersonaAdapter.setObjetList(list);
    }

    @Override
    public void showListFamilyInformacion(List<Object> list) {
        informacionPersonaAdapter.setObjetList(list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backPress:
                getDialog().dismiss();
                break;
        }
    }
}
