package com.consultoraestrategia.ss_crmeducativo.portal.main.entities;

import java.util.ArrayList;
import java.util.List;

public class PadreMentorUi {
    private int usuarioId;
    private int personaId;
    private String nombre;
    private String nombres;
    private String apellidos;
    private char etiqueta;
    private String url_imagen;
    private List<HijoUi> hijoUiList = new ArrayList<>();

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public char getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(char etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public List<HijoUi> getHijoUiList() {
        return hijoUiList;
    }

    public void setHijoUiList(List<HijoUi> hijoUiList) {
        this.hijoUiList = hijoUiList;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
