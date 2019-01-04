package com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.adapters.holder.CeldaFechaHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.adapters.holder.CeldaTareaFecha2Holder;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.adapters.holder.CeldaTareaRetrasoHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.adapters.holder.CeldaTipoNotaHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.adapters.holder.ColumnaTareaCursoHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.adapters.holder.ColumnaTareasHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.adapters.holder.FilaTareasHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CellUI2;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CursoTareasUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CellUI3;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TipoTareaUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CellUI4;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

public class TareasTableAdapter extends AbstractTableAdapter<TipoTareaUi, TareasUi,Object>{

   // private static final int COLUMNA_CANTIDAD_TAREA=1, COLUMNA_CURSO=2;
   // private static final int FILA_FECHA_TAREA=3, FILA_TIPO_NOTA=4;
    private static  final int CELL_TAREA = 0, CELL_NOTA=1, CELL_FECHA=2, CELL_RETRASO=3;
    String TAG= TareasTableAdapter.class.getSimpleName();

    public TareasTableAdapter(Context context ) {
        super(context);
    }

    @Override
    public int getColumnHeaderItemViewType(int position) {
        /*Object c= mColumnHeaderItems.get(position);
        if(c instanceof CursosUi)return COLUMNA_CURSO;
        else return  COLUMNA_CANTIDAD_TAREA;*/
       return 0;
    }

    @Override
    public int getRowHeaderItemViewType(int position){

       /* TareasUi o = mRowHeaderItems.get(position);
        if(o instanceof CellUI4)return FILA_TIPO_NOTA;
        else return FILA_FECHA_TAREA;*/
       return 0;
    }

    @Override
    public int getCellItemViewType(int position) {
        Object cell = mCellItems.get(0).get(position);
            if(cell  instanceof TareasUi)return  CELL_TAREA;
            else if(cell instanceof CellUI3) return CELL_FECHA;
            else if(cell instanceof CellUI2)return  CELL_RETRASO;
            else  return CELL_NOTA;

    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        View layout;
      /* layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_cell_fecha_tarea, parent, false);
        return new CeldaFechaHolder(layout);*/
        Log.d(TAG, "onCreateCellViewHolder " + viewType);
        switch (viewType) {
            case CELL_NOTA:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_cell_tipo_nota, parent, false);
                return new CeldaTipoNotaHolder(layout);
            case  CELL_FECHA:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_cell_tarea_fecha2, parent, false);
                return new CeldaTareaFecha2Holder(layout);
            case  CELL_RETRASO:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_cell_tarea_retraso, parent, false);
                return new CeldaTareaRetrasoHolder(layout);
            default:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_cell_fecha_tarea, parent, false);
                return new CeldaFechaHolder(layout);
        }
    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object cellItemModel, int columnPosition, int rowPosition) {

            if(cellItemModel instanceof TareasUi){

                if(holder instanceof CeldaFechaHolder ){
                    CeldaFechaHolder celdaFechaHolder = (CeldaFechaHolder)holder;
                    celdaFechaHolder.bind(((TareasUi)cellItemModel));
                }
            }
            else if(cellItemModel instanceof CellUI3){
                if(holder instanceof CeldaTareaFecha2Holder ){
                    CeldaTareaFecha2Holder celdaFechaHolder = (CeldaTareaFecha2Holder)holder;
                    celdaFechaHolder.bind(((CellUI3)cellItemModel));
                }
            }
            else if(cellItemModel instanceof CellUI2){
                if(holder instanceof CeldaTareaRetrasoHolder ){
                    CeldaTareaRetrasoHolder celdaFechaHolder = (CeldaTareaRetrasoHolder)holder;
                    celdaFechaHolder.bind(((CellUI2)cellItemModel));
                }
            }
            else if(cellItemModel instanceof CellUI4){
                if(holder instanceof CeldaTipoNotaHolder) {
                    CeldaTipoNotaHolder celdaTipoNotaHolder= (CeldaTipoNotaHolder)holder;
                    celdaTipoNotaHolder.bind(((CellUI4)cellItemModel),rowPosition);
                }

            }

    }

    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout;
        layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_tareas_columna, parent, false);
        return new ColumnaTareasHolder(layout);
        /*switch (viewType) {
            case COLUMNA_CANTIDAD_TAREA:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_tareas_columna, parent, false);
                return new ColumnaTareasHolder(layout);
            default:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_header_colum_tarea, parent, false);
                return new ColumnaTareaCursoHolder(layout);
        }*/

    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object columnHeaderItemModel, int columnPosition) {
        ((ColumnaTareasHolder)holder).bind((TipoTareaUi)columnHeaderItemModel);
      //  ColumnaTareaCursoHolder columnaTareaCursoHolder=(ColumnaTareaCursoHolder)holder;
       //ColumnaTareasHolder columnaTareasHolder= (ColumnaTareasHolder)holder;
       // if(columnHeaderItemModel instanceof  CursosUi)((ColumnaTareaCursoHolder)holder).bind((CursosUi)columnHeaderItemModel);
      //  else if(columnHeaderItemModel instanceof  NumeroTareasUI)((ColumnaTareasHolder)holder).bind((NumeroTareasUI)columnHeaderItemModel);

    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout;
        layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_fila_tarea, parent, false);
        return new FilaTareasHolder(layout);
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object rowHeaderItemModel, int rowPosition) {
   if(holder instanceof  FilaTareasHolder &&  rowHeaderItemModel instanceof TareasUi )((FilaTareasHolder)holder).bind((TareasUi)rowHeaderItemModel);
    }

    @Override
    public View onCreateCornerView() {
        return LayoutInflater.from(mContext).inflate(R.layout.table_header_colum_tarea, null);
    }


    public void setCornerView(CursoTareasUi cursosUi, int m_nRowHeaderWidth, int m_nColumnHeaderHeight) {
        View view = onCreateCornerView();
        ColumnaTareaCursoHolder viewHolder = new ColumnaTareaCursoHolder(view);
        getTableView().addView(view, new FrameLayout.LayoutParams(m_nRowHeaderWidth, m_nColumnHeaderHeight));
        viewHolder.bind(cursosUi);
    }

}
