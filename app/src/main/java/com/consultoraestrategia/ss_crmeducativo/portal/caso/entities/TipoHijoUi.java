package com.consultoraestrategia.ss_crmeducativo.portal.caso.entities;

public class TipoHijoUi {

    public enum TipoPadre{MERITO, DEMERITO}
    private int tipo;
    private String nombre;
    private TipoPadre tipoPadre;

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoPadre getTipoPadre() {
        return tipoPadre;
    }

    public void setTipoPadre(TipoPadre tipoPadre) {
        this.tipoPadre = tipoPadre;
    }
}
