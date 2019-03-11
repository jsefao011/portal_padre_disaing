package com.consultoraestrategia.ss_crmeducativo.portal.main.entities;

/**
 * Created by irvinmarin on 16/10/2017.
 */

public class ItemMenuUI {
    private TipoMenu tipoMenu;
    private String nombre;
    private boolean seleccionado;
    private int icono;

    public ItemMenuUI(TipoMenu tipoMenu, String nombre, boolean seleccionado, int icono) {
        this.tipoMenu = tipoMenu;
        this.nombre = nombre;
        this.seleccionado = seleccionado;
        this.icono = icono;
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

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }
}
