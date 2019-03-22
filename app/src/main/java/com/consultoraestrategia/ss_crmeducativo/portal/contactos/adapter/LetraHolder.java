package com.consultoraestrategia.ss_crmeducativo.portal.contactos.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

class LetraHolder extends RecyclerView.ViewHolder{
    private static String TAG = LetraHolder.class.getSimpleName();

    @BindView(R.id.letra)
    TextView txt_letra;

    public LetraHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(String letra){
        txt_letra.setText(letra);
    }

}