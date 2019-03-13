package com.consultoraestrategia.ss_crmeducativo.login.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.transition.TransitionManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.login.LoginPresenter;
import com.consultoraestrategia.ss_crmeducativo.login.LoginPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.login.LoginView;
import com.consultoraestrategia.ss_crmeducativo.login.adapter.PersonaAdapter;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.LoginRepository;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.local.LocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.remote.RemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetAdminService;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetPasswordServidor;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetPersonaServidor;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetUserSave;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetUsuarioSimple;
import com.consultoraestrategia.ss_crmeducativo.login.domain.usecases.GetUsuarioUI;
import com.consultoraestrategia.ss_crmeducativo.login.entity.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.login.listener.PersonaListener;
import com.consultoraestrategia.ss_crmeducativo.login.preferent.LoginPreferentRepository;
import com.consultoraestrategia.ss_crmeducativo.login.preferent.LoginPreferentRepositoryImpl;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin.SEDatosCompletosLoginRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin.local.SEDatosCompletosLoginLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin.remote.SEDatosCompletosLoginRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosCargaAcademica.BEDatosCargaAcademicaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosCargaAcademica.local.BEDatosCargaAcademicaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosCargaAcademica.remote.BEDatosCargaAcademicaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioAsistencia.BEDatosEnvioAsistenciaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioAsistencia.local.BEDatosEnvioAsistenciaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioAsistencia.remote.AsistenciaLoginRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioGrupo.BEDatosEnvioGrupoRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioGrupo.local.BEDatosEnvioGrupoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioGrupo.remote.BEDatosEnvioGrupoRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioHorario.BEDatosEnvioHorarioRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioHorario.local.BEDatosEnvioHorarioLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioHorario.remote.BEDatosEnvioHorarioRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioMensajeria.BEDatosEnvioMensajeriaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioMensajeria.local.BEDatosEnvioMensajeriaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioMensajeria.remote.BEDatosEnvioMensajeriaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioTipoNota.BEDatosEnvioTipoNotaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioTipoNota.local.BEDatosEnvioTipoNotaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioTipoNota.remote.BEDatosEnvioTipoNotaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEvaluacionResultado.BEDatosEvaluacionResultadoRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEvaluacionResultado.local.BEDatosEvaluacionResultadoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEvaluacionResultado.remote.BEDatosEvaluacionResultadoRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosRubroEvaluacionProceso.BEDatosRubroEvaluacionProcesoRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosRubroEvaluacionProceso.local.BEDatosRubroEvaluacionProcesoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosRubroEvaluacionProceso.remote.BEDatosRubroEvaluacionProcesoRemotaDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSilaboEventoEnvio.BEDatosSilaboEventoEnvioRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSilaboEventoEnvio.local.BEDatosSilaboEventoEnvioLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSilaboEventoEnvio.remote.BEDatosSilaboEventoEnvioRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.obtenerDatosLogin.BEObtenerDatosLoginRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.obtenerDatosLogin.local.BEObtenerDatosLoginLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.obtenerDatosLogin.remote.BEObtenerDatosLoginRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.util.RepositoryInjector;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.BorrarUsuarioLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosCargaAcademicaLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosEnvioAsistenciaLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosEnvioGrupoLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosEnvioHorarioLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosEnvioMensajeriaLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosEnvioTipoNotaLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosEvaluacionResultadoLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetDatosSilaboEventoEnvio;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.GetObtenerDatosLogin;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.SaveDatosCompletosLogin;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by irvinmarin on 20/10/2017.
 */

public abstract class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView, TextView.OnEditorActionListener, PersonaListener, View.OnClickListener {

    Toolbar toolbar;
    AppBarLayout appBarLayout;
    ImageView imageLogoProgressPage;
    ProgressBar progressbar;
    TextInputEditText edittextUsername;
    TextInputEditText edittextPasswordUser;
    TextInputLayout tilUsername;
    TextInputEditText edittextPassword;
    ConstraintLayout contendoPrincipal;
    ProgressBar pgrProgress;
    TextView textProgress;
    View viewPageProgress;
    Button btnSiguienteUser;
    TextView txtNombreUsuario;
    TextInputLayout textInputLayout5;
    Button btnSiguientePassword;
    Button btnSiguienteCorreo;
    ImageView btnClosePassword;
    CircleImageView imgUsuarioPassword;
    TextView txtUsuarioPassword;
    CardView groupPageUser;
    View contenloginPassword;
    View contenloginUser;
    View contenLoginListaUsuario;
    View contentLoginCorreo;
    View contentLoginDni;
    //View contentLoginPasswordUser;
    TextInputEditText editttextDni;
    Button btnSiguienteDni;
    RecyclerView rcContactos;
    TextView btnQuitarUsuario;
    ImageView btnAtrasLstUsu;
    TextView txtrecPassw;
    TextInputEditText edittextCorreo;
    ImageView btnAtrasCorreo;
    ImageView btnAtrasDni;
    ImageView btnBtnAtrasLstUsuPass;

    private ApiRetrofit apiRetrofit;
    private LoginPreferentRepository loginPreferentRepository;
    private PersonaAdapter usuarioAdapter;
    private ImageView imgInstitucion;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_login_mvp);
        toolbar = findViewById(R.id.toolbar);
        appBarLayout = findViewById(R.id.appBarLayout);
        imageLogoProgressPage = findViewById(R.id.image_logo_progress_page);
        progressbar = findViewById(R.id.progressbar);
        edittextUsername = findViewById(R.id.edittext_username);
        tilUsername = findViewById(R.id.til_username);
        edittextPassword = findViewById(R.id.edittext_password);
        contendoPrincipal = findViewById(R.id.contendoPrincipal);
        pgrProgress= findViewById(R.id.pgr_progresshorizontal);
        textProgress = findViewById(R.id.text_progress);
        viewPageProgress = findViewById(R.id.group_page_progress);
        btnSiguienteUser = findViewById(R.id.btn_siguiente_user);
        btnSiguienteUser.setOnClickListener(this);
        txtNombreUsuario  = findViewById(R.id.txt_nombre_usuario);
        textInputLayout5  = findViewById(R.id.textInputLayout5);
        btnSiguientePassword  = findViewById(R.id.btn_siguiente_password);
        btnSiguientePassword.setOnClickListener(this);
        btnClosePassword = findViewById(R.id.btn_close_password);
        btnClosePassword.setOnClickListener(this);
        imgUsuarioPassword = findViewById(R.id.img_usuario_password);
        txtUsuarioPassword  = findViewById(R.id.txt_usuario_password);
        groupPageUser = findViewById(R.id.group_page_user);
        contenloginPassword  = findViewById(R.id.conten_login_mvp_password);
        contenloginUser = findViewById(R.id.conten_login_mvp_user);
        contentLoginCorreo = findViewById(R.id.conten_login_mvp_correo);
        contenLoginListaUsuario = findViewById(R.id.conten_login_lista_usuario);
        rcContactos = findViewById(R.id.rc_contactos);
        btnQuitarUsuario = findViewById(R.id.btn_quitar_usuario);
        btnQuitarUsuario.setOnClickListener(this);
        btnAtrasLstUsu = findViewById(R.id.btn_atras_lst_usu);
        btnAtrasLstUsu.setOnClickListener(this);
        txtrecPassw = findViewById(R.id.txtrecPassw);
        txtrecPassw.setOnClickListener(this);
        edittextCorreo = findViewById(R.id.edittext_correo);
        btnSiguienteCorreo = findViewById(R.id.btn_siguiente_correo);
        btnSiguienteCorreo.setOnClickListener(this);
        btnSiguienteDni = findViewById(R.id.btn_siguiente_dni);
        btnSiguienteDni.setOnClickListener(this);
        editttextDni = findViewById(R.id.edittext_dni);
        contentLoginDni = findViewById(R.id.conten_login_mvp_dni);
        btnAtrasDni = findViewById(R.id.btn_atras_dni);
        btnAtrasDni.setOnClickListener(this);
        btnAtrasCorreo = findViewById(R.id.btn_atras_correo);
        btnAtrasCorreo.setOnClickListener(this);
        edittextPasswordUser = findViewById(R.id.edittext_password_user);
        //contentLoginPasswordUser = findViewById(R.id.conten_login_mvp_password_user);
        btnBtnAtrasLstUsuPass = findViewById(R.id.btn_atras_lst_usu_pass);
        btnBtnAtrasLstUsuPass.setOnClickListener(this);
        imgInstitucion = findViewById(R.id.img_institucion);

        setSupportActionBar(toolbar);
        setupLogoAnim();
        setupEditorListener();
        setupPreferenciaRepository();
        setupAdapter();

    }

    private void setupAdapter() {
        rcContactos.setLayoutManager(new LinearLayoutManager(this));
        this.usuarioAdapter = new PersonaAdapter(new ArrayList<PersonaUi>(), this);
        rcContactos.setAdapter(this.usuarioAdapter);
    }

    private void setupPreferenciaRepository() {
        this.loginPreferentRepository = new LoginPreferentRepositoryImpl(this);
    }

    private void setupEditorListener() {
        edittextPassword.setOnEditorActionListener(this);
        edittextUsername.setOnEditorActionListener(this);
        edittextPasswordUser.setOnEditorActionListener(this);
        edittextCorreo.setOnEditorActionListener(this);
        editttextDni.setOnEditorActionListener(this);
    }


    @Override
    protected LoginView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected String getTag() {
        return LoginActivity.class.getSimpleName();
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected LoginPresenter getPresenter() {
        this.apiRetrofit = UtilServidor.getInstance().getApiRetrofit();
        return new LoginPresenterImpl(
                new UseCaseHandler(
                        new UseCaseThreadPoolScheduler()),
                getResources(),
                new GetUsuarioUI(LoginRepository.getInstace(
                        new LocalDataSource(InjectorUtils.provideSessionUserDao()),
                        new RemoteDataSource(this)
                )),
                this,
                new GetUsuarioSimple(LoginRepository.getInstace(
                        new LocalDataSource(InjectorUtils.provideSessionUserDao()),
                        new RemoteDataSource(this)
                )),
                new GetDatosCargaAcademicaLogin(BEDatosCargaAcademicaRepository.getInstance(
                        new BEDatosCargaAcademicaLocalDataSource(),
                        new BEDatosCargaAcademicaRemoteDataSource(UtilServidor.getInstance()),
                        UtilServidor.getInstance())
                ),
                new GetDatosEnvioAsistenciaLogin(BEDatosEnvioAsistenciaRepository.getInstance(
                        new BEDatosEnvioAsistenciaLocalDataSource(),
                        new AsistenciaLoginRemoteDataSource(UtilServidor.getInstance()),
                        UtilServidor.getInstance()
                )),
                new GetDatosEnvioGrupoLogin(BEDatosEnvioGrupoRepository.getInstance(
                        new BEDatosEnvioGrupoLocalDataSource(),
                        new BEDatosEnvioGrupoRemoteDataSource(UtilServidor.getInstance()),
                        UtilServidor.getInstance()
                )),
                new GetDatosEnvioHorarioLogin(BEDatosEnvioHorarioRepository.getInstance(
                        new BEDatosEnvioHorarioLocalDataSource(),
                        new BEDatosEnvioHorarioRemoteDataSource(UtilServidor.getInstance()),
                        UtilServidor.getInstance()
                )),
                new GetDatosEnvioMensajeriaLogin(BEDatosEnvioMensajeriaRepository.getInstance(
                        new BEDatosEnvioMensajeriaLocalDataSource(),
                        new BEDatosEnvioMensajeriaRemoteDataSource(UtilServidor.getInstance()),
                        UtilServidor.getInstance()
                )),
                new GetDatosEnvioTipoNotaLogin(
                        BEDatosEnvioTipoNotaRepository.getInstance(
                                new BEDatosEnvioTipoNotaLocalDataSource(),
                                new BEDatosEnvioTipoNotaRemoteDataSource(UtilServidor.getInstance()),
                                UtilServidor.getInstance()
                        )),
                new GetDatosEvaluacionResultadoLogin(BEDatosEvaluacionResultadoRepository.getInstance(
                        new BEDatosEvaluacionResultadoLocalDataSource(),
                        new BEDatosEvaluacionResultadoRemoteDataSource(UtilServidor.getInstance()),
                        UtilServidor.getInstance()
                )),
                new GetDatosRubroEvaluacionProceso(BEDatosRubroEvaluacionProcesoRepository.getInstance(
                        new BEDatosRubroEvaluacionProcesoLocalDataSource(InjectorUtils.provideEvaluacionProcesoDao(), InjectorUtils.provideRubroProcesoDao()),
                        new BEDatosRubroEvaluacionProcesoRemotaDataSource(UtilServidor.getInstance()),
                        UtilServidor.getInstance()
                )),
                new GetDatosSilaboEventoEnvio(BEDatosSilaboEventoEnvioRepository.getInstance(
                        new BEDatosSilaboEventoEnvioLocalDataSource(),
                        new BEDatosSilaboEventoEnvioRemoteDataSource(UtilServidor.getInstance()),
                        UtilServidor.getInstance()
                )),
                new GetObtenerDatosLogin(BEObtenerDatosLoginRepository.getInstance(
                        new BEObtenerDatosLoginLocalDataSource(),
                        new BEObtenerDatosLoginRemoteDataSource(UtilServidor.getInstance()),
                        UtilServidor.getInstance()
                )),
                new SaveDatosCompletosLogin(SEDatosCompletosLoginRepository.getInstance(
                        new SEDatosCompletosLoginLocalDataSource(),
                        new SEDatosCompletosLoginRemoteDataSource()
                )),
                new BorrarUsuarioLogin(RepositoryInjector.getSEDatosCompletosLoginRepository()),
                new GetPasswordServidor(LoginRepository.getInstace(
                        new LocalDataSource(InjectorUtils.provideSessionUserDao()),
                        new RemoteDataSource(this)
                )),
                new GetPersonaServidor(LoginRepository.getInstace(
                        new LocalDataSource(InjectorUtils.provideSessionUserDao()),
                        new RemoteDataSource(this)
                )),
                new GetAdminService(LoginRepository.getInstace(
                        new LocalDataSource(InjectorUtils.provideSessionUserDao()),
                                new RemoteDataSource(this)
                )),
                new GetUserSave(LoginRepository.getInstace(
                        new LocalDataSource(InjectorUtils.provideSessionUserDao()),
                        new RemoteDataSource(this)
                ))


        );
    }


    @Override
    protected ViewGroup getRootLayout() {
        return contendoPrincipal;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressbar;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_servers) {
            presenter.onServerMenuClicked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void disableInputs() {
        btnSiguientePassword.setEnabled(false);
        edittextUsername.setEnabled(false);
        edittextPassword.setEnabled(false);
        edittextCorreo.setEnabled(false);
    }

    @Override
    public void showProgressBar() {
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void enableInputs() {
        edittextUsername.setEnabled(true);
        edittextPassword.setEnabled(true);
        btnSiguientePassword.setEnabled(true);
        edittextCorreo.setEnabled(true);
    }


    @Override
    public void clearImputs() {
        edittextUsername.setText("");
        edittextPassword.setText("");
        edittextCorreo.setText("");
    }

    @Override
    public void clearFocusPassword() {
        edittextPasswordUser.setText("");
        edittextCorreo.setText("");
        editttextDni.setText("");
        edittextPassword.setText("");
    }

    @Override
    public void clearFocusCorreo() {
        edittextCorreo.setText("");
        editttextDni.setText("");
    }

    @Override
    public void clearFocusDni() {
        editttextDni.setText("");
    }



    @Override
    public void showServerListDialog() {
    }

    @Override
    public void showEdtDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_dialog_edt, null);
        final EditText editText = dialogView.findViewById(R.id.edt_content);
        builder
                .setView(dialogView)
                .setTitle(R.string.login_dialog_add_server)
                .setPositiveButton(R.string.global_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        if (presenter != null) {
                            presenter.onCustomUrlOk(editText.getText().toString());
                        }
                    }
                })
                .setNegativeButton(R.string.global_btn_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showProgressPage() {
        viewPageProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoginPage() {
        groupPageUser.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoginCorreo() {
        contentLoginCorreo.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoginDNI() {
        contentLoginDni.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoginDNI() {
        contentLoginDni.setVisibility(View.GONE);
    }

    /*@Override
    public void showLoginPasswordUser() {
        contentLoginPasswordUser.setVisibility(View.VISIBLE);
    }*/

    /*@Override
    public void hideLoginPasswordUser() {
        contentLoginPasswordUser.setVisibility(View.GONE);
    }*/

    @Override
    public void hideLoginCorreo() {
        contentLoginCorreo.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressPage() {
        viewPageProgress.setVisibility(View.GONE);
    }

    @Override
    public void hideLoginPage() {
        groupPageUser.setVisibility(View.GONE);
    }

    @Override
    public void showListUsuario(List<PersonaUi> personaUiList, boolean elimiarUsuario) {
        usuarioAdapter.setUsuarios(personaUiList, elimiarUsuario);
    }

    @Override
    public void removeUsuario(PersonaUi personaUi) {
        usuarioAdapter.removerUsuario(personaUi);
    }

    @Override
    public void updateProgress(int progress) {
        pgrProgress.setProgress(progress);
    }

    @Override
    public void updateProgressText(String text) {
        TransitionManager.beginDelayedTransition(contendoPrincipal);
        textProgress.setText(text);
    }

    @Override
    public void setupLogoAnim() {

            Glide.with(this)
                    .asGif()
                    .load(Uri.parse("file:///android_asset/DownloadData2-min.gif"))
                    .apply(Utils.getGlideRequestOptions(R.drawable.ic_error_outline_black).placeholder(R.drawable.logo_consultoraa))
                    .into(imageLogoProgressPage);


    }

    @Override
    public void ShowLoginPagePassword() {
        contenloginPassword.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoginPagePassword() {
        contenloginPassword.setVisibility(View.GONE);
    }

    @Override
    public void showLoginPageUser() {
        contenloginUser.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoginPageUser() {
        contenloginUser.setVisibility(View.GONE);
    }

    @Override
    public void showLoginPageListUser() {
        contenLoginListaUsuario.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoginPageListUser() {
        contenLoginListaUsuario.setVisibility(View.GONE);
    }

    @Override
    public void setUrlImgenPassword(String url) {
       try {
           Glide.with(this)
                   .load(url)
                   .apply(Utils.getGlideRequestOptions(R.drawable.ic_account_circle))
                   .into(imgUsuarioPassword);
       }
       catch (Exception e){e.getStackTrace();}

    }


    @Override
    public void setNombrePersona(String nombre) {
        txtNombreUsuario.setText(nombre);
    }
    @Override
    public void setUserName(String usuario) {
        txtUsuarioPassword.setText(usuario);
    }


    @Override
    public void onErroUsuario(String error) {
        edittextUsername.requestFocus();
        edittextUsername.setSelected(true);
        edittextUsername.setError(error);
    }

    @Override
    public void onErrorPassword(String error) {
        edittextPassword.requestFocus();
        edittextPassword.setSelected(true);
        edittextPassword.setError(error);
    }

    @Override
    public void onErrorCorreo(String error) {
        edittextCorreo.requestFocus();
        edittextCorreo.setSelected(true);
        edittextCorreo.setError(error);
    }

    @Override
    public void onErrorDni(String error) {
        editttextDni.requestFocus();
        editttextDni.setSelected(true);
        editttextDni.setError(error);
    }

    @Override
    public void onInvalitedCorreo(String error) {
        edittextCorreo.requestFocus();
        edittextCorreo.setSelected(true);
        edittextCorreo.setError(error);
    }

    @Override
    public void onInvalitedPassword(String error) {
        edittextPassword.requestFocus();
        edittextPassword.setSelected(true);
        edittextPassword.setError(error);
    }

    @Override
    public void onCredencialesIncorrectos(String error) {
        edittextUsername.requestFocus();
        edittextUsername.setSelected(true);
        edittextUsername.setError(error);
    }

    @Override
    public void onInvalitedDni(String error) {
        editttextDni.requestFocus();
        editttextDni.setSelected(true);
        editttextDni.setError(error);
    }

    @Override
    public void onCancelarTodosLlamadasHTTPS() {
        apiRetrofit.cancelAll();
    }

    @Override
    public void onSetTextoBtnQuitarUsuario(String nombre) {
        btnQuitarUsuario.setText(nombre);
    }

    @Override
    public void showAtnAtrasLstUsu() {
        btnAtrasLstUsu.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidebtnAtrasLstUsu() {
        btnAtrasLstUsu.setVisibility(View.GONE);
    }

    @Override
    public void validadoUser(String user) {
        txtUsuarioPassword.setText(user);
    }

    @Override
    public void clearFocus() {
        try {
            edittextPassword.clearFocus();
            edittextUsername.clearFocus();
            edittextPassword.setText("");
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edittextPassword.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(edittextUsername.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        apiRetrofit = null;
        this.loginPreferentRepository = null;
    }


    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        boolean handled = false;
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            int i = textView.getId();
            if (i == R.id.edittext_password) {
                onBtnSiguientePassword();
            } else if (i == R.id.edittext_username) {
                onBtnSiguienteUser();
            } else if (i == R.id.edittext_correo){
                onBtnSiguienteCorreo();
            }else if (i == R.id.edittext_dni){
                onBtnSiguienteDni();
            }/*else if (i == R.id.edittext_password_user){
                onBtnSiguientePasswordUsuario();
            }*/
            handled = true;
        }
        return handled;

    }



    @Override
    public void getTodosUsuarios(Callback<List<PersonaUi>> usuarioCallback) {
        if (loginPreferentRepository != null)
            loginPreferentRepository.getTodosUsuarios(usuarioCallback);
    }

    @Override
    public void guardarUsuario(PersonaUi usuarioUi, Callback<Boolean> usuarioCallback) {
        if (loginPreferentRepository != null)
            loginPreferentRepository.guardarUsuario(usuarioUi, usuarioCallback);
    }

    @Override
    public void eliminarUsuario(PersonaUi usuarioUi, Callback<Boolean> usuarioCallback) {
        if (loginPreferentRepository != null)
            loginPreferentRepository.eliminarUsuario(usuarioUi, usuarioCallback);
    }

    @Override
    public void elimarTodosUsuario(Callback<Boolean> usuarioCallback) {
        if (loginPreferentRepository != null)
            loginPreferentRepository.elimarTodosUsuario(usuarioCallback);
    }

    @Override
    public void onClickPersona(PersonaUi personaUi) {
        presenter.onClickUsuario(personaUi);
    }

    @Override
    public void hideKeyBoard() {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edittextUsername.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onClickedBtnQuitarUsuario() {
        presenter.onClickedBtnQuitarUsuario();
    }

    public void onClickedBtnAtrasListUsua() {
        presenter.onClickedBtnAtrasListUsua();
    }

    public void onclickedrecPasswd(){
        presenter.onConfirmacionPassword();
    }

    public void onClickedBtnClosePassword() {
        presenter.onClickedBtnClosePassword();
    }

    public void onBtnSiguienteUser() {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edittextUsername.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        presenter.onBtnSiguienteUser(edittextUsername.getText().toString(), edittextPasswordUser.getText().toString());
    }

    public void onBtnSiguienteDni(){
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editttextDni.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        presenter.onBtnSiguienteDni(edittextUsername.getText().toString(), edittextPasswordUser.getText().toString(), edittextCorreo.getText().toString(), editttextDni.getText().toString());
    }

    public void onBtnAtrasCorreo(){
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edittextCorreo.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        presenter.onClickedBtnAtrasCorreo();
    }

    public void onBtnAtrasDni(){
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editttextDni.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        presenter.onClickedBtnAtrasDni();
    }

    /*public void onBtnSiguientePasswordUsuario(){
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edittextPasswordUser.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        presenter.onBtnSiguientePasswordUsuario(edittextUsername.getText().toString(), edittextPasswordUser.getText().toString());
    }*/

    public void onBtnSiguientePassword(){
        presenter.onBtnSiguientePassword(edittextPassword.getText().toString());
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edittextPassword.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBtnSiguienteCorreo(){
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edittextCorreo.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        presenter.onBtnSiguienteCorreo(edittextUsername.getText().toString(), edittextPasswordUser.getText().toString(), edittextCorreo.getText().toString());
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_quitar_usuario)onClickedBtnQuitarUsuario();
        if(v.getId()==R.id.btn_atras_lst_usu)onClickedBtnAtrasListUsua();
        if(v.getId()==R.id.txtrecPassw)onclickedrecPasswd();
        if(v.getId()==R.id.btn_close_password)onClickedBtnClosePassword();
        //if(v.getId()==R.id.btn_siguiente_password_user)onBtnSiguientePasswordUsuario();
        if(v.getId()==R.id.btn_siguiente_password)onBtnSiguientePassword();
        if(v.getId()==R.id.btn_siguiente_user)onBtnSiguienteUser();
        if (v.getId()==R.id.btn_siguiente_correo)onBtnSiguienteCorreo();
        if (v.getId()==R.id.btn_siguiente_dni)onBtnSiguienteDni();
        if (v.getId()==R.id.btn_atras_correo)onBtnAtrasCorreo();
        if (v.getId()==R.id.btn_atras_dni)onBtnAtrasDni();
        if (v.getId()==R.id.btn_atras_lst_usu_pass)onClickedBtnAtrasListUsua();
    }


    @Override
    public void onErrorPasswordUsuario(String string) {
        edittextPasswordUser.setError(string);
        editttextDni.requestFocus();
        editttextDni.setSelected(true);
    }

    public static void enableDisableView(View view, boolean enabled) {
        view.setEnabled(enabled);
        if ( view instanceof ViewGroup ) {
            ViewGroup group = (ViewGroup)view;

            for ( int idx = 0 ; idx < group.getChildCount() ; idx++ ) {
                enableDisableView(group.getChildAt(idx), enabled);
            }
        }
    }

    @Override
    public void disabledOnClick() {
        enableDisableView(groupPageUser, false);
    }

    @Override
    public void enableOnClick() {
        enableDisableView(groupPageUser, true);
    }

    @Override
    public void setUrlImgenInstitucion(String imagenUrl) {
        Glide.with(this)
                .load(imagenUrl)
                .apply(new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imgInstitucion);
    }
}
