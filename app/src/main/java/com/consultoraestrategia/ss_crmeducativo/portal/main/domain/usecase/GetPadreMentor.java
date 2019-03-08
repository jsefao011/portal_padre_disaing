package com.consultoraestrategia.ss_crmeducativo.portal.main.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.portal.main.data.sourse.MainRepository;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.PadreMentorUi;

public class GetPadreMentor {

    private MainRepository repository;

    public GetPadreMentor(MainRepository repository) {
        this.repository = repository;
    }


    public PadreMentorUi execute(Request request) {
        return repository.getPadreMentor(request.getUsuarioId());
    }

    public final static class Request{
        private int usuarioId;

        public Request(int usuarioId) {
            this.usuarioId = usuarioId;
        }

        public int getUsuarioId() {
            return usuarioId;
        }
    }

}
