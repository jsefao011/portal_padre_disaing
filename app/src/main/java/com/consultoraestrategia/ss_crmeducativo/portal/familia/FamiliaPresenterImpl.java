package com.consultoraestrategia.ss_crmeducativo.portal.familia;


import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.domain.usecase.GetPersonFamilia;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.domain.usecase.SetPersonFamilia;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.ui.FamiliaView;
import com.consultoraestrategia.ss_crmeducativo.portal.wrapper.MainParametrosGlobales;

import java.util.ArrayList;
import java.util.List;

public class FamiliaPresenterImpl extends BaseFragmentPresenterImpl<FamiliaView> implements FamiliaPresenter{

    private static final String TAG = FamiliaPresenterImpl.class.getSimpleName();
    private GetPersonFamilia getPersonFamilia;
    private SetPersonFamilia setPersonFamilia;
    private int personaId;

    public FamiliaPresenterImpl(UseCaseHandler handler, Resources res, GetPersonFamilia getPersonFamilia, SetPersonFamilia setPersonFamilia) {
        super(handler, res);
        this.getPersonFamilia = getPersonFamilia;
        this.setPersonFamilia = setPersonFamilia;
    }

    @Override
    protected String getTag() {
        return TAG;
    }


    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        MainParametrosGlobales mainParametrosGlobales = MainParametrosGlobales.clone(extras);
        if(mainParametrosGlobales!=null){
            this.personaId=mainParametrosGlobales.getPadre_mentor_personaId();
        }
    }

    @Override
    public void onCLickAcceptButtom() {

    }

    private void setGetPersonFamilia() {
        Log.d(TAG, "getComentariosList");
        handler.execute(getPersonFamilia,
                new GetPersonFamilia.RequestValues(personaId), new UseCase.UseCaseCallback<GetPersonFamilia.ResponseValues>() {
                    @Override
                    public void onSuccess(GetPersonFamilia.ResponseValues response) {
                        List<Object> objects = new ArrayList<>();
                        objects.addAll(response.getList());
                        if (view != null) view.showListFamilia(objects);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        setGetPersonFamilia();
    }

    @Override
    public void onResumeFragment() {
        setGetPersonFamilia();
    }

    @Override
    public void onSaveEditPerson(List<Object> objectList) {
        setUpdatePersonFamilia(objectList);
    }

    private void setUpdatePersonFamilia(List<Object> objectList){
        Log.d(TAG, "setComentariosList");

        handler.execute(setPersonFamilia,
                new SetPersonFamilia.RequestValues(objectList,personaId), new UseCase.UseCaseCallback<SetPersonFamilia.ResponseValues>() {
                    @Override
                    public void onSuccess(SetPersonFamilia.ResponseValues response) {
                        List<Object> objects = new ArrayList<>();
                        objects.addAll(response.getList());
                        Log.d("AGTAMANIO", "as" + response.getList().size());
                        if (view != null) view.showListFamilia(objects);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
