package com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasCurso.presenter;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CellUI2;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CellUI4;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CursoTareasUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CellUI3;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TipoTareaUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.tareasCurso.view.TareaCursoView;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.useCase.GetTareasCursoAlumno;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.TabsTareasListPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.TabsTareasListaView;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

public class TareaCursoPresenterImpl extends TabsTareasListPresenterImpl implements TareaCursoPresenter {

    String TAG= TareaCursoPresenterImpl.class.getSimpleName();
    TareaCursoView view;
    public TareaCursoPresenterImpl(UseCaseHandler handler, GetTareasCursoAlumno getTareasCursoAlumno) {
        super(handler, getTareasCursoAlumno);
    }

    @Override
    public void attachView(TabsTareasListaView view) {
        super.attachView(view);
        this.view= (TareaCursoView) view;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initC();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initC() {
        List<Persona> persona = SQLite.select().from(Persona.class).queryList();
        Log.d(getClass().getSimpleName(), "personas "+ persona.size());
        List<CursoTareasUi> cursoTareasUiList= new ArrayList<>();


        //crear filas
        List<TareasUi> fila = new ArrayList<>();
        TareasUi tareasUi= new TareasUi();
        tareasUi.setId(1);
        tareasUi.setDescripcion("Si se entrega luego de la fecha asignada no se revisara");
        tareasUi.setDia("Martes");
        tareasUi.setFecha(23);
        tareasUi.setMes("Octubre");
        tareasUi.setNombre("Elaborar un mapa conceptual de funciones.");
        tareasUi.setEstado(0);
        CellUI3 tareas1= new CellUI3();
        tareas1.setId(1);
        tareas1.setFecha("Lun, 30 Sep");
        fila.add(tareasUi);
        TareasUi tareasUi1= new TareasUi();
        tareasUi1.setId(2);
        tareasUi1.setDescripcion("Si se entrega luego de la fecha asignada no se revisara");
        tareasUi1.setDia("Miercoles");
        tareasUi1.setFecha(15);
        tareasUi1.setMes("Setiembre");
        tareasUi1.setNombre("Resolver los casos del libro de actividades pag.27 y 29.");
        tareasUi1.setEstado(0);
        CellUI3 tareas2= new CellUI3();
        tareas2.setId(2);
        tareas2.setFecha("Mar, 12 Sep");
        fila.add(tareasUi1);
        TareasUi tareasUi2= new TareasUi();
        tareasUi2.setId(3);
        tareasUi2.setDescripcion("Si se entrega luego de la fecha asignada no se revisara");
        tareasUi2.setDia("JUeves");
        tareasUi2.setFecha(9);
        tareasUi2.setMes("Setiembre");
        tareasUi2.setNombre("Resolver los casos del libro de actividades pag.27 y 29.");
        tareasUi2.setEstado(3);
        CellUI3 tareas3= new CellUI3();
        tareas3.setId(3);
        tareas3.setFecha("Mar, 08 Sep");
        fila.add(tareasUi2);
        TareasUi tareasUi3= new TareasUi();
        tareasUi3.setId(4);
        tareasUi3.setDescripcion("Si se entrega luego de la fecha asignada no se revisara");
        tareasUi3.setDia("Domingo");
        tareasUi3.setFecha(26);
        tareasUi3.setMes("Setiembre");
        tareasUi3.setNombre("Elaborar un mapa conceptual de funciones");
        tareasUi3.setEstado(2);
        CellUI3 tareas4= new CellUI3();
        tareas4.setId(4);
        tareas4.setFecha("Vier, 12 Octubre");
        fila.add(tareasUi3);
        TareasUi tareasUi4= new TareasUi();
        tareasUi4.setId(5);
        tareasUi4.setDescripcion("Se revisrar en el salon de clases");
        tareasUi4.setDia("Lunes");
        tareasUi4.setFecha(11);
        tareasUi4.setEstado(0);
        tareasUi4.setMes("Setiembre");
        tareasUi4.setNombre("Desarrolla ejercicios del libro");
        CellUI3 tareas5= new CellUI3();
        tareas5.setId(5);
        tareas5.setFecha("Mar, 12 Sep");
        fila.add(tareasUi4);

        //crear columnas
        List<List<Object>> cellslists = new ArrayList<>();


        List<TareasUi>listar= new ArrayList<>();
        List<TareasUi>listap= new ArrayList<>();
        List<TareasUi>listat= new ArrayList<>();
        int countRe=0;
        int countPe=0;

        for(TareasUi t: fila){

            switch (t.getEstado()){
                case 2:
                    listap.add(t);
                    countPe++;
                    break;
                case 3:
                    listar.add(t);
                    countRe++;
                    break;
                 default:
                     listat.add(t);
                     break;
            }
        }

        List<TipoTareaUi> columna = new ArrayList<>();

        TipoTareaUi tipoTareaUi= new TipoTareaUi(1, fila.size(),"Asiganadas", "#444547", fila);
        columna.add(tipoTareaUi);
        TipoTareaUi tipoTareaUi1= new TipoTareaUi(2, countRe,"Retrasadas", "#d33321", listar);
        columna.add(tipoTareaUi1);
        TipoTareaUi tipoTareaUi2= new TipoTareaUi(3, countPe,"Pendientes", "#444547", listap);
        columna.add(tipoTareaUi2);
        TipoTareaUi tipoTareaUi3= new TipoTareaUi(4, fila.size()-countPe,"Entregadas", "#444547", listat);
        columna.add(tipoTareaUi3);

        //cell1
        List<Object>lista3= new ArrayList<>();
       CellUI4 cellUI4 = new CellUI4(1, " icono ");
        lista3.add(tareasUi);
        lista3.add(new CellUI2());
        lista3.add(tareas1);
        lista3.add(cellUI4);
        cellslists.add(lista3);
        //cell2*/
       List<Object>lista6= new ArrayList<>();
        lista6.add(tareasUi1);
        lista6.add(new CellUI2());
        lista6.add(tareas2);
        lista6.add(cellUI4);
        cellslists.add(lista6);
        //cell3
        List<Object>lista7= new ArrayList<>();
        lista7.add(tareasUi2);
        lista7.add(new CellUI2(1, "5 dias"));
        lista7.add(new CellUI3());
        lista7.add(new CellUI4());
        cellslists.add(lista7);
        //cell4
        List<Object>lista8= new ArrayList<>();
        lista8.add(tareasUi3);
        lista8.add(new CellUI2());
        lista8.add(tareas4);
        lista8.add(cellUI4);
        cellslists.add(lista8);
        //cell5
        List<Object>lista9= new ArrayList<>();
        lista9.add(tareasUi4);
        lista9.add(new CellUI2());
        lista9.add(tareas5);
        lista9.add(cellUI4);
        cellslists.add(lista9);

        //lista de cursos con tareas
        CursoTareasUi c1 = new CursoTareasUi(1," Matematica ", " Guillermo Apaza Daniel ",fila,columna,cellslists, "#7df442");
        cursoTareasUiList.add(c1);
        CursoTareasUi c2 =new CursoTareasUi(1," Comunicacion ", " Alvarado Dnte Camilo ",fila,columna,cellslists, "#0083ff");
        cursoTareasUiList.add(c2);
        if(view!=null)  view.showTabletareas(cursoTareasUiList);

    }

}
