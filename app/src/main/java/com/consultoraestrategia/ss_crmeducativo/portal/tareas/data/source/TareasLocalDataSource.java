package com.consultoraestrategia.ss_crmeducativo.portal.tareas.data.source;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.curso.CursoDao;
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
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.FechaUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareaUiCount;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.ValorTipoNotaUi;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TareasLocalDataSource implements TareasDataSource {
String TAG= TareasLocalDataSource.class.getSimpleName();

CursoDao cursoDao;

    public TareasLocalDataSource(CursoDao cursoDao) {
        this.cursoDao = cursoDao;
    }
    @Override
    public void getTareasGenerales(int alumnoId,  SucessCallback<List<Object>>listSucessCallback) {

        Log.d(TAG, "alumnoId "+alumnoId);
        List<Object>objectList= new ArrayList<>();
        List<CargaCursos> cargaCursosList= SQLite.select()
                .from(CargaCursos.class)
                .innerJoin(DetalleContratoAcad.class)
                .on(CargaCursos_Table.cargaCursoId.withTable().eq(DetalleContratoAcad_Table.cargaCursoId.withTable()))
                .innerJoin(Contrato.class)
                .on(DetalleContratoAcad_Table.idContrato.withTable().eq(Contrato_Table.idContrato.withTable()))
                .where(Contrato_Table.personaId.withTable().eq(alumnoId))
                .and(Contrato_Table.vigente.eq(1)).queryList();

        Log.d(TAG, "cargaCursosList "+ cargaCursosList.size());

        for(CargaCursos cargaCursos: cargaCursosList){
            TareasUi tareasUi= new TareasUi();
            Log.d(TAG, "cargaCursos id "+ cargaCursos.getCargaCursoId());
            //traer docente
            Persona persona = SQLite.select().from(Persona.class)
                    .innerJoin(Empleado.class)
                    .on(Persona_Table.personaId.withTable().eq(Empleado_Table.personaId.withTable()))
                    .where(Empleado_Table.empleadoId.withTable().eq(cargaCursos.getEmpleadoId())).querySingle();
            if(persona!=null)tareasUi.setDocente(persona.getNombreCompleto());
            //traer curso;
            List<Integer>integerList= new ArrayList<>();
            integerList.add(cargaCursos.getCargaCursoId());
            List<CursoCustom>cursoCustomList= cursoDao.obtenerPorCargaCursos(integerList);
            for(CursoCustom cursoCustom:cursoCustomList ){
                Log.d(TAG, "curso nombre "+ cursoCustom.getNombre());
                tareasUi.setCurso(cursoCustom.getNombre());
            }
            //traer tareas
            List<TareasC > listaTareasCList= SQLite.select()
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
                    .where(CargaCursos_Table.cargaCursoId.withTable().eq(cargaCursos.getCargaCursoId()))
                .orderBy(TareasC_Table.fechaAccion.asc())
                .queryList();
            int countPorEntregas=0;
            int countCalificadas=0;
            Log.d(TAG, "listaTareasCList "+listaTareasCList.size());
            for(TareasC tareasC: listaTareasCList){
                tareasUi.setNombre(tareasC.getTitulo());
                //tareasUi.setId(tareasC.getKey());
                //traer fecha tarea
                tareasUi.setFechaUiInicio(getFecha(tareasC.getFechaCreacion()));
                tareasUi.setFechaUiFin(getFecha(tareasC.getFechaEntrega()));
                //traer tipo nota
                ValorTipoNotaC valorTipoNotaC= SQLite.select().from(ValorTipoNotaC.class)
                        .innerJoin(EvaluacionProcesoC.class)
                        .on(ValorTipoNotaC_Table.valorTipoNotaId.withTable()
                                .eq(EvaluacionProcesoC_Table.valorTipoNotaId.withTable()))
                        .innerJoin(RubroEvaluacionProcesoC.class)
                        .on(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable()
                                .eq(RubroEvaluacionProcesoC_Table.key.withTable()))
                        .innerJoin(TareaRubroEvaluacionProceso.class)
                        .on(RubroEvaluacionProcesoC_Table.key.withTable()
                                .eq(TareaRubroEvaluacionProceso_Table.rubroEvalProcesoId.withTable()))
                        .where(TareaRubroEvaluacionProceso_Table.tareaId.withTable().eq(tareasC.getTareaId()))
                        .and(EvaluacionProcesoC_Table.alumnoId.withTable().eq(alumnoId))
                        .querySingle();
                Log.d(TAG, "VALOR TIPO NOTA  "+ valorTipoNotaC.getAlias());
                if(valorTipoNotaC !=null){
                    ValorTipoNotaUi valorTipoNotaUi= new ValorTipoNotaUi();
                    valorTipoNotaUi.setAlias(valorTipoNotaC.getAlias());
                    valorTipoNotaUi.setUrl(valorTipoNotaC.getIcono());
                    tareasUi.setValorTipoNotaUi(valorTipoNotaUi);
                    countCalificadas++;
                }
                objectList.add(tareasUi);
                Log.d(TAG, "countCalificadas "+ countCalificadas);
                Log.d(TAG, "por entregar   "+ (listaTareasCList.size()-countCalificadas));
            }

            //
        }
        listSucessCallback.onLoad(true, objectList);

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
        return fechaUi;
    }
}
