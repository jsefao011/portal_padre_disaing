package com.consultoraestrategia.ss_crmeducativo.portal.eventos.buscarEventos;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.eventos.buscarEventos.ui.BuscarEventosView;

public class BuscarEventosPresenterImplement extends BaseFragmentPresenterImpl<BuscarEventosView> implements BuscarEventosPresenter {


    public BuscarEventosPresenterImplement(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return BuscarEventosPresenterImplement.class.getSimpleName();
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
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(getTag(), "onResume");
    }
}
