package com.consultoraestrategia.ss_crmeducativo.portal.eventos.data.source;

public class EventosRepository implements EventosDataSource{

    private EventosDataSource eventosDataSource;

    public EventosRepository(EventosDataSource eventosDataSource) {
        this.eventosDataSource = eventosDataSource;
    }

    @Override
    public void getEventosColegio() {
        eventosDataSource.getEventosColegio();
    }
}
