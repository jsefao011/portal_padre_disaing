package com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities;

public class TareaUiCount {

   public enum Tipo{ASIGANADAS, POR_ENTREGAR, CALIFICADAS}
    private int cantidad;
    private Tipo tipoTarea;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Tipo getTipoTarea() {
        return tipoTarea;
    }

    public void setTipoTarea(Tipo tipoTarea) {
        this.tipoTarea = tipoTarea;
    }
}
