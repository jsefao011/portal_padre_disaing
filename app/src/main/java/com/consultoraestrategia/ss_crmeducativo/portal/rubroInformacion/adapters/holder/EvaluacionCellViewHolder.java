package com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.adapters.holder;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.NotaCell;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 8/03/2018.
 */

public class EvaluacionCellViewHolder extends CellViewHolder<NotaCell> {
    @BindView(R.id.text_fondo)
    TextView textFondo;
    @BindView(R.id.txt_evalaucion)
    TextView txtEvalaucion;
    @BindView(R.id.root)
    ConstraintLayout root;
    public EvaluacionCellViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(NotaCell cell) {
        if(cell.isSelected()){
            colorPaint(cell);
        }else {
            colorPaintReset();
        }
    }

    public void colorPaint(NotaCell valorTipoNotaUi){
        txtEvalaucion.setVisibility(View.VISIBLE);
        switch (valorTipoNotaUi.getColor()){
            case AZUL:
                textFondo.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.md_blue_300));
                txtEvalaucion.setTextColor(Color.WHITE);
                break;
            case ROJO:
                textFondo.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.md_pink_50));
                txtEvalaucion.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.md_red_300));
                break;
            case ANARANJADO:
                textFondo.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.md_orange_300));
                txtEvalaucion.setTextColor(Color.WHITE);
                break;
            case DEFAULT:
                textFondo.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.md_grey_300));
                break;
            case VERDE:
                textFondo.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.md_green_300));
                txtEvalaucion.setTextColor(Color.WHITE);
                break;
        }
    }

    private void colorPaintReset(){
        textFondo.setBackgroundColor(Color.WHITE);
        txtEvalaucion.setVisibility(View.GONE);
    }

}
