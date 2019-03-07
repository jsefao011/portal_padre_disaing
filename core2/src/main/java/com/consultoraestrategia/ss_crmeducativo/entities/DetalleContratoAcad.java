package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by irvinmarin on 22/03/2017.
 */
@Table(database = AppDatabase.class)
public class DetalleContratoAcad extends BaseModel {

    @Unique
    @PrimaryKey
    private int idContratoDetAcad;
    @Column
    private int idContrato;
    @Column
    private int cargaCursoId;
    @Column
    private int cargaAcademicaId;
    @Column
    private String notaPromedio;
    @Column
    private String escala;

    public DetalleContratoAcad() {
    }


    public DetalleContratoAcad(int idContratoDetAcad, int idContrato, int cargaCursoId, int cargaAcademicaId, String notaPromedio, String escala) {
        this.idContratoDetAcad = idContratoDetAcad;
        this.idContrato = idContrato;
        this.cargaCursoId = cargaCursoId;
        this.cargaAcademicaId = cargaAcademicaId;
        this.notaPromedio = notaPromedio;
        this.escala = escala;
    }

    public int getIdContratoDetAcad() {
        return idContratoDetAcad;
    }

    public void setIdContratoDetAcad(int idContratoDetAcad) {
        this.idContratoDetAcad = idContratoDetAcad;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public int getCargaCursoId() {
        return cargaCursoId;
    }

    public void setCargaCursoId(int cargaCursoId) {
        this.cargaCursoId = cargaCursoId;
    }

    public int getCargaAcademicaId() {
        return cargaAcademicaId;
    }

    public void setCargaAcademicaId(int cargaAcademicaId) {
        this.cargaAcademicaId = cargaAcademicaId;
    }

    public String getNotaPromedio() {
        return notaPromedio;
    }

    public void setNotaPromedio(String notaPromedio) {
        this.notaPromedio = notaPromedio;
    }

    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

    @Override
    public String toString() {
        return "DetalleContratoAcad{" +
                "idContratoDetAcad=" + idContratoDetAcad +
                ", idContrato=" + idContrato +
                ", cargaCursoId=" + cargaCursoId +
                ", cargaAcademicaId=" + cargaAcademicaId +
                ", notaPromedio=" + notaPromedio +
                ", escala='" + escala + '\'' +
                '}';
    }
}
