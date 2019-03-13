package com.consultoraestrategia.ss_crmeducativo.portal.contactos.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ContactosHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.nombre)
    TextView textNombre;
    @BindView(R.id.direccion)
    TextView textDireccion;
    @BindView(R.id.telefono)
    TextView textTelefono;
    @BindView(R.id.ciudad)
    TextView ciudad;
    @BindView(R.id.imagenPersona)
    CircleImageView imgContacto;

    public ContactosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }


    public void bind(PersonaUi personaUi) {

        textNombre.setText(personaUi.getNombres());
        textTelefono.setText(personaUi.getCelular());
        ciudad.setText(personaUi.getNumDoc());
        textDireccion.setText("Av. Los Girasoles 456");

        Glide.with(itemView.getContext())
                .load(personaUi.getFoto())
                .apply(Utils.getGlideRequestOptions())
                .into(imgContacto);
        Log.d("sizeListObjecta", "true" + personaUi.toString());
    }
}
