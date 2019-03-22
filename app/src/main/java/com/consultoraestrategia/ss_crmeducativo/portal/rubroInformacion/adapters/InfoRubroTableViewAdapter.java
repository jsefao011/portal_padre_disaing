package com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.adapters.holder.CornerHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.adapters.holder.DefaultCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.adapters.holder.DefaultColumnViewHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.adapters.holder.EvaluacionCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.adapters.holder.NotaCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.adapters.holder.NotaRowViewHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.adapters.holder.RubroRowViewHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.adapters.holder.SelectorIconosRowViewHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.adapters.holder.SelectorValoresColumnViewHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.Cell;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.Column;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.CompetenciaCell;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.ImagenColumn;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.NotaCell;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.NotaColumn;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.Row;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.TextoColum;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

/**
 * Created by Jse on 15/09/2018.
 */

public class InfoRubroTableViewAdapter extends AbstractTableAdapter<Column, Row, Cell> {

    private static final int CELL_COMPETENCIA = 1, CELL_NOTA = 2;
    private static final int COLUMN_TEXTO = 11, COLUMN_IMAGEN = 12, COLUMN_NOTA = 13;

    public InfoRubroTableViewAdapter(Context p_jContext) {
        super(p_jContext);
    }

    @Override
    public int getColumnHeaderItemViewType(int position) {

        if (mColumnHeaderItems.size() != 0) {
            Column column = mColumnHeaderItems.get(position);
            if(column instanceof TextoColum) {
                return COLUMN_TEXTO;
            } else if(column instanceof ImagenColumn){
                return COLUMN_IMAGEN;
            }else if(column instanceof NotaColumn){
                return COLUMN_NOTA;
            }
        }

            return 0;
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getCellItemViewType(int position) {
        int cantidad = mCellItems.size();
        if (cantidad != 0) {
            Cell cell = mCellItems.get(0).get(position);
            if(cell instanceof CompetenciaCell){
                return CELL_COMPETENCIA;
            }else if(cell instanceof NotaCell){
                return CELL_NOTA;
            }
        }
        return 0;
    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        View layout;
        switch (viewType) {
            default:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_empty, parent, false);
                return new DefaultCellViewHolder(layout);
            case CELL_COMPETENCIA:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cell_nota_competencia_layout, parent, false);
                return new NotaCellViewHolder(layout);
            case CELL_NOTA:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_cell_evaluacion_layout, parent, false);
                return new EvaluacionCellViewHolder(layout);
        }
    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nXPosition, int p_nYPosition) {
        if (holder instanceof NotaCellViewHolder && p_jValue instanceof CompetenciaCell){
            CompetenciaCell competenciaCell = (CompetenciaCell)p_jValue;
            NotaCellViewHolder notaCellViewHolder = (NotaCellViewHolder)holder;
            notaCellViewHolder.bind(competenciaCell);
        }else if(holder instanceof EvaluacionCellViewHolder && p_jValue instanceof NotaCell){
            NotaCell notaCell = (NotaCell)p_jValue;
            EvaluacionCellViewHolder evaluacionCellViewHolder = (EvaluacionCellViewHolder)holder;
            evaluacionCellViewHolder.bind(notaCell);
        }
    }

    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout;
        switch (viewType){
            default:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_empty, parent, false);
                return new DefaultColumnViewHolder(layout);
            case COLUMN_TEXTO:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_view_selector_valores_layout, parent, false);
                return new SelectorValoresColumnViewHolder(layout);
            case COLUMN_IMAGEN:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_view_selector_iconos_layout, parent, false);
                return new SelectorIconosRowViewHolder(layout);
            case COLUMN_NOTA:
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_view_nota_evaluacion_layout, parent, false);
                return new NotaRowViewHolder(layout);
        }

    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nXPosition) {
        if (holder instanceof SelectorValoresColumnViewHolder && p_jValue instanceof TextoColum) {
            TextoColum textoColum = (TextoColum)p_jValue;
            SelectorValoresColumnViewHolder selectorValoresColumnViewHolder = (SelectorValoresColumnViewHolder) holder;
            selectorValoresColumnViewHolder.bind(textoColum);
        }else if(holder instanceof SelectorIconosRowViewHolder && p_jValue instanceof ImagenColumn){
            ImagenColumn imagenColumn = (ImagenColumn)p_jValue;
            SelectorIconosRowViewHolder selectorIconosRowViewHolder = (SelectorIconosRowViewHolder)holder;
            selectorIconosRowViewHolder.bind(imagenColumn);
        }else if(holder instanceof NotaRowViewHolder && p_jValue instanceof NotaColumn){
            NotaColumn notaColumn = (NotaColumn)p_jValue;
            NotaRowViewHolder notaRowViewHolder = (NotaRowViewHolder)holder;
            notaRowViewHolder.bind(notaColumn);
        }
    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_row_rubro_evaluacion_layout, parent, false);
        return new RubroRowViewHolder(layout);
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nYPosition) {
        Row column =  (Row) p_jValue;
        RubroRowViewHolder rubroRowViewHolder = (RubroRowViewHolder)holder;
        rubroRowViewHolder.bind(column);
    }

    @Override
    public View onCreateCornerView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.table_view_corner_layout_rubrica_individual, null);
        return view;
    }

    public void setCornerView(String titulo, int m_nRowHeaderWidth, int m_nColumnHeaderHeight) {
        View view = onCreateCornerView();
        CornerHolder viewHolder = new CornerHolder(view);
        getTableView().addView(view, new FrameLayout.LayoutParams(m_nRowHeaderWidth, m_nColumnHeaderHeight));
        viewHolder.bind(titulo);
    }
}
