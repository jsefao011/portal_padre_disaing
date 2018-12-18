package com.consultoraestrategia.ss_crmeducativo.portal.main.entities;

/**
 * Created by irvinmarin on 16/10/2017.
 */

public class ItemMenuUI {
    private TipoMenu tipoMenu;
    private String nombre;
    private boolean seleccionado;

    public ItemMenuUI(TipoMenu tipoMenu, String nombre, boolean seleccionado) {
        this.tipoMenu = tipoMenu;
        this.nombre = nombre;
        this.seleccionado = seleccionado;
    }

    public TipoMenu getTipoMenu() {
        return tipoMenu;
    }

    public void setTipoMenu(TipoMenu tipoMenu) {
        this.tipoMenu = tipoMenu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
}
