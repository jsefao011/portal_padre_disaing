package com.consultoraestrategia.ss_crmeducativo.portal.main;

import android.content.res.Resources;
import android.util.Log;
import android.util.SparseBooleanArray;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.GlobalSettings;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.ItemMenuUI;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.TipoMenu;
import com.consultoraestrategia.ss_crmeducativo.portal.main.view.MainView;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

import java.util.ArrayList;
import java.util.List;

public class MainPresenterImpl extends BasePresenterImpl<MainView> implements MainPresenter{
    private ItemMenuUI itemMenuUI;
    private ArrayList<ItemMenuUI> configuracionUiList;
    private ArrayList<ItemMenuUI> configuracionUiListEstudiante;
    private ArrayList<ItemMenuUI> configuracionUiListFamilia;

    public MainPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(SessionUser.getCurrentUser()==null){
            if(view!=null)view.starLoginActivity();
            if(view!=null)view.close();
        }

        if(view!=null)view.initFragmentEstudianteTarea();

        configuracionUiList = new ArrayList<>();
        configuracionUiList.add(new ItemMenuUI(TipoMenu.COLEGIO_EVENTO,"Evento", false));
        configuracionUiList.add(new ItemMenuUI(TipoMenu.COLEGIO_CALENDARIO,"Calendario", false));
        configuracionUiList.add(new ItemMenuUI(TipoMenu.COLEGIO_DIRECTORIO,"Directorio", false));

        configuracionUiListEstudiante = new ArrayList<>();
        itemMenuUI = new ItemMenuUI(TipoMenu.ESTUDIANTE_TAREAS,"Tareas", true);
        configuracionUiListEstudiante.add(itemMenuUI);
        configuracionUiListEstudiante.add(new ItemMenuUI(TipoMenu.ESTUDIANTE_RUBROS,"Rubros", false));
        configuracionUiListEstudiante.add(new ItemMenuUI(TipoMenu.ESTUDIANTE_ASISTENCIA,"Asistencia", false));
        configuracionUiListEstudiante.add(new ItemMenuUI(TipoMenu.ESTUDIANTE_CONDUCTA,"Comportamiento", false));
        configuracionUiListEstudiante.add(new ItemMenuUI(TipoMenu.ESTUDIANTE_ESTADOCUENTA,"Estado de cuenta", false));
        configuracionUiListEstudiante.add(new ItemMenuUI(TipoMenu.ESTUDIANTE_CURSO,"Cursos", false));

        configuracionUiListFamilia = new ArrayList<>();
        configuracionUiListFamilia.add(new ItemMenuUI(TipoMenu.FAMILIA_ACTUALIZAR_PERFIL, "Actualizar Perfil", false));
        configuracionUiListFamilia.add(new ItemMenuUI(TipoMenu.FAMILIA_INFOGRAFIA, "Infografia", false));

        showListInfoEstudiante();


    }

    @Override
    protected String getTag() {
        return getClass().getSimpleName();
    }



    @Override
    public void onClickBtnInfoColegio() {
       showListInfoColegio();
    }

    private void showListInfoColegio() {
        if(view != null)view.showMenuList(new ArrayList<Object>(configuracionUiList));
    }

    @Override
    public void onClickBtnInfoEstudiante() {
        showListInfoEstudiante();
    }

    private void showListInfoEstudiante(){
        if(view != null)view.showMenuList(new ArrayList<Object>(configuracionUiListEstudiante));
    }

    private void showListInfoFamilia(){
        if(view != null)view.showMenuList(new ArrayList<Object>(configuracionUiListFamilia));
    }

    @Override
    public void onClickBtnInfoFamilia() {
        showListInfoFamilia();
    }

    @Override
    public void onMenuSelected(Object o) {
        if(!(o instanceof ItemMenuUI))return;
        if( this.itemMenuUI != null)itemMenuUI.setSeleccionado(false);

        ItemMenuUI itemMenuUI = (ItemMenuUI)o;
        itemMenuUI.setSeleccionado(true);
        this.itemMenuUI = itemMenuUI;
        if (view != null) {
            view.MenuViewNotifyDataSetChanged();
        }

        switch (itemMenuUI.getTipoMenu()){
            case COLEGIO_EVENTO:
                if(view!=null)view.initFragmentColegioEvento();
                break;
            case COLEGIO_CALENDARIO:
                if(view!=null)view.initFragmentColegioCalendario();
                break;
            case COLEGIO_DIRECTORIO:

                break;
            case ESTUDIANTE_TAREAS:
                if(view!=null)view.initFragmentEstudianteTarea();
                break;
            case ESTUDIANTE_ASISTENCIA:
                if(view!=null)view.initFragmentEstudianteAsistencia();
                break;
            case ESTUDIANTE_CONDUCTA:
                if(view!=null)view.initFragmentEstudianteConducta();
                break;
            case ESTUDIANTE_ESTADOCUENTA:
                if(view!=null)view.initFragmentEstudianteEstadoCuenta();
                break;
            case ESTUDIANTE_CURSO:
                if(view!=null)view.initFragmentEstudianteCurso();
                break;
            case ESTUDIANTE_RUBROS:
                if(view!=null)view.initFragmentEstudianteRubros();
                break;
            case FAMILIA_ACTUALIZAR_PERFIL:
                if(view!=null)view.initFragmentFamiliaActualizarPerfil();
                break;
            case FAMILIA_INFOGRAFIA:
                if(view!=null)view.initFragmentFamiliaInfografia();
                break;
        }



    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }
}
