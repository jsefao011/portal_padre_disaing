package com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities;

import java.util.List;

public class TareasUi {

    private int id;
    private String nombre;
    private String curso;
    private String Docente;
    private int estado;
    private FechaUi fechaUiInicio;
    private FechaUi fechaUiFin;
    private ValorTipoNotaUi valorTipoNotaUi;


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

    public ValorTipoNotaUi getValorTipoNotaUi() {
        return valorTipoNotaUi;
    }

    public void setValorTipoNotaUi(ValorTipoNotaUi valorTipoNotaUi) {
        this.valorTipoNotaUi = valorTipoNotaUi;
    }
}


