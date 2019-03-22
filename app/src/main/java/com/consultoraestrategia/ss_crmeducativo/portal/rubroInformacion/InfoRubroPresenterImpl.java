package com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.domain.useCase.GetInfoRubroProceso;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.Cell;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.Column;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.CompetenciaCell;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.IndicadorUI;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.Row;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.ui.InfoRubroFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.ui.InfoRubroView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jse on 15/09/2018.
 */

public class InfoRubroPresenterImpl extends BaseFragmentPresenterImpl<InfoRubroView> implements InfoRubroPresenter {

    private String evaluacionProcesoId;
    private int cargaCursoId;
    private String TAG =InfoRubroPresenterImpl.class.getSimpleName();
    private GetInfoRubroProceso getInfoRubroProceso;
    List<Row> rows;
    List<Cell> cells;


    public InfoRubroPresenterImpl(UseCaseHandler handler, Resources res, GetInfoRubroProceso getInfoRubroProceso) {
        super(handler, res);
        this.getInfoRubroProceso=getInfoRubroProceso;
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
    public void onCreate() {
        super.onCreate();

    }

    private void getInfoRubro() {
        handler.execute(getInfoRubroProceso, new GetInfoRubroProceso.RequestValues(evaluacionProcesoId, cargaCursoId), new UseCase.UseCaseCallback<GetInfoRubroProceso.ResponseValue>() {
            @Override
            public void onSuccess(GetInfoRubroProceso.ResponseValue response) {
                Log.d(TAG,"onError getInfoRubro ");
                if(view!=null)view.setNombreRubrica(response.getInfoRubroUi().getNombreRubro());
                showCurso(response.getInfoRubroUi().getCursoUi());
                showAlumno(response.getInfoRubroUi().getAlumnoUi());
                if(view!=null)view.showTableView(response.getInfoRubroUi().getCellListList(),response.getInfoRubroUi().getColumns(), response.getInfoRubroUi().getRows(), "Indicadores");
            }

            @Override
            public void onError() {
                Log.d(TAG,"onError getInfoRubro ");
            }
        });
    }




    private void showAlumno(AlumnoUi alumnoUi) {
        if(view!=null)view.setAlumno(alumnoUi);
    }

    private void showCurso(CursoUi cursoUi) {
        if(view!=null)view.setNombreCurso(cursoUi.getNombre().toUpperCase()+ " " +cursoUi.getPeriodo() +" - " +cursoUi.getSeccion());

    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        this.evaluacionProcesoId= extras.getString(InfoRubroFragment.ID_EVALUACION_PROCESO);
        this.cargaCursoId= extras.getInt(InfoRubroFragment.ID_CARGA_CURSO);
        Log.d(TAG,"idRubroProcesoId "+ evaluacionProcesoId + " cargaCursoId "+ cargaCursoId);
        getInfoRubro();
    }
}
