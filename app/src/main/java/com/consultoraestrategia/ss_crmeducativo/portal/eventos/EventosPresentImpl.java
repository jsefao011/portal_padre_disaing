package com.consultoraestrategia.ss_crmeducativo.portal.eventos;

import android.content.res.Resources;
import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.eventos.ui.EventosView;
import com.consultoraestrategia.ss_crmeducativo.portal.wrapper.MainParametrosGlobales;

public class EventosPresentImpl extends BaseFragmentPresenterImpl<EventosView> implements EventosPresent {



    public EventosPresentImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return null;
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

        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
