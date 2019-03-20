package com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo_portal.R;


import butterknife.BindView;
import butterknife.ButterKnife;

public class InformacionLetraHolder extends RecyclerView.ViewHolder {
    private static String TAG = InformacionLetraHolder.class.getSimpleName();

    @BindView(R.id.letra)
    TextView txt_letra;

    public InformacionLetraHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(String letra) {
        txt_letra.setText(letra);
    }

}