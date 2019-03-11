package com.consultoraestrategia.ss_crmeducativo.portal.familia.adaper.holder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.adaper.FamiliaAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class FamiliaHolder extends RecyclerView.ViewHolder {


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


    public FamiliaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @SuppressLint("NewApi")
    public void bind(final PersonaUi personaUis, final FamiliaAdapter familiaAdapter, final boolean state) {
        this.personaUi = personaUis;

        Log.d("PersonauI", personaUi.toString());

        textNombre.setText(personaUi.getNombre());
        textAPellido.setText(personaUi.getApellidoCompleto());
        textTelefono.setText(personaUi.getTelefono());
        gmail.setText(personaUi.getCorreo());
        direccion.setText("Av. Los Girasoles 456");
        view.setRotation(180);

        Glide.with(itemView.getContext())
                .load(personaUi.getUrlImagen())
                .apply(Utils.getGlideRequestOptions())
                .into(imageView);

        switch (personaUi.getTipo()){
            case HIJO:
                textTipo.setText("HIJO");
                break;
            case MADRE:
                textTipo.setText("MADRE");
                break;
            case PADRE:
                textTipo.setText("PADRE");
                break;
        }

        textTelefono.setSelection(textTelefono.getText().length());
        gmail.setSelection(gmail.getText().length());
        direccion.setSelection(direccion.getText().length());


        textTelefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty())personaUi.setTelefono(s.toString());
                familiaAdapter.updateItem(personaUi);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        gmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty())personaUi.setCorreo(s.toString());
                familiaAdapter.updateItem(personaUi);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        direccion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty())personaUi.setDireccion(s.toString());
                familiaAdapter.updateItem(personaUi);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


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
            constraintLayout.setVisibility(View.GONE);
        }else {
            verMas = true;
            view.setRotation(180);
            constraintLayout.setVisibility(View.VISIBLE);
        }
    }



}
