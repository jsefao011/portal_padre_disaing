package com.consultoraestrategia.ss_crmeducativo.portal.main.view;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.portal.main.MainPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.HijoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.ProgramaEducativoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.wrapper.MainParametrosGlobales;

import java.util.List;

public interface MainView extends BaseView<MainPresenter> {
    void showMenuList(List<Object> objects);

    void MenuViewNotifyDataSetChanged();
    void initFragmentEstudianteTarea(MainParametrosGlobales mainParametrosGlobales);
    void initFragmentEstudianteAsistencia(MainParametrosGlobales mainParametrosGlobales);
    void initFragmentEstudianteConducta(MainParametrosGlobales mainParametrosGlobales);
    void initFragmentEstudianteEstadoCuenta(MainParametrosGlobales mainParametrosGlobales);
    void initFragmentEstudianteCurso(MainParametrosGlobales mainParametrosGlobales);
    void initFragmentColegioEvento(MainParametrosGlobales mainParametrosGlobales);
    void initFragmentFamiliaActualizarPerfil(MainParametrosGlobales mainParametrosGlobales);
    void initFragmentFamiliaInfografia(MainParametrosGlobales mainParametrosGlobales);

    void initFragmentColegioCalendario(MainParametrosGlobales mainParametrosGlobales);

    void starLoginActivity();

    void close();

    void initFragmentEstudianteRubros(MainParametrosGlobales mainParametrosGlobales);

    void initFragmentFamiliaPerfilFamiliar(MainParametrosGlobales mainParametrosGlobales);

    void initFragmentColegioDirectorio(MainParametrosGlobales mainParametrosGlobales);

    void setLogoPadre(char etiqueta, String url_imagen);

    void setLogoHijo(char etiqueta, String url_imagen);

    void setUnknowHijo();

    void showFragmentListHijoSelected(List<HijoUi> hijoUiList);

    void setCalendarioPeriodo(String nombre);

    void showPopListProgramaEduc(List<ProgramaEducativoUi> programaEducativoUiList);
}
