package com.consultoraestrategia.ss_crmeducativo.portal.programahorario;


import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.ProgramaHorarioUi;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.ui.ProgramaHorarioView;

public interface ProgramaHorarioPresenter extends BaseFragmentPresenter<ProgramaHorarioView> {
    void ProgramaHorarioUi(ProgramaHorarioUi programaHorarioUi);
    void postShowHorario();

}
