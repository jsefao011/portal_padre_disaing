package com.consultoraestrategia.ss_crmeducativo.portal.eventos.entities;

public class EventosUi {

    public enum Tipo{PUBLIC, PRIVATE}

    private int id;
    private String personaPublicado;
    private String contenido;
    private int imagen;
    private Tipo tipo = Tipo.PUBLIC;
    private String fecha;


    public EventosUi(String personaPublicado, String contenido, int imagen, Tipo tipo, String fecha) {
        this.personaPublicado = personaPublicado;
        this.contenido = contenido;
        this.imagen = imagen;
        this.tipo = tipo;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersonaPublicado() {
        return personaPublicado;
    }

    public void setPersonaPublicado(String personaPublicado) {
        this.personaPublicado = personaPublicado;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "EventosUi{" +
                "id=" + id +
                ", personaPublicado='" + personaPublicado + '\'' +
                ", contenido='" + contenido + '\'' +
                ", imagen=" + imagen +
                ", tipo=" + tipo +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
