package com.consultoraestrategia.ss_crmeducativo.portal.programahorario.complejo;

import android.content.res.Resources;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.ProgramaHorarioPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.domain.usecase.GetCursoCompletos;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.domain.usecase.GetProgramaHorarioCompleto;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.domain.usecase.GetReporteHorario;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.CursoUi;

import java.util.List;

public class ProgramaHorarioComplejoPresenterImpl extends ProgramaHorarioPresenterImpl {

    private GetCursoCompletos getCursoCompletos;

    public ProgramaHorarioComplejoPresenterImpl(UseCaseHandler handler, Resources res, GetProgramaHorarioCompleto getProgramaHorarioCompleto, GetReporteHorario getReporteHorario, GetCursoCompletos getCursoCompletos) {
        super(handler, res, getProgramaHorarioCompleto, getReporteHorario);
        this.getCursoCompletos = getCursoCompletos;
    }

    @Override
    protected void listarCurso(final Callback<List<CursoUi>> callback) {
        handler.execute(getCursoCompletos, new GetCursoCompletos.RequestValues(programaEducativoId, hijoPersonaId),
                new UseCase.UseCaseCallback<GetCursoCompletos.ResponseValue>() {
                    @Override
                    public void onSuccess(GetCursoCompletos.ResponseValue response) {
                        callback.onLoad(true,response.getCursoUiList());
                    }

                    @Override
                    public void onError() {
                        callback.onLoad(false,null);
                    }
                });
    }

    @Override
    public void onCLickAcceptButtom() {

    }
}
