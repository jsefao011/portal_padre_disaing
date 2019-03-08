package com.consultoraestrategia.ss_crmeducativo.portal.familia.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.personaDao.PersonaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.CasoReporte_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones_Table;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.data.source.FamiliaDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.entities.PersonaUi;
import com.raizlabs.android.dbflow.converter.SqlDateConverter;
import com.raizlabs.android.dbflow.sql.language.Operator;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
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
            HashSet<Object> objectSet = new HashSet<>();

            List<Integer> listIdHijos = new ArrayList<>();
            List<Relaciones> listRelacionesHijos = SQLite.select()
                    .from(Relaciones.class)
                    .where(Relaciones_Table.personaVinculadaId.withTable().eq(idPersona))
                    .queryList();

            for (Relaciones relaciones : listRelacionesHijos) {

                Persona persona = personaDao.get(relaciones.getPersonaPrincipalId());
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
                    personaUi.setApoderado(false);
                    objectSet.add(personaUi);
                }
                listIdHijos.add(relaciones.getPersonaPrincipalId());
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
                        break;
                    case Relaciones.PADRE:
                        personaUi.setTipo(PersonaUi.Tipo.PADRE);
                        break;
                }

                objectSet.add(personaUi);
            }

            List<Object> objectList = new ArrayList<>(objectSet);
            callback.onLoad(true, objectList);

        } catch (Exception e) {
            e.printStackTrace();
            callback.onLoad(false, new ArrayList<Object>());
        }
    }

}
