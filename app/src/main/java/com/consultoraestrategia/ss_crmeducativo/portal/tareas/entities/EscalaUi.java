package com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities;

public class EscalaUi {

    private int escalaEvaluacionId;

    private String nombre;

    private int valorMinimo;

    private int valorMaximo;

    private int estado;

    private int entidadId;

    public int getEscalaEvaluacionId() {
        return escalaEvaluacionId;
    }

    public void setEscalaEvaluacionId(int escalaEvaluacionId) {
        this.escalaEvaluacionId = escalaEvaluacionId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(int valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public int getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(int valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(int entidadId) {
        this.entidadId = entidadId;
    }
}
