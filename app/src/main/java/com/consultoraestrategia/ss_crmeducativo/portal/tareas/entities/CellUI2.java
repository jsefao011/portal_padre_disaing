package com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities;

public class CellUI2 {
    private int id;
    private String cantidad;

    public CellUI2(int id, String cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    public CellUI2() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
}
