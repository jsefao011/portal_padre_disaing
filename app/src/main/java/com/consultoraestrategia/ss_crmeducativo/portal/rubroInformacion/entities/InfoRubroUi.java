package com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities;

import java.util.ArrayList;
import java.util.List;

public class InfoRubroUi {

    private AlumnoUi alumnoUi;
    private CursoUi cursoUi;
    private String nombreRubro;
    private List<Row> rows= new ArrayList<>();
    private List<List<Cell>> cellListList=  new ArrayList<>();
    private List<Column> columns= new ArrayList<>();
    private String desempenio;
    private String puntos;
    private String logro;

    public AlumnoUi getAlumnoUi() {
        return alumnoUi;
    }

    public void setAlumnoUi(AlumnoUi alumnoUi) {
        this.alumnoUi = alumnoUi;
    }

    public CursoUi getCursoUi() {
        return cursoUi;
    }

    public void setCursoUi(CursoUi cursoUi) {
        this.cursoUi = cursoUi;
    }

    public String getNombreRubro() {
        return nombreRubro;
    }

    public void setNombreRubro(String nombreRubro) {
        this.nombreRubro = nombreRubro;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public List<List<Cell>> getCellListList() {
        return cellListList;
    }


    public void setCellListList(List<List<Cell>> cellListList) {
        this.cellListList = cellListList;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public String getDesempenio() {
        return desempenio;
    }

    public void setDesempenio(String desempenio) {
        this.desempenio = desempenio;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }

    public String getLogro() {
        return logro;
    }

    public void setLogro(String logro) {
        this.logro = logro;
    }
}
