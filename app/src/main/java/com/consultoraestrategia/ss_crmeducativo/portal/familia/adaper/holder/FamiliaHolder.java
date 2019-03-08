package com.consultoraestrategia.ss_crmeducativo.portal.familia.adaper.holder;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class FamiliaHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.nombre)
    TextView textNombre;
    @BindView(R.id.direccion)
    TextView textDireccion;
    @BindView(R.id.telefono)
    TextView textTelefono;
    @BindView(R.id.mail)
    TextView mail;
    @BindView(R.id.tipo)
    TextView textTipo;
    @BindView(R.id.view)
    ImageView view;
    @BindView(R.id.imagenPersona)
    CircleImageView imgContacto;
    private boolean verMas = true;


    public FamiliaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(PersonaUi personaUi) {

        Log.d("PersonauI", personaUi.toString());

        textNombre.setText(personaUi.getNombre());
        textTelefono.setText(personaUi.getTelefono());
        mail.setText(personaUi.getCorreo());
        textDireccion.setText("Av. Los Girasoles 456");

        Glide.with(itemView.getContext())
                .load(personaUi.getUrlImagen())
                .apply(Utils.getGlideRequestOptions())
                .into(imgContacto);

        switch (personaUi.getTipo()){
            case HIJO:
                textTipo.setText("H - ");
                break;
            case MADRE:
                textTipo.setText("M - ");
                break;
            case PADRE:
                textTipo.setText("P - ");
                break;
        }
    }


    @OnClick({R.id.view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.view:
                onVerMas();
                break;
        }
    }

    private void onVerMas() {
        if(verMas){
            verMas = false;
            view.setRotation(0);
        }else {
            verMas = true;
            view.setRotation(180);
        }
    }



}
