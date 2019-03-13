package com.consultoraestrategia.ss_crmeducativo.portal.programahorario.adapter.holder;

import android.graphics.Color;
import android.view.View;

import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.BanerUi;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.CursoHorarioDiaUi;
import com.consultoraestrategia.ss_crmeducativo.util.CircularTextView;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CeldaHorarioHolder extends AbstractViewHolder {
    @BindView(R.id.fondo)
    View fondo;
    @BindView(R.id.fondo_banner)
    View fondoBanner;
    @BindView(R.id.position)
    CircularTextView position;
    public CeldaHorarioHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindHorario(CursoHorarioDiaUi celdaHorarioUi, int p_nYPosition) {
        showFondoHorario();
        alternarFondoHorario(p_nYPosition);

        if(celdaHorarioUi.getCursoUi()!=null){
            position.setVisibility(View.VISIBLE);
            position.setStrokeWidth(2);
            position.setSolidColor(celdaHorarioUi.getCursoUi().getColor1());
            position.setStrokeColor(celdaHorarioUi.getCursoUi().getColor1());
            position.setText(String.valueOf(celdaHorarioUi.getCursoUi().getPosicion()));
            position.setTextColor(Color.parseColor("#ffffff"));
        }else {
            position.setVisibility(View.GONE);
        }

    }

    private void alternarFondoHorario(int p_nYPosition) {
        if((p_nYPosition % 2)==0){
            fondo.setAlpha(0.7f);
        }else {
            fondo.setAlpha(1.0f);
        }
    }

    public void bindBanner(BanerUi banerUi) {
        showFondoBanner();
    }

    private void showFondoHorario(){
        fondo.setVisibility(View.VISIBLE);
        fondoBanner.setVisibility(View.GONE);
    }

    private void showFondoBanner(){
        fondo.setVisibility(View.GONE);
        fondoBanner.setVisibility(View.VISIBLE);
    }
}
