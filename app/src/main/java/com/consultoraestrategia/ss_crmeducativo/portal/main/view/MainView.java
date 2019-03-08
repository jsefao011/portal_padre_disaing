package com.consultoraestrategia.ss_crmeducativo.portal.main.view;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.portal.main.MainPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.HijoUi;

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
    void initFragmentFamiliaActualizarPerfil();
    void initFragmentFamiliaInfografia();

    void initFragmentColegioCalendario();

    void starLoginActivity();

    void close();

    void initFragmentEstudianteRubros();

    void initFragmentFamiliaPerfilFamiliar();

    void initFragmentColegioDirectorio();

    void setLogoPadre(char etiqueta, String url_imagen);

    void setLogoHijo(char etiqueta, String url_imagen);

    void setUnknowHijo();

    void showFragmentListHijoSelected(List<HijoUi> hijoUiList);

    void setCalendarioPeriodo(String nombre);
}
