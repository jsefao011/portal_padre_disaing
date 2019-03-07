package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

/**
 * Created by SCIEV on 21/12/2017.
 */
//Execpecion del uso del BaseRelEntity ya que esta tabla se va actualiziar
@Table(database = AppDatabase.class)
public class DesempenioIcd extends BaseRelEntity{
    @PrimaryKey
    @Column
    private int desempenioIcdId;
    @Column
    private int desempenioId;
    @Column
    private int icdId;
    @Column
    private String descripcion;
    @Column
    private int peso;


    public DesempenioIcd() {
    }


    public int getDesempenioIcdId() {
        return desempenioIcdId;
    }

    public void setDesempenioIcdId(int desempenioIcdId) {
        this.desempenioIcdId = desempenioIcdId;
    }

    public int getDesempenioId() {
        return desempenioId;
    }

    public void setDesempenioId(int desempenioId) {
        this.desempenioId = desempenioId;
    }

    public int getIcdId() {
        return icdId;
    }

    public void setIcdId(int icdId) {
        this.icdId = icdId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getUsuarioAccionId() {
        return usuarioAccionId;
    }

    public void setUsuarioAccionId(int usuarioAccionId) {
        this.usuarioAccionId = usuarioAccionId;
    }
}
