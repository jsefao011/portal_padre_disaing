package com.consultoraestrategia.ss_crmeducativo.portal.contactos.cabecera;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.detalleCompaneros.DetalleCompanerosFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.detalleCompaneros.DetalleCompanerosView;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.detalleDirectivos.DetalleDirectivosFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.detalleDirectivos.DetalleDirectivosView;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.detalleProfesores.DetalleProfesoresFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.detalleProfesores.DetalleProfesoresView;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.domain.usecase.GetAdministrativo;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.domain.usecase.GetCompaneros;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.domain.usecase.GetDocentes;
import com.consultoraestrategia.ss_crmeducativo.portal.wrapper.MainParametrosGlobales;

import java.util.ArrayList;
import java.util.List;

public class ContactosPresenterImpl extends BaseFragmentPresenterImpl<ContactosView> implements ContactosPresenter{

    private static final String TAG = ContactosPresenterImpl.class.getSimpleName();
    ContactosView contactosView;
    DetalleCompanerosView detalleCompanerosView;
    DetalleDirectivosView detalleDirectivosView;
    DetalleProfesoresView detalleProfesoresView;
    GetAdministrativo getAdministrativo;
    GetCompaneros getCompaneros;
    GetDocentes getDocentes;
    private int idAlumno;


    public ContactosPresenterImpl(UseCaseHandler handler, Resources res, GetAdministrativo getAdministrativo, GetCompaneros getCompaneros, GetDocentes getDocentes) {
        super(handler, res);
        this.getAdministrativo = getAdministrativo;
        this.getCompaneros = getCompaneros;
        this.getDocentes = getDocentes;
    }

    @Override
    public void onAttach(ContactosView contactosView,  DetalleProfesoresFragment detalleProfesoresFragment, DetalleDirectivosFragment detalleDirectivosFragment, DetalleCompanerosFragment detalleCompanerosFragment) {
        this.contactosView = contactosView;
        this.detalleCompanerosView = detalleCompanerosFragment;
        this.detalleDirectivosView = detalleDirectivosFragment;
        this.detalleProfesoresView = detalleProfesoresFragment;
    }

    @Override
    public void onResumen() {
        setGetDocentes();
        setGetAdministrativo();
        setGetCompaneros();
    }

    @Override
    protected String getTag() {
        return ContactosPresenterImpl.class.getSimpleName();
    }



    @Override
    public void onChildsFragmentViewCreated() {
        setGetDocentes();
        setGetAdministrativo();
        setGetCompaneros();
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
        if(mainParametrosGlobales!=null){
            this.idAlumno=mainParametrosGlobales.getHijo_selected_personaId();
            Log.d("setExtras", " " + idAlumno);
        }
    }

    private void setGetCompaneros() {
        Log.d(TAG, "setGetCompaneros");
        handler.execute(getCompaneros,
                new GetCompaneros.RequestValues(1590, 1), new UseCase.UseCaseCallback<GetCompaneros.ResponseValues>() {
                    @Override
                    public void onSuccess(GetCompaneros.ResponseValues response) {
                        List<Object> objects = new ArrayList<>();
                        objects.addAll(response.getList());
                        if (detalleCompanerosView != null) detalleCompanerosView.showListCompaneros(objects);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void setGetDocentes() {
        Log.d(TAG, "getDocentesList");
        handler.execute(getDocentes,
                new GetDocentes.RequestValues(1590, 1), new UseCase.UseCaseCallback<GetDocentes.ResponseValues>() {
                    @Override
                    public void onSuccess(GetDocentes.ResponseValues response) {
                        List<Object> objects = new ArrayList<>();
                        objects.addAll(response.getList());
                        if (detalleProfesoresView != null) detalleProfesoresView.showListProfesores(objects);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void setGetAdministrativo() {
        Log.d(TAG, "getAdministrativosList");
        handler.execute(getAdministrativo,
                new GetAdministrativo.RequestValues(idAlumno, 1), new UseCase.UseCaseCallback<GetAdministrativo.ResponseValues>() {
                    @Override
                    public void onSuccess(GetAdministrativo.ResponseValues response) {
                        List<Object> objects = new ArrayList<>();
                        objects.addAll(response.getList());
                        if (view != null) detalleDirectivosView.showListDirectivos(objects);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
