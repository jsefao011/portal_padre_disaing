package com.consultoraestrategia.ss_crmeducativo.portal.programahorario.ui;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.ProgramaHorarioPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.DiaUi;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.HoraUi;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.ProgramaHorarioUi;

import java.util.List;

public interface ProgramaHorarioView extends BaseView<ProgramaHorarioPresenter> {
    void showHorario(List<DiaUi> columna, List<HoraUi> fila, List<List<Object>> celdaHorarioUiListList);
    void showListaProgramaEducativo(List<ProgramaHorarioUi> programaHorarioUiList);
    void showCurso(List<CursoUi> cursoUiList);


}
