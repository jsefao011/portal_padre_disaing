package com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.dialogFragment.BaseDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.infoRubro.domain.useCase.TransformarJsonRubroObjeto;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.infoRubro.entities.Alumno;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.InfoRubroPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.InfoRubroPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.adapters.InfoRubroTableViewAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.adapters.holder.CornerHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.data.InfoRubroDataLocalSource;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.data.InfoRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.domain.useCase.GetInfoRubroProceso;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.Cell;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.Column;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.Row;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.evrencoskun.tableview.TableView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Jse on 15/09/2018.
 */

public class InfoRubroFragment extends BaseDialogFragment<InfoRubroView, InfoRubroPresenter, InfoRubroListener> implements InfoRubroView, View.OnClickListener {

    @BindView(R.id.text_alumn_name)
    TextView textAlumnName;
    @BindView(R.id.img_alumn_profile)
    CircleImageView imgAlumnProfile;
    @BindView(R.id.edt_puntos)
    TextInputEditText edtPuntos;
    @BindView(R.id.til_puntos)
    TextInputLayout tilPuntos;
    @BindView(R.id.edt_nota)
    TextInputEditText edtNota;
    @BindView(R.id.til_nota)
    TextInputLayout tilNota;
    @BindView(R.id.edt_desempenio)
    TextInputEditText edtDesempenio;
    @BindView(R.id.til_desempenio)
    TextInputLayout tilDesempenio;
    @BindView(R.id.edt_logro)
    TextInputEditText edtLogro;
    @BindView(R.id.til_logro)
    TextInputLayout tilLogro;
    @BindView(R.id.table)
    TableView table;
    @BindView(R.id.txt_contador)
    TextView txtContador;
    @BindView(R.id.text_alumn_lastname)
    TextView textAlumnLastname;
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.txt_rubrica)
    TextView txtRubrica;
    @BindView(R.id.txt_curso)
    TextView txtCurso;

    @BindView(R.id.btnRetroceder)
    Button btnRetroceder;
    private Unbinder unbinder;
    private InfoRubroTableViewAdapter adapter;
    private InfoRubroPresenter presenter;

    public static final String ID_EVALUACION_PROCESO = "EvaluacionProcesoId";
    public static final String ID_CARGA_CURSO = "CargaCursoId";

    public static InfoRubroFragment newInstance(String evaluacionProcesoId, int cargaCursoId) {
        Bundle args = new Bundle();
        args.putString(ID_EVALUACION_PROCESO, evaluacionProcesoId);
        args.putInt(ID_CARGA_CURSO, cargaCursoId);
        InfoRubroFragment fragment = new InfoRubroFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapter();
        btnRetroceder.setOnClickListener(this);
    }

    @Override
    protected String getLogTag() {
        return null;
    }

    @Override
    protected InfoRubroPresenter getPresenter() {
        presenter = new InfoRubroPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetInfoRubroProceso(new InfoRubroRepository(new InfoRubroDataLocalSource(InjectorUtils.provideCursoDao()
                , InjectorUtils.provideCompetenciaDao(), InjectorUtils.provideIndicadorDao()))));
        return presenter;
    }

    @Override
    protected InfoRubroFragment getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
       View view=inflater.inflate(R.layout.fragment_eval_rubrica_bidimencionale_grupales, container, false);
       unbinder = ButterKnife.bind(this, view);
       return  view;
    }

    @Override
    protected ViewGroup getRootLayout() {
        return null;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }


    private void setupAdapter() {
        this.adapter = new InfoRubroTableViewAdapter(getContext());
        table.setAdapter(adapter);
        table.setIgnoreSelectionColors(false);
        table.setHasFixedWidth(false);
        table.setIgnoreSelectionColors(true);
    }


    @Override
    public void showTableView(List<List<Cell>> cellListList, List<Column> columnList, List<Row> rowList, String nombreTabla) {
        adapter.setAllItems(columnList, rowList, cellListList);
        int row_header_width = (int) getContext().getResources().getDimension(R.dimen.info_rubro_column_corner_width);
        int row_header_height = (int) getContext().getResources().getDimension(R.dimen.info_rubro_column_corner_height);
        adapter.setCornerView(nombreTabla, row_header_width, row_header_height);
    }

    @Override
    public void setPuntos(String puntos) {
        edtPuntos.setText(puntos);
    }

    @Override
    public void setAlumno(AlumnoUi alumno) {
        textAlumnName.setText(alumno.getNombre());
        textAlumnLastname.setText(alumno.getApellidos());

        Glide.with(this)
                .load(alumno.getUrlpicture())
                .apply(new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_info_outline_black_24dp))
                .into(imgAlumnProfile);
    }

    @Override
    public void setDesempenio(String desempenio) {
        edtDesempenio.setText(desempenio);
    }

    @Override
    public void setNota(String nota) {
        edtNota.setText(nota);
    }

    @Override
    public void setLogro(String logro) {
        edtLogro.setText(logro);
    }

    @Override
    public void setNombreRubrica(String nombreRubrica) {
        txtRubrica.setText(nombreRubrica);
    }

    @Override
    public void setNombreCurso(String nombreCurso) {
        txtCurso.setText(nombreCurso);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
        this.getDialog().getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        this.getDialog().getWindow().
                setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }



    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          default:
              dismiss();
              break;
      }
    }
}
