package com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities;

import java.util.List;

public class TipoTareaUi {
    private int id;
    private int cantidad;
    private String tipo;
    List<TareasUi>listaTareas;
    private String colortext;

    public TipoTareaUi() {
    }

    public TipoTareaUi(int id, int cantidad, String tipo, String colortext) {
        this.id = id;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.colortext = colortext;
    }

    public TipoTareaUi(int id, int cantidad, String tipo,  String colortext, List<TareasUi> listaTareas) {
        this.id = id;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.colortext = colortext;
        this.listaTareas = listaTareas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<TareasUi> getListaTareas() {
        return listaTareas;
    }

    public void setListaTareas(List<TareasUi> listaTareas) {
        this.listaTareas = listaTareas;
    }


    public String getColortext() {
        return colortext;
    }

    public void setColortext(String colortext) {
        this.colortext = colortext;
    }
}
