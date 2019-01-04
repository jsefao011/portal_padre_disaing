package com.consultoraestrategia.ss_crmeducativo.portal.tareas.data.source;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.entities.TareasC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class TareasLocalDataSource implements TareasDataSource {
String TAG= TareasLocalDataSource.class.getSimpleName();

    @Override
    public void getTareasCursosList(CallbackTareasCursosAlumno callbackTareasCursosAlumno) {
        Log.d(TAG, "getTareasCursosList ");
        List<TareasC > lista= SQLite.select()
                .from(TareasC.class)
                .where(TareasC_Table.estadoId.notIn(265))
                .and(TareasC_Table.sesionAprendizajeId.eq(0))
                .orderBy(TareasC_Table.fechaAccion.asc())
                .queryList();
        for(TareasC tareasC:lista )Log.d(TAG, "TAREAS "+tareasC.getTitulo() + " count "+ lista.size());

    }
}
