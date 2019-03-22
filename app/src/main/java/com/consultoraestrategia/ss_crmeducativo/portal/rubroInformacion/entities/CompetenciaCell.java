package com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities;

/**
 * Created by Jse on 15/09/2018.
 */

public class CompetenciaCell extends Cell {
    public enum Tipo{TRANSVERSAL, ENFOQUE,BASE}
    public enum TipoIndicador {SABER,SER, DEFAULT, HACER
    }
    private Tipo tipo = Tipo.BASE;
    private TipoIndicador tipoIndicador;

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public TipoIndicador getTipoIndicador() {
        return tipoIndicador;
    }

    public void setTipoIndicador(TipoIndicador tipoIndicador) {
        this.tipoIndicador = tipoIndicador;
    }
}
