package com.consultoraestrategia.ss_crmeducativo.portal.programahorario.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.BanerUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilaBanerHolder extends AbstractViewHolder {
    @BindView(R.id.fondo)
    View fondo;
    @BindView(R.id.txt_hora)
    TextView txtHora;
    public FilaBanerHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    public void bind(BanerUi p_jValue) {
        String hora = p_jValue.getDesde().substring(0, 5) + "-" + p_jValue.getHasta().substring(0, 5);
        txtHora.setText(hora);
    }
}
