package com.consultoraestrategia.ss_crmeducativo.portal.programahorario.data.source;

import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.DiaUi;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.HoraDiaUi;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.HoraUi;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.ProgramaHorarioUi;

import java.util.List;

public interface ProgramaHorarioDatSource {

    interface Callback<T>{
        void load(boolean success, T item);
    }

    void listarProgramasHorarioCargaCurso(int cargaCursoId, Callback<List<ProgramaHorarioUi>> callback);
    void listarProgramasHorarioProgramaEducativo(int programaEducativo, Callback<List<ProgramaHorarioUi>> callback);
    void listarCursosCargaCursoProgramaEducativo(int programaEducativoId, int empleadoId, Callback<List<CursoUi>> callback);
    void listarDias(List<Integer> horarioProgramaIdList, Callback<List<DiaUi>> callback);
    void listarHoras(int horarioProgramaId, Callback<List<HoraUi>> callback);
    void listarHorasporCursos(int cargaCursoId, int programaHorarioId, Callback<List<HoraDiaUi>> callback);
    void obtenerCursosPorCargaCurso(int cargaCursoId, int programaEducativoId, Callback<List<CursoUi>> callback);
}
