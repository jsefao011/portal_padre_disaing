package com.consultoraestrategia.ss_crmeducativo.portal.main.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.portal.main.data.sourse.MainRepository;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.ProgramaEducativoUi;

import java.util.List;

public class GetProgramaEducativoList {
    private MainRepository repository;

    public GetProgramaEducativoList(MainRepository repository) {
        this.repository = repository;
    }

    public List<ProgramaEducativoUi> execute(int alumnoId){
        return repository.onGetProgramaEducativo(alumnoId);
    }
}
