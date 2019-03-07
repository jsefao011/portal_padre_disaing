package com.consultoraestrategia.ss_crmeducativo.dao.personaDao;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocenteDet;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocenteDet_Table;
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
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario_Table;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Where;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.Property;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by @stevecampos on 26/04/2018.
 */

public class PersonaDaoImpl extends BaseIntegerDaoImpl<Persona, Persona_Table> implements PersonaDao {

    private static PersonaDao mInstance;

    private PersonaDaoImpl() {
    }

    public static PersonaDao getInstance() {
        if (mInstance == null) {
            mInstance = new PersonaDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Class<Persona> getEntityClass() {
        return Persona.class;
    }

    @Override
    protected Class<Persona_Table> getTableclass() {
        return Persona_Table.class;
    }

    @Override
    protected Property<Integer> getPrimaryKeyProperty() {
        return Persona_Table.personaId;
    }


    @Override
    public List<Persona> getPersonas(int usuarioId) {

        List<Persona> personaList = new ArrayList<>();

        Where<Persona> where = SQLite.select(
                Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES))
                .from(Persona.class)
                .innerJoin(Usuario.class)
                .on(Persona_Table.personaId.withTable()
                        .eq(Usuario_Table.personaId.withTable()))
                        .where(Usuario_Table.usuarioId.eq(usuarioId));

        personaList = where.queryList();
        return personaList;
    }

    @Override
    public List<Persona> getAlumnosDeCargaCurso(int cargaCursoId) {
        return searchAlumnosDeCargaCurso(true, false, null,cargaCursoId);
    }

    @Override
    public List<Persona> searchAlumnosDeCargaCurso(boolean orderByNombre, boolean orderByApellido , String persona, int cargaCursoId) {
        List<Persona> personaList = new ArrayList<>();
        Log.d(getClass().getSimpleName(), "cargaCursoId "+ cargaCursoId);
        try {//cargaCursoId 1375
            CargaCursos cargaCursos = SQLite.select()
                    .from(CargaCursos.class)
                    .where(CargaCursos_Table.cargaCursoId.eq(cargaCursoId))
                    .querySingle();

            if(cargaCursos.getComplejo()== 0){
                Where<Persona> where = SQLite.select(
                        Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES))
                        .from(Persona.class)
                        .innerJoin(Contrato.class)
                        .on(Persona_Table.personaId.withTable()
                                .eq(Contrato_Table.personaId.withTable()))
                        .innerJoin(DetalleContratoAcad.class)
                        .on(Contrato_Table.idContrato.withTable()
                                .eq(DetalleContratoAcad_Table.idContrato.withTable()))
                        .where(DetalleContratoAcad_Table.cargaCursoId.eq(cargaCursoId))
                        .and(Contrato_Table.vigente.withTable().eq(1));

                if(orderByNombre){
                    where.orderBy(Persona_Table.nombres.withTable().asc());
                }else if(orderByApellido){
                    where.orderBy(Persona_Table.apellidoPaterno.withTable().asc());
                }

                personaList = where.queryList();
                Log.d(getClass().getSimpleName(), " size personas list "+personaList.size() );
            }else {
                Empleado empleado = SQLite.select()
                        .from(Empleado.class)
                        .where(Empleado_Table.personaId.eq(SessionUser.getCurrentUser().getPersonaId()))
                        .querySingle();
                if(empleado!=null){
                    Where<Persona> where = SQLite.select(
                            Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES))
                            .from(Persona.class)
                            .innerJoin(CargaCursoDocenteDet.class)
                            .on(Persona_Table.personaId.withTable().eq(CargaCursoDocenteDet_Table.alumnoId.withTable()))
                            .innerJoin(CargaCursoDocente.class)
                            .on(CargaCursoDocenteDet_Table.cargaCursoDocenteId.withTable().eq(CargaCursoDocente_Table.cargaCursoDocenteId.withTable()))
                            .where(CargaCursoDocente_Table.cargaCursoId.withTable().eq(cargaCursoId))
                            .and(Contrato_Table.vigente.withTable().withTable().eq(1))
                            .and(CargaCursoDocente_Table.docenteId.withTable().eq(empleado.getEmpleadoId()));
                    if(orderByNombre){
                        where.orderBy(Persona_Table.apellidoPaterno.withTable().asc());
                    }else if(orderByApellido){
                        where.orderBy(Persona_Table.nombres.withTable().asc());
                    }

                    personaList = where.queryList();
                }

            }

            if(TextUtils.isEmpty(persona))return personaList;

            List<Persona> personasFiltro = new ArrayList<>();
            for (Persona itemPersona : personaList){

                if(!TextUtils.isEmpty(itemPersona.getNombres())){
                    Log.d(getTableclass().getSimpleName(), "Filtro :"+itemPersona.getNombres().toLowerCase()+" = "+persona);
                    if(itemPersona.getNombres().toLowerCase().contains(persona)){
                        personasFiltro.add(itemPersona);
                    }
                }

                if(!TextUtils.isEmpty(itemPersona.getApellidos())){
                    Log.d(getTableclass().getSimpleName(), "Filtro :"+itemPersona.getApellidos().toLowerCase()+" = "+persona);
                    if(itemPersona.getApellidos().toLowerCase().contains(persona.toLowerCase().trim())){
                        int posicion = personasFiltro.indexOf(itemPersona);
                        if(posicion == -1) personasFiltro.add(itemPersona);
                    }
                }
            }

            return personasFiltro;

        }catch (Exception e){
            e.printStackTrace();
            return personaList;
        }
    }

    @Override
    public List<Persona> getAlumnosDeRubro(boolean orderByNombre, boolean orderByApellido, String persona, String rubroEvaluacionId) {
        IProperty[] parametros = Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES);
        List<Persona> personaList = SQLite.select(parametros)
                .from(Persona.class)
                .innerJoin(EvaluacionProcesoC.class)
                .on(EvaluacionProcesoC_Table.alumnoId.withTable()
                        .eq(Persona_Table.personaId.withTable()))
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable().eq(rubroEvaluacionId))
                .groupBy(parametros)
                .queryList();
        if(orderByNombre){
            //Clase anónima
            Collections.sort(personaList, new Comparator<Persona>() {
                @Override
                public int compare(Persona o1, Persona o2) {
                    int result = 0;
                    try {
                        result = o1.getNombres().toLowerCase().compareTo(o2.getNombres().toLowerCase());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return result;
                }
            });


        }else if(orderByApellido){
            //Clase anónima
            Collections.sort(personaList, new Comparator<Persona>() {
                @Override
                public int compare(Persona o1, Persona o2) {
                    int result = 0;
                    try {
                        result = o1.getApellidos().toLowerCase().compareTo(o2.getApellidos().toLowerCase());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return result;
                }
            });

        }

        if(TextUtils.isEmpty(persona))return personaList;

        List<Persona> personasFiltro = new ArrayList<>();
        for (Persona itemPersona : personaList){

            if(!TextUtils.isEmpty(itemPersona.getNombres())){
                Log.d(getTableclass().getSimpleName(), "Filtro :"+itemPersona.getNombres().toLowerCase()+" = "+persona);
                if(itemPersona.getNombres().toLowerCase().contains(persona)){
                    personasFiltro.add(itemPersona);
                }
            }

            if(!TextUtils.isEmpty(itemPersona.getApellidos())){
                Log.d(getTableclass().getSimpleName(), "Filtro :"+itemPersona.getApellidos().toLowerCase()+" = "+persona);
                if(itemPersona.getApellidos().toLowerCase().contains(persona.toLowerCase().trim())){
                    int posicion = personasFiltro.indexOf(itemPersona);
                    if(posicion == -1) personasFiltro.add(itemPersona);
                }
            }
        }

        return personasFiltro;
    }



    @Override
    public List<Persona> getHijos(int personaPadre) {
        return SQLite.select()
                .from(Persona.class)
                .innerJoin(Relaciones.class)
                .on(Relaciones_Table.personaPrincipalId.withTable().eq(Persona_Table.personaId.withTable()))
                .where(Relaciones_Table.personaVinculadaId.withTable().is(personaPadre))
                .queryList();
    }

    @Override
    public List<Persona> getAlumnosDeRubroEquipo(List<String> rubroEquipoKeyList) {

        return SQLite.select(Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES))
                .from(Persona.class)
                .innerJoin(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class)
                .on(Persona_Table.personaId.withTable()
                        .eq(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.personaId.withTable()))
                .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.rubroEvaluacionEquipoId.in(rubroEquipoKeyList))
                .queryList();
    }
}
