package com.consultoraestrategia.ss_crmeducativo.portal.caso.entities;

import java.util.List;

public class AlumnoUi {

    private int alumnoId;
    private List<CasoUi>casoUiList;
    private List<TipoPadreUi>tipoPadreUiList;

    public int getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
    }

    public List<CasoUi> getCasoUiList() {
        return casoUiList;
    }

    public void setCasoUiList(List<CasoUi> casoUiList) {
        this.casoUiList = casoUiList;
    }

    public List<TipoPadreUi> getTipoPadreUiList() {
        return tipoPadreUiList;
    }

    public void setTipoPadreUiList(List<TipoPadreUi> tipoPadreUiList) {
        this.tipoPadreUiList = tipoPadreUiList;
    }
}
