package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

@Table(database = AppDatabase.class)
public class ArchivosRubroProceso extends BaseEntity {

    public ArchivosRubroProceso() { }

    @Column
    public String archivoRubroId;
    @Column
    public String url;
    @Column
    public int tipoArchivoId;
    @Column
    public String rubroEvalProcesoId;

    public String getArchivoRubroId() {
        return archivoRubroId;
    }

    public void setArchivoRubroId(String archivoRubroId) {
        this.archivoRubroId = archivoRubroId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTipoArchivoId() {
        return tipoArchivoId;
    }

    public void setTipoArchivoId(int tipoArchivoId) {
        this.tipoArchivoId = tipoArchivoId;
    }

    public String getRubroEvalProcesoId() {
        return rubroEvalProcesoId;
    }

    public void setRubroEvalProcesoId(String rubroEvalProcesoId) {
        this.rubroEvalProcesoId = rubroEvalProcesoId;
    }
}
