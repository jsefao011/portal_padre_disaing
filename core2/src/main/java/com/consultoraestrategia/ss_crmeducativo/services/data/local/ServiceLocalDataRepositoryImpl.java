package com.consultoraestrategia.ss_crmeducativo.services.data.local;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.dao.evaluacionProceso.EvaluacionProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.sessionUser.SessionUserDao;
import com.consultoraestrategia.ss_crmeducativo.entities.*;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosRubroEvaluacionProceso.local.BEDatosRubroEvaluacionProcesoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.ConsultaUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.TransaccionUtils;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosCargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosCasos;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioAsistencia;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioGrupo;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioHorario;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioMensajeria;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioTipoNota;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEvaluacionResultado;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosSesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEGuardarEntidadesGlobal;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEObtenerDatosLogin;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosEnvioAsistencia;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosSilaboEventoEnvio;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosTareasRecursos;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.google.gson.Gson;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Jse on 30/12/2018.
 */

public class ServiceLocalDataRepositoryImpl implements ServiceLocalDataRepository {

    private SessionUserDao sessionUserDao;
    private EvaluacionProcesoDao evaluacionProcesoDao;

    public ServiceLocalDataRepositoryImpl(SessionUserDao sessionUserDao) {
        this.sessionUserDao = sessionUserDao;
        evaluacionProcesoDao = InjectorUtils.provideEvaluacionProcesoDao();
    }


    @Override
    public void changeEstadoGlobal(BEGuardarEntidadesGlobal item, BERespuesta respuesta, int syncFlag, SuccessCallBack callBack) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();

            if(respuesta.getCommit_Asistencia()){
                GEDatosEnvioAsistencia geDatosEnvioAsistencia = item.getAsistencia();
                changeEstadoGlobals(geDatosEnvioAsistencia,syncFlag,databaseWrapper);
            }

            if(respuesta.getCommit_Grupo()){
                BEDatosEnvioGrupo beDatosEnvioGrupo = item.getGrupo();
                changeEstadoGlobals(beDatosEnvioGrupo,syncFlag,databaseWrapper);
            }

            if(respuesta.getCommit_Mensajes()){
                BEDatosEnvioMensajeria beDatosEnvioMensajeria = item.getMensajeria();
                changeEstadoGlobals(beDatosEnvioMensajeria,syncFlag,databaseWrapper);
            }

            if(respuesta.isCommit_RubroEvaluacionProceso()){
                GEDatosRubroEvaluacionProceso geDatosRubroEvaluacionProceso = item.getRubroEvaluacionProceso();
                changeEstadoGlobals(geDatosRubroEvaluacionProceso,syncFlag,databaseWrapper);
            }

            if(respuesta.isCommit_Sesion()){
                BEDatosSesionAprendizaje beDatosSesionAprendizaje = item.getSesionAprendizaje();
                changeEstadoGlobals(beDatosSesionAprendizaje,syncFlag,databaseWrapper);
            }

            if(respuesta.isCommit_TareaRecurso()){
                GEDatosTareasRecursos geDatosTareasRecursos = item.getTareaRecursos();
                changeEstadoGlobals(geDatosTareasRecursos,syncFlag,databaseWrapper);

            }

            databaseWrapper.setTransactionSuccessful();
            callBack.onResponse(true);
        } catch (Exception e){
            e.printStackTrace();
            callBack.onResponse(false);
        }finally {
            databaseWrapper.endTransaction();
        }
    }

    //#region changeEstadoGlobals
    //#region changeEstadoGlobals GEDatosEnvioAsistencia
    private void changeEstadoGlobals(GEDatosEnvioAsistencia geDatosEnvioAsistencia, int syncFlag, DatabaseWrapper databaseWrapper) {
        BEDatosEnvioAsistencia beDatosEnvioAsistencia = geDatosEnvioAsistencia.getBeDatosEnvioAsistencia();
        TransaccionUtils.fastStoreListSyncFlagUpdate(AsistenciaSesionAlumnoC.class, beDatosEnvioAsistencia.getAsistenciaAlumnos(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(JustificacionC.class, beDatosEnvioAsistencia.getObtenerjustificacion(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(ArchivoAsistencia.class, beDatosEnvioAsistencia.getArchivoAsistencia(), syncFlag,databaseWrapper, false);

        BEDatosEnvioTipoNota beDatosEnvioTipoNota = geDatosEnvioAsistencia.getBeDatosEnvioTipoNota();
        TransaccionUtils.fastStoreListSyncFlagUpdate(TipoNotaC.class, beDatosEnvioTipoNota.getTipoNota(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(ValorTipoNotaC.class, beDatosEnvioTipoNota.getValorTipoNota(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(RelProgramaEducativoTipoNota.class, beDatosEnvioTipoNota.getRel_Programa_Educativo_TipoNota(), syncFlag, databaseWrapper, false);
    }
    //#endregion changeEstadoGlobals GEDatosEnvioAsistencia

    //#region changeEstadoGlobals BEDatosEnvioGrupo
    private void changeEstadoGlobals(BEDatosEnvioGrupo beDatosEnvioGrupo, int syncFlag, DatabaseWrapper databaseWrapper) {
        TransaccionUtils.fastStoreListSyncFlagUpdate(GrupoEquipoC.class, beDatosEnvioGrupo.getGrupo_equipo(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(EquipoIntegranteC.class, beDatosEnvioGrupo.getEquipo_integrante(),syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(EquipoC.class, beDatosEnvioGrupo.getEquipo(), syncFlag, databaseWrapper, false);
    }
    //#endregion changeEstadoGlobals BEDatosEnvioGrupo

    //#region changeEstadoGlobals BEDatosEnvioMensajeria
    private void changeEstadoGlobals(BEDatosEnvioMensajeria beDatosEnvioMensajeria, int syncFlag, DatabaseWrapper databaseWrapper) {
        TransaccionUtils.fastStoreListSyncFlagUpdate(MensajeUsuarioC.class, beDatosEnvioMensajeria.getMensajeUsuario(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(MensajeIntencionItemC.class, beDatosEnvioMensajeria.getMensajeIntencionItem(),syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(CanalDestinoEstadoC.class, beDatosEnvioMensajeria.getCanalDestinoEstado(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(MensajeC.class, beDatosEnvioMensajeria.getMensajes(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(InteraccionTextual.class, beDatosEnvioMensajeria.getListInteraccionTextual(), syncFlag, databaseWrapper, false);
    }
    //#endregion changeEstadoGlobals BEDatosEnvioMensajeria

    //#region changeEstadoGlobals GEDatosRubroEvaluacionProceso
    private void changeEstadoGlobals(GEDatosRubroEvaluacionProceso geDatosRubroEvaluacionProceso, int syncFlag, DatabaseWrapper databaseWrapper) {
        BEDatosEnvioGrupo beDatosEnvioGrupo = geDatosRubroEvaluacionProceso.getBeDatosEnvioGrupo();
        TransaccionUtils.fastStoreListSyncFlagUpdate(GrupoEquipoC.class, beDatosEnvioGrupo.getGrupo_equipo(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(EquipoIntegranteC.class, beDatosEnvioGrupo.getEquipo_integrante(),syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(EquipoC.class, beDatosEnvioGrupo.getEquipo(), syncFlag, databaseWrapper, false);

        BEDatosEnvioTipoNota beDatosEnvioTipoNota = geDatosRubroEvaluacionProceso.getBeDatosEnvioTipoNota();
        TransaccionUtils.fastStoreListSyncFlagUpdate(TipoNotaC.class, beDatosEnvioTipoNota.getTipoNota(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(ValorTipoNotaC.class, beDatosEnvioTipoNota.getValorTipoNota(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(RelProgramaEducativoTipoNota.class, beDatosEnvioTipoNota.getRel_Programa_Educativo_TipoNota(), syncFlag,databaseWrapper, false);

        BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso = geDatosRubroEvaluacionProceso.getBeDatosRubroEvaluacionProceso();
        TransaccionUtils.fastStoreListSyncFlagUpdate(RubroEvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getRubroEvaluacionProceso(), syncFlag, databaseWrapper, true);
        TransaccionUtils.fastStoreListSyncFlagUpdate(RubroEvalRNPFormulaC.class, beDatosRubroEvaluacionProceso.getRubroEvalProcesoFormula(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class, beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoEquipo(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class, beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoIntegrante(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(EquipoEvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getObtenerEquipoEvaluacionProceso(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(RubroEvaluacionProcesoCampotematicoC.class, beDatosRubroEvaluacionProceso.getRubro_evaluacion_proceso_campotematico(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(CriterioRubroEvaluacionC.class, beDatosRubroEvaluacionProceso.getObtenerCriterioRubroEvaluacionProceso(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(EvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getEvaluacionProceso(), syncFlag,databaseWrapper, true);

        //TransaccionUtils.fastStoreListSyncFlagUpdate(ComentarioPredeterminado.class, beDatosRubroEvaluacionProceso.getComentarioPredeterminado(), syncFlag,databaseWrapper, false);
        //TransaccionUtils.fastStoreListSyncFlagUpdateRel(RubroEvaluacionProcesoComentario.class, beDatosRubroEvaluacionProceso.getRubroEvaluacionProcesoComentario(), syncFlag,databaseWrapper, false);
        //TransaccionUtils.fastStoreListSyncFlagUpdate(ArchivosRubroProceso.class, beDatosRubroEvaluacionProceso.getArchivoRubroProceso(), syncFlag,databaseWrapper, false);

        GEDatosTareasRecursos beDatosTareaRecursos = geDatosRubroEvaluacionProceso.getBeDatosTareaRecursos();

        TransaccionUtils.fastStoreListSyncFlagUpdate(RecursoDidacticoEventoC.class,beDatosTareaRecursos.getRecursoDidactico(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(TareasRecursosC.class,beDatosTareaRecursos.getTareasRecursos(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(TareasC.class,beDatosTareaRecursos.getTareas(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(TareaRubroEvaluacionProceso.class,beDatosTareaRecursos.getTareaRubroEvaluacionProceso(), syncFlag, databaseWrapper, false);
    }
    //#endregion changeEstadoGlobals GEDatosRubroEvaluacionProceso

    //#region changeEstadoGlobals BEDatosSesionAprendizaje
    private void changeEstadoGlobals(BEDatosSesionAprendizaje beDatosSesionAprendizaje, int syncFlag, DatabaseWrapper databaseWrapper) {
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(SesionAprendizaje.class, beDatosSesionAprendizaje.getSesionAprendizaje(),syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(Competencia.class, beDatosSesionAprendizaje.getCompetencias(),syncFlag,databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(Competencia.class, beDatosSesionAprendizaje.getCapacidades(),syncFlag,databaseWrapper,  false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(DesempenioIcd.class, beDatosSesionAprendizaje.getDesempenioIcd(),syncFlag,databaseWrapper,  false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(Icds.class, beDatosSesionAprendizaje.getIcd(),syncFlag,databaseWrapper,  false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(CampoTematico.class, beDatosSesionAprendizaje.getCampoTematico(),syncFlag,databaseWrapper,  false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(RecursoDidacticoEventoC.class, beDatosSesionAprendizaje.getRecursoDidacticoEvento(),syncFlag,databaseWrapper,  false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(TareasC.class, beDatosSesionAprendizaje.getTareaEvento(),syncFlag,databaseWrapper,  false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(TareasRecursosC.class, beDatosSesionAprendizaje.getTareaEventoRecurso(),syncFlag,databaseWrapper,  false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(ActividadAprendizaje.class, beDatosSesionAprendizaje.getActividadEvento(),syncFlag,databaseWrapper,  false);

        TransaccionUtils.fastStoreListSyncFlagUpdateRel(UnidadAprendizaje.class, beDatosSesionAprendizaje.getUnidadAprendizaje(),syncFlag,databaseWrapper,  false);
    }
    //#endregion changeEstadoGlobals BEDatosSesionAprendizaje

    //#region changeEstadoGlobals GEDatosTareasRecursos
    private void changeEstadoGlobals(GEDatosTareasRecursos geDatosTareasRecursos, int syncFlag, DatabaseWrapper databaseWrapper) {
        TransaccionUtils.fastStoreListSyncFlagUpdate(TareasC.class, geDatosTareasRecursos.getTareas(), syncFlag, databaseWrapper, true);
        TransaccionUtils.fastStoreListSyncFlagUpdate(RecursoDidacticoEventoC.class, geDatosTareasRecursos.getRecursoDidactico(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(TareasRecursosC.class, geDatosTareasRecursos.getTareasRecursos(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdateRel(RecursoArchivo.class, geDatosTareasRecursos.getRecursoArchivo(), syncFlag, databaseWrapper, false);
        TransaccionUtils.fastStoreListSyncFlagUpdate(Archivo.class, geDatosTareasRecursos.getArchivo(), syncFlag, databaseWrapper, false);
    }
    //#endregion changeEstadoGlobals GEDatosTareasRecursos
    //#endregion

    @Override
    public void saveDatosGlobal(BERespuesta item, ServiceDataSource.SuccessCallBack callBack) {
        Log.d(getClass().getSimpleName(), "Time init: " + new Date().getTime() );
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            saveDatosGlobal(item.getCargaAcademica(),databaseWrapper);
            saveDatosGlobal(item.getAsistencia(),databaseWrapper);
            saveDatosGlobal(item.getGrupo(),databaseWrapper);
            saveDatosGlobal(item.getEnvioHorario(),databaseWrapper);
            saveDatosGlobal(item.getMensajeria(),databaseWrapper);
            saveDatosGlobal(item.getTiponota(),databaseWrapper);
            saveDatosGlobal(item.getRubroevaluacionResultado(),databaseWrapper);
            saveDatosGlobal(item.getRubroEvaluacionProceso(),databaseWrapper);
            saveDatosGlobal(item.getSilaboEvento(), databaseWrapper);
            saveDatosGlobal(item.getLogin(), databaseWrapper);

            SessionUser user = SessionUser.getCurrentUser();
            if (user != null) {
                user.setDataImported(true);
                Log.d(getClass().getSimpleName(),"fecha servidor: " + item.getFecha_servidor());
                user.setFechaServidor(item.getLogin().getFecha_servidor());
                user.save();
            } else {
                throw new Error("NO se puede encontrar la SessionUser");
            }
            Log.d(getClass().getSimpleName(), "Time finish: " + new Date().getTime() );
            databaseWrapper.setTransactionSuccessful();
            callBack.onResponse(true);
        } catch (Exception e){
            e.printStackTrace();
            callBack.onResponse(false);
        }finally {
            databaseWrapper.endTransaction();
        }
    }

    //#region saveDatosGlobal
    //#region saveDatosGlobal BEDatosCargaAcademica
    public void saveDatosGlobal(BEDatosCargaAcademica beDatosCargaAcademica,DatabaseWrapper databaseWrapper){
        TransaccionUtils.fastStoreListInsert(Contrato.class, beDatosCargaAcademica.getContratos(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(DetalleContratoAcad.class, beDatosCargaAcademica.getDetalleContratoAcad(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CargaCursoDocente.class, beDatosCargaAcademica.getCargaCursoDocente(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CargaCursoDocenteDet.class, beDatosCargaAcademica.getCargaCursoDocenteDet(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CargaCursos.class, beDatosCargaAcademica.getCargaCursos(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CargaAcademica.class, beDatosCargaAcademica.getCargasAcademicas(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Empleado.class, beDatosCargaAcademica.getEmpleados(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(PlanCursos.class, beDatosCargaAcademica.getPlanCursos(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Cursos.class, beDatosCargaAcademica.getCursos(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Periodo.class, beDatosCargaAcademica.getPeriodos(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Seccion.class, beDatosCargaAcademica.getSecciones(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CalendarioAcademico.class, beDatosCargaAcademica.getCalendarioAcademicos(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(ProgramasEducativo.class, beDatosCargaAcademica.getProgramasEducativos(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(NivelAcademico.class, beDatosCargaAcademica.getNivelesAcademicos(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(PlanEstudios.class, beDatosCargaAcademica.getPlanEstudios(), databaseWrapper, true);
        //transaccionUtils.add(TransaccionUtils.fastStoreListInsert(PlanesEstudiosAlumno.class, beDatosCargaAcademica.getPlanEstudiosAlumno(), transaccionUtils);
        TransaccionUtils.fastStoreListInsert(Aula.class, beDatosCargaAcademica.getAulas(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CalendarioPeriodo.class, beDatosCargaAcademica.getCalendarioPeriodos(), databaseWrapper, true);//1

        List<Archivo> archivoList = SQLite.select()
                .from(Archivo.class)
                .queryList();
        List<Archivo> archivoListNew =  beDatosCargaAcademica.getArchivo();
        for (Archivo archivo: archivoListNew){
            for (Archivo antiguoArchivo : archivoList){
                if(antiguoArchivo.getKey().equals(archivo.getKey())){
                    archivo.setLocalpath(antiguoArchivo.getLocalpath());
                    break;
                }
            }
        }

        TransaccionUtils.fastStoreListInsert(Archivo.class,archivoListNew, databaseWrapper, true);

        //region Caso
        List<String> casoIdList = new ArrayList<>();
        for (Caso caso : beDatosCargaAcademica.getCaso())casoIdList.add(caso.getKey());
        TransaccionUtils.deleteTable(CasoArchivo.class, CasoArchivo_Table.casoId.in(casoIdList));

        TransaccionUtils.fastStoreListInsert(Caso.class, beDatosCargaAcademica.getCaso(), databaseWrapper, true);//1

        List<CasoArchivo> casoArchivoList = SQLite.select()
                .from(CasoArchivo.class)
                .queryList();
        List<CasoArchivo> casoArchivoListNew =  beDatosCargaAcademica.getCasoArchivo();
        for (CasoArchivo archivo: casoArchivoListNew){
            for (CasoArchivo antiguoArchivo : casoArchivoList){
                if(antiguoArchivo.getKey().equals(archivo.getKey())){
                    archivo.setLocalPath(antiguoArchivo.getLocalPath());
                    break;
                }
            }
        }
        TransaccionUtils.fastStoreListInsert(CasoArchivo.class, casoArchivoListNew, databaseWrapper, true);//1
        TransaccionUtils.fastStoreListInsert(CasoReporte.class, beDatosCargaAcademica.getCasoReporte(), databaseWrapper, true);//1
        //endregion

        TransaccionUtils.fastStoreListSetData(PersonaGeoOrg.class, beDatosCargaAcademica.getPersonaGeoOrg(), databaseWrapper, true);//1
        TransaccionUtils.fastStoreListInsert(Organigrama.class, beDatosCargaAcademica.getOrganigrama(), databaseWrapper, true);//1
        TransaccionUtils.fastStoreListInsert(GeoRefOrganigrama.class, beDatosCargaAcademica.getGeoRefOrganigrama(), databaseWrapper, true);//1
        TransaccionUtils.fastStoreListSetData(TipoEntidadGeo.class, beDatosCargaAcademica.getTipoEntidadGeo(), databaseWrapper, true);//1
    }
    //#endregion saveDatosGlobal BEDatosCargaAcademica

    //#region saveDatosGlobal BEDatosEnvioAsistencia
    public void saveDatosGlobal(BEDatosEnvioAsistencia beDatosEnvioAsistencia,DatabaseWrapper databaseWrapper){
          TransaccionUtils.fastStoreListInsert(AsistenciaSesionAlumnoC.class, beDatosEnvioAsistencia.getAsistenciaAlumnos(), databaseWrapper, true);
          TransaccionUtils.fastStoreListInsert(JustificacionC.class, beDatosEnvioAsistencia.getObtenerjustificacion(), databaseWrapper, true);
          TransaccionUtils.fastStoreListInsert(ArchivoAsistencia.class, beDatosEnvioAsistencia.getArchivoAsistencia(), databaseWrapper, true);
    }
    //#endregion saveDatosGlobal BEDatosEnvioAsistencia

    //#region saveDatosGlobal BEDatosEnvioGrupo
    public void saveDatosGlobal(BEDatosEnvioGrupo beDatosEnvioGrupo,DatabaseWrapper databaseWrapper){
        TransaccionUtils.fastStoreListInsert(EquipoC.class, beDatosEnvioGrupo.getEquipo(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(EquipoIntegranteC.class, beDatosEnvioGrupo.getEquipo_integrante(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(GrupoEquipoC.class, beDatosEnvioGrupo.getGrupo_equipo(), databaseWrapper, true);
    }
    //#endregion saveDatosGlobal BEDatosEnvioGrupo

    //#region saveDatosGlobal BEDatosEnvioHorario
    public void saveDatosGlobal(BEDatosEnvioHorario beDatosEnvioHorario,DatabaseWrapper databaseWrapper){
        TransaccionUtils.fastStoreListInsert(Hora.class, beDatosEnvioHorario.getHora(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(HorarioPrograma.class, beDatosEnvioHorario.getHorarioPrograma(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(HorarioHora.class, beDatosEnvioHorario.getHorarioHora(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(DetalleHorario.class, beDatosEnvioHorario.getDetalleHorario(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Dia.class, beDatosEnvioHorario.getDia(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Horario.class, beDatosEnvioHorario.getHorario(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(HorarioDia.class, beDatosEnvioHorario.getHorarioDia(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CursosDetHorario.class, beDatosEnvioHorario.getCursosDetHorario(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(DiaHora.class, beDatosEnvioHorario.getObtenerDiaHora(), databaseWrapper, true);
    }
    //#endregion saveDatosGlobal BEDatosEnvioHorario

    //#region saveDatosGlobal BEDatosEnvioMensajeria
    public void saveDatosGlobal(BEDatosEnvioMensajeria beDatosEnvioMensajeria,DatabaseWrapper databaseWrapper){
        TransaccionUtils.fastStoreListInsert(MensajeUsuarioC.class, beDatosEnvioMensajeria.getMensajeUsuario(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(MensajeIntencionItemC.class, beDatosEnvioMensajeria.getMensajeIntencionItem(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(UsuarioCanalComunicacion.class, beDatosEnvioMensajeria.getUsCanalComunicacion(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CanalComunicacion.class, beDatosEnvioMensajeria.getCanalComunicacion(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CanalDestinoEstadoC.class, beDatosEnvioMensajeria.getCanalDestinoEstado(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(MensajeC.class, beDatosEnvioMensajeria.getMensajes(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Intencion.class, beDatosEnvioMensajeria.getIntenciones(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(IntencionItem.class, beDatosEnvioMensajeria.getIntencionItems(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(ListaUsuario.class, beDatosEnvioMensajeria.getListaUsuarios(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(ListaUsuarioDetalle.class, beDatosEnvioMensajeria.getListUsuarioDetalle(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(MensajePredeterminado.class, beDatosEnvioMensajeria.getListaMensajePredeterminado(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(MensajePredeterminadoDetalle.class, beDatosEnvioMensajeria.getListMensajePredeterminadoDetalle(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(MensajePredIntencion.class, beDatosEnvioMensajeria.getListMensajePredIntencion(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(InteraccionTextual.class, beDatosEnvioMensajeria.getListInteraccionTextual(), databaseWrapper, false);
    }
    //#endregion saveDatosGlobal BEDatosEnvioMensajeria

    //#region saveDatosGlobal BEDatosEnvioTipoNota
    public void saveDatosGlobal(BEDatosEnvioTipoNota beDatosEnvioTipoNota,DatabaseWrapper databaseWrapper){
        TransaccionUtils.fastStoreListInsert(TipoNotaC.class, beDatosEnvioTipoNota.getTipoNota(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(ValorTipoNotaC.class, beDatosEnvioTipoNota.getValorTipoNota(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(RelProgramaEducativoTipoNota.class, beDatosEnvioTipoNota.getRel_Programa_Educativo_TipoNota(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(EscalaEvaluacion.class, beDatosEnvioTipoNota.getEscalaEvaluacion(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(T_RN_MAE_TIPO_EVALUACION.class, beDatosEnvioTipoNota.getRn_mae_tipo_evaluacion(), databaseWrapper, true);

    }
    //#endregion saveDatosGlobal BEDatosEnvioTipoNota

    //#region saveDatosGlobal BEDatosEvaluacionResultado
    public void saveDatosGlobal(BEDatosEvaluacionResultado beDatosEvaluacionResultado, DatabaseWrapper databaseWrapper){
          TransaccionUtils.fastStoreListInsert(RubroEvaluacionResultado.class, beDatosEvaluacionResultado.getRubroEvaluacionResultado(), databaseWrapper, true);
          TransaccionUtils.fastStoreListInsert(RubroEvalRNRFormula.class, beDatosEvaluacionResultado.getRubroEvalResultadoFormula(), databaseWrapper, true);
          TransaccionUtils.fastStoreListInsert(EvaluacionResultado.class, beDatosEvaluacionResultado.getEvaluacionResultado(), databaseWrapper, true);
    }
    //#endregion saveDatosGlobal BEDatosEnvioTipoNota

    //#region saveDatosGlobal BEDatosRubroEvaluacionProceso
    public void saveDatosGlobal(BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso, DatabaseWrapper databaseWrapper){
        TransaccionUtils.fastStoreListInsert(RubroEvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getRubroEvaluacionProceso(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(RubroEvalRNPFormulaC.class, beDatosRubroEvaluacionProceso.getRubroEvalProcesoFormula(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class, beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoEquipo(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class, beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoIntegrante(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(EquipoEvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getObtenerEquipoEvaluacionProceso(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(RubroEvaluacionProcesoCampotematicoC.class, beDatosRubroEvaluacionProceso.getRubro_evaluacion_proceso_campotematico(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CriterioRubroEvaluacionC.class, beDatosRubroEvaluacionProceso.getObtenerCriterioRubroEvaluacionProceso(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(EvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getEvaluacionProceso(), databaseWrapper, true);

        //region ComentarioPredeterminado
        /*TransaccionUtils.fastStoreListInsert(ComentarioPredeterminado.class, beDatosRubroEvaluacionProceso.getComentarioPredeterminado(), databaseWrapper, true);

        List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCList = beDatosRubroEvaluacionProceso.getRubroEvaluacionProceso();
        List<String> rubroEvalProcesoIdList = new ArrayList<>();
        if(rubroEvaluacionProcesoCList!=null)for(RubroEvaluacionProcesoC rubroEvaluacionProcesoC: rubroEvaluacionProcesoCList)rubroEvalProcesoIdList.add(rubroEvaluacionProcesoC.getKey());
        TransaccionUtils.deleteTable(RubroEvaluacionProcesoComentario.class, RubroEvaluacionProcesoComentario_Table.rubroEvalProcesoId.in(rubroEvalProcesoIdList));
        TransaccionUtils.deleteTable(ArchivosRubroProceso.class, ArchivosRubroProceso_Table.rubroEvalProcesoId.in(rubroEvalProcesoIdList));
        TransaccionUtils.fastStoreListInsert(RubroEvaluacionProcesoComentario.class, beDatosRubroEvaluacionProceso.getRubroEvaluacionProcesoComentario(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(ArchivosRubroProceso.class, beDatosRubroEvaluacionProceso.getArchivoRubroProceso(), databaseWrapper, true);*/
        //endregion ComentarioPredeterminado
    }
    //#endregion saveDatosGlobal BEDatosRubroEvaluacionProceso

    //#region saveDatosGlobal GEDatosSilaboEventoEnvio
    public void saveDatosGlobal(GEDatosSilaboEventoEnvio beDatosSilaboEventoEnvio, DatabaseWrapper databaseWrapper){

        //region RecursoDidacticoEventoC
        List<RecursoDidacticoEventoC> recursoDidacticoEventoCList = beDatosSilaboEventoEnvio.getRecursoDidactico();
        List<String> recursoDiactivoIdList = new ArrayList<>();
        if(recursoDidacticoEventoCList!=null)for(RecursoDidacticoEventoC recursoDidacticoEventoC: recursoDidacticoEventoCList)recursoDiactivoIdList.add(recursoDidacticoEventoC.getKey());
        TransaccionUtils.deleteTable(RecursoDidacticoEventoC.class, RecursoDidacticoEventoC_Table.recursoDidacticoId.in(recursoDiactivoIdList));
        TransaccionUtils.deleteTable(RecursoArchivo.class, RecursoArchivo_Table.recursoDidacticoId.in(recursoDiactivoIdList));
        TransaccionUtils.deleteTable(RecursoReferenciaC.class, RecursoReferenciaC_Table.recursoDidacticoId.in(recursoDiactivoIdList));
        TransaccionUtils.deleteTable(TareasRecursosC.class, TareasRecursosC_Table.recursoDidacticoId.in(recursoDiactivoIdList));

        TransaccionUtils.fastStoreListInsert(RecursoDidacticoEventoC.class, recursoDidacticoEventoCList, databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(RecursoArchivo.class, beDatosSilaboEventoEnvio.getRecursoArchivo(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(RecursoReferenciaC.class, beDatosSilaboEventoEnvio.getRecursoReferencia(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(TareasRecursosC.class, beDatosSilaboEventoEnvio.getTareasRecursos(), databaseWrapper, true);
        //endregion RecursoDidacticoEventoC

        TransaccionUtils.fastStoreListInsert(ActividadAprendizaje.class, beDatosSilaboEventoEnvio.getActividad(), databaseWrapper, true);
        //final Gson gson = new Gson();
        //final String representacionJSON = gson.toJson(beDatosSilaboEventoEnvio.getRecursoDidactico());
        //ApiRetrofit.Log.d(getClass().getSimpleName(), "getRecursoDidactico : " + representacionJSON);

        TransaccionUtils.fastStoreListInsert(TareasC.class, beDatosSilaboEventoEnvio.getTareas(), databaseWrapper, true);
        /*Objeto a eliminar est repetida */
        TransaccionUtils.fastStoreListInsertRel(UnidadTipo.class, beDatosSilaboEventoEnvio.getUnidadTipo(), databaseWrapper, true);


        //region sesion aprendizaje
        List<SesionAprendizaje> sesionAprendizajeList = beDatosSilaboEventoEnvio.getSesiones();
        List<Integer> sesionAprendizajeIdLis = new ArrayList<>();
        if(sesionAprendizajeList!=null)for (SesionAprendizaje sesionAprendizaje: sesionAprendizajeList)sesionAprendizajeIdLis.add(sesionAprendizaje.getSesionAprendizajeId());
        List<SesionEventoCompetenciaDesempenioIcd> sesionEventoCompetenciaDesempenioIcds = ConsultaUtils.getChangeItemsTableChild(SesionEventoCompetenciaDesempenioIcd.class, SesionEventoCompetenciaDesempenioIcd_Table.sesionAprendizajeId.in(sesionAprendizajeIdLis));
        List<Integer> sesionCompetenciaDesempenioIcdIdList = new ArrayList<>();
        for (SesionEventoCompetenciaDesempenioIcd sesionEventoCompetenciaDesempenioIcd: sesionEventoCompetenciaDesempenioIcds)sesionCompetenciaDesempenioIcdIdList.add(sesionEventoCompetenciaDesempenioIcd.getSesionCompetenciaDesempenioIcdId());
        TransaccionUtils.deleteTable(T_GC_REL_COMPETENCIA_SESION_EVENTO.class, T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.in(sesionAprendizajeIdLis));
        TransaccionUtils.deleteTable(SesionEventoCompetenciaDesempenioIcd.class, T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.in(sesionAprendizajeIdLis));
        TransaccionUtils.deleteTable(SesionEventoDesempenioIcdCampotematico.class, SesionEventoDesempenioIcdCampotematico_Table.sesionCompetenciaDesempenioIcdId.in(sesionCompetenciaDesempenioIcdIdList));

        TransaccionUtils.fastStoreListInsert(SesionAprendizaje.class, sesionAprendizajeList, databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(T_GC_REL_COMPETENCIA_SESION_EVENTO.class, beDatosSilaboEventoEnvio.getCompetenciaSesion(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(SesionEventoCompetenciaDesempenioIcd.class, beDatosSilaboEventoEnvio.getRel_sesion_evento_competencia_desempenioicd(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(SesionEventoDesempenioIcdCampotematico.class, beDatosSilaboEventoEnvio.getSesion_desempenio_icd_campotematico(), databaseWrapper, true);
        //endregion sesion aprendizaje

        TransaccionUtils.fastStoreListInsert(Competencia.class, beDatosSilaboEventoEnvio.getCompetencias(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(DesempenioIcd.class, beDatosSilaboEventoEnvio.getRel_desempenio_icd(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Icds.class, beDatosSilaboEventoEnvio.getIcds(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CampoTematico.class, beDatosSilaboEventoEnvio.getCampoTematico(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsertRel(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO.class, beDatosSilaboEventoEnvio.getRel_unidad_apren_evento_tipo(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(AreaInstrumento.class, beDatosSilaboEventoEnvio.getObtenerAreaInstrumento(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(TipoInstrumento.class, beDatosSilaboEventoEnvio.getObtenerTipoInstrumento(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Dimension.class, beDatosSilaboEventoEnvio.getObtenerDimension(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(DimensionCaracteristica.class, beDatosSilaboEventoEnvio.getObtenerDimensionCaracteristica(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(DimensionObservada.class, beDatosSilaboEventoEnvio.getObtenerDimensionObservada(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(InstrumentoObservado.class, beDatosSilaboEventoEnvio.getObtenerInstrumentoObservado(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(InstrumentoEvaluacion.class, beDatosSilaboEventoEnvio.getInstrumento_eval(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(TareaRubroEvaluacionProceso.class, beDatosSilaboEventoEnvio.getTareaRubroEvaluacionProceso(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Desempenio.class, beDatosSilaboEventoEnvio.getObtenerDesempenio(), databaseWrapper, true);

        //#region SilaboEvento
        List<Integer> listSilabosId = new ArrayList<>();
        List<SilaboEvento> silaboEventos = beDatosSilaboEventoEnvio.getSilaboEvento();
        if (silaboEventos!=null)for (SilaboEvento s:silaboEventos) listSilabosId.add(s.getSilaboEventoId());
        List<Integer>listSilabosCompetencaiId = new ArrayList<>();
        List<SilaboCompetencia> silaboCompetencias = ConsultaUtils.getChangeItemsTableChild(SilaboCompetencia.class, SilaboCompetencia_Table.silaboEventoId.in(listSilabosId));
        for (SilaboCompetencia silaboCompetencia:silaboCompetencias)listSilabosCompetencaiId.add(silaboCompetencia.getSilaboEventoCompetenciaId());
        List<Integer> silabocompetenciadesempenioicdIdList = new ArrayList<>();
        List<SilaboEventoCompentenciaDesempenioIcd> silaboEventoCompentenciaDesempenioIcds = ConsultaUtils.getChangeItemsTableChild(SilaboEventoCompentenciaDesempenioIcd.class, SilaboEventoCompentenciaDesempenioIcd_Table.silaboEventoCompetenciaId.in(listSilabosCompetencaiId));
        for(SilaboEventoCompentenciaDesempenioIcd silaboEventoCompentenciaDesempenioIcd: silaboEventoCompentenciaDesempenioIcds)silabocompetenciadesempenioicdIdList.add(silaboEventoCompentenciaDesempenioIcd.getSilaboCompetenciaDesempenioIcdId());

        TransaccionUtils.deleteTable(SilaboCompetencia.class, SilaboCompetencia_Table.silaboEventoId.in(listSilabosId));
        TransaccionUtils.deleteTable(SilaboEventoCompentenciaDesempenioIcd.class, SilaboEventoCompentenciaDesempenioIcd_Table.silaboEventoCompetenciaId.in(listSilabosCompetencaiId));
        TransaccionUtils.deleteTable(SilaboEventoDesempenioIcdCampotematico.class, SilaboEventoDesempenioIcdCampotematico_Table.silabocompetenciadesempenioicdId.in(silabocompetenciadesempenioicdIdList));

        TransaccionUtils.fastStoreListInsert(SilaboCompetencia.class, beDatosSilaboEventoEnvio.getSilabocompetencia(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(SilaboEventoCompentenciaDesempenioIcd.class, beDatosSilaboEventoEnvio.getSilaboeventocompetenciadesempenioicd(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(SilaboEventoDesempenioIcdCampotematico.class, beDatosSilaboEventoEnvio.getSilaboeventodesempenioicdcampotematico(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(SilaboEvento.class, silaboEventos, databaseWrapper, true);
        //#endregion SilaboEvento

        //region UnidadAprendizaje
        List<Integer> listUnidadAprendizajeId = new ArrayList<>();
        List<UnidadAprendizaje> listUnidadAprendizaje= beDatosSilaboEventoEnvio.getUnidadAprendizaje();
        if(listUnidadAprendizaje!=null)for(UnidadAprendizaje unidadAprendizaje: listUnidadAprendizaje)listUnidadAprendizajeId.add(unidadAprendizaje.getUnidadAprendizajeId());
        List<Integer> unidadCompetenciaIdList = new ArrayList<>();
        List<CompetenciaUnidad> competenciaUnidadList = ConsultaUtils.getChangeItemsTableChild(CompetenciaUnidad.class, CompetenciaUnidad_Table.unidadAprendizajeId.in(listUnidadAprendizajeId));
        for(CompetenciaUnidad competenciaUnidad: competenciaUnidadList)unidadCompetenciaIdList.add(competenciaUnidad.getCompetenciaId());
        List<Integer> unidadCompetenciaDesempenioIcdIdList = new ArrayList<>();
        List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD> rel_unidad_evento_competencia_desempenio_icds = ConsultaUtils.getChangeItemsTableChild(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class, T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.unidadCompetenciaId.in(listUnidadAprendizajeId));
        for(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD evento_competencia_desempenio_icd : rel_unidad_evento_competencia_desempenio_icds)unidadCompetenciaDesempenioIcdIdList.add(evento_competencia_desempenio_icd.getUnidadCompetenciaDesempenioIcdId());

        TransaccionUtils.deleteTable(CompetenciaUnidad.class, CompetenciaUnidad_Table.unidadAprendizajeId.in(listUnidadAprendizajeId));
        TransaccionUtils.deleteTable(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class, T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.unidadCompetenciaId.in(unidadCompetenciaIdList));
        TransaccionUtils.deleteTable(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO.class, T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO_Table.unidadCompetenciaDesempenioIcdId.in(unidadCompetenciaDesempenioIcdIdList));

        TransaccionUtils.fastStoreListInsert(CompetenciaUnidad.class, beDatosSilaboEventoEnvio.getCompetenciaUnidad(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class, beDatosSilaboEventoEnvio.getRel_unidad_evento_competencia_desempenio_icd(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO.class, beDatosSilaboEventoEnvio.getRel_unidad_evento_competencia_desempenio_icd_campo_tematico(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(UnidadAprendizaje.class, beDatosSilaboEventoEnvio.getUnidadAprendizaje(), databaseWrapper, true);
        //endregion

    }
    //#endregion saveDatosGlobal GEDatosSilaboEventoEnvio

    //#region saveDatosGlobal GEDatosTareasRecursos
    public void saveDatosGlobal(GEDatosTareasRecursos geDatosTareasRecursos, DatabaseWrapper databaseWrapper){
        /*
                TransaccionUtils.fastStoreListInsert(TareasRecursosC.class, geDatosTareasRecursos.|(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(TareasC.class, geDatosTareasRecursos.getTareas(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(TareaRubroEvaluacionProceso.class, geDatosTareasRecursos.getTareaRubroEvaluacionProceso(), databaseWrapper, true);
                TransaccionUtils.fastStoreListInsert(RecursoDidacticoEventoC.class, geDatosTareasRecursos.getRecursoDidactico(), databaseWrapper, true);*/
    }
    //#endregion saveDatosGlobal GEDatosTareasRecursos

    //#region saveDatosGlobal BEObtenerDatosLogin
    public void saveDatosGlobal(BEObtenerDatosLogin beObtenerDatosLogin, DatabaseWrapper databaseWrapper){
        TransaccionUtils.fastStoreListInsert(AnioAcademico.class, beObtenerDatosLogin.getAnioAcademicos(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CuentaCorriente.class, beObtenerDatosLogin.getCuentaCorriente(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(PlanCursosAlumno.class, beObtenerDatosLogin.getPlanCursosAlumno(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Estados.class, beObtenerDatosLogin.getEstados(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Tipos.class, beObtenerDatosLogin.getTipos(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Persona.class, beObtenerDatosLogin.getPersonas(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Relaciones.class, beObtenerDatosLogin.getRelaciones(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(CursoCompetencia.class, beObtenerDatosLogin.getCursoCompetencias(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(ColorCondicionalC.class, beObtenerDatosLogin.getColorCondicional(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(IndicarLogro.class, beObtenerDatosLogin.getIndicarLogro(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Usuario.class, beObtenerDatosLogin.getUsuariosrelacionados(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(ParametrosDisenio.class, beObtenerDatosLogin.getObtener_parametros_disenio(), databaseWrapper, true);
        TransaccionUtils.fastStoreListSave(Rutas.class, beObtenerDatosLogin.getRutas(), databaseWrapper, true);
        TransaccionUtils.fastStoreListSave(ParametroConfiguracion.class, beObtenerDatosLogin.getParametroConfiguracions(), databaseWrapper, true);
        TransaccionUtils.fastStoreListInsert(Ubicaciones.class, beObtenerDatosLogin.getUbicaciones(), databaseWrapper, true);
    }
    //#endregion saveDatosGlobal BEObtenerDatosLogin
    //#endregion

    @Override
    public void getDatosExportarGlobal(CallBack<BEGuardarEntidadesGlobal> callBack) {
       try {
           SessionUser user = SessionUser.getCurrentUser();
           if(user.getFechaServidor()==0){
               Log.d(getClass().getSimpleName(), "getFechaServidor: "+ user.getFechaServidor());
               callBack.onResponse(false, null);
               return;
           }

           onUpdateEvaluacionFormula(new ServiceDataSource.SuccessCallBack() {
               @Override
               public void onResponse(boolean success) {

               }
           });

           BEGuardarEntidadesGlobal beGuardarEntidadesGlobal = new BEGuardarEntidadesGlobal();
           beGuardarEntidadesGlobal.setAsistencia(getGEDatosEnvioAsistencia());
           beGuardarEntidadesGlobal.setGrupo(getBEDatosEnvioGrupo());
           beGuardarEntidadesGlobal.setMensajeria(getBEDatosEnvioMensajeria());
           beGuardarEntidadesGlobal.setRubroEvaluacionProceso(getGEDatosRubroEvaluacionProceso());
           beGuardarEntidadesGlobal.setSesionAprendizaje(getBEDatosSesionAprendizaje());
           beGuardarEntidadesGlobal.setTareaRecursos(getGEDatosTareasRecursos());
           beGuardarEntidadesGlobal.setUsuarioId(user.getUserId());
           beGuardarEntidadesGlobal.setCasos(getBEDatosCasos());
           Date date =  new Date(user.getFechaServidor());
           Calendar cal = Calendar.getInstance(); // locale-specific
           cal.setTime(date);
           //cal.set(Calendar.HOUR_OF_DAY, 0);
           //cal.set(Calendar.MINUTE, 0);
           cal.set(Calendar.SECOND, 0);
           cal.set(Calendar.MILLISECOND, 0);
           beGuardarEntidadesGlobal.setFecha_servidor(cal.getTimeInMillis());

           callBack.onResponse(true, beGuardarEntidadesGlobal);
       }catch (Exception e){
           e.printStackTrace();
           callBack.onResponse(false, null);
       }

    }


    public void onUpdateEvaluacionFormula(ServiceDataSource.SuccessCallBack callBack) {
         /* Log.d(getClass().getSimpleName(), "onUpdateEvaluacionFormula");
        try {
            List<EvaluacionProcesoC> evaluacionProcesoCList = SQLite.select()
                    .from(EvaluacionProcesoC.class)
                    .where(EvaluacionProcesoC_Table.formulaSinc.eq(true))
                    .queryList();
            List<String> rubrosAsociadosIdList = new ArrayList<>();
            for (EvaluacionProcesoC evaluacionProcesoC : evaluacionProcesoCList)rubrosAsociadosIdList.add(evaluacionProcesoC.getRubroEvalProcesoId());

            Log.d(BEDatosRubroEvaluacionProcesoLocalDataSource.class.getSimpleName(),"size: " + evaluacionProcesoCList.size());

            IProperty[] parametros = Utils.f_allcolumnTable(RubroEvalRNPFormulaC_Table.ALL_COLUMN_PROPERTIES);
            List<RubroEvalRNPFormulaC> rubroFormulaList = SQLite.select(parametros)
                    .from(RubroEvalRNPFormulaC.class)
                    .innerJoin(RubroEvaluacionProcesoC.class)
                    .on(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable()
                            .eq(RubroEvaluacionProcesoC_Table.key.withTable()))
                    .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable().in(rubrosAsociadosIdList))
                    .and(RubroEvaluacionProcesoC_Table.tipoFormulaId.withTable().notEq(0))
                    .groupBy(parametros)
                    .queryList();

            Log.d(BEDatosRubroEvaluacionProcesoLocalDataSource.class.getSimpleName(),"rubroFormulaList size: " + rubroFormulaList.size());

            Log.d(BEDatosRubroEvaluacionProcesoLocalDataSource.class.getSimpleName(),"rubroFormulaList: " + rubroFormulaList.size());

            List<String> listIdRubrosActualizados = new ArrayList<>();
            for (EvaluacionProcesoC itemEvaluacionProcesoC: evaluacionProcesoCList){
                RubroEvalRNPFormulaC rubroEvalRNPFormulaC = null;
                for (RubroEvalRNPFormulaC itemRubroEvalRNPFormulaC: rubroFormulaList){
                    if(itemEvaluacionProcesoC.getRubroEvalProcesoId().equals(itemRubroEvalRNPFormulaC.getRubroEvaluacionSecId())){
                        rubroEvalRNPFormulaC = itemRubroEvalRNPFormulaC;
                        break;
                    }
                }

                if(rubroEvalRNPFormulaC != null){

                    boolean success = evaluacionProcesoDao.evaluarRubroFormulaPersona(rubroEvalRNPFormulaC.getRubroEvaluacionPrimId(),itemEvaluacionProcesoC.getAlumnoId() );
                    if(success){
                        //Log.d(EvaluacionFormulaLocal.class.getSimpleName(),"success: " + success);
                        int poscion = listIdRubrosActualizados.indexOf(rubroEvalRNPFormulaC.getRubroEvaluacionPrimId());
                        if(poscion!=-1)listIdRubrosActualizados.add(rubroEvalRNPFormulaC.getRubroEvaluacionPrimId());
                    }
                }
                itemEvaluacionProcesoC.setFormulaSinc(false);
                itemEvaluacionProcesoC.save();
            }

            for (String itemId: listIdRubrosActualizados){
                RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                        .from(RubroEvaluacionProcesoC.class)
                        .where(RubroEvaluacionProcesoC_Table.key.eq(itemId))
                        .querySingle();
                rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                rubroEvaluacionProcesoC.save();
            }

            for(RubroEvalRNPFormulaC rubroformula : rubroFormulaList){
                 Log.d(getClass().getSimpleName(), "rubroformula id  "+ rubroformula.getRubroEvaluacionPrimId());
                 evaluacionProcesoDao.f_mediaDesviacionEstandar(rubroformula.getRubroEvaluacionPrimId());
             }



            callBack.onResponse(true);
        }catch (Exception e){
            e.printStackTrace();
            callBack.onResponse(false);
        }*/
    }

    //#region getDatosExportarGlobal
    private GEDatosEnvioAsistencia getGEDatosEnvioAsistencia() {

        BEDatosEnvioAsistencia beDatosEnvioAsistencia = new BEDatosEnvioAsistencia();
        beDatosEnvioAsistencia.setAsistenciaAlumnos(ConsultaUtils.getChangeItemsTable(AsistenciaSesionAlumnoC.class));
        List<String> asistenciaSesionAlumnoKey = new ArrayList<>();
        for (AsistenciaSesionAlumnoC asistenciaSesionAlumnoC: beDatosEnvioAsistencia.getAsistenciaAlumnos()){
            asistenciaSesionAlumnoKey.add(asistenciaSesionAlumnoC.getKey());
        }
        List<JustificacionC> justificacionCList = ConsultaUtils.getChangeItemsTableChild(JustificacionC.class, JustificacionC_Table.asistenciaId.in(asistenciaSesionAlumnoKey));
        List<String> justificacionKeys = new ArrayList<>();
        for (JustificacionC justificacionC : justificacionCList)justificacionKeys.add(justificacionC.getJustificacionId());
        beDatosEnvioAsistencia.setObtenerjustificacion(justificacionCList);
        beDatosEnvioAsistencia.setArchivoAsistencia(ConsultaUtils.getChangeItemsTableChild(ArchivoAsistencia.class, ArchivoAsistencia_Table.justifiacionId.in(justificacionKeys)));

        List<String> tipoNotakeys = new ArrayList<>();
        for (AsistenciaSesionAlumnoC asistenciaSesionAlumnoC: beDatosEnvioAsistencia.getAsistenciaAlumnos()) {
            TipoNotaC tipoNotaC = SQLite.select()
                    .from(TipoNotaC.class)
                    .innerJoin(ValorTipoNotaC.class)
                    .on(TipoNotaC_Table.key.withTable()
                            .eq(ValorTipoNotaC_Table.tipoNotaId.withTable()))
                    .where(ValorTipoNotaC_Table.key.withTable().is(asistenciaSesionAlumnoC.getValorTipoNotaId()))
                    .querySingle();
            if(tipoNotaC == null)continue;

            tipoNotakeys.add(tipoNotaC.getKey());
        }

        BEDatosEnvioTipoNota beDatosEnvioTipoNota = new BEDatosEnvioTipoNota();
        beDatosEnvioTipoNota.setTipoNota(ConsultaUtils.getChangeItemsTableChild(TipoNotaC.class, TipoNotaC_Table.key.in(tipoNotakeys)));
        beDatosEnvioTipoNota.setValorTipoNota(ConsultaUtils.getChangeItemsTableChild(ValorTipoNotaC.class, ValorTipoNotaC_Table.tipoNotaId.in(tipoNotakeys)));

        GEDatosEnvioAsistencia geDatosEnvioAsistencia = new GEDatosEnvioAsistencia();
        geDatosEnvioAsistencia.setBeDatosEnvioAsistencia(beDatosEnvioAsistencia);
        geDatosEnvioAsistencia.setBeDatosEnvioTipoNota(beDatosEnvioTipoNota);
        return geDatosEnvioAsistencia;
    }

    private BEDatosCasos getBEDatosCasos() {
        BEDatosCasos beDatosCasos = new BEDatosCasos();
        List<Caso> casoList = ConsultaUtils.getChangeItemsTable(Caso.class);
        beDatosCasos.setCaso(casoList);
        List<String> casoIdList = new ArrayList<>();
        for (Caso caso: casoList)casoIdList.add(caso.getKey());
        List<CasoArchivo> casoArchivos = ConsultaUtils.getChangeItemsTableChild(CasoArchivo.class, CasoArchivo_Table.casoId.in(casoIdList));
        for (CasoArchivo archivo : casoArchivos){
            String fileName = archivo.getPath();
            if(!TextUtils.isEmpty(fileName)){
                int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
                String file = fileName.substring(p + 1);
                archivo.setPath(file);
            }
        }

        beDatosCasos.setCasoArchivo(casoArchivos);
        return beDatosCasos;
    }

    private BEDatosEnvioGrupo getBEDatosEnvioGrupo(){
        BEDatosEnvioGrupo beDatosEnvioGrupo = new BEDatosEnvioGrupo();
        beDatosEnvioGrupo.setEquipo(ConsultaUtils.getChangeItemsTable(EquipoC.class));
        beDatosEnvioGrupo.setEquipo_integrante(ConsultaUtils.getChangeItemsTable(EquipoIntegranteC.class));
        beDatosEnvioGrupo.setEquipo_integrante(ConsultaUtils.getChangeItemsTable(EquipoIntegranteC.class));
        beDatosEnvioGrupo.setGrupo_equipo(ConsultaUtils.getChangeItemsTable(GrupoEquipoC.class));
        return beDatosEnvioGrupo;
    }

    private BEDatosEnvioMensajeria getBEDatosEnvioMensajeria(){
        BEDatosEnvioMensajeria beDatosEnvioMensajeria = new BEDatosEnvioMensajeria();

        List<MensajeUsuarioC> mensajeUsuario = ConsultaUtils.getChangeItemsTable(MensajeUsuarioC.class);//no se modifico el id de tipo int a String
        List<MensajeIntencionItemC> mensajeIntencionItem = ConsultaUtils.getChangeItemsTable(MensajeIntencionItemC.class);
        //List<UsuarioCanalComunicacion> usCanalComunicacion = ConsultaUtils.getChangeItemsTable(UsuarioCanalComunicacion.class);
        //List<CanalComunicacion> canalComunicacion = ConsultaUtils.getChangeItemsTable(CanalComunicacion.class);
        List<CanalDestinoEstadoC> canalDestinoEstado = ConsultaUtils.getChangeItemsTable(CanalDestinoEstadoC.class);
        List<MensajeC> mensajes = ConsultaUtils.getChangeItemsTable(MensajeC.class);
        //List<Intencion> intenciones = ConsultaUtils.getChangeItemsTable(Intencion.class);
        //List<IntencionItem> intencionItems = ConsultaUtils.getChangeItemsTable(IntencionItem.class);
        //List<ListaUsuario> listaUsuarios = ConsultaUtils.getChangeItemsTable(ListaUsuario.class);
        //List<ListaUsuarioDetalle> listUsuarioDetalle = ConsultaUtils.getChangeItemsTable(ListaUsuarioDetalle.class);
        List<MensajePredeterminado> listaMensajePredeterminado = ConsultaUtils.getChangeItemsTable(MensajePredeterminado.class);
        List<MensajePredeterminadoDetalle> listMensajePredeterminadoDetalle = ConsultaUtils.getChangeItemsTable(MensajePredeterminadoDetalle.class);
        List<MensajePredIntencion> listMensajePredIntencion = ConsultaUtils.getChangeItemsTable(MensajePredIntencion.class);

        beDatosEnvioMensajeria.setMensajeUsuario(mensajeUsuario);
        beDatosEnvioMensajeria.setMensajeIntencionItem(mensajeIntencionItem);
        beDatosEnvioMensajeria.setCanalDestinoEstado(canalDestinoEstado);
        beDatosEnvioMensajeria.setMensajes(mensajes);
        beDatosEnvioMensajeria.setListaMensajePredeterminado(listaMensajePredeterminado);
        beDatosEnvioMensajeria.setListMensajePredeterminadoDetalle(listMensajePredeterminadoDetalle);
        beDatosEnvioMensajeria.setListMensajePredIntencion(listMensajePredIntencion);

        beDatosEnvioMensajeria.setListInteraccionTextual(ConsultaUtils.getChangeItemsTable(InteraccionTextual.class));
        return beDatosEnvioMensajeria;
    }

    private GEDatosRubroEvaluacionProceso getGEDatosRubroEvaluacionProceso(){

        BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso = new BEDatosRubroEvaluacionProceso();

        //#region RubroEvaluacionProcesoC

        List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoList;
        rubroEvaluacionProcesoList = ConsultaUtils.getChangeItemsTable(RubroEvaluacionProcesoC.class);

        beDatosRubroEvaluacionProceso.setRubroEvaluacionProceso(rubroEvaluacionProcesoList);
        //#endregion RubroEvaluacionProcesoC

        List<String> rubroEvaluacionProcesoKey = new ArrayList<>();
        for (RubroEvaluacionProcesoC rubroEvaluacionProcesoC: beDatosRubroEvaluacionProceso.getRubroEvaluacionProceso()){
            rubroEvaluacionProcesoKey.add(rubroEvaluacionProcesoC.getKey());
        }
        //#region RubroEvalRNPFormulaC
        beDatosRubroEvaluacionProceso.setRubroEvalProcesoFormula(
                ConsultaUtils.getChangeItemsTableChild(RubroEvalRNPFormulaC.class,
                        RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.in(rubroEvaluacionProcesoKey)));
        //#endregion RubroEvaluacionProcesoC

        for (RubroEvalRNPFormulaC rubroEvalRNPFormulaC: beDatosRubroEvaluacionProceso.getRubroEvalProcesoFormula()){
            String key = rubroEvalRNPFormulaC.getRubroEvaluacionSecId();
            if(rubroEvaluacionProcesoKey.indexOf(key) == -1){
                rubroEvaluacionProcesoKey.add(key);
                //#region RubroEvaluacionProcesoC Secundario
                rubroEvaluacionProcesoList.add(
                        ConsultaUtils.getChangeItemTableChild(
                                RubroEvaluacionProcesoC.class,
                                T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.key.is(key)));
                //#endregion RubroEvaluacionProcesoC Secundario
            }
        }

        //#region EvaluacionProcesoC
        beDatosRubroEvaluacionProceso.setEvaluacionProceso(
                ConsultaUtils.getChangeItemsTableChild(
                        EvaluacionProcesoC.class, EvaluacionProcesoC_Table.rubroEvalProcesoId.in(rubroEvaluacionProcesoKey)));
        //#endregion EvaluacionProcesoC

        //#region EquipoEvaluacionProcesoC
        beDatosRubroEvaluacionProceso.setObtenerEquipoEvaluacionProceso(
                ConsultaUtils.getChangeItemsTableChild(
                        EquipoEvaluacionProcesoC.class, EquipoEvaluacionProcesoC_Table.rubroEvalProcesoId.in(rubroEvaluacionProcesoKey)));
        //#endregion EquipoEvaluacionProcesoC

        //#region RubroEvaluacionProcesoCampotematicoC
        beDatosRubroEvaluacionProceso.setRubro_evaluacion_proceso_campotematico(ConsultaUtils.getChangeItemsTableChild(RubroEvaluacionProcesoCampotematicoC.class, RubroEvaluacionProcesoCampotematicoC_Table.rubroEvalProcesoId.in(rubroEvaluacionProcesoKey)));
        //#endregion RubroEvaluacionProcesoCampotematicoC

        //#region CriterioRubroEvaluacionC
        beDatosRubroEvaluacionProceso.setObtenerCriterioRubroEvaluacionProceso(ConsultaUtils.getChangeItemsTableChild(CriterioRubroEvaluacionC.class, CriterioRubroEvaluacionC_Table.rubroEvalProcesoId.in(rubroEvaluacionProcesoKey)));
        //#endregion CriterioRubroEvaluacionC

        //#region T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC
        beDatosRubroEvaluacionProceso.setObtenerRubroEvaluacionProcesoEquipo(
                ConsultaUtils.getChangeItemsTableChild(
                        T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class, T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.rubroEvalProcesoId.in(rubroEvaluacionProcesoKey)));
        //#endregion T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC

        List<String> equipoRubroEvaluacionProcesoKey = new ArrayList<>();
        for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC procesoEquipoc: beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoEquipo()){
            equipoRubroEvaluacionProcesoKey.add(procesoEquipoc.getKey());
        }

        //#region T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC
        beDatosRubroEvaluacionProceso.setObtenerRubroEvaluacionProcesoIntegrante(
                ConsultaUtils.getChangeItemsTableChild(
                        T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class, T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.rubroEvaluacionEquipoId.in(equipoRubroEvaluacionProcesoKey)));
        //#endregion T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC

        GEDatosRubroEvaluacionProceso geDatosRubroEvaluacionProceso = new GEDatosRubroEvaluacionProceso();
        geDatosRubroEvaluacionProceso.setBeDatosRubroEvaluacionProceso(beDatosRubroEvaluacionProceso);

        List<String> tipoNotakeys = new ArrayList<>();
        for (RubroEvaluacionProcesoC rubroEvaluacionProcesoC: rubroEvaluacionProcesoList) {
            tipoNotakeys.add(rubroEvaluacionProcesoC.getTipoNotaId());
        }

        BEDatosEnvioTipoNota beDatosEnvioTipoNota = new BEDatosEnvioTipoNota();
        beDatosEnvioTipoNota.setTipoNota(ConsultaUtils.getChangeItemsTableChild(TipoNotaC.class, TipoNotaC_Table.key.in(tipoNotakeys)));
        beDatosEnvioTipoNota.setValorTipoNota(ConsultaUtils.getChangeItemsTableChild(ValorTipoNotaC.class, ValorTipoNotaC_Table.tipoNotaId.in(tipoNotakeys)));
        geDatosRubroEvaluacionProceso.setBeDatosEnvioTipoNota(beDatosEnvioTipoNota);

        List<String> equipokeys = new ArrayList<>();
        for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC maeRubroEvaluacionProcesoEquipoc: beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoEquipo()) {
            equipokeys.add(maeRubroEvaluacionProcesoEquipoc.getEquipoId());
        }
        BEDatosEnvioGrupo beDatosEnvioGrupo = new BEDatosEnvioGrupo();
        beDatosEnvioGrupo.setEquipo(ConsultaUtils.getChangeItemsTableChild(EquipoC.class, EquipoC_Table.key.in(equipokeys)));
        beDatosEnvioGrupo.setEquipo_integrante(ConsultaUtils.getChangeItemsTableChild(EquipoIntegranteC.class, EquipoIntegranteC_Table.equipoId.in(equipokeys)));

        List<String> grupokeys = new ArrayList<>();
        for (EquipoC equipo: beDatosEnvioGrupo.getEquipo()) {
            grupokeys.add(equipo.getGrupoEquipoId());
        }
        beDatosEnvioGrupo.setGrupo_equipo(ConsultaUtils.getChangeItemsTableChild(GrupoEquipoC.class, GrupoEquipoC_Table.key.in(grupokeys)));
        geDatosRubroEvaluacionProceso.setBeDatosEnvioGrupo(beDatosEnvioGrupo);

        List<String> rubroEvaluacionKey = new ArrayList<>();
        for (RubroEvaluacionProcesoC rubroEvaluacionProcesoC:
                rubroEvaluacionProcesoList)rubroEvaluacionKey.add(rubroEvaluacionProcesoC.getKey());

        final GEDatosTareasRecursos beDatosTareasRecursos = new GEDatosTareasRecursos();

        List<TareaRubroEvaluacionProceso> tareaRubroEvaluacionProcesoList = ConsultaUtils.getChangeItemsTableChild(TareaRubroEvaluacionProceso.class,
                    TareaRubroEvaluacionProceso_Table.rubroEvalProcesoId.in(rubroEvaluacionKey));

        List<String> TareaKeyList = new ArrayList<>();
        for (TareaRubroEvaluacionProceso tareaRubroEvaluacionProceso: tareaRubroEvaluacionProcesoList){
                TareaKeyList.add(tareaRubroEvaluacionProceso.getTareaId());
        }
        List<TareasC> tareasCList = ConsultaUtils.getChangeItemsTableChild(TareasC.class,
                TareasC_Table.key.in(TareaKeyList));

        beDatosTareasRecursos.setTareas(tareasCList);

        List<String> tareaKey = new ArrayList<>();
        for (TareasC tareasC: beDatosTareasRecursos.getTareas()){
            tareaKey.add(tareasC.getKey());
        }
        beDatosTareasRecursos.setTareasRecursos(ConsultaUtils.getChangeItemsTableChild(TareasRecursosC.class, TareasRecursosC_Table.tareaId.in(tareaKey)));
        List<String> recursosKey = new ArrayList<>();
        for (TareasRecursosC tareasRecursosC: beDatosTareasRecursos.getTareasRecursos()){
            recursosKey.add(tareasRecursosC.getRecursoDidacticoId());
        }
        beDatosTareasRecursos.setRecursoDidactico(ConsultaUtils.getChangeItemsTableChild(RecursoDidacticoEventoC.class, RecursoDidacticoEventoC_Table.key.in(recursosKey)));

        beDatosTareasRecursos.setTareaRubroEvaluacionProceso(tareaRubroEvaluacionProcesoList);
        Log.d(getClass().getSimpleName(),"size tarea recuros" +  beDatosTareasRecursos.getTareaRubroEvaluacionProceso().size());
        geDatosRubroEvaluacionProceso.setBeDatosTareaRecursos(beDatosTareasRecursos);

        BEDatosEvaluacionResultado beDatosEvaluacionResultado = new BEDatosEvaluacionResultado();
        List<RubroEvaluacionResultado> rubroEvaluacionResultado = ConsultaUtils.getChangeItemsTable(RubroEvaluacionResultado.class);
        List<EvaluacionResultado> evaluacionResultados = ConsultaUtils.getChangeItemsTable(EvaluacionResultado.class);
        beDatosEvaluacionResultado.setRubroEvaluacionResultado(rubroEvaluacionResultado);
        beDatosEvaluacionResultado.setEvaluacionResultado(evaluacionResultados);
        geDatosRubroEvaluacionProceso.setBeDatosRubroEvaluacionResultado(beDatosEvaluacionResultado);
        return geDatosRubroEvaluacionProceso;
    }

    private BEDatosSesionAprendizaje getBEDatosSesionAprendizaje(){
        BEDatosSesionAprendizaje beDatosSesionAprendizaje = new BEDatosSesionAprendizaje();
        beDatosSesionAprendizaje.setSesionAprendizaje(ConsultaUtils.getChangeItemsTable(SesionAprendizaje.class));
        return beDatosSesionAprendizaje;
    }

    private GEDatosTareasRecursos getGEDatosTareasRecursos(){
        final GEDatosTareasRecursos beDatosTareasRecursos = new GEDatosTareasRecursos();
        List<TareasC> tareasCList = ConsultaUtils.getChangeItemsTable(TareasC.class);
        beDatosTareasRecursos.setTareas(tareasCList);

        List<String> tareaKey = new ArrayList<>();
        for (TareasC tareasC: beDatosTareasRecursos.getTareas()){
            tareaKey.add(tareasC.getKey());
        }
        beDatosTareasRecursos.setTareasRecursos(ConsultaUtils.getChangeItemsTableChild(TareasRecursosC.class, TareasRecursosC_Table.tareaId.in(tareaKey)));
        List<String> recursosKey = new ArrayList<>();
        for (TareasRecursosC tareasRecursosC: beDatosTareasRecursos.getTareasRecursos()){
            recursosKey.add(tareasRecursosC.getRecursoDidacticoId());
        }

        List<RecursoDidacticoEventoC> recursoDidacticoEventoCList = ConsultaUtils.getChangeItemsTableChild(RecursoDidacticoEventoC.class, RecursoDidacticoEventoC_Table.key.in(recursosKey));
        beDatosTareasRecursos.setRecursoDidactico(recursoDidacticoEventoCList);

        List<String> recusoDidacticoIdList = new ArrayList<>();
        for (RecursoDidacticoEventoC recursoDidacticoEventoC: recursoDidacticoEventoCList)recusoDidacticoIdList.add(recursoDidacticoEventoC.getKey());

        List<RecursoArchivo> recursoArchivoList = ConsultaUtils.getChangeItemsTableChild(RecursoArchivo.class, RecursoArchivo_Table.recursoDidacticoId.in(recusoDidacticoIdList));
        beDatosTareasRecursos.setRecursoArchivo(recursoArchivoList);

        List<String> archivoIdList = new ArrayList<>();
        for (RecursoArchivo recursoArchivo: recursoArchivoList)archivoIdList.add(recursoArchivo.getArchivoId());
        List<Archivo> archivoList = ConsultaUtils.getChangeItemsTableChild(Archivo.class, Archivo_Table.key.in(archivoIdList));
        for (Archivo archivo : archivoList){
            String fileName = archivo.getPath();
            if(!TextUtils.isEmpty(fileName)){
                int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
                String file = fileName.substring(p + 1);
                archivo.setPath(file);
                Log.d(getClass().getSimpleName(),"file: " + file);
            }
        }
        beDatosTareasRecursos.setArchivo(archivoList);
        /*List<TareaRubroEvaluacionProceso> tareaRubroEvaluacionProcesoList = ConsultaUtils.getChangeItemsTableChild(TareaRubroEvaluacionProceso.class,
                TareaRubroEvaluacionProceso_Table.tareaId.in(tareaKey));

        beDatosTareasRecursos.setTareaRubroEvaluacionProceso(tareaRubroEvaluacionProcesoList);*/

        return beDatosTareasRecursos;
    }
    //#endregion getDatosExportarGlobal

}
