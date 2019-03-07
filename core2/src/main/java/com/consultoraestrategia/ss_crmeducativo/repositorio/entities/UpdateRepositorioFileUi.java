package com.consultoraestrategia.ss_crmeducativo.repositorio.entities;

public class UpdateRepositorioFileUi extends RepositorioFileUi {
    private RepositorioUploadEstadoFileU uploadEstadoFileU = RepositorioUploadEstadoFileU.SIN_SUBIR;
    private DownloadCancelUi downloadCancelUi;

    public RepositorioUploadEstadoFileU getUploadEstadoFileU() {
        return uploadEstadoFileU;
    }

    public void setUploadEstadoFileU(RepositorioUploadEstadoFileU uploadEstadoFileU) {
        this.uploadEstadoFileU = uploadEstadoFileU;
    }

    public DownloadCancelUi getDownloadCancelUi() {
        return downloadCancelUi;
    }

    public void setDownloadCancelUi(DownloadCancelUi downloadCancelUi) {
        this.downloadCancelUi = downloadCancelUi;
    }
}
