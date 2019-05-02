package com.consultoraestrategia.ss_crmeducativo.portal.eventos.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.portal.eventos.entities.EventosUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventosHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.nombrePersonaEvento)
    TextView textNombre;
    @BindView(R.id.estadoEvento)
    TextView textEstado;
    @BindView(R.id.textView2)
    TextView textContenido;
    @BindView(R.id.imagenEvento)
    ImageView imagenEvento;


    public EventosHolder(View view) {
        super(view);
        ButterKnife.bind(this,view);

    }

    public void bind(final EventosUi eventosUi) {
        textNombre.setText(eventosUi.getPersonaPublicado());
        textContenido.setText(eventosUi.getContenido());
        if (eventosUi.getImagen()!=0)imagenEvento.setImageResource(eventosUi.getImagen());
        else imagenEvento.setVisibility(View.GONE);

        switch (eventosUi.getTipo()){
            case PRIVATE:
                textEstado.setText("Privado");
                break;
            case PUBLIC:
                textEstado.setText("Publico");
                break;
        }

    }
}
