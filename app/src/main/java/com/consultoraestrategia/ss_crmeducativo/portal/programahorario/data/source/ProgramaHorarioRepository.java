package com.consultoraestrategia.ss_crmeducativo.portal.programahorario.data.source;

import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.data.source.local.ProgramaHorarioLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.DiaUi;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.HoraDiaUi;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.HoraUi;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.ProgramaHorarioUi;

import java.util.List;

public class ProgramaHorarioRepository implements ProgramaHorarioDatSource {

    private static ProgramaHorarioRepository mInstance;
    private ProgramaHorarioLocalDataSource localDataSource;

    private ProgramaHorarioRepository(ProgramaHorarioLocalDataSource localDataSource) {
        this.localDataSource = localDataSource;
    }

    public static ProgramaHorarioRepository getInstance(ProgramaHorarioLocalDataSource localDataSource) {
        if (mInstance == null) {
            mInstance = new ProgramaHorarioRepository(localDataSource);
        }
        return mInstance;
    }


    @Override
    public void listarProgramasHorarioCargaCurso(int cargaCursoId, Callback<List<ProgramaHorarioUi>> callback) {
        localDataSource.listarProgramasHorarioCargaCurso(cargaCursoId, callback);
    }

    @Override
    public void listarProgramasHorarioProgramaEducativo(int programaEducativo, Callback<List<ProgramaHorarioUi>> callback) {
        localDataSource.listarProgramasHorarioProgramaEducativo(programaEducativo, callback);
    }

    @Override
    public void listarCursosCargaCursoProgramaEducativo(int programaEducativoId, int empleadoId, Callback<List<CursoUi>> callback) {
        localDataSource.listarCursosCargaCursoProgramaEducativo(programaEducativoId, empleadoId,callback);
    }

    @Override
    public void listarDias(List<Integer> horarioProgramaIdList, Callback<List<DiaUi>> callback) {
        localDataSource.listarDias(horarioProgramaIdList, callback);
    }

    @Override
    public void listarHoras(int horarioProgramaId, Callback<List<HoraUi>> callback) {
        localDataSource.listarHoras(horarioProgramaId, callback);
    }

    @Override
    public void listarHorasporCursos(int cargaCursoId, int programaHorarioId, Callback<List<HoraDiaUi>> callback) {
        localDataSource.listarHorasporCursos(cargaCursoId, programaHorarioId, callback);
    }

    @Override
    public void obtenerCursosPorCargaCurso(int cargaCursoId, int programaEducativoId, Callback<List<CursoUi>> callback) {
        localDataSource.obtenerCursosPorCargaCurso(cargaCursoId, programaEducativoId, callback);
    }
}
