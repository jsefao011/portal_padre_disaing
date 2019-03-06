package com.consultoraestrategia.ss_crmeducativo.portal.demo.infoRubro.adapter.holder;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.portal.demo.infoRubro.entities.Row;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 8/03/2018.
 */

public class RubroRowViewHolder extends AbstractViewHolder
{
    @BindView(R.id.txt_rubro_evaluacion)
    TextView txtRubroEvaluacion;
    @BindView(R.id.root)
    ConstraintLayout root;

    public RubroRowViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(Row rowHeader) {
        txtRubroEvaluacion.setText(rowHeader.getContenido());
    }
}
