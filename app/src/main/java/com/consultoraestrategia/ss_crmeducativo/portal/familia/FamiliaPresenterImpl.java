package com.consultoraestrategia.ss_crmeducativo.portal.familia;


import android.content.res.Resources;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.domain.usecase.GetPersonFamilia;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.ui.FamiliaView;
import java.util.ArrayList;
import java.util.List;

public class FamiliaPresenterImpl extends BaseFragmentPresenterImpl<FamiliaView> implements FamiliaPresenter{

    private static final String TAG = FamiliaPresenterImpl.class.getSimpleName();
    private GetPersonFamilia getPersonFamilia;
    private FamiliaPresenter familiaPresenter;
    private int personaId;

    public FamiliaPresenterImpl(UseCaseHandler handler, Resources res, GetPersonFamilia getPersonFamilia) {
        super(handler, res);
        this.getPersonFamilia = getPersonFamilia;
    }

    @Override
    protected String getTag() {
        return TAG;
    }


    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }

    private void setGetPersonFamilia() {
        Log.d(TAG, "getComentariosList");
        handler.execute(getPersonFamilia,
                new GetPersonFamilia.RequestValues(2899), new UseCase.UseCaseCallback<GetPersonFamilia.ResponseValues>() {
                    @Override
                    public void onSuccess(GetPersonFamilia.ResponseValues response) {
                        List<Object> objects = new ArrayList<>();
                        objects.addAll(response.getList());
                        Log.d("AGTAMANIO", "as" + response.getList().size());
                        if (view != null) view.showListComentarios(objects);
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
    public void setExtras(int personaId) {
        this.personaId = personaId;
    }

    @Override
    public void onResumeFragment() {
        setGetPersonFamilia();
    }
}
