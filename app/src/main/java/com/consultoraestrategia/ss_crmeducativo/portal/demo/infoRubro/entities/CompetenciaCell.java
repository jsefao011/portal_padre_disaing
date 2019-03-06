package com.consultoraestrategia.ss_crmeducativo.portal.demo.infoRubro.entities;

/**
 * Created by Jse on 15/09/2018.
 */

public class CompetenciaCell extends Cell{
    public enum Tipo{TRANSVERSAL, ENFOQUE,BASE}
    private Tipo tipo = Tipo.BASE;

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
