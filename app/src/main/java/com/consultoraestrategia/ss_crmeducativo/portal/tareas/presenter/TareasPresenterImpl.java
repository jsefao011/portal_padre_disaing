package com.consultoraestrategia.ss_crmeducativo.portal.tareas.presenter;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.consultoraestrategia.ss_crmeducativo.portal.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.portal.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.view.TareasFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.view.TareasView;

public class TareasPresenterImpl implements TareasPresenter {

    private UseCaseHandler handler;

    public TareasPresenterImpl(UseCaseHandler handler) {
        this.handler = handler;
    }

    private Class<? extends Fragment> fragmentClassVisible;

    @Override
    public void onPageChanged(Class<? extends Fragment> fragmentClass) {
        if (fragmentClass == null) return;
        fragmentClassVisible = fragmentClass;

        if (fragmentClass.equals(TareasFragment.class)) {

            return;
        }


    }

    @Override
    public void attachView(TareasView view) {

    }

    @Override
    public void onCreate() {

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
