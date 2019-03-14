package com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities;

import java.util.List;

public class CursoUi {

    private String curso;
    private String docente;
    List<TareasUi> tareasUiList;
    List<TareaUiCount>tareaUiCountList;
    private String coloCurso;


    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public List<TareasUi> getTareasUiList() {
        return tareasUiList;
    }

    public void setTareasUiList(List<TareasUi> tareasUiList) {
        this.tareasUiList = tareasUiList;
    }

    public List<TareaUiCount> getTareaUiCountList() {
        return tareaUiCountList;
    }

    public void setTareaUiCountList(List<TareaUiCount> tareaUiCountList) {
        this.tareaUiCountList = tareaUiCountList;
    }

    public String getColoCurso() {
        return coloCurso;
    }

    public void setColoCurso(String coloCurso) {
        this.coloCurso = coloCurso;
    }
}
