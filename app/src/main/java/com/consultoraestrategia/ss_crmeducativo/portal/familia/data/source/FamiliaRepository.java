package com.consultoraestrategia.ss_crmeducativo.portal.familia.data.source;

import com.consultoraestrategia.ss_crmeducativo.portal.familia.data.source.local.FamiliaLocalDataSource;

import java.util.List;

public class FamiliaRepository implements FamiliaDataSource{

    private FamiliaLocalDataSource familiaLocalDataSource;

    public FamiliaRepository(FamiliaLocalDataSource familiaLocalDataSource) {
        this.familiaLocalDataSource = familiaLocalDataSource;
    }

    @Override
    public void getListFamilia(int idPersona, SucessCallback<List<Object>> callback) {
        familiaLocalDataSource.getListFamilia(idPersona, callback);
    }
}
