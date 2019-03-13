package com.consultoraestrategia.ss_crmeducativo.portal.programahorario.complejo.ui;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.ProgramaHorarioPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.complejo.ProgramaHorarioComplejoPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.data.source.ProgramaHorarioRepository;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.data.source.local.ProgramaHorarioLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.domain.usecase.GetCursoCompletos;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.domain.usecase.GetProgramaHorarioCompleto;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.domain.usecase.GetReporteHorario;
import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.ui.ProgramaHorarioActivity;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.evrencoskun.tableview.TableView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProgramaHorarioComplejoActivity extends ProgramaHorarioActivity {
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.rc_programa_horario)
    RecyclerView rcProgramaHorario;
    @BindView(R.id.horario)
    TableView horario;
    @BindView(R.id.rc_cursos)
    RecyclerView rcCursos;
    @BindView(R.id.root)
    ConstraintLayout root;


    @Override
    protected ProgramaHorarioPresenter getPresenter() {

        ProgramaHorarioRepository programaHorarioRepository = ProgramaHorarioRepository.getInstance(
                new ProgramaHorarioLocalDataSource(
                        InjectorUtils.provideHorarioProgramaDao(),
                        InjectorUtils.provideDiaDao(),
                        InjectorUtils.provideCursoDao(),
                        InjectorUtils.provideCargaCursoDao(),
                        InjectorUtils.provideParametrosDisenioDao()));

        return new ProgramaHorarioComplejoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetProgramaHorarioCompleto(programaHorarioRepository),
                new GetReporteHorario(programaHorarioRepository),
                new GetCursoCompletos(programaHorarioRepository));
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.horario_activity_v2, container, false);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return root;
    }

    @Override
    protected RecyclerView getRcCurso() {
        return rcCursos;
    }

    @Override
    protected RecyclerView getRcProgramaHorario() {
        return rcProgramaHorario;
    }

    @Override
    protected TableView getHorario() {
        return horario;
    }

    @Override
    protected ProgressBar getProgress() {
        return progressBar;
    }

}
