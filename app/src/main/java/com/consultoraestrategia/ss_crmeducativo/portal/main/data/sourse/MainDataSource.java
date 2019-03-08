package com.consultoraestrategia.ss_crmeducativo.portal.main.data.sourse;

import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.PadreMentorUi;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.ProgramaEducativoUi;

import java.util.List;

public interface MainDataSource {

    interface Callback<T>{
        void onLoad(boolean succes, T item);
    }

    List<ProgramaEducativoUi> onGetProgramaEducativo(int usuarioid);

    PadreMentorUi getPadreMentor(int usuarioId);
}
