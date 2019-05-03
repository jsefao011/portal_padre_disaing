package com.consultoraestrategia.ss_crmeducativo.portal.asistencia.presenter;

import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.base.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.asistenciaColegio.AsistenciaColegioFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.asistenciaCurso.AsistenciaCursoFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.ui.AsistenciaFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.ui.AsistenciaView;

public interface AsistenciaPresenter extends BaseFragmentPresenter<AsistenciaView> {

    void onAttach(AsistenciaFragment asistenciaFragment, AsistenciaCursoFragment asistenciaCursoFragment, AsistenciaColegioFragment asistenciaColegioFragment);
    void onChildsFragmentViewCreated();
    void onDestroyTabAsistenciaCurso();
    void onDestroyTabAsistenciaColegio();

    void onPeriodoSelected(PeriodoUi periodoUi);
}
