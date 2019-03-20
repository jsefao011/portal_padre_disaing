package com.consultoraestrategia.ss_crmeducativo.portal.contactos.adapter.holder;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.listener.ContactoListener;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ContactosHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.nombre)
    TextView textNombre;
    @BindView(R.id.imagenPersona)
    CircleImageView imgContacto;
    @BindView(R.id.linearSettings)
    LinearLayout linearLayoutSettings;
    @BindView(R.id.linearApoderado)
    LinearLayout linearLayoutApoderado;
    @BindView(R.id.linearLlamar)
    LinearLayout linearLayoutLlamar;
    @BindView(R.id.linearMensaje)
    LinearLayout linearLayoutMensaje;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    private boolean verMas=true;
    private ContactoListener contactoListener;
    private PersonaUi personaUi;

    public ContactosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }


    public void bind(PersonaUi personaUi, ContactoListener contactoListener) {
        this.contactoListener = contactoListener;
        this.personaUi = personaUi;
        textNombre.setText(personaUi.getNombres());

        if (personaUi.getApoderadoUi()==null)linearLayoutApoderado.setVisibility(View.GONE);

        Glide.with(itemView.getContext())
                .load(personaUi.getFoto())
                .apply(Utils.getGlideRequestOptions())
                .into(imgContacto);
    }

    @OnClick({R.id.constraintLayoutGeneral, R.id.linearLlamar, R.id.linearApoderado, R.id.linearMensaje, R.id.linearSettings})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.constraintLayoutGeneral:
                verMas();
                break;
            case R.id.linearLlamar:
                contactoListener.callPerson(personaUi.getCelular());
                break;
            case R.id.linearApoderado:
                if (personaUi.getApoderadoUi()!=null)
                contactoListener.callPerson(personaUi.getApoderadoUi().getCelular());
                break;
            case R.id.linearMensaje:
                contactoListener.sendMessage(personaUi.getCelular());
                break;
            case R.id.linearSettings:
               contactoListener.settings(personaUi);
                break;
        }
    }

    public  void verMas(){
        if(verMas){
            verMas = false;
            constraintLayout.setVisibility(View.VISIBLE);
        }else {
            verMas = true;
            constraintLayout.setVisibility(View.GONE);
        }
    }
}
