package com.consultoraestrategia.ss_crmeducativo.portal.wrapper;


import android.os.Bundle;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainParametrosGlobales implements Serializable {

    private final static String ETIQUETA = MainParametrosGlobales.class.getSimpleName();

    private int padre_mentor_usuarioId;
    private int padre_mentor_personaId;
    private String padre_mentor_nombres;
    private String padre_mentor_apellidos;
    private String padre_mentor_imagen;
    private List<Integer> padre_mentor_list_hijos_persona_Id = new ArrayList<>(); //puede venir vacio sino tine hijos

    private int hijo_selected_usuarioId;//puede venir vacio sino tine hijos
    private int hijo_selected_personaId;//puede venir vacio sino tine hijos
    private String hijo_selected_nombres;//puede venir vacio sino tine hijos
    private String hijo_selected_apellidos;///puede venir vacio sino tine hijos
    private String hijo_selected_imagen;//puede venir vacio sino tine hijos
    private List<Integer> hijo_list_programa_educativo_Id = new ArrayList<>();//puede venir vacio sino tine hijos

    private int hijo_programa_educativo_Id;//puede venir vacio sino tine un hijo seleccionado o el hijo no tine programa educativo

    public MainParametrosGlobales() {
    }

    public Bundle getBundle(){
        Bundle bundle = new Bundle();
        bundle.putSerializable(ETIQUETA, this);
        return bundle;
    }

    public static MainParametrosGlobales clone(Bundle bundle) {
        if(bundle==null)return null;
        MainParametrosGlobales serializable = (MainParametrosGlobales) bundle.getSerializable(ETIQUETA);
        Log.d(MainParametrosGlobales.class.getSimpleName(), bundle.toString());
        return serializable;
    }
    public int getPadre_mentor_usuarioId() {
        return padre_mentor_usuarioId;
    }

    public void setPadre_mentor_usuarioId(int padre_mentor_usuarioId) {
        this.padre_mentor_usuarioId = padre_mentor_usuarioId;
    }

    public int getPadre_mentor_personaId() {
        return padre_mentor_personaId;
    }

    public void setPadre_mentor_personaId(int padre_mentor_personaId) {
        this.padre_mentor_personaId = padre_mentor_personaId;
    }

    public String getPadre_mentor_nombres() {
        return padre_mentor_nombres;
    }

    public void setPadre_mentor_nombres(String padre_mentor_nombres) {
        this.padre_mentor_nombres = padre_mentor_nombres;
    }

    public String getPadre_mentor_apellidos() {
        return padre_mentor_apellidos;
    }

    public void setPadre_mentor_apellidos(String padre_mentor_apellidos) {
        this.padre_mentor_apellidos = padre_mentor_apellidos;
    }

    public String getPadre_mentor_imagen() {
        return padre_mentor_imagen;
    }

    public void setPadre_mentor_imagen(String padre_mentor_imagen) {
        this.padre_mentor_imagen = padre_mentor_imagen;
    }

    public List<Integer> getPadre_mentor_list_hijos_persona_Id() {
        return padre_mentor_list_hijos_persona_Id;
    }

    public void setPadre_mentor_list_hijos_persona_Id(List<Integer> padre_mentor_list_hijos_persona_Id) {
        this.padre_mentor_list_hijos_persona_Id = padre_mentor_list_hijos_persona_Id;
    }

    public int getHijo_selected_usuarioId() {
        return hijo_selected_usuarioId;
    }

    public void setHijo_selected_usuarioId(int hijo_selected_usuarioId) {
        this.hijo_selected_usuarioId = hijo_selected_usuarioId;
    }

    public int getHijo_selected_personaId() {
        return hijo_selected_personaId;
    }

    public void setHijo_selected_personaId(int hijo_selected_personaId) {
        this.hijo_selected_personaId = hijo_selected_personaId;
    }

    public String getHijo_selected_nombres() {
        return hijo_selected_nombres;
    }

    public void setHijo_selected_nombres(String hijo_selected_nombres) {
        this.hijo_selected_nombres = hijo_selected_nombres;
    }

    public String getHijo_selected_apellidos() {
        return hijo_selected_apellidos;
    }

    public void setHijo_selected_apellidos(String hijo_selected_apellidos) {
        this.hijo_selected_apellidos = hijo_selected_apellidos;
    }

    public String getHijo_selected_imagen() {
        return hijo_selected_imagen;
    }

    public void setHijo_selected_imagen(String hijo_selected_imagen) {
        this.hijo_selected_imagen = hijo_selected_imagen;
    }

    public int getHijo_programa_educativo_Id() {
        return hijo_programa_educativo_Id;
    }

    public void setHijo_programa_educativo_Id(int hijo_programa_educativo_Id) {
        this.hijo_programa_educativo_Id = hijo_programa_educativo_Id;
    }

    public List<Integer> getHijo_list_programa_educativo_Id() {
        return hijo_list_programa_educativo_Id;
    }

    public void setHijo_list_programa_educativo_Id(List<Integer> hijo_list_programa_educativo_Id) {
        this.hijo_list_programa_educativo_Id = hijo_list_programa_educativo_Id;
    }
}
