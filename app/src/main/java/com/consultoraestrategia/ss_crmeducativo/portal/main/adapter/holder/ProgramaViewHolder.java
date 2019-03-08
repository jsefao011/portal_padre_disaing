package com.consultoraestrategia.ss_crmeducativo.portal.main.adapter.holder;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.ItemMenuUI;
import com.consultoraestrategia.ss_crmeducativo.portal.main.listener.MenuListener;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by irvinmarin on 09/10/2017.
 */

public class ProgramaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.txtAccesoName)
    TextView txtAccesoName;
    @BindView(R.id.imgIcon)
    ImageView imgIcon;
    private MenuListener listener;
    private ItemMenuUI itemMenuUI;

    public ProgramaViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void bind(final ItemMenuUI itemMenuUI, final MenuListener periodoListener) {
        this.listener = periodoListener;
        this.itemMenuUI = itemMenuUI;
        txtAccesoName.setText(itemMenuUI.getNombre());
        itemView.setOnClickListener(this);
        if(itemMenuUI.isSeleccionado()){
            itemView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.md_blue_50));
        }else {
            itemView.setBackgroundColor(Color.WHITE);
        }
        imgIcon.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), itemMenuUI.getIcono()));
    }

    @Override
    public void onClick(View view) {
        listener.onMenuSelected(itemMenuUI);
    }

}
