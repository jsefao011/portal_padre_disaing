package com.consultoraestrategia.ss_crmeducativo.portal.main.data.sourse;

import com.consultoraestrategia.ss_crmeducativo.portal.main.data.sourse.local.MainLocaDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.PadreMentorUi;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.ProgramaEducativoUi;

import java.util.List;

public class MainRepository implements MainDataSource {

    private MainLocaDataSource locaDataSource;

    public MainRepository(MainLocaDataSource locaDataSource) {
        this.locaDataSource = locaDataSource;
    }

    @Override
    public List<ProgramaEducativoUi> onGetProgramaEducativo(int usuarioid) {
        return locaDataSource.onGetProgramaEducativo(usuarioid);
    }

    @Override
    public PadreMentorUi getPadreMentor(int usuarioId) {
        return locaDataSource.getPadreMentor(usuarioId);
    }
}
