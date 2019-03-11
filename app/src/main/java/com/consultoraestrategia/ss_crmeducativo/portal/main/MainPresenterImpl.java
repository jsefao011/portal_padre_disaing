package com.consultoraestrategia.ss_crmeducativo.portal.main;

import android.content.res.Resources;
import android.util.Log;
import android.util.SparseBooleanArray;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.GlobalSettings;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.portal.main.domain.usecase.GetPadreMentor;
import com.consultoraestrategia.ss_crmeducativo.portal.main.domain.usecase.GetProgramaEducativoList;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.HijoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.ItemMenuUI;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.PadreMentorUi;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.ProgramaEducativoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.TipoMenu;
import com.consultoraestrategia.ss_crmeducativo.portal.main.view.MainView;
import com.consultoraestrategia.ss_crmeducativo.portal.wrapper.MainParametrosGlobales;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.ArrayList;
import java.util.List;

public class MainPresenterImpl extends BasePresenterImpl<MainView> implements MainPresenter{
    private ItemMenuUI itemMenuUI;
    private ArrayList<ItemMenuUI> configuracionUiList;
    private ArrayList<ItemMenuUI> configuracionUiListEstudiante;
    private ArrayList<ItemMenuUI> configuracionUiListFamilia;
    private List<HijoUi> hijoUiList =  new ArrayList<>();
    private HijoUi hijoUiSelected;
    private List<ProgramaEducativoUi> programaEducativoUiList = new ArrayList<>();
    private GetPadreMentor getPadreMentor;
    private GetProgramaEducativoList getProgramaEducativoList;
    private PadreMentorUi padreMentor;
    private ProgramaEducativoUi programaEducativoSelected;
    private MainParametrosGlobales mainParametrosGlobales = new MainParametrosGlobales();

    public MainPresenterImpl(UseCaseHandler handler, Resources res, GetPadreMentor getPadreMentor, GetProgramaEducativoList getProgramaEducativoList) {
        super(handler, res);
        this.getPadreMentor = getPadreMentor;
        this.getProgramaEducativoList = getProgramaEducativoList;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        SessionUser sessionUser = SessionUser.getCurrentUser();
        if(sessionUser==null){
            if(view!=null)view.starLoginActivity();
            if(view!=null)view.close();
            return;
        }

        getPadre(sessionUser.getUserId());
        getHijos();
        getProgramaEducativo();
        setupMenu();
        setupAccesos();
    }

    private void getProgramaEducativo() {
        this.programaEducativoUiList.clear();
        this.programaEducativoSelected = null;
        if(hijoUiSelected!=null){
            List<ProgramaEducativoUi> programaEducativoUiList = getProgramaEducativoList.execute(hijoUiSelected.getPersonaId());
            if(programaEducativoUiList!=null)this.programaEducativoUiList.addAll(programaEducativoUiList);
            for (ProgramaEducativoUi programaEducativoUi: this.programaEducativoUiList){
                if(programaEducativoUi.isSelected())this.programaEducativoSelected = programaEducativoUi;
            }
        }

    }

    private void getHijos() {
        hijoUiSelected = null;
        hijoUiList.clear();
        if(padreMentor!=null&&padreMentor.getHijoUiList()!=null){
            hijoUiList.addAll(padreMentor.getHijoUiList());
            for (HijoUi hijoUi : hijoUiList){
                if(hijoUi.isSelected())hijoUiSelected = hijoUi;
            }
        }
    }

    private void setupMenu() {
        if(padreMentor!=null)if(view!=null)view.setLogoPadre(padreMentor.getEtiqueta(), padreMentor.getUrl_imagen());
        setupHijo();
        setupProgramaEducativo();
    }

    private void setupHijo() {
        if(hijoUiSelected!=null){
            if(view!=null)view.setLogoHijo(hijoUiSelected.getEtiqueta(), hijoUiSelected.getUrl_imagen());
        }else {
            if(view!=null)view.setUnknowHijo();
        }
    }

    private void getPadre(int userId) {
        padreMentor = getPadreMentor.execute(new GetPadreMentor.Request(userId));
    }

    private void setupAccesos() {

        configuracionUiList = new ArrayList<>();
        configuracionUiList.add(new ItemMenuUI(TipoMenu.COLEGIO_EVENTO,"Evento", true, R.drawable.ic_evento));
        configuracionUiList.add(new ItemMenuUI(TipoMenu.COLEGIO_CALENDARIO,"Calendario", false,R.drawable.ic_calendario));
        configuracionUiList.add(new ItemMenuUI(TipoMenu.COLEGIO_DIRECTORIO,"Agenda", false,R.drawable.ic_agenda));

        configuracionUiListEstudiante = new ArrayList<>();
        configuracionUiListEstudiante.add(new ItemMenuUI(TipoMenu.ESTUDIANTE_TAREAS,"Tareas", false, R.drawable.ic_portafolio));
        configuracionUiListEstudiante.add(new ItemMenuUI(TipoMenu.ESTUDIANTE_RUBROS,"Evaluación", false, R.drawable.ic_evaluacion));
        configuracionUiListEstudiante.add(new ItemMenuUI(TipoMenu.ESTUDIANTE_ASISTENCIA,"Asistencia", false, R.drawable.ic_evento));
        configuracionUiListEstudiante.add(new ItemMenuUI(TipoMenu.ESTUDIANTE_CONDUCTA,"Comportamiento", false, R.drawable.ic_comportamiento));
        configuracionUiListEstudiante.add(new ItemMenuUI(TipoMenu.ESTUDIANTE_ESTADOCUENTA,"Estado de cuenta", false, R.drawable.ic_estodo_cuenta));
        configuracionUiListEstudiante.add(new ItemMenuUI(TipoMenu.ESTUDIANTE_CURSO,"Cursos", false,R.drawable.ic_cursos));

        configuracionUiListFamilia = new ArrayList<>();
        configuracionUiListFamilia.add(new ItemMenuUI(TipoMenu.FAMILIA_ACTUALIZAR_PERFIL, "Actualizar Perfil", false, R.drawable.ic_evento));
        configuracionUiListFamilia.add(new ItemMenuUI(TipoMenu.FAMILIA_INFOGRAFIA, "Infografia", false,R.drawable.ic_evento));
        configuracionUiListFamilia.add(new ItemMenuUI(TipoMenu.FAMILIA_PERFIL_FAMILIAR,"Perfil familiar", false, R.drawable.ic_portafolio));

        showListInfoColegio();

        List<ItemMenuUI> itemMenuUIList = new ArrayList<>();
        itemMenuUIList.addAll(configuracionUiList);
        itemMenuUIList.addAll(configuracionUiListEstudiante);
        itemMenuUIList.addAll(configuracionUiListFamilia);

        for (ItemMenuUI itemMenuUI: itemMenuUIList){
            if(itemMenuUI.isSeleccionado()){
                this.itemMenuUI = itemMenuUI;
            }
        }

        changeFrgment();
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
        if(view != null)view.showMenuList(new ArrayList<Object>(configuracionUiListFamilia));
    }

    @Override
    public void onMenuSelected(ItemMenuUI itemMenuUI) {
        if( this.itemMenuUI != null)this.itemMenuUI.setSeleccionado(false);
        itemMenuUI.setSeleccionado(true);
        this.itemMenuUI = itemMenuUI;
        if (view != null) {
            view.MenuViewNotifyDataSetChanged();
        }
        changeFrgment();
    }

    @Override
    public void onClickHijo() {
        List<HijoUi> hijoUiList = new ArrayList<>();
        for (HijoUi hijoUi: this.hijoUiList){
            if(!hijoUi.isSelected())hijoUiList.add(hijoUi);
        }
        if(view!=null) view.showFragmentListHijoSelected(hijoUiList);
    }

    @Override
    public void onClickSelectedHijo(HijoUi hijoUi) {
        if(hijoUiSelected!=null)hijoUiSelected.setSelected(false);
        hijoUi.setSelected(true);
        hijoUiSelected = hijoUi;
        setupHijo();
        getProgramaEducativo();
        setupProgramaEducativo();
        changeFrgment();
    }

    private void changeFrgment() {

        if(padreMentor!=null){
            mainParametrosGlobales.setPadre_mentor_usuarioId(padreMentor.getUsuarioId());
            mainParametrosGlobales.setPadre_mentor_personaId(padreMentor.getPersonaId());
            mainParametrosGlobales.setPadre_mentor_nombres(padreMentor.getNombres());
            mainParametrosGlobales.setPadre_mentor_apellidos(padreMentor.getApellidos());
            mainParametrosGlobales.setPadre_mentor_imagen(padreMentor.getUrl_imagen());
            List<Integer> hijoIdList = new ArrayList<>();
            for (HijoUi hijoUi: hijoUiList)hijoIdList.add(hijoUi.getPersonaId());
            mainParametrosGlobales.setPadre_mentor_list_hijos_persona_Id(hijoIdList);
        }

        if(hijoUiSelected!=null){
            mainParametrosGlobales.setHijo_selected_personaId(hijoUiSelected.getPersonaId());
            mainParametrosGlobales.setHijo_selected_usuarioId(hijoUiSelected.getUsuarioId());
            mainParametrosGlobales.setHijo_selected_nombres(hijoUiSelected.getNombres());
            mainParametrosGlobales.setHijo_selected_apellidos(hijoUiSelected.getApellidos());
            mainParametrosGlobales.setHijo_selected_imagen(hijoUiSelected.getUrl_imagen());
            List<Integer> programaEducativoIdList = new ArrayList<>();
            for (ProgramaEducativoUi programaEducativoUi: programaEducativoUiList)programaEducativoIdList.add(programaEducativoUi.getId());
            mainParametrosGlobales.setPadre_mentor_list_hijos_persona_Id(programaEducativoIdList);
        }

        if(programaEducativoSelected!=null){
            mainParametrosGlobales.setHijo_programa_educativo_Id(programaEducativoSelected.getId());
        }

        if(itemMenuUI==null)return;
        switch (itemMenuUI.getTipoMenu()){
            case COLEGIO_EVENTO:
                if(view!=null)view.initFragmentColegioEvento(mainParametrosGlobales);
                break;
            case COLEGIO_CALENDARIO:
                if(view!=null)view.initFragmentColegioCalendario(mainParametrosGlobales);
                break;
            case COLEGIO_DIRECTORIO:
                if(view!=null)view.initFragmentColegioDirectorio(mainParametrosGlobales);
                break;
            case ESTUDIANTE_TAREAS:
                if(view!=null)view.initFragmentEstudianteTarea(mainParametrosGlobales);
                break;
            case ESTUDIANTE_ASISTENCIA:
                if(view!=null)view.initFragmentEstudianteAsistencia(mainParametrosGlobales);
                break;
            case ESTUDIANTE_CONDUCTA:
                if(view!=null)view.initFragmentEstudianteConducta(mainParametrosGlobales);
                break;
            case ESTUDIANTE_ESTADOCUENTA:
                if(view!=null)view.initFragmentEstudianteEstadoCuenta(mainParametrosGlobales);
                break;
            case ESTUDIANTE_CURSO:
                if(view!=null)view.initFragmentEstudianteCurso(mainParametrosGlobales);
                break;
            case ESTUDIANTE_RUBROS:
                if(view!=null)view.initFragmentEstudianteRubros(mainParametrosGlobales);
                break;
            case FAMILIA_ACTUALIZAR_PERFIL:
                if(view!=null)view.initFragmentFamiliaActualizarPerfil(mainParametrosGlobales);
                break;
            case FAMILIA_INFOGRAFIA:
                if(view!=null)view.initFragmentFamiliaInfografia(mainParametrosGlobales);
                break;
            case FAMILIA_PERFIL_FAMILIAR:
                if(view!=null)view.initFragmentFamiliaPerfilFamiliar(mainParametrosGlobales);
                break;
        }
    }

    private void setupProgramaEducativo() {
        if(programaEducativoSelected!=null){
            if(view!=null)view.setCalendarioPeriodo(programaEducativoSelected.getNombre());
        }else {
            if(view!=null)view.setCalendarioPeriodo("");
        }


    }

    @Override
    public void onClickedProgramaEducativo() {
        if(view!=null)view.showPopListProgramaEduc(programaEducativoUiList);
    }

    @Override
    public void onSelectedProgramaEduca(ProgramaEducativoUi programaEducativoUi) {
        if(programaEducativoSelected!=null)programaEducativoSelected.setSelected(false);
        programaEducativoUi.setSelected(true);
        programaEducativoSelected = programaEducativoUi;
        setupProgramaEducativo();
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }
}
