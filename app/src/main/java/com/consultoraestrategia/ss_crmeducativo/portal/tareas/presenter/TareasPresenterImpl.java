package com.consultoraestrategia.ss_crmeducativo.portal.tareas.presenter;




import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.view.TareasFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.view.TareasView;

public class TareasPresenterImpl implements TareasPresenter {

    private UseCaseHandler handler;

    TareasView tareasView;
    private String TAG=TareasPresenterImpl.class.getSimpleName();

    public TareasPresenterImpl(UseCaseHandler handler) {
        this.handler = handler;
    }

    @Override
    public void onPageChanged(int position) {


    }

    @Override
    public void attachView(TareasView view) {
       this.tareasView=view;

    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onBackPressed() {

    }
}
