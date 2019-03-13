package com.consultoraestrategia.ss_crmeducativo.portal.programahorario.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.HoraUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilaHoraHolder extends AbstractViewHolder {
    @BindView(R.id.fondo)
    View fondo;
    @BindView(R.id.txt_hora)
    TextView txtHora;
    public FilaHoraHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(HoraUi p_jValue, int p_nYPosition) {
        alternarFondoHorario(p_nYPosition);
        String hora = p_jValue.getDesde().substring(0, 5) + "-" + p_jValue.getHasta().substring(0, 5);
        txtHora.setText(hora);
    }


    private void alternarFondoHorario(int p_nYPosition) {
        if((p_nYPosition % 2)==0){
            fondo.setAlpha(0.7f);
        }else {
            fondo.setAlpha(1.0f);
        }
    }
}
