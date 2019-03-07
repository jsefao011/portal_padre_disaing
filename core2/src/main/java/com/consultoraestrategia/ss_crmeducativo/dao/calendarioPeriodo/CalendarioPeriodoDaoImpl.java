package com.consultoraestrategia.ss_crmeducativo.dao.calendarioPeriodo;


import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.Property;

import java.util.List;

/**
 * Created by SCIEV on 31/08/2018.
 */

public class CalendarioPeriodoDaoImpl extends BaseIntegerDaoImpl<CalendarioPeriodo, CalendarioPeriodo_Table> implements CalendarioPeriodoDao{


    private static CalendarioPeriodoDaoImpl mInstance;

    private CalendarioPeriodoDaoImpl() {
    }

    public static CalendarioPeriodoDaoImpl getInstance() {
        if (mInstance == null) {
            mInstance = new CalendarioPeriodoDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Property<Integer> getPrimaryKeyProperty() {
        return CalendarioPeriodo_Table.calendarioPeriodoId;
    }

    @Override
    protected Class<CalendarioPeriodo> getEntityClass() {
        return CalendarioPeriodo.class;
    }

    @Override
    protected Class<CalendarioPeriodo_Table> getTableclass() {
        return CalendarioPeriodo_Table.class;
    }

    @Override
    public List<CalendarioPeriodo> getRubrosEvalProceso(List<String> rubroEvalProcesoKeyList) {
        return   SQLite.select(Utils.f_allcolumnTable(CalendarioPeriodo_Table.ALL_COLUMN_PROPERTIES))
                .from(CalendarioPeriodo.class)
                .innerJoin(RubroEvaluacionProcesoC.class)
                .on(CalendarioPeriodo_Table.calendarioPeriodoId.withTable()
                        .eq(RubroEvaluacionProcesoC_Table.calendarioPeriodoId.withTable()))
                .where(RubroEvaluacionProcesoC_Table.key.in(rubroEvalProcesoKeyList))
                .groupBy(Utils.f_allcolumnTable(CalendarioPeriodo_Table.ALL_COLUMN_PROPERTIES))
                .queryList();
    }
}
