package com.consultoraestrategia.ss_crmeducativo.dao.dimensionObservada;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionObservada;

import java.util.List;

/**
 * Created by SCIEV on 16/08/2018.
 */

public interface DimensionObservadaDao extends BaseDao<DimensionObservada> {
    List<DimensionObservada> getTwoMaxDimensionesAlumno(int personaId, int entidadId);
    List<DimensionObservada> getDimensionesAlumno(int alumnoId, int entidadId);
    List<DimensionObservada> getDimensionesAlumnoInstrumento(int alumnoId, int entidadId, String instrumentoId);
}
