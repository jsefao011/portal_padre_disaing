package com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CursoTareasUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.evrencoskun.tableview.TableView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TareasCursosHolder  extends RecyclerView.ViewHolder  {

    @BindView(R.id.table)
    TableView table;
    private TareasTableAdapter adaptertable;
    private  CursoTareasUi cursoTareasUi;

    public TareasCursosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    public void bind(CursoTareasUi cursoTareasUi, Context context ){
        this.cursoTareasUi=cursoTareasUi;
        initView(context);
        int row_header_width = (int) context.getResources().getDimension(R.dimen.table_corner_tareas_width);
        int row_header_height = (int) context.getResources().getDimension(R.dimen.table_corner_tareas_height);
        adaptertable.setCornerView(cursoTareasUi,row_header_width,row_header_height);

    }

    private void initView(Context context) {
        adaptertable = new TareasTableAdapter(context);
        table.setAdapter(adaptertable);
        table.setIgnoreSelectionColors(false);
        table.setHasFixedWidth(true);
        table.setIgnoreSelectionColors(true);
        table.setShadowColor(Color.parseColor(cursoTareasUi.getColorfondo()));
        if(cursoTareasUi!=null)adaptertable.setAllItems(cursoTareasUi.getListaTipoTColums(),cursoTareasUi.getListaTRows(),cursoTareasUi.getCeldaUiList());
    }


}