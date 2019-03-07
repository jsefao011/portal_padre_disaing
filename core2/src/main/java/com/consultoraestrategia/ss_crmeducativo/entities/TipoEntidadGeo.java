package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

@Table(database = AppDatabase.class)
public class TipoEntidadGeo extends BaseEntity {
    @PrimaryKey
    private int subtTipoId;
    @PrimaryKey
    private int georeferenciaId;
    @PrimaryKey
    private int entidadId;

    public int getSubtTipoId() {
        return subtTipoId;
    }

    public void setSubtTipoId(int subtTipoId) {
        this.subtTipoId = subtTipoId;
    }

    public int getGeoreferenciaId() {
        return georeferenciaId;
    }

    public void setGeoreferenciaId(int georeferenciaId) {
        this.georeferenciaId = georeferenciaId;
    }

    public int getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(int entidadId) {
        this.entidadId = entidadId;
    }
}

