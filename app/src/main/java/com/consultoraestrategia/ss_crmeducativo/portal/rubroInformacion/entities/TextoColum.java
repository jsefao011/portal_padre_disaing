package com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities;



/**
 * Created by Jse on 16/09/2018.
 */

public class TextoColum extends Column {
    private ColorNota colorNota = ColorNota.BLANCO;
    private String valorNumerico;

    public ColorNota getColorNota() {
        return colorNota;
    }

    public void setColorNota(ColorNota colorNota) {
        this.colorNota = colorNota;
    }

    public String getValorNumerico() {
        return valorNumerico;
    }

    public void setValorNumerico(String valorNumerico) {
        this.valorNumerico = valorNumerico;
    }
}
