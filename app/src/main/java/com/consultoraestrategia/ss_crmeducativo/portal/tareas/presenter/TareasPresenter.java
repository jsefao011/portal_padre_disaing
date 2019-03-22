package com.consultoraestrategia.ss_crmeducativo.portal.tareas.presenter;

import com.consultoraestrategia.ss_crmeducativo.base.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.TareaView;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.TareasFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasCurso.FragmentTareasCurso;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasGenerales.FragmentTareasGenerales;


public interface TareasPresenter extends BaseFragmentPresenter<TareaView> {
    void onPageChanged(int position);
    void onAttach(TareasFragment tareasFragment, FragmentTareasCurso fragmentTareasCurso, FragmentTareasGenerales fragmentTareasGenerales);
    void onChildsFragmentViewCreated();

    void onDestroyTabTareaCurso();

    void onDestroyTabTareaGeneral();
    void onClickRubroInformacion(TareasUi tareasUi);
}
