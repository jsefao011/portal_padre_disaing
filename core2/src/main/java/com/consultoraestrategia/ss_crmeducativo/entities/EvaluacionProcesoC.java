package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

/**
 * Created by irvinmarin on 23/06/2017.
 */

@Table(database = AppDatabase.class)
public class EvaluacionProcesoC extends BaseEntity {
    @Column
    private String evaluacionProcesoId;
    @Column
    private long evaluacionResultadoId;
    @Column
    private double nota;
    @Column
    private String escala;
    @Column
    private String rubroEvalProcesoId;
    @Column
    private int sesionAprendizajeId;
    @Column
    private String valorTipoNotaId;
    @Column
    private String equipoId;
    @Column
    private int alumnoId;
    @Column
    int calendarioPeriodoId;
    @Column
    boolean formulaSinc;

    @Column
    int msje;

    public EvaluacionProcesoC() {
    }

    public int getMsje() {
        return msje;
    }

    public void setMsje(int msje) {
        this.msje = msje;
    }

    public String getEvaluacionProcesoId() {
        return evaluacionProcesoId;
    }

    public void setEvaluacionProcesoId(String evaluacionProcesoId) {
        this.evaluacionProcesoId = evaluacionProcesoId;
    }

    public long getEvaluacionResultadoId() {
        return evaluacionResultadoId;
    }

    public void setEvaluacionResultadoId(long evaluacionResultadoId) {
        this.evaluacionResultadoId = evaluacionResultadoId;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

    public String getRubroEvalProcesoId() {
        return rubroEvalProcesoId;
    }

    public void setRubroEvalProcesoId(String rubroEvalProcesoId) {
        this.rubroEvalProcesoId = rubroEvalProcesoId;
    }

    public int getSesionAprendizajeId() {
        return sesionAprendizajeId;
    }

    public void setSesionAprendizajeId(int sesionAprendizajeId) {
        this.sesionAprendizajeId = sesionAprendizajeId;
    }

    public String getValorTipoNotaId() {
        return valorTipoNotaId;
    }

    public void setValorTipoNotaId(String valorTipoNotaId) {
        this.valorTipoNotaId = valorTipoNotaId;
    }

    public String getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(String equipoId) {
        this.equipoId = equipoId;
    }

    public int getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
    }

    public int getCalendarioPeriodoId() {
        return calendarioPeriodoId;
    }

    public void setCalendarioPeriodoId(int calendarioPeriodoId) {
        this.calendarioPeriodoId = calendarioPeriodoId;
    }

    public boolean isFormulaSinc() {
        return formulaSinc;
    }

    public void setFormulaSinc(boolean formulaSinc) {
        this.formulaSinc = formulaSinc;
    }

    public static EvaluacionProcesoC getRubroAlumnRubroId(String idRubroProtected, int personaId) {
        return SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.alumnoId.is(personaId))
                .and(EvaluacionProcesoC_Table.rubroEvalProcesoId.is(idRubroProtected))
                .querySingle();
    }

    @Override
    public String toString() {
        return "EvaluacionProcesoC{" +
                "evaluacionProcesoId='" + evaluacionProcesoId + '\'' +
                ", rubroEvalProcesoId='" + rubroEvalProcesoId + '\'' +
                ", valorTipoNotaId='" + valorTipoNotaId + '\'' +
                ", alumnoId=" + alumnoId +
                ", msje=" + msje +
                '}';
    }
}
