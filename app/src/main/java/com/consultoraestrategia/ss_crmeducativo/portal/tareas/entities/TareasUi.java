package com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities;

import java.util.List;

public class TareasUi {

    public enum Tipo{NORMAL, RUBRO}
    public enum TipoLista{GENERAL, CURSO}
    private int id;
    private String nombre;
    private String curso;
    private String Docente;
    private int estado;
    private FechaUi fechaUiInicio;
    private FechaUi fechaUiFin;
    private String descripcion;
    private TipoNotaUi tipoNotaUi;
    private Tipo tipo;
    private int count;
    private TipoLista tipoLista;



    public TareasUi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getDocente() {
        return Docente;
    }

    public void setDocente(String docente) {
        Docente = docente;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public FechaUi getFechaUiInicio() {
        return fechaUiInicio;
    }

    public void setFechaUiInicio(FechaUi fechaUiInicio) {
        this.fechaUiInicio = fechaUiInicio;
    }

    public FechaUi getFechaUiFin() {
        return fechaUiFin;
    }

    public void setFechaUiFin(FechaUi fechaUiFin) {
        this.fechaUiFin = fechaUiFin;
    }

    public TipoNotaUi getTipoNotaUi() {
        return tipoNotaUi;
    }

    public void setTipoNotaUi(TipoNotaUi tipoNotaUi) {
        this.tipoNotaUi = tipoNotaUi;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public TipoLista getTipoLista() {
        return tipoLista;
    }

    public void setTipoLista(TipoLista tipoLista) {
        this.tipoLista = tipoLista;
    }
}


