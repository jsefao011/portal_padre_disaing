package com.consultoraestrategia.ss_crmeducativo.dao.personaDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDao;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;

import java.util.List;

/**
 * Created by @stevecampos on 26/04/2018.
 */

public interface PersonaDao extends BaseIntegerDao<Persona> {
    List<Persona> getPersonas(int usuarioId);
    List<Persona> getAlumnosDeCargaCurso(int cargaCursoId);
    List<Persona> searchAlumnosDeCargaCurso(boolean orderByNombre, boolean orderByApellido, String persona, int cargaCursoId);
    List<Persona> getAlumnosDeRubro(boolean orderByNombre, boolean orderByApellido, String persona, String rubroEvaluacionId);
    List<Persona> getHijos(int personaPadre);
    List<Persona> getAlumnosDeRubroEquipo(List<String> rubroEquipoKeyList);
}
