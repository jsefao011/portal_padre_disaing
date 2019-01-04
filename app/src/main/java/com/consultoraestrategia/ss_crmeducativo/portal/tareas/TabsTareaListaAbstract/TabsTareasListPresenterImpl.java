package com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.useCase.GetTareasCursoAlumno;

public abstract  class TabsTareasListPresenterImpl implements TabsTareasListPresenter {
  private int tipoTarea=0;
  private UseCaseHandler handler;
  GetTareasCursoAlumno getTareasCursoAlumno;
  private TabsTareasListaView view;
  private String TAG= TabsTareasListPresenterImpl.class.getSimpleName();

    public TabsTareasListPresenterImpl(UseCaseHandler handler, GetTareasCursoAlumno getTareasCursoAlumno) {
        this.handler = handler;
        this.getTareasCursoAlumno=getTareasCursoAlumno;
    }


  @Override
    public void attachView(TabsTareasListaView view) {
        this.view=view;
    }

    @Override
    public void onCreate() {
      Log.d(TAG, "onCreate ");

    }

    private void getTareasCursoAlumno() {
        Log.d(TAG, "getTareasCursoAlumno ");
        handler.execute(getTareasCursoAlumno, new GetTareasCursoAlumno.RequestValues(), new UseCase.UseCaseCallback<GetTareasCursoAlumno.ResponseValue>() {
            @Override
            public void onSuccess(GetTareasCursoAlumno.ResponseValue response) {

            }

            @Override
            public void onError() {

            }
        });
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
