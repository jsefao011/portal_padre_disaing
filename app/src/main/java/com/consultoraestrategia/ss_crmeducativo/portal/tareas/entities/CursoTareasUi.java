package com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities;

import java.util.List;

public class CursoTareasUi {
    private int id;
    private String curso;
    private String docente;
    private List<TareasUi> listaTRows;
    private List<TipoTareaUi>listaTipoTColums;
    private List<List<Object>> celdaUiList;
    private String colorfondo;

    public CursoTareasUi() {
    }

    public CursoTareasUi(int id, String curso, String docente, List<TareasUi> listaTRows, List<TipoTareaUi> listaTipoTColums, List<List<Object>> celdaUiList, String colorfondo) {
        this.id = id;
        this.curso = curso;
        this.docente = docente;
        this.listaTRows = listaTRows;
        this.listaTipoTColums = listaTipoTColums;
        this.celdaUiList = celdaUiList;
        this.colorfondo = colorfondo;
    }

    public int getId() {
        return id;
    }

    public String getCurso() {
        return curso;
    }

    public String getDocente() {
        return docente;
    }

    public List<TareasUi> getListaTRows() {
        return listaTRows;
    }

    public List<TipoTareaUi> getListaTipoTColums() {
        return listaTipoTColums;
    }

    public List<List<Object>> getCeldaUiList() {
        return celdaUiList;
    }

    public String getColorfondo() {
        return colorfondo;
    }
}
