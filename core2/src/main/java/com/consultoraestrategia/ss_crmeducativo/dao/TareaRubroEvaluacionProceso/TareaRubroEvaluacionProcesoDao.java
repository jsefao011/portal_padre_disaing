package com.consultoraestrategia.ss_crmeducativo.dao.TareaRubroEvaluacionProceso;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.entities.TareaRubroEvaluacionProceso;

public interface TareaRubroEvaluacionProcesoDao extends BaseDao<TareaRubroEvaluacionProceso> {
    boolean elimarTareaRubroEvaluacionProceso(String rubroEvalProcesoId);
    TareaRubroEvaluacionProceso getTareaRubroPorRubroId(String rubroEvaluacionProcesoId);
}
