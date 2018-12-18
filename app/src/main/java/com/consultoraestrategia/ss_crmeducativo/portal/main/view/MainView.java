package com.consultoraestrategia.ss_crmeducativo.portal.main.view;

import com.consultoraestrategia.ss_crmeducativo.portal.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.portal.main.MainPresenter;

import java.util.List;

public interface MainView extends BaseView<MainPresenter> {
    void showMenuList(List<Object> objects);

    void MenuViewNotifyDataSetChanged();
    void initFragmentEstudianteTarea();
    void initFragmentEstudianteAsistencia();
    void initFragmentEstudianteConducta();
    void initFragmentEstudianteEstadoCuenta();
    void initFragmentEstudianteCurso();
    void initFragmentColegioEvento();

    void initFragmentColegioCalendario();
}
