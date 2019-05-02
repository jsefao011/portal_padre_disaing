package com.consultoraestrategia.ss_crmeducativo.portal.eventos.data.source;

public interface EventosDataSource {

    interface SucessCallback<T> {
        void onLoad(boolean success, T item);
    }

    void getEventosColegio();
}
