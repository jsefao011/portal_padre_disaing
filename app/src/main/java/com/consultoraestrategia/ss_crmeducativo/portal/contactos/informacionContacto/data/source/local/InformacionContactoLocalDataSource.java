package com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.personaDao.PersonaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones_Table;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.data.source.InformacionContactoDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.entities.PersonaUi;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class InformacionContactoLocalDataSource implements InformacionContactoDataSource {

    private static String TAG = InformacionContactoLocalDataSource.class.getSimpleName();
    private PersonaDao personaDao;

    public InformacionContactoLocalDataSource(PersonaDao personaDao) {
    this.personaDao = personaDao;
    }

    @Override
    public void getInformacionALumno(int idAlumno, SucessCallback<List<Object>> sucessCallback) {
        Log.d(TAG, "idAlumno" + idAlumno);

        try{
            HashSet<PersonaUi> objectSet = new HashSet<>();
            List<Integer> integerList  = new ArrayList<>();
            List<Relaciones> relaciones = SQLite.select()
                    .from(Relaciones.class)
                    .where(Relaciones_Table.personaPrincipalId.withTable().eq(idAlumno))
                    .queryList();
            for (Relaciones rls : relaciones) {
                integerList.add(rls.getPersonaVinculadaId());
                Persona persona = personaDao.get(rls.getPersonaVinculadaId());
                if (persona != null) {
                    PersonaUi personaUi = new PersonaUi();
                    personaUi.setPersonaId(persona.getPersonaId());
                    personaUi.setNombres(persona.getNombres());
                    personaUi.setApellidoPaterno(persona.getApellidoPaterno());
                    personaUi.setApellidoMaterno(persona.getApellidoMaterno());
                    personaUi.setNumDoc(persona.getNumDoc());
                    personaUi.setFoto(persona.getFoto());
                    personaUi.setFechaNac(persona.getFechaNac());
                    personaUi.setDireccion(persona.getDireccion());
                    personaUi.setCorreo(persona.getCorreo());
                    personaUi.setCelular(persona.getCelular());
                    personaUi.setTipoObjeto(PersonaUi.TipoObjeto.PERSONA);



                    switch (rls.getTipoId()) {
                        case Relaciones.MADRE:
                            personaUi.setTipo(PersonaUi.Tipo.MADRE);
                            personaUi.setNumOrd(2);
                            break;
                        case Relaciones.PADRE:
                            personaUi.setTipo(PersonaUi.Tipo.PADRE);
                            personaUi.setNumOrd(1);
                            break;
                        case Relaciones.ABUELO_A:
                            personaUi.setTipo(PersonaUi.Tipo.ABUELA);
                            personaUi.setNumOrd(3);
                            break;
                        case Relaciones.TIO_A:
                            personaUi.setTipo(PersonaUi.Tipo.TIO);
                            personaUi.setNumOrd(4);
                            break;
                    }

                    objectSet.add(personaUi);
                }
            }

                List<Relaciones> relacionesHijos = SQLite.select()
                        .from(Relaciones.class)
                        .where(Relaciones_Table.personaVinculadaId.withTable().in(integerList))
                        .queryList();

                 for (Relaciones relacionesHijo : relacionesHijos){
                     Persona personaHijo = personaDao.get(relacionesHijo.getPersonaPrincipalId());
                     if (personaHijo!=null){
                         PersonaUi personaUiHijo = new PersonaUi();
                         personaUiHijo.setPersonaId(personaHijo.getPersonaId());
                         personaUiHijo.setNombres(personaHijo.getNombres());
                         personaUiHijo.setApellidoMaterno(personaHijo.getApellidoMaterno());
                         personaUiHijo.setApellidoPaterno(personaHijo.getApellidoMaterno());
                         personaUiHijo.setCorreo(personaHijo.getCorreo());
                         personaUiHijo.setCelular(personaHijo.getCelular());
                         personaUiHijo.setDireccion(personaHijo.getDireccion());
                         personaUiHijo.setNumDoc(personaHijo.getNumDoc());
                         personaUiHijo.setFoto(personaHijo.getFoto());
                         personaUiHijo.setOcupacion(personaHijo.getOcupacion());
                         personaUiHijo.setTipo(PersonaUi.Tipo.HIJO);
                         personaUiHijo.setTipoObjeto(PersonaUi.TipoObjeto.PERSONA);
                         personaUiHijo.setNumOrd(5);
                         objectSet.add(personaUiHijo);

                     }
                 }

            List<PersonaUi> personaUiList = new ArrayList<>(objectSet);
            Collections.sort(personaUiList, new Comparator<PersonaUi>() {
                @Override
                public int compare(PersonaUi o1, PersonaUi o2) {
                    return new Integer(o1.getNumOrd()).compareTo(new Integer(o2.getNumOrd()));
                }
            });
            List<Object> list = new ArrayList<Object>(personaUiList);

            sucessCallback.onLoad(true, list);
        }catch (Exception e){
            e.printStackTrace();
            sucessCallback.onLoad(false, new ArrayList<Object>());
        }
    }

    @Override
    public void getInformacionDocente(int idDocentePerson, SucessCallback<List<Object>> sucessCallback) {

        Log.d("getInformacionDocente", "idAlumno" + idDocentePerson);

        try{
            List<Object> list = new ArrayList<>();
            Persona persona = personaDao.get(idDocentePerson);
            if (persona!=null){
                PersonaUi personaUi = new PersonaUi();
                personaUi.setPersonaId(persona.getPersonaId());
                personaUi.setNombres(persona.getNombres());
                personaUi.setCelular(persona.getCelular());
                personaUi.setCorreo(persona.getCorreo());
                personaUi.setDireccion(persona.getDireccion());
                personaUi.setFoto(persona.getFoto());
                personaUi.setTipo(PersonaUi.Tipo.DOCENTE);
                personaUi.setTipoObjeto(PersonaUi.TipoObjeto.PERSONA);
                list.add(personaUi);
                sucessCallback.onLoad(true, list);
            }
        }catch (Exception e){
            e.printStackTrace();
            sucessCallback.onLoad(false, new ArrayList<Object>());
        }

    }


}
