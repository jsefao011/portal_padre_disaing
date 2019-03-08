package com.consultoraestrategia.ss_crmeducativo.portal.familia.data.source;

import java.util.List;

public interface FamiliaDataSource {


    interface SucessCallback<T> {
        void onLoad(boolean success, T item);
    }

    void getListFamilia(int idPersona, SucessCallback<List<Object>> callback);
}
