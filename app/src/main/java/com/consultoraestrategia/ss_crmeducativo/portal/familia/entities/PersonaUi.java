package com.consultoraestrategia.ss_crmeducativo.portal.familia.entities;

public class PersonaUi {

    public enum Tipo{PADRE, MADRE, HIJO, ABUELO, TIO}
    private int idPersona;
    private String nombre;
    private String apellidoCompleto;
    private String correo;
    private String telefono;
    private String direccion;
    private boolean apoderado;
    private Tipo tipo = Tipo.HIJO;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String urlImagen;
    private int num;

    public PersonaUi() {
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoCompleto() {
        return apellidoCompleto;
    }

    public void setApellidoCompleto(String apellidoCompleto) {
        this.apellidoCompleto = apellidoCompleto;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isApoderado() {
        return apoderado;
    }

    public void setApoderado(boolean apoderado) {
        this.apoderado = apoderado;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonaUi personaUi = (PersonaUi) o;

        return idPersona == personaUi.idPersona;
    }

    @Override
    public int hashCode() {
        return idPersona;
    }

    @Override
    public String toString() {
        return "PersonaUi{" +
                "idPersona=" + idPersona +
                ", nombre='" + nombre + '\'' +
                ", apellidoCompleto='" + apellidoCompleto + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", apoderado=" + apoderado +
                ", tipo=" + tipo +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", urlImagen='" + urlImagen + '\'' +
                '}';
    }
}

