package com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.adapters.holder;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.NotaColumn;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotaRowViewHolder extends ColumnHeaderViewHolder<NotaColumn>
{
    @BindView(R.id.txt_nota_evaluacion)
    TextView txtNotaEvaluacion;
    @BindView(R.id.root)
    ConstraintLayout root;
    public NotaRowViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void bind(NotaColumn rowHeader) {
        txtNotaEvaluacion.setText(rowHeader.getContenido());
    }
}

