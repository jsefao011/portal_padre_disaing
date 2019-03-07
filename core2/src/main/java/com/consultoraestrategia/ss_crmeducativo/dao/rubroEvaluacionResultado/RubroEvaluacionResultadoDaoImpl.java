package com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionResultado;


import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionResultado;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionResultado_Table;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import okhttp3.internal.Util;


/**
 * Created by kike on 22/05/2018.
 */

public class RubroEvaluacionResultadoDaoImpl extends BaseDaoImpl<RubroEvaluacionResultado, RubroEvaluacionResultado_Table> implements RubroEvaluacionResultadoDao {
    public static final String TAG = RubroEvaluacionResultadoDaoImpl.class.getSimpleName();
    private static RubroEvaluacionResultadoDao mInstance;
    public static RubroEvaluacionResultadoDao getInstance() {
        if (mInstance == null) {
            mInstance = new RubroEvaluacionResultadoDaoImpl();
        }
        return mInstance;
    }
    @Override
    protected Class<RubroEvaluacionResultado> getEntityClass() {
        return RubroEvaluacionResultado.class;
    }

    @Override
    protected Class<RubroEvaluacionResultado_Table> getTableclass() {
        return RubroEvaluacionResultado_Table.class;
    }


    @Override
    public void actualizarEstadoRubroEvaluar(int compentenciaId, int calendarioPeriodoId, int silaboEventoId, int estadoEvaluador) {
        Log.d(TAG, "COMPENTECA "+ compentenciaId);
            RubroEvaluacionResultado rubroEvaluacionResultado= SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionResultado_Table.ALL_COLUMN_PROPERTIES))
                    .from(RubroEvaluacionResultado.class)
                    .where(RubroEvaluacionResultado_Table.rubroEvalResultadoId.withTable().eq(compentenciaId))
                    .and(RubroEvaluacionResultado_Table.silaboEventoId.withTable().eq(silaboEventoId))
                    .and(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable().eq(calendarioPeriodoId)).querySingle();
            if(rubroEvaluacionResultado!=null){
                rubroEvaluacionResultado.setEstadoId(estadoEvaluador);
                rubroEvaluacionResultado.setSyncFlag(BaseEntity.FLAG_UPDATED);
                rubroEvaluacionResultado.update();
            }


    }

    @Override
    public void actualizarResultadodoAncla(int compentenciaId, int calendarioPeriodoId, int silaboEventoId, String rubroProcesoKey, int anclado) {
        Log.d(TAG, "actualizarResultadodoAncla " + rubroProcesoKey + " anclado "+ anclado+ " compentenciaId " +compentenciaId +" silaboEventoId "+ silaboEventoId +" calendarioPeriodoId "+calendarioPeriodoId);
            RubroEvaluacionResultado rubroEvaluacionResultado= SQLite.select(Utils.f_allcolumnTable(RubroEvaluacionResultado_Table.ALL_COLUMN_PROPERTIES))
                    .from(RubroEvaluacionResultado.class)
                    .where(RubroEvaluacionResultado_Table.competenciaId.withTable().eq(compentenciaId))
                    .and(RubroEvaluacionResultado_Table.silaboEventoId.withTable().eq(silaboEventoId))
                    .and(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable().eq(calendarioPeriodoId)).querySingle();

            Log.d(TAG, "rubroEvaluacionResultado " + rubroEvaluacionResultado.getRubroEvalResultadoId());
          if(rubroEvaluacionResultado!=null){
              rubroEvaluacionResultado.setRubroEvalProcesoId(rubroProcesoKey);
              rubroEvaluacionResultado.setEstadoId(anclado);
              rubroEvaluacionResultado.setSyncFlag(BaseEntity.FLAG_UPDATED);
              rubroEvaluacionResultado.save();
          }

    }
}
