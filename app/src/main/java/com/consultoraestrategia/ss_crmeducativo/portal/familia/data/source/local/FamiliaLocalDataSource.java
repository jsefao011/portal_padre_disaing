package com.consultoraestrategia.ss_crmeducativo.portal.familia.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.dao.personaDao.PersonaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CasoReporte_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones_Table;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.data.source.FamiliaDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.converter.SqlDateConverter;
import com.raizlabs.android.dbflow.sql.language.Operator;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Where;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class FamiliaLocalDataSource implements FamiliaDataSource {

    private static String TAG = FamiliaLocalDataSource.class.getSimpleName();
    private PersonaDao personaDao;

    public FamiliaLocalDataSource(PersonaDao personaDao) {
        this.personaDao = personaDao;
    }

    @Override
    public void getListFamilia(int idPersona, SucessCallback<List<Object>> callback) {

        try {
            callback.onLoad(true, getListFamilias(idPersona));

        } catch (Exception e) {
            e.printStackTrace();
            callback.onLoad(false, new ArrayList<Object>());
        }
    }

    @Override
    public void setListFamilia(List<Object> listFamilia, int idPersona, SucessCallback<List<Object>> callback) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();

                for (Object o : listFamilia){
                    if (o instanceof PersonaUi){
                        PersonaUi personaUi = (PersonaUi) o;
                        Persona persona = personaDao.get(personaUi.getIdPersona());
                        if (persona!=null){
                            persona.setCelular(personaUi.getTelefono());
                            persona.setDireccion(personaUi.getDireccion());
                            persona.setCorreo(personaUi.getCorreo());
                            persona.update();
                            databaseWrapper.setTransactionSuccessful();
                        }
                    }
                }
                callback.onLoad(true, getListFamilias(idPersona));
            }catch (Exception e){
                e.printStackTrace();
                callback.onLoad(false, listFamilia);
            }finally {
            databaseWrapper.endTransaction();
        }
    }

    private List<Object> getListFamilias(int idPersona){

        HashSet<PersonaUi> objectSet = new HashSet<>();

        List<Integer> listIdHijos = new ArrayList<>();
        List<Persona> listRelacionesHijos = SQLite.select()
                .from(Persona.class)
                .innerJoin(Relaciones.class)
                .on(Persona_Table.personaId.withTable()
                .eq(Relaciones_Table.personaPrincipalId.withTable()))
                .where(Relaciones_Table.personaVinculadaId.withTable().eq(idPersona))
                .queryList();



        for (Persona persona: listRelacionesHijos) {

            //Persona persona = personaDao.get(relaciones.getPersonaPrincipalId());
            if (persona != null) {
                PersonaUi personaUi = new PersonaUi();
                personaUi.setIdPersona(persona.getPersonaId());
                personaUi.setNombre(persona.getFirstName());
                personaUi.setApellidoCompleto(persona.getApellidos());
                personaUi.setCorreo(persona.getCorreo());
                personaUi.setApellidoMaterno(persona.getApellidoMaterno());
                personaUi.setApellidoPaterno(persona.getApellidoPaterno());
                personaUi.setTelefono(persona.getCelular());
                personaUi.setDireccion(persona.getDireccion());
                personaUi.setUrlImagen(persona.getFoto());
                personaUi.setTipo(PersonaUi.Tipo.HIJO);
                personaUi.setNum(3);
                personaUi.setApoderado(false);
                objectSet.add(personaUi);
            }
            listIdHijos.add(persona.getPersonaId());
        }



        List<Relaciones> listRelacionesPadres = SQLite.select()
                .from(Relaciones.class)
                .where(Relaciones_Table.personaPrincipalId.withTable().in(listIdHijos))
                .queryList();

        for (Relaciones relaciones : listRelacionesPadres){
            Persona persona = personaDao.get(relaciones.getPersonaVinculadaId());
            PersonaUi personaUi = new PersonaUi();
            personaUi.setIdPersona(persona.getPersonaId());
            personaUi.setNombre(persona.getFirstName());
            personaUi.setApellidoCompleto(persona.getApellidos());
            personaUi.setCorreo(persona.getCorreo());
            personaUi.setApellidoMaterno(persona.getApellidoMaterno());
            personaUi.setApellidoPaterno(persona.getApellidoPaterno());
            personaUi.setTelefono(persona.getCelular());
            personaUi.setDireccion(persona.getDireccion());
            personaUi.setUrlImagen(persona.getFoto());
            personaUi.setApoderado(false);

            Contrato contrato = null;
            contrato = SQLite.select()
                    .from(Contrato.class)
                    .where(Contrato_Table.apoderadoId.eq(relaciones.getPersonaVinculadaId()))
                    .querySingle();

            if (contrato!=null){
                personaUi.setApoderado(true);
            }

            switch (relaciones.getTipoId()){
                case Relaciones.MADRE:
                    personaUi.setTipo(PersonaUi.Tipo.MADRE);
                    personaUi.setNum(2);
                    break;
                case Relaciones.PADRE:
                    personaUi.setTipo(PersonaUi.Tipo.PADRE);
                    personaUi.setNum(1);
                    break;
            }

            objectSet.add(personaUi);
        }

        List<PersonaUi> objectList = new ArrayList<>(objectSet);
        Collections.sort(objectList, new Comparator<PersonaUi>() {
            @Override
            public int compare(PersonaUi o1, PersonaUi o2) {
                return new Integer(o1.getNum()).compareTo(new Integer(o2.getNum()));
            }
        });
        List<Object> list = new ArrayList<Object>(objectList);

        return list;
    }




}
