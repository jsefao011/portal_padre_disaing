package com.consultoraestrategia.ss_crmeducativo.portal.caso.entities;

import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.CursoCustom;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

import java.util.List;

public class CasoUi {

    private String id;
    private String descripcion;
    private FechaUi fechaUi;
    private TipoHijoUi tipoHijoUi;
    private CursoUi cursoUi;
    private List<RepositorioFileUi> repositorioFileUiList;

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


    public List<RepositorioFileUi> getRepositorioFileUiList() {
        return repositorioFileUiList;
    }

    public void setRepositorioFileUiList(List<RepositorioFileUi> repositorioFileUiList) {
        this.repositorioFileUiList = repositorioFileUiList;
    }
}
