package com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities;

public class IndicadorUI {
    public enum TipoCompencia {
        COMPETENCIA_BASE, COMPETENCIA_TRANS, COMPETENCIA_ENFQ
    }
    public enum TipoIndicador {SABER,SER, DEFAULT, HACER
    }

    private TipoCompencia tipoCompencia;
    private TipoIndicador tipoIndicador;
    private String indicador;
    private int indicadorId;

    public TipoCompencia getTipoCompencia() {
        return tipoCompencia;
    }

    public void setTipoCompencia(TipoCompencia tipoCompencia) {
        this.tipoCompencia = tipoCompencia;
    }

    public TipoIndicador getTipoIndicador() {
        return tipoIndicador;
    }

    public void setTipoIndicador(TipoIndicador tipoIndicador) {
        this.tipoIndicador = tipoIndicador;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public int getIndicadorId() {
        return indicadorId;
    }

    public void setIndicadorId(int indicadorId) {
        this.indicadorId = indicadorId;
    }
}
