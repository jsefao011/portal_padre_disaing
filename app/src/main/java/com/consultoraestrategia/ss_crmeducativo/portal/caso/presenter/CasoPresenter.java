package com.consultoraestrategia.ss_crmeducativo.portal.caso.presenter;


import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.ui.CasoView;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

public interface CasoPresenter  extends BaseFragmentPresenter<CasoView>{

    void onClickDownload(RepositorioFileUi repositorioFileUi);

    void onClickClose(RepositorioFileUi repositorioFileUi);

    void onClickArchivo(RepositorioFileUi repositorioFileUi);
}
