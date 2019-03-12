package com.consultoraestrategia.ss_crmeducativo.portal.tareas.data.source;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.curso.CursoDao;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TareaRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.entities.TareaRubroEvaluacionProceso_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.CursoCustom;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.FechaUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareaUiCount;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.internal.Util;

public class TareasLocalDataSource implements TareasDataSource {
String TAG= TareasLocalDataSource.class.getSimpleName();

CursoDao cursoDao;

    public TareasLocalDataSource(CursoDao cursoDao) {
        this.cursoDao = cursoDao;
    }
    @Override
    public void getTareasGenerales(int alumnoId,  SucessCallback<List<Object>>listSucessCallback) {

     //   Log.d(TAG, "alumnoId "+alumnoId);
        List<Object>objectList= new ArrayList<>();
        List<CargaCursos> cargaCursosList= getcargaCursosLIst(alumnoId);
        //Log.d(TAG, "cargaCursosList "+ cargaCursosList.size());
        int countCalificadas=0;
        int total=0;
        for(CargaCursos cargaCursos: cargaCursosList){
          // Log.d(TAG, "cargaCursos  id empleado "+ cargaCursos.getEmpleadoId() + "carga id "+ cargaCursos.getCargaCursoId());
           //traer tareas
            List<TareasC > listaTareasCList= gettareasList(cargaCursos.getCargaCursoId());
           // Log.d(TAG, "listaTareasCList "+listaTareasCList.size());
            for(TareasC tareasC: listaTareasCList){
                total++;
                Log.d(TAG, "tareasC titulo  "+tareasC.getTitulo()+ " id tarea "+ tareasC.getTareaId());
                TareasUi tareasUi= new TareasUi();
                Persona persona;
                From<Persona> personaWhere = SQLite.select(Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES))
                        .from(Persona.class).
                        innerJoin(Empleado.class)
                        .on(Persona_Table.personaId.withTable().eq(Empleado_Table.personaId.withTable()));
                if(cargaCursos.getComplejo()!=0){
                     persona = personaWhere.innerJoin(CargaCursoDocente.class)
                            .on(Empleado_Table.empleadoId.withTable()
                            .eq(CargaCursoDocente_Table.docenteId.withTable()))
                            .where(CargaCursoDocente_Table.cargaCursoId.withTable().eq(cargaCursos.getCargaCursoId())).querySingle();
                }else persona= personaWhere.where(Empleado_Table.empleadoId.withTable().eq(cargaCursos.getEmpleadoId())).querySingle();

                if(persona!=null){
                    Log.d(TAG, "docente "+persona.getNombreCompleto());
                    tareasUi.setDocente(persona.getNombreCompleto());
                }
                //traer docente

                //traer curso;
                List<Integer>integerList= new ArrayList<>();
                integerList.add(cargaCursos.getCargaCursoId());
                List<CursoCustom>cursoCustomList= cursoDao.obtenerPorCargaCursos(integerList);
                for(CursoCustom cursoCustom:cursoCustomList )tareasUi.setCurso(cursoCustom.getNombre());


                tareasUi.setNombre(tareasC.getTitulo());

                //traer fecha tarea
                tareasUi.setFechaUiInicio(getFecha(tareasC.getFechaCreacion()));
                tareasUi.setFechaUiFin(getFecha(tareasC.getFechaEntrega()));
                //traer tipo nota
                ValorTipoNotaC valorTipoNotaC=
                        SQLite.select(Utils.f_allcolumnTable(ValorTipoNotaC_Table.ALL_COLUMN_PROPERTIES))
                                .from(ValorTipoNotaC.class)
                                .innerJoin(EvaluacionProcesoC.class)
                                .on(ValorTipoNotaC_Table.valorTipoNotaId.withTable()
                                        .eq(EvaluacionProcesoC_Table.valorTipoNotaId.withTable()))
                                .innerJoin(RubroEvaluacionProcesoC.class)
                                .on(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable()
                                        .eq(RubroEvaluacionProcesoC_Table.key.withTable()))
                                .innerJoin(TareaRubroEvaluacionProceso.class)
                                .on(RubroEvaluacionProcesoC_Table.key.withTable()
                                        .eq(TareaRubroEvaluacionProceso_Table.rubroEvalProcesoId.withTable()))
                                .where(TareaRubroEvaluacionProceso_Table.tareaId.withTable()
                                        .eq(tareasC.getTareaId()))
                                .and(EvaluacionProcesoC_Table.alumnoId.withTable().eq(alumnoId))
                                .querySingle();
                //agregar el valor  tipo de nota de un rubro relacionado a la tareas
                if(valorTipoNotaC !=null){
                    ValorTipoNotaUi valorTipoNotaUi= new ValorTipoNotaUi();
                    Log.d(TAG, "VALOR icno   "+ valorTipoNotaC.getIcono());
                    valorTipoNotaUi.setAlias(valorTipoNotaC.getAlias());
                    valorTipoNotaUi.setUrl("http://192.168.1.151/SistemaAcademico/Images/Expresiones/Contento.png");
                    tareasUi.setValorTipoNotaUi(valorTipoNotaUi);
                    countCalificadas++;
                }
                //agregar el valor  tipo de nota de un rubro relacionado a la tareas
                objectList.add(tareasUi);
            }

        }
        //cabecera count de tareas
        TareaUiCount tareaUiCountAs= new TareaUiCount();
        tareaUiCountAs.setTipoTarea(TareaUiCount.Tipo.ASIGANADAS);
        tareaUiCountAs.setCantidad(total);
        objectList.add(tareaUiCountAs);

        TareaUiCount tareaUiCountporE= new TareaUiCount();
        tareaUiCountporE.setTipoTarea(TareaUiCount.Tipo.POR_ENTREGAR);
        tareaUiCountporE.setCantidad(total-countCalificadas);
        objectList.add(tareaUiCountporE);

        TareaUiCount tareaUiCountcal= new TareaUiCount();
        tareaUiCountcal.setTipoTarea(TareaUiCount.Tipo.CALIFICADAS);
        tareaUiCountcal.setCantidad(countCalificadas);
        objectList.add(tareaUiCountcal);

       listSucessCallback.onLoad(true, objectList);

    }

    @Override
    public void getTareasPorCurso(int alumnoId, SucessCallback<List<CursoUi>> listSucessCallback) {
        List<CursoUi>cursoUiList= new ArrayList<>();
        List<CargaCursos> cargaCursosList= getcargaCursosLIst(alumnoId);
        for(CargaCursos cargaCursos: cargaCursosList){
            CursoUi cursoUi= new CursoUi();
            int countCalificadas=0;
            int total=0;
            List<TareasUi>tareasUiList= new ArrayList<>();
            List<TareasC > listaTareasCList= gettareasList(cargaCursos.getCargaCursoId());
            for(TareasC tareasC: listaTareasCList){
                total++;
                Log.d(TAG, "tareasC titulo  "+tareasC.getTitulo()+ " id tarea "+ tareasC.getTareaId());
                TareasUi tareasUi= new TareasUi();
                Persona persona;
                From<Persona> personaWhere = SQLite.select(Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES))
                        .from(Persona.class).
                                innerJoin(Empleado.class)
                        .on(Persona_Table.personaId.withTable().eq(Empleado_Table.personaId.withTable()));
                if(cargaCursos.getComplejo()!=0){
                    persona = personaWhere.innerJoin(CargaCursoDocente.class)
                            .on(Empleado_Table.empleadoId.withTable()
                                    .eq(CargaCursoDocente_Table.docenteId.withTable()))
                            .where(CargaCursoDocente_Table.cargaCursoId.withTable().eq(cargaCursos.getCargaCursoId())).querySingle();
                }else persona= personaWhere.where(Empleado_Table.empleadoId.withTable().eq(cargaCursos.getEmpleadoId())).querySingle();

                if(persona!=null){
                    Log.d(TAG, "docente "+persona.getNombreCompleto());
                    tareasUi.setDocente(persona.getNombreCompleto());
                    cursoUi.setDocente(persona.getNombreCompleto());
                }
                //traer docente

                //traer curso;
                List<Integer>integerList= new ArrayList<>();
                integerList.add(cargaCursos.getCargaCursoId());
                List<CursoCustom>cursoCustomList= cursoDao.obtenerPorCargaCursos(integerList);
                for(CursoCustom cursoCustom:cursoCustomList ){
                    tareasUi.setCurso(cursoCustom.getNombre());
                    cursoUi.setCurso(cursoCustom.getNombre());
                }
                tareasUi.setNombre(tareasC.getTitulo());
                //traer fecha tarea
                tareasUi.setFechaUiInicio(getFecha(tareasC.getFechaCreacion()));
                tareasUi.setFechaUiFin(getFecha(tareasC.getFechaEntrega()));
                //traer tipo nota
                ValorTipoNotaC valorTipoNotaC=
                        SQLite.select(Utils.f_allcolumnTable(ValorTipoNotaC_Table.ALL_COLUMN_PROPERTIES))
                                .from(ValorTipoNotaC.class)
                                .innerJoin(EvaluacionProcesoC.class)
                                .on(ValorTipoNotaC_Table.valorTipoNotaId.withTable()
                                        .eq(EvaluacionProcesoC_Table.valorTipoNotaId.withTable()))
                                .innerJoin(RubroEvaluacionProcesoC.class)
                                .on(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable()
                                        .eq(RubroEvaluacionProcesoC_Table.key.withTable()))
                                .innerJoin(TareaRubroEvaluacionProceso.class)
                                .on(RubroEvaluacionProcesoC_Table.key.withTable()
                                        .eq(TareaRubroEvaluacionProceso_Table.rubroEvalProcesoId.withTable()))
                                .where(TareaRubroEvaluacionProceso_Table.tareaId.withTable()
                                        .eq(tareasC.getTareaId()))
                                .and(EvaluacionProcesoC_Table.alumnoId.withTable().eq(alumnoId))
                                .querySingle();
                //agregar el valor  tipo de nota de un rubro relacionado a la tareas
                if(valorTipoNotaC !=null){
                    ValorTipoNotaUi valorTipoNotaUi= new ValorTipoNotaUi();
                    Log.d(TAG, "VALOR icno   "+ valorTipoNotaC.getIcono());
                    valorTipoNotaUi.setAlias(valorTipoNotaC.getAlias());
                    valorTipoNotaUi.setUrl("http://192.168.1.151/SistemaAcademico/Images/Expresiones/Contento.png");
                    tareasUi.setValorTipoNotaUi(valorTipoNotaUi);
                    countCalificadas++;
                }
                //agregar el valor  tipo de nota de un rubro relacionado a la tareas
                tareasUiList.add(tareasUi);
            }
            if(tareasUiList.size()>0){
                List<TareaUiCount>tareaUiCountList= new ArrayList<>();
                TareaUiCount tareaUiCountAs= new TareaUiCount();
                tareaUiCountAs.setTipoTarea(TareaUiCount.Tipo.ASIGANADAS);
                tareaUiCountAs.setCantidad(total);
                tareaUiCountList.add(tareaUiCountAs);

                TareaUiCount tareaUiCountporE= new TareaUiCount();
                tareaUiCountporE.setTipoTarea(TareaUiCount.Tipo.POR_ENTREGAR);
                tareaUiCountporE.setCantidad(total-countCalificadas);
                tareaUiCountList.add(tareaUiCountporE);

                TareaUiCount tareaUiCountcal= new TareaUiCount();
                tareaUiCountcal.setTipoTarea(TareaUiCount.Tipo.CALIFICADAS);
                tareaUiCountcal.setCantidad(countCalificadas);
                tareaUiCountList.add(tareaUiCountcal);

                cursoUi.setTareasUiList(tareasUiList);
                cursoUi.setTareaUiCountList(tareaUiCountList);
                cursoUiList.add(cursoUi);
            }
        }

        listSucessCallback.onLoad(true, cursoUiList);


    }

    public  List<CargaCursos> getcargaCursosLIst(int alumnoId){
        List<CargaCursos> cargaCursosList=cargaCursosList= SQLite.select()
                .from(CargaCursos.class)
                .innerJoin(DetalleContratoAcad.class)
                .on(CargaCursos_Table.cargaCursoId.withTable().eq(DetalleContratoAcad_Table.cargaCursoId.withTable()))
                .innerJoin(Contrato.class)
                .on(DetalleContratoAcad_Table.idContrato.withTable().eq(Contrato_Table.idContrato.withTable()))
                .where(Contrato_Table.personaId.withTable().eq(alumnoId))
                .and(Contrato_Table.vigente.eq(1)).queryList();
        return cargaCursosList;
    }

    public  List<TareasC > gettareasList(int cargaCursoId){
        List<TareasC > listaTareasCList= SQLite.select(Utils.f_allcolumnTable(TareasC_Table.ALL_COLUMN_PROPERTIES))
                .from(TareasC.class).
                        innerJoin(UnidadAprendizaje.class)
                .on(TareasC_Table.unidadAprendizajeId.withTable()
                        .eq(UnidadAprendizaje_Table.unidadAprendizajeId.withTable()))
                .innerJoin(SilaboEvento.class)
                .on(UnidadAprendizaje_Table.silaboEventoId.withTable()
                        .eq(SilaboEvento_Table.silaboEventoId.withTable()))
                .innerJoin(CargaCursos.class)
                .on(SilaboEvento_Table.cargaCursoId.withTable()
                        .eq(CargaCursos_Table.cargaCursoId.withTable()))
                .where(CargaCursos_Table.cargaCursoId.withTable().eq(cargaCursoId))
                .orderBy(TareasC_Table.fechaAccion.withTable().asc())
                .queryList();
        return listaTareasCList;
    }



    public FechaUi getFecha(long timesTamp) {

        FechaUi fechaUi= new FechaUi();
        String[] vobj_days = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
        String[] vobj_Meses = {"Ene.", "Feb.", "Mar.", "Abr.", "May.", "Jun.", "Jul.", "Ago.", "Sep.", "Oct.", "Nov.", "Dic."};

        Date date = new Date(timesTamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        fechaUi.setFecha(dayOfMonth);
        fechaUi.setDia(vobj_days[dayOfWeek - 1]);
        fechaUi.setMes(vobj_Meses[month]);
        Log.d(TAG, "dayOfMonth "+ dayOfMonth+ " diasemana"+ vobj_days[dayOfWeek - 1]+ "mes "+vobj_Meses[month] );
        return fechaUi;
    }
}
