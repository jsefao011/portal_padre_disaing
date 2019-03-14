package com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities;

public class TipoNotaUi {

    public enum Tipo{SELECTOR_ICONOS, SELECTOR_VALORES, SELECTOR_NUMERICO, TECLADO_NUMERICO}
    private String tiponotaId;
    private ValorTipoNotaUi valorTipoNotaUi;
    private double nota;
    private int tipoId;
    private EscalaUi escalaUi;
    private Tipo tipo;

    public String getTiponotaId() {
        return tiponotaId;
    }

    public void setTiponotaId(String tiponotaId) {
        this.tiponotaId = tiponotaId;
    }

    public ValorTipoNotaUi getValorTipoNotaUi() {
        return valorTipoNotaUi;
    }

    public void setValorTipoNotaUi(ValorTipoNotaUi valorTipoNotaUi) {
        this.valorTipoNotaUi = valorTipoNotaUi;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public EscalaUi getEscalaUi() {
        return escalaUi;
    }

    public void setEscalaUi(EscalaUi escalaUi) {
        this.escalaUi = escalaUi;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
