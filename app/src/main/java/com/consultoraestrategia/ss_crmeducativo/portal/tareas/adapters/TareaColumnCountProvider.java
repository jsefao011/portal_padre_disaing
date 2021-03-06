package com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters;

import android.content.Context;
import android.support.annotation.NonNull;

import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;

public class TareaColumnCountProvider implements AutoColumnGridLayoutManager.ColumnCountProvider {


    @NonNull
    private final Context context;

    public TareaColumnCountProvider(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public int getColumnCount(int recyclerViewWidth) {
        return columnsForWidth(recyclerViewWidth);
    }

    public int columnsForWidth(int widthPx) {
        int widthDp = dpFromPx(widthPx);

            return 1;

    }

    public int dpFromPx(float px) {
        return (int)(px / context.getResources().getDisplayMetrics().density + 0.5f);
    }
}