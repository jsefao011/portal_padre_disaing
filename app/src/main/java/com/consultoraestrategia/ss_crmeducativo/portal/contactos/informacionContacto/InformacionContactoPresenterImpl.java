package com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.data.source.InformacionContactoRepository;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.domain.usecase.GetDocente;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.domain.usecase.GetListFamily;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.ui.InformacionContactoFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.ui.InformacionContactoView;

import java.util.ArrayList;
import java.util.List;


public class InformacionContactoPresenterImpl extends BaseFragmentPresenterImpl<InformacionContactoView> implements InformacionContactoPresenter {

    private GetListFamily getListFamily;
    private GetDocente getDocente;
    private InformacionContactoRepository informacionContactoRepository;
    private int idPersona;
    private int tipoContacto;
    public static final String TAG = InformacionContactoPresenterImpl.class.getSimpleName();


    public InformacionContactoPresenterImpl(UseCaseHandler handler, Resources res, GetListFamily getListFamily, GetDocente getDocente) {
        super(handler, res);
        this.getDocente = getDocente;
        this.getListFamily = getListFamily;
    }

    @Override
    protected String getTag() {
        return InformacionContactoFragment.class.getSimpleName();
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
        this.idPersona = extras.getInt(InformacionContactoFragment.PERSONA_ID);
        this.tipoContacto = extras.getInt(InformacionContactoFragment.TIPO_CONTACTO);
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        Log.d(TAG, "onViewCreated");
        if (tipoContacto==1)getDocenteInfo();
        else if(tipoContacto==0)getLisFamily();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        if (tipoContacto==1)getDocenteInfo();
        else if(tipoContacto==0)getLisFamily();
    }


    @Override
    public void onCreateView() {
        super.onCreateView();
        Log.d(TAG, "onCreateView");
        if (tipoContacto==1)getDocenteInfo();
        else if(tipoContacto==0)getLisFamily();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void getLisFamily() {
        Log.d(TAG, "getLisFamily");
        handler.execute(getListFamily,
                new GetListFamily.RequestValues(idPersona), new UseCase.UseCaseCallback<GetListFamily.ResponseValues>() {
                    @Override
                    public void onSuccess(GetListFamily.ResponseValues response) {
                        view.showListPersonInformacion(response.getList());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    public void getDocenteInfo() {
        Log.d(TAG, "getDocenteInfo");
        handler.execute(getDocente,
                new GetDocente.RequestValues(idPersona), new UseCase.UseCaseCallback<GetDocente.ResponseValues>() {

                    @Override
                    public void onSuccess(GetDocente.ResponseValues response) {
                        view.showListPersonInformacion(response.getObjectList());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
