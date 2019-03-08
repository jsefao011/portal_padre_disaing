package com.consultoraestrategia.ss_crmeducativo.portal.caso.entities;

import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.CursoCustom;

public class CasoUi {

    private String id;
    private String descripcion;
    private FechaUi fechaUi;
    private TipoHijoUi tipoHijoUi;
    private CursoUi cursoUi;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public FechaUi getFechaUi() {
        return fechaUi;
    }

    public void setFechaUi(FechaUi fechaUi) {
        this.fechaUi = fechaUi;
    }

    public TipoHijoUi getTipoHijoUi() {
        return tipoHijoUi;
    }

    public void setTipoHijoUi(TipoHijoUi tipoHijoUi) {
        this.tipoHijoUi = tipoHijoUi;
    }

    public CursoUi getCursoUi() {
        return cursoUi;
    }

    public void setCursoUi(CursoUi cursoUi) {
        this.cursoUi = cursoUi;
    }

    ;


}
