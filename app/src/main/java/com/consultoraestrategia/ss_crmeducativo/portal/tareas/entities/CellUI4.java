package com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities;

import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.TareasUi;

public class CellUI4 {
    private int id;
    private String tipo;

    public CellUI4() {
    }

    public CellUI4(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
