package com.consultoraestrategia.ss_crmeducativo.portal.contactos.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.data.source.ContactosDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.entities.ApoderadoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.entities.PersonaUi;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ContactosLocalDataSource implements ContactosDataSource {

    private static String TAG = ContactosDataSource.class.getSimpleName();

    public ContactosLocalDataSource() {
    }

    @Override
    public void getCompaneros(int idAlumno, int idProgramaEducativo, SucessCallback<List<Object>> callback) {
        Log.d("intIdAlumno", "true" + idAlumno);

        try{
            List<Integer> integerList = new ArrayList<>();
            Set<Object> objectList = new LinkedHashSet<>();
            List<CargaCursos> cargaCursos = SQLite.select()
                    .from(CargaCursos.class)
                    .innerJoin(DetalleContratoAcad.class)
                    .on(CargaCursos_Table.cargaCursoId.withTable().eq(DetalleContratoAcad_Table.cargaCursoId.withTable()))
                    .innerJoin(Contrato.class)
                    .on(DetalleContratoAcad_Table.idContrato.withTable().eq(Contrato_Table.idContrato.withTable()))
                    .where(Contrato_Table.personaId.withTable().eq(idAlumno))
                    //.and(CargaCursos_Table.cargaAcademicaId.withTable().eq(DetalleContratoAcad_Table.cargaAcademicaId.withTable()))
                    .and(Contrato_Table.vigente.withTable().eq(1))
                    .queryList();

            for (CargaCursos cargaCurso: cargaCursos)integerList.add(cargaCurso.getCargaAcademicaId());

            List<Persona> personasList = SQLite.select()
                    .from(Persona.class)
                    .innerJoin(Contrato.class)
                    .on(Persona_Table.personaId.withTable().eq(Contrato_Table.personaId.withTable()))
                    .innerJoin(DetalleContratoAcad.class)
                    .on(Contrato_Table.idContrato.withTable().eq(DetalleContratoAcad_Table.idContrato.withTable()))
                    .where(DetalleContratoAcad_Table.cargaAcademicaId.withTable().in(integerList))
                    .and(Contrato_Table.vigente.withTable().eq(1))
                    .orderBy(Persona_Table.nombres.withTable().asc())
                    .queryList();

            for (Persona persona : personasList){
                PersonaUi personaUi = new PersonaUi();
                personaUi.setPersonaId(persona.getPersonaId());
                personaUi.setNombres(persona.getNombres());
                personaUi.setApellidoMaterno(persona.getApellidoMaterno());
                personaUi.setApellidoPaterno(persona.getApellidoPaterno());
                personaUi.setCelular(persona.getCelular());
                personaUi.setCorreo(persona.getCorreo());
                personaUi.setDireccion(persona.getDireccion());
                personaUi.setFechaNac(persona.getFechaNac());
                personaUi.setFoto(persona.getFoto());
                personaUi.setGenero(persona.getGenero());
                personaUi.setEstadoCivil(persona.getEstadoCivil());
                personaUi.setNumDoc(persona.getNumDoc());
                personaUi.setTipoObjeto(PersonaUi.TipoObjeto.PERSONA);

                PersonaUi personaUi1 = new PersonaUi();
                personaUi1.setTipoObjeto(PersonaUi.TipoObjeto.LETRA);
                char letter = personaUi.getNombres().charAt(0);
                String letterString = String.valueOf(letter);
                personaUi1.setNombres(letterString);
                objectList.add(personaUi1);

                Persona apoderado = SQLite.select()
                        .from(Persona.class)
                        .innerJoin(Contrato.class)
                        .on(Persona_Table.personaId.withTable().eq(Contrato_Table.personaId.withTable()))
                        .where(Contrato_Table.personaId.withTable().eq(personaUi.getPersonaId()))
                        .querySingle();

                if (apoderado!=null){
                    ApoderadoUi apoderadoUi = new ApoderadoUi();
                    apoderadoUi.setId(apoderado.getPersonaId());
                    apoderadoUi.setNombre(personaUi.getNombres());
                    apoderadoUi.setApellido(personaUi.getApellidoMaterno() + " " + personaUi.getApellidoPaterno());
                    apoderadoUi.setCelular(personaUi.getCelular());
                    apoderadoUi.setCorreo(personaUi.getCorreo());
                    personaUi.setApoderadoUi(apoderadoUi);
                }

                objectList.add(personaUi);
            }


            List<Object> objects = new ArrayList<>(objectList);
            callback.onLoad(true, objects);
        }catch (Exception e){
            e.printStackTrace();
            callback.onLoad(false, new ArrayList<Object>());
        }


    }

    @Override
    public void getDocentes(int idAlumno, int idProgramaEducativo, SucessCallback<List<Object>> callback) {

        try{
            List<Integer> integerList = new ArrayList<>();
            List<Object> objectList = new ArrayList<>();
            List<CargaCursos> cargaCursos = SQLite.select()
                    .from(CargaCursos.class)
                    .innerJoin(DetalleContratoAcad.class)
                    .on(CargaCursos_Table.cargaCursoId.withTable().eq(DetalleContratoAcad_Table.cargaCursoId.withTable()))
                    .innerJoin(Contrato.class)
                    .on(DetalleContratoAcad_Table.idContrato.withTable().eq(Contrato_Table.idContrato.withTable()))
                    .where(Contrato_Table.personaId.withTable().eq(1590))
                    .and(CargaCursos_Table.cargaAcademicaId.withTable().eq(DetalleContratoAcad_Table.cargaAcademicaId.withTable()))
                    .and(Contrato_Table.vigente.withTable().eq(1))
                    .queryList();

            for (CargaCursos cargaCurso : cargaCursos) integerList.add(cargaCurso.getEmpleadoId());

            List<Persona> personasList = SQLite.select()
                    .from(Persona.class)
                    .innerJoin(Empleado.class)
                    .on(Persona_Table.personaId.withTable().eq(Empleado_Table.personaId.withTable()))
                    .where(Empleado_Table.empleadoId.withTable().in(integerList))
                    .queryList();

            for (Persona persona : personasList){
                PersonaUi personaUi = new PersonaUi();
                personaUi.setPersonaId(persona.getPersonaId());
                personaUi.setNombres(persona.getNombres());
                personaUi.setApellidoMaterno(persona.getApellidoMaterno());
                personaUi.setApellidoPaterno(persona.getApellidoPaterno());
                personaUi.setCelular(persona.getCelular());
                personaUi.setCorreo(persona.getCorreo());
                personaUi.setDireccion(persona.getDireccion());
                personaUi.setFechaNac(persona.getFechaNac());
                personaUi.setFoto(persona.getFoto());
                personaUi.setGenero(persona.getGenero());
                personaUi.setEstadoCivil(persona.getEstadoCivil());
                personaUi.setNumDoc(persona.getNumDoc());
                personaUi.setTipoObjeto(PersonaUi.TipoObjeto.PERSONA);

                PersonaUi personaUi1 = new PersonaUi();
                personaUi1.setTipoObjeto(PersonaUi.TipoObjeto.LETRA);
                char letter = personaUi.getNombres().charAt(0);
                String letterString = String.valueOf(letter);
                personaUi1.setNombres(letterString);
                objectList.add(personaUi1);
                objectList.add(personaUi);
            }


            List<Object> objects = new ArrayList<>(objectList);
            callback.onLoad(true, objects);



        }catch (Exception e){
            e.printStackTrace();
            callback.onLoad(false, new ArrayList<Object>());
        }
    }

    @Override
    public void getDirectivos(int idAlumno, int idProgramaEducativo, SucessCallback<List<Object>> callback) {

    }
}
