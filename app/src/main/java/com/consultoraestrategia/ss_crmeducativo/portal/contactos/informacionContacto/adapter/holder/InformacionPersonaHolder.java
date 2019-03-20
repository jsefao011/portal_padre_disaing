package com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.adapter.holder;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.informacionContacto.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import org.zakariya.stickyheaders.SectioningAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class InformacionPersonaHolder extends SectioningAdapter.HeaderViewHolder {


    @BindView(R.id.nombreFamilia)
    TextView textNombre;
    @BindView(R.id.primerApellido)
    TextView textAPellido;
    @BindView(R.id.celular)
    EditText textTelefono;
    @BindView(R.id.direcciones)
    EditText direccion;
    @BindView(R.id.tipo)
    TextView textTipo;
    @BindView(R.id.gmail)
    EditText gmail;
    @BindView(R.id.imagenFoto)
    CircleImageView imageView;
    @BindView(R.id.view)
    ImageView view;
    @BindView(R.id.recept)
    ConstraintLayout constraintLayout;
    private boolean verMas = true;
    private PersonaUi personaUi;


    public InformacionPersonaHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }


    public void bind(PersonaUi personaUi) {
        this.personaUi = personaUi;

        textNombre.setText(personaUi.getNombres());
        textAPellido.setText(personaUi.getApellidoMaterno() + " " + personaUi.getApellidoPaterno());
        textTelefono.setText(personaUi.getCelular());
        gmail.setText(personaUi.getCorreo());
        direccion.setText("Av. Los Girasoles 456");
        view.setRotation(180);

        Glide.with(itemView.getContext())
                .load(personaUi.getFoto())
                .apply(Utils.getGlideRequestOptions())
                .into(imageView);

        switch (personaUi.getTipo()){
            case HIJO:
                textTipo.setText("HERMANO");
                break;
            case MADRE:
                textTipo.setText("MADRE");
                break;
            case PADRE:
                textTipo.setText("PADRE");
                break;
            case ABUELA:
                textTipo.setText("ABUELO(A)");
                break;
            case TIO:
                textTipo.setText("TIO(A)");
            case DOCENTE:
                textTipo.setText("DOCENTE");
                view.setVisibility(View.GONE);
                break;
            case TUTOR:
                textTipo.setText("DOCENTE - TUTOR");
                break;
        }

    }


    @OnClick({R.id.cl_onClick})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cl_onClick:
                onVerMas();
                break;
        }
    }

    private void onVerMas() {
        if(verMas){
            verMas = false;
            view.setRotation(0);
            constraintLayout.setVisibility(View.GONE);
        }else {
            verMas = true;
            view.setRotation(180);
            constraintLayout.setVisibility(View.VISIBLE);
        }
    }
}
