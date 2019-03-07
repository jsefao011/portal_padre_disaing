/* JSON API for android application [Web Service] */
package com.consultoraestrategia.ss_crmeducativo.api.retrofit;

import android.text.TextUtils;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametroChangeAdminService;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametroChangeData;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametroChangeUser;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametroLogin;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametrosExportar;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametrosExportarGlobal;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametrosImportar;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.UsuarioAdminService;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.service.Service;
import com.consultoraestrategia.ss_crmeducativo.entities.AdminService;
import com.consultoraestrategia.ss_crmeducativo.entities.GlobalSettings;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosCargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioAsistencia;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioGrupo;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioHorario;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioMensajeria;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioTipoNota;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEvaluacionResultado;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosSesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEGuardarEntidadesGlobal;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosEnvioAsistencia;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosSilaboEventoEnvio;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEObtenerDatosLogin;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosTareasRecursos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Multipart;

public class ApiRetrofit {
    private static final String TAG = "RestAPI";
    public static final String REMOTE_URL = "http://crmeducativo.consultoraestrategia.com/CRMMovil/PortalAcadMovil.ashx";

    private String url = REMOTE_URL;
    private static ApiRetrofit instance = null;
    private Retrofit retrofit;
    private Service service;
    private OkHttpClient okHttpClient;

    public static ApiRetrofit getInstance() {
        if (instance == null) {
            instance = new ApiRetrofit();
        }

        instance.updateServerUrl();
        instance.initialize();
        return instance;
    }


    private ApiRetrofit() {
    }

    public void updateServerUrl(){
        String serverUrl = GlobalSettings.getServerUrl();
        if (!TextUtils.isEmpty(serverUrl)) {
            this.url = serverUrl;
        }
    }


    public void initialize(){
        Log.d(TAG,url);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        setTime(30,30,30,TimeUnit.SECONDS);

        this.retrofit  = new retrofit2.Retrofit.Builder()
                .baseUrl(this.url.trim() + "/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        this.service = retrofit.create(Service.class);
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public void setTime(long connectTimeout, long writeTimeout, long readTimeout, TimeUnit unit) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder
                .connectTimeout(connectTimeout, unit) // connect timeout
                .writeTimeout(writeTimeout, unit) // write timeout
                .readTimeout(readTimeout, unit);
                // read timeout
        okHttpClient = builder.build();
    }

    public class ApiRequestBody<T extends Parameters>{
        @SerializedName("interface")
        String _interface = "RestAPI";
        String method;
        T parameters;

        public ApiRequestBody(String method, T parameters) {
            this.method = method;
            this.parameters = parameters;
        }

        public String get_interface() {
            return _interface;
        }

        public void set_interface(String _interface) {
            this._interface = _interface;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public T getParameters() {
            return parameters;
        }

        public void setParameters(T parameters) {
            this.parameters = parameters;
        }

        @Override
        public String toString() {
            return "ApiRequestBody{" +
                    "_interface='" + _interface + '\'' +
                    ", method='" + method + '\'' +
                    ", parameters=" + parameters +
                    '}';
        }
    }

    public static abstract class Parameters{}

    public Call<RestApiResponse<BEDatosRubroEvaluacionProceso>> flst_ObtenerRubroEvaluacionProcesoPrueba(int usuarioId) throws Exception {
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","RestAPI");
        o.put("method", "flst_ObtenerRubroEvaluacionProceso");
        p.put("vint_UsuarioId", usuarioId);
        o.put("parameters", p);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), o.toString());
        return service.flst_ObtenerRubroEvaluacionProceso(body);
    }
    
    public Call<RestApiResponse<BEDatosRubroEvaluacionProceso>> flst_ObtenerRubroEvaluacionProceso(int usuarioId) throws Exception {
        ParametroLogin parametroLogin = new ParametroLogin();
        parametroLogin.setUsuarioId(usuarioId);
        ApiRequestBody<ParametroLogin> apiRequestBody = new ApiRequestBody<>("flst_ObtenerRubroEvaluacionProceso",parametroLogin);
        return service.flst_ObtenerRubroEvaluacionProceso(apiRequestBody);
    }

    public Call<RestApiResponse<BEObtenerDatosLogin>> flst_ObtenerDatosLogin(int usuarioId) throws Exception {
        ParametroLogin parametroLogin = new ParametroLogin();
        parametroLogin.setUsuarioId(usuarioId);
        ApiRequestBody<ParametroLogin> apiRequestBody = new ApiRequestBody<>("flst_ObtenerDatosLogin",parametroLogin);
        return service.flst_ObtenerDatosLogin(apiRequestBody);
    }

    public Call<RestApiResponse<GEDatosSilaboEventoEnvio>> flst_ObtenerSilaboEvento(int usuarioId) throws Exception {
        ParametroLogin parametroLogin = new ParametroLogin();
        parametroLogin.setUsuarioId(usuarioId);
        ApiRequestBody<ParametroLogin> apiRequestBody = new ApiRequestBody<>("flst_ObtenerSilaboEvento",parametroLogin);
        return service.flst_ObtenerSilaboEvento(apiRequestBody);
    }

    public Call<RestApiResponse<BEDatosEvaluacionResultado>> flst_ObtenerRubroEvaluacionResultado(int usuarioId) throws Exception {
        ParametroLogin parametroLogin = new ParametroLogin();
        parametroLogin.setUsuarioId(usuarioId);
        ApiRequestBody<ParametroLogin> apiRequestBody = new ApiRequestBody<>("flst_ObtenerRubroEvaluacionResultado",parametroLogin);
        return service.flst_ObtenerRubroEvaluacionResultado(apiRequestBody);
    }

    public  Call<RestApiResponse<BEDatosEnvioTipoNota>> flst_ObtenerTipoNota(int usuarioId) throws Exception {
        ParametroLogin parametroLogin = new ParametroLogin();
        parametroLogin.setUsuarioId(usuarioId);
        ApiRequestBody<ParametroLogin> apiRequestBody = new ApiRequestBody<>("flst_ObtenerTipoNota",parametroLogin);
        return service.flst_ObtenerTipoNota(apiRequestBody);
    }

    public Call<RestApiResponse<BEDatosEnvioMensajeria>> flst_ObtenerMensajeria(int usuarioId) throws Exception {
        ParametroLogin parametroLogin = new ParametroLogin();
        parametroLogin.setUsuarioId(usuarioId);
        ApiRequestBody<ParametroLogin> apiRequestBody = new ApiRequestBody<>("flst_ObtenerMensajeria",parametroLogin);
        return service.flst_ObtenerMensajeria(apiRequestBody);
    }

    public Call<RestApiResponse<BEDatosEnvioHorario>> flst_ObtenerHorario(int usuarioId) throws Exception {
        ParametroLogin parametroLogin = new ParametroLogin();
        parametroLogin.setUsuarioId(usuarioId);
        ApiRequestBody<ParametroLogin> apiRequestBody = new ApiRequestBody<>("flst_ObtenerHorario",parametroLogin);
        return service.flst_ObtenerHorario(apiRequestBody);
    }

    public  Call<RestApiResponse<BEDatosEnvioGrupo>> flst_ObtenerGrupo(int usuarioId) throws Exception {
        ParametroLogin parametroLogin = new ParametroLogin();
        parametroLogin.setUsuarioId(usuarioId);
        ApiRequestBody<ParametroLogin> apiRequestBody = new ApiRequestBody<>("flst_ObtenerGrupo",parametroLogin);
        return service.flst_ObtenerGrupo(apiRequestBody);
    }

    public  Call<RestApiResponse<BEDatosEnvioAsistencia>> flst_ObtenerAsistencia(int usuarioId) throws Exception {
        ParametroLogin parametroLogin = new ParametroLogin();
        parametroLogin.setUsuarioId(usuarioId);
        ApiRequestBody<ParametroLogin> apiRequestBody = new ApiRequestBody<>("flst_ObtenerAsistencia",parametroLogin);
        return service.flst_ObtenerAsistencia(apiRequestBody);
    }

    public  Call<RestApiResponse<BEDatosCargaAcademica>> flst_ObtenerCargaAcademica(int usuarioId) throws Exception {
        ParametroLogin parametroLogin = new ParametroLogin();
        parametroLogin.setUsuarioId(usuarioId);
        ApiRequestBody<ParametroLogin> apiRequestBody = new ApiRequestBody<>("flst_ObtenerCargaAcademica",parametroLogin);
        return service.flst_ObtenerCargaAcademica(apiRequestBody);
    }

    public  Call<RestApiResponse<Long>> fins_ListarSesion_FechaActualizacion(int sesionEventoId){
        ParametroChangeData parametroChangeData = new ParametroChangeData();
        parametroChangeData.setSesionEventoId(sesionEventoId);
        ApiRequestBody< ParametroChangeData> apiRequestBody = new ApiRequestBody<>("fins_ListarSesion_FechaActualizacion",parametroChangeData);
        return service.fins_ListarSesion_FechaActualizacion(apiRequestBody);
    }

    public  Call<RestApiResponse<Long>> fins_ListarRubroEvaluacionProceso_FechaActualizacion(String rubroEvaluacionId) {
        ParametroChangeData parametroChangeData = new ParametroChangeData();
        parametroChangeData.setRubroEvaluacionId(rubroEvaluacionId);
        ApiRequestBody< ParametroChangeData> apiRequestBody = new ApiRequestBody<>("fins_ListarRubroEvaluacionProceso_FechaActualizacion",parametroChangeData);
        return service.fins_ListarRubroEvaluacionProceso_FechaActualizacion(apiRequestBody);
    }

    public  Call<RestApiResponse<Long>> fins_ListarGrupoEquipo_FechaActualizacion(String grupoEquipoId) {
        ParametroChangeData parametroChangeData = new ParametroChangeData();
        parametroChangeData.setGrupoEquipoId(grupoEquipoId);
        ApiRequestBody< ParametroChangeData> apiRequestBody = new ApiRequestBody<>("fins_ListarGrupoEquipo_FechaActualizacion",parametroChangeData);
        return service.fins_ListarGrupoEquipo_FechaActualizacion(apiRequestBody);
    }

    public  Call<RestApiResponse<BEDatosSesionAprendizaje>> flst_ObtenerSesionByUnidad(BEVariables beVariables) {
        ParametroChangeData parametroChangeData = new ParametroChangeData();
        parametroChangeData.setBeVariables(beVariables);//
        ApiRequestBody< ParametroChangeData> apiRequestBody = new ApiRequestBody<>("flst_ObtenerSesionByUnidad",parametroChangeData);
        return service.flst_ObtenerSesionByUnidad(apiRequestBody);
    }

    public Call<RestApiResponse<GEDatosRubroEvaluacionProceso>> fins_ListarRubroEvaluacionProcesoBYCALENDARIOPERIODO(BEVariables beVariables) {
        ParametroChangeData parametroChangeData = new ParametroChangeData();
        parametroChangeData.setBeVariables(beVariables);//
        ApiRequestBody< ParametroChangeData> apiRequestBody = new ApiRequestBody<>("fins_ListarRubroEvaluacionProcesoBYCALENDARIOPERIODO",parametroChangeData);
        return service.fins_ListarRubroEvaluacionProcesoBYCALENDARIOPERIODO(apiRequestBody);
    }

    public Call<RestApiResponse<BEDatosEnvioGrupo>> fins_ListarGruposBYUsuario(BEVariables beVariables) {
        ParametroChangeData parametroChangeData = new ParametroChangeData();
        parametroChangeData.setBeVariables(beVariables);//
        ApiRequestBody< ParametroChangeData> apiRequestBody = new ApiRequestBody<>("fins_ListarGruposBYUsuario",parametroChangeData);
        return service.fins_ListarGruposBYUsuario(apiRequestBody);
    }

    public  Call<RestApiResponse<BEDatosCargaAcademica>> flst_ObtenerCalendario_Acad_Per_PerDet(BEVariables beVariables) {
        ParametroChangeData parametroChangeData = new ParametroChangeData();
        parametroChangeData.setBeVariables(beVariables);//
        ApiRequestBody< ParametroChangeData> apiRequestBody = new ApiRequestBody<>("flst_ObtenerCalendario_Acad_Per_PerDet",parametroChangeData);
        return service.flst_ObtenerCalendario_Acad_Per_PerDet(apiRequestBody);
    }

    public  Call<RestApiResponse<Persona>> flst_ObtenerPersona(String usuario) {
        ParametroChangeUser parametroChangeUser = new ParametroChangeUser();
        parametroChangeUser.setUsuario(usuario);//
        Log.d(TAG,"url: " + url);
        Log.d(TAG, "json: " + parametroChangeUser.getUsuario());
        ApiRequestBody< ParametroChangeUser> apiRequestBody = new ApiRequestBody<>("flst_ObtenerPersona",parametroChangeUser);
        return service.flst_ObtenerPersona(apiRequestBody);
    }

    public Call<RestApiResponse<AdminService>> f_BuscarUsuarioCent(int opcion, String usuario, String passwordd, String correo, String numeroDocumento, String urlServidor) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Service getResponse = new Retrofit.Builder()
                .baseUrl(urlServidor+"/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(Service.class);

        UsuarioAdminService usuarioService = new UsuarioAdminService();
        usuarioService.setOpcion(opcion);
        usuarioService.setUsuario(usuario);
        usuarioService.setPasswordd(passwordd);
        usuarioService.setCorreo(correo);
        usuarioService.setNumDoc(numeroDocumento);

        ParametroChangeAdminService parametroChangeAdminService= new ParametroChangeAdminService(usuarioService);

        ApiRequestBody< ParametroChangeAdminService> apiRequestBody = new ApiRequestBody<>("f_BuscarUsuarioCent",parametroChangeAdminService);

        final Gson gsons = new Gson();
        final String representacionJSON = gsons.toJson(apiRequestBody);
        Log.d(TAG, "apiRequestBody : " + representacionJSON);
        return getResponse.f_BuscarUsuarioCent(apiRequestBody);
    }

    public Call<RestApiResponse<Usuario>> fobj_ObtenerUsuario(String parameterUserName, String parameterPassword) {
        ParametroChangeUser parametroChangeUser = new ParametroChangeUser();
        parametroChangeUser.setUsuarioLogin(parameterUserName);
        parametroChangeUser.setPasswordLogin(parameterPassword);
        Log.d(TAG,"url: " + url);
        Log.d(TAG, "json: " + parametroChangeUser.toString());
        ApiRequestBody< ParametroChangeUser> apiRequestBody = new ApiRequestBody<>("fobj_ObtenerUsuario",parametroChangeUser);
        return service.fobj_ObtenerUsuario(apiRequestBody);
    }

    public Call<RestApiResponse<Usuario>> fobj_ObtenerUsuario(int usuarioId) {
        ParametroChangeUser parametroChangeUser = new ParametroChangeUser();
        parametroChangeUser.setUsuarioId(usuarioId);//
        Log.d(TAG,"url: " + url);
        Log.d(TAG, "json: " + parametroChangeUser.toString());
        ApiRequestBody< ParametroChangeUser> apiRequestBody = new ApiRequestBody<>("fobj_ObtenerUsuario_By_Id",parametroChangeUser);
        return service.fobj_ObtenerUsuario(apiRequestBody);
    }

    public  Call<RestApiResponse<List<Persona>>> flst_ObtenerPersonaByCorreoGmail(int idusuario) {
        ParametroChangeUser parametroChangeUser = new ParametroChangeUser();
        parametroChangeUser.setIdpersona(idusuario);//
        Log.d(TAG,"url: " + url);
        Log.d(TAG, "json 2: " + parametroChangeUser.getIdpersona());
        ApiRequestBody< ParametroChangeUser> apiRequestBody = new ApiRequestBody<>("flst_ObtenerPersonaByCorreoGmail",parametroChangeUser);
        return service.flst_ObtenerPersonaByCorreoGmail(apiRequestBody);
    }


    public <V extends BEVariables> Call<RestApiResponse<GEDatosEnvioAsistencia>> fins_ListarEnvioAsistencia(V importar) {
        ParametrosImportar<BEVariables>parametrosImportar = new ParametrosImportar<>();
        parametrosImportar.setObjeto(importar);
        Log.d(TAG,"url: " + url);
        Log.d(TAG, "json : " + importar.toString());
        ApiRequestBody<ParametrosImportar<BEVariables>> apiRequestBody = new ApiRequestBody<>("fins_ListarEnvioAsistencia",parametrosImportar);
        return service.fins_ListarEnvioAsistencia(apiRequestBody);
    }

    public Call<RestApiResponse<BERespuesta>> fins_GuardarGrupoEquipo_Android(BEDatosEnvioGrupo item) {
        ParametrosExportar<BEDatosEnvioGrupo> parametrosExportar = new ParametrosExportar<>();
        parametrosExportar.setInsertarVariasEntidades(item);
        Log.d(TAG,"url: " + url);
        Log.d(TAG, "json : " + item.toString());
        ApiRequestBody<ParametrosExportar<BEDatosEnvioGrupo>> apiRequestBody = new ApiRequestBody<>("fins_GuardarGrupoEquipo_Android",parametrosExportar);
        return service.fins_GuardarGrupoEquipo_Android(apiRequestBody);

    }

    public <V extends BEVariables> Call<RestApiResponse<BEDatosEnvioGrupo>> fins_ListarGrupoEquipo_Equipo_EquiIntegrante(V importar) {

        ParametrosImportar<BEVariables>parametrosImportar = new ParametrosImportar<>();
        parametrosImportar.setObjeto(importar);
        Log.d(TAG,"url: " + url);
        Log.d(TAG, "json : " + importar.toString());
        ApiRequestBody<ParametrosImportar<BEVariables>> apiRequestBody = new ApiRequestBody<>("fins_ListarGrupoEquipo_Equipo_EquiIntegrante",parametrosImportar);
        return service.fins_ListarGrupoEquipo_Equipo_EquiIntegrante(apiRequestBody);
    }

    public Call<RestApiResponse<BERespuesta>> fins_GuardarMensajesProcesoAndroid(BEDatosEnvioMensajeria item) {
        ParametrosExportar<BEDatosEnvioMensajeria> parametrosExportar = new ParametrosExportar<>();
        parametrosExportar.setInsertarVariasEntidades(item);
        Log.d(TAG,"url: " + url);
        Log.d(TAG, "json : " + item.toString());
        ApiRequestBody<ParametrosExportar<BEDatosEnvioMensajeria>> apiRequestBody = new ApiRequestBody<>("fins_GuardarMensajesProcesoAndroid",parametrosExportar);
        return service.fins_GuardarMensajesProcesoAndroid(apiRequestBody);
    }


    public <V extends BEVariables> Call<RestApiResponse<BEDatosEnvioTipoNota>> fins_ListarTipoNotas(V importar) {
        ParametrosImportar<BEVariables>parametrosImportar = new ParametrosImportar<>();
        parametrosImportar.setObjeto(importar);
        Log.d(TAG,"url: " + url);
        Log.d(TAG, "json : " + importar.toString());
        ApiRequestBody<ParametrosImportar<BEVariables>> apiRequestBody = new ApiRequestBody<>("fins_ListarTipoNotas",parametrosImportar);
        return service.fins_ListarTipoNotas(apiRequestBody);
    }


    public <V extends BEVariables> Call<RestApiResponse<BEDatosEvaluacionResultado>> fins_ListarRubroEvaluacionResultado(V importar) {
        ParametrosImportar<BEVariables>parametrosImportar = new ParametrosImportar<>();
        parametrosImportar.setObjeto(importar);
        Log.d(TAG,"url: " + url);
        Log.d(TAG, "json : " + importar.toString());
        ApiRequestBody<ParametrosImportar<BEVariables>> apiRequestBody = new ApiRequestBody<>("fins_ListarRubroEvaluacionResultado",parametrosImportar);
        return service.fins_ListarRubroEvaluacionResultado(apiRequestBody);
    }

    public Call<RestApiResponse<BERespuesta>> fins_GuardarSesion_Android(BEDatosSesionAprendizaje item) {
        ParametrosExportar<BEDatosSesionAprendizaje> parametrosExportar = new ParametrosExportar<>();
        parametrosExportar.setInsertarVariasEntidades(item);
        Log.d(TAG,"url: " + url);
        Log.d(TAG, "json : " + item.toString());
        ApiRequestBody<ParametrosExportar<BEDatosSesionAprendizaje>> apiRequestBody = new ApiRequestBody<>("fins_GuardarSesion_Android",parametrosExportar);
        return service.fins_GuardarSesion_Android(apiRequestBody);

    }

    public <V extends BEVariables> Call<RestApiResponse<BEDatosSesionAprendizaje>> fins_ListarSesiones(V importar) {
        ParametrosImportar<BEVariables> parametrosImportar = new ParametrosImportar<>();
        parametrosImportar.setObjeto(importar);
        Log.d(TAG,"url: " + url);
        Log.d(TAG, "json : " + importar.toString());
        ApiRequestBody<ParametrosImportar<BEVariables>> apiRequestBody = new ApiRequestBody<>("fins_ListarSesiones",parametrosImportar);
        return service.fins_ListarSesiones(apiRequestBody);
    }

    public Call<RestApiResponse<BERespuesta>> fins_GuardarTareRecursosAndroid(GEDatosTareasRecursos item) {
        ParametrosExportar<GEDatosTareasRecursos> parametrosExportar = new ParametrosExportar<>();
        parametrosExportar.setInsertarVariasEntidades(item);
        Log.d(TAG,"url: " + url);
        ApiRequestBody<ParametrosExportar<GEDatosTareasRecursos>> apiRequestBody = new ApiRequestBody<>("fins_GuardarTareRecursosAndroid",parametrosExportar);
        return service.fins_GuardarTareRecursosAndroid(apiRequestBody);

    }

    public <V extends BEVariables> Call<RestApiResponse<GEDatosTareasRecursos>> fins_ListarTareRecursos(V importar) {
        ParametrosImportar<BEVariables>parametrosImportar = new ParametrosImportar<>();
        parametrosImportar.setObjeto(importar);
        Log.d(TAG,"url: " + url);
        ApiRequestBody<ParametrosImportar<BEVariables>> apiRequestBody = new ApiRequestBody<>("fins_ListarTareRecursos",parametrosImportar);
        final Gson gson = new Gson();
        final String representacionJSON = gson.toJson(apiRequestBody);
        Log.d(TAG, "apiRequestBody : " + representacionJSON);
        return service.fins_ListarTareRecursos(apiRequestBody);
    }

    public Call<RestApiResponse<BERespuesta>> fins_GuardarAsistencia_Android(GEDatosEnvioAsistencia item) {
        ParametrosExportar<GEDatosEnvioAsistencia> parametrosExportar = new ParametrosExportar<>();
        parametrosExportar.setInsertarVariasEntidades(item);
        Log.d(TAG,"url: " + url);
        Log.d(TAG, "json : " + item.toString());
        ApiRequestBody<ParametrosExportar<GEDatosEnvioAsistencia>> apiRequestBody = new ApiRequestBody<>("fins_GuardarAsistencia_Android",parametrosExportar);
        return service.fins_GuardarAsistencia_Android(apiRequestBody);
    }

    public Call<RestApiResponse<BERespuesta>> fins_GuardarRubroEvaluacionProcesoAndroid(GEDatosRubroEvaluacionProceso item) {
        ParametrosExportar<GEDatosRubroEvaluacionProceso> parametrosExportar = new ParametrosExportar<>();
        parametrosExportar.setInsertarVariasEntidades(item);
        Log.d(TAG,"url: " + url);
        Log.d(TAG, "json : " + item.toString());
        ApiRequestBody<ParametrosExportar<GEDatosRubroEvaluacionProceso>> apiRequestBody = new ApiRequestBody<>("fins_GuardarRubroEvaluacionProcesoAndroid",parametrosExportar);
        return service.fins_GuardarRubroEvaluacionProcesoAndroid(apiRequestBody);
    }

    public <V extends BEVariables> Call<RestApiResponse<GEDatosRubroEvaluacionProceso>> fins_ListarRubroEvaluacionProceso(V importar) {
        ParametrosImportar<BEVariables>parametrosImportar = new ParametrosImportar<>();
        parametrosImportar.setObjeto(importar);
        Log.d(TAG,"url: " + url);
        Log.d(TAG, "json : " + importar.toString());
        ApiRequestBody<ParametrosImportar<BEVariables>> apiRequestBody = new ApiRequestBody<>("fins_ListarRubroEvaluacionProceso",parametrosImportar);
        return service.fins_ListarRubroEvaluacionProceso(apiRequestBody);
    }

    public Call<RestApiResponse<BEObtenerDatosLogin>> flst_Obtenerlistado_Personas(int usuarioId) {
        ParametroLogin parametroLogin = new ParametroLogin();
        parametroLogin.setUsuarioId(usuarioId);
        Log.d(TAG,"url: " + url);
        Log.d(TAG, "usuarioId : " + usuarioId);
        ApiRequestBody<ParametroLogin> apiRequestBody = new ApiRequestBody<>("flst_Obtenerlistado_Personas",parametroLogin);
        return service.flst_Obtenerlistado_Personas(apiRequestBody);
    }

    public Call<RestApiResponse<BERespuesta>> fins_GuardarMensajeria(BEDatosEnvioMensajeria item) {
        ParametrosExportar<BEDatosEnvioMensajeria> parametrosExportar = new ParametrosExportar<>();
        parametrosExportar.setInsertarVariasEntidades(item);
        Log.d(TAG,"url: " + url);
        Log.d(TAG, "json : " + item.toString());
        ApiRequestBody<ParametrosExportar<BEDatosEnvioMensajeria>> apiRequestBody = new ApiRequestBody<>("fins_GuardarMensajeria",parametrosExportar);
        return service.fins_GuardarMensajeria(apiRequestBody);
    }

    public Call<RestApiResponse<BERespuesta>> fins_GuardarEntidades_Global(BEGuardarEntidadesGlobal beGuardarEntidadesGlobal) {

        ParametrosExportarGlobal parametrosExportarGlobal = new ParametrosExportarGlobal();
        parametrosExportarGlobal.setBeGuardarEntidadesGlobal(beGuardarEntidadesGlobal);
        Log.d(TAG,"url: " + url);
        ApiRequestBody<ParametrosExportarGlobal> apiRequestBody = new ApiRequestBody<>("fins_GuardarEntidades_Global",parametrosExportarGlobal);
        final Gson gson = new Gson();
        final String representacionJSON = gson.toJson(apiRequestBody);
        Log.d(TAG, "apiRequestBody : " + representacionJSON);
        return service.fins_GuardarEntidades_Global(apiRequestBody);
    }

    public Call<RestApiResponse<BERespuesta>> fins_GuardarEntidades_GlobalSimple(BEGuardarEntidadesGlobal beGuardarEntidadesGlobal) {
        ParametrosExportarGlobal parametrosExportarGlobal = new ParametrosExportarGlobal();
        parametrosExportarGlobal.setBeGuardarEntidadesGlobal(beGuardarEntidadesGlobal);
        Log.d(TAG,"url: " + url);
        ApiRequestBody<ParametrosExportarGlobal> apiRequestBody = new ApiRequestBody<>("fins_GuardarEntidades_Global_Simple",parametrosExportarGlobal);
        final Gson gson = new Gson();
        final String representacionJSON = gson.toJson(apiRequestBody);
        Log.d(TAG, "apiRequestBody : " + representacionJSON);
        return service.fins_GuardarEntidades_Global(apiRequestBody);

    }

    public Call<ResponseBody> fins_GuardarEntidades_Global2(BEGuardarEntidadesGlobal beGuardarEntidadesGlobal) {
        ParametrosExportarGlobal parametrosExportarGlobal = new ParametrosExportarGlobal();
        parametrosExportarGlobal.setBeGuardarEntidadesGlobal(beGuardarEntidadesGlobal);
        Log.d(TAG,"url: " + url);
        ApiRequestBody<ParametrosExportarGlobal> apiRequestBody = new ApiRequestBody<>("fins_GuardarEntidades_Global",parametrosExportarGlobal);
        final Gson gson = new Gson();
        final String representacionJSON = gson.toJson(apiRequestBody);
        Log.d(TAG, "apiRequestBody : " + representacionJSON);
        return service.fins_GuardarEntidades_Global2(apiRequestBody);
    }

    public ResponseBody downloadFileByUrl(String url) throws IOException {
        Call<ResponseBody> request = service.downloadFileByUrl(url);
        return request.execute().body();
    }


    public Call<String> uploadMultiFileJustificacion(String urlServidor, String path) {
        //final String BASE_URL = "http://192.168.1.110:3000/HandlerRespositorioArchivos.ashx";
        File file = new File(path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", "archivo", requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        RequestBody tipos = RequestBody.create(MediaType.parse("*/*"), "1");
        RequestBody tipos2 = RequestBody.create(MediaType.parse("*/*"), "1");

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder
                .connectTimeout(120, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(120, TimeUnit.SECONDS) // write timeout
                .readTimeout(120, TimeUnit.SECONDS);
        // read timeout
        OkHttpClient okHttpClient = builder.build();

        Service getResponse = new Retrofit.Builder()
                .baseUrl(urlServidor+"/")
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(Service.class);

        return getResponse.uploadFile(fileToUpload, filename, tipos, tipos2);

    }

    public Call<String> uploadMultiFileArchivo(String urlServidor, String path) {
        //final String BASE_URL = "http://192.168.1.110:3000/HandlerRespositorioArchivos.ashx";
        File file = new File(path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", "archivo", requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        RequestBody tipos = RequestBody.create(MediaType.parse("*/*"), "2");
        RequestBody tipos2 = RequestBody.create(MediaType.parse("*/*"), "1");

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder
                .connectTimeout(120, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(120, TimeUnit.SECONDS) // write timeout
                .readTimeout(120, TimeUnit.SECONDS);
        // read timeout
        OkHttpClient okHttpClient = builder.build();

        Service getResponse = new Retrofit.Builder()
                .baseUrl(urlServidor+"/")
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(Service.class);

        return getResponse.uploadFile(fileToUpload, filename, tipos, tipos2);

    }

    public Call<String> uploadMultiFileCaso(String urlServidor, String path) {
        //final String BASE_URL = "http://192.168.1.111:3000/RepositorioArchivos.ashx";
        File file = new File(path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", "archivo", requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        RequestBody tipos = RequestBody.create(MediaType.parse("*/*"), "3");
        RequestBody tipos2 = RequestBody.create(MediaType.parse("*/*"), "1");

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder
                .connectTimeout(120, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(120, TimeUnit.SECONDS) // write timeout
                .readTimeout(120, TimeUnit.SECONDS);
        // read timeout
        OkHttpClient okHttpClient = builder.build();

        Service getResponse = new Retrofit.Builder()
                .baseUrl(urlServidor+"/")
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(Service.class);

        return getResponse.uploadFile(fileToUpload, filename, tipos, tipos2);
    }

    public void cancelAll(){
        okHttpClient.dispatcher().cancelAll();
    }


    public static class Log{
        public static void d(String TAG, String message) {
            int maxLogSize = 2000;
            for(int i = 0; i <= message.length() / maxLogSize; i++) {
                int start = i * maxLogSize;
                int end = (i+1) * maxLogSize;
                end = end > message.length() ? message.length() : end;
                android.util.Log.d(TAG, message.substring(start, end));
            }
        }
    }

}


