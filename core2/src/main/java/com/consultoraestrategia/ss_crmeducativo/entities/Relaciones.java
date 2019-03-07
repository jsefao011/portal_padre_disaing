package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by irvinmarin on 23/03/2017.
 */

@Table(database = AppDatabase.class)
public class Relaciones extends BaseModel {

    public static int PADRE= 181, MADRE=182;
    @Unique
    @Column
    @PrimaryKey
    private int idRelacion;
    @Column
    private int personaPrincipalId;
    @Column
    private int personaVinculadaId;
    @Column
    private int tipoId;
    @Column
    private boolean activo;

    public Relaciones() {
    }

    public Relaciones(int idRelacion, int personaPrincipalId, int personaVinculadaId, int tipoId, boolean activo) {
        this.idRelacion = idRelacion;
        this.personaPrincipalId = personaPrincipalId;
        this.personaVinculadaId = personaVinculadaId;
        this.tipoId = tipoId;
        this.activo = activo;
    }

    public int getPersonaPrincipalId() {
        return personaPrincipalId;
    }

    public void setPersonaPrincipalId(int personaPrincipalId) {
        this.personaPrincipalId = personaPrincipalId;
    }

    public int getPersonaVinculadaId() {
        return personaVinculadaId;
    }

    public void setPersonaVinculadaId(int personaVinculadaId) {
        this.personaVinculadaId = personaVinculadaId;
    }

    public int getIdRelacion() {
        return idRelacion;
    }

    public void setIdRelacion(int idRelacion) {
        this.idRelacion = idRelacion;
    }


    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Relaciones{" +
                "idRelacion=" + idRelacion +
                ", personaPrincipalId=" + personaPrincipalId +
                ", personaVinculadaId=" + personaVinculadaId +
                ", tipoId=" + tipoId +
                ", activo=" + activo +
                '}';
    }
}
