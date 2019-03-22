package com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.adapters.holder;

import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.content.res.AppCompatResources;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.CompetenciaCell;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 8/03/2018.
 */

public class NotaCellViewHolder extends CellViewHolder<CompetenciaCell> {
    @BindView(R.id.txt_nota_evaluacion)
    TextView txtNotaEvaluacion;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.root)
    ConstraintLayout root;
    public NotaCellViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void bind(CompetenciaCell cell) {
        txtNotaEvaluacion.setText(cell.getContenido());
        Drawable drawable = null;
        switch (cell.getTipo()){
            case BASE:
                drawable = AppCompatResources.getDrawable(imageView.getContext(), R.drawable.ic_base);
                break;
            case ENFOQUE:
                drawable = AppCompatResources.getDrawable(imageView.getContext(),  R.drawable.ic_enfoque_1);
                break;
            case TRANSVERSAL:
                drawable = AppCompatResources.getDrawable(imageView.getContext(), R.drawable.ic_transversal);
                break;
        }
        if(drawable != null) imageView.setImageDrawable(drawable);
    }
}
