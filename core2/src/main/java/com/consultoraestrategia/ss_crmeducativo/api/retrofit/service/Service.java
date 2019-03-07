package com.consultoraestrategia.ss_crmeducativo.api.retrofit.service;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametroChangeData;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametroChangeUser;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametroChangeAdminService;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametrosExportar;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametrosExportarGlobal;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametrosImportar;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros.ParametroLogin;
import com.consultoraestrategia.ss_crmeducativo.entities.AdminService;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
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
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosEnvioAsistencia;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosSilaboEventoEnvio;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEObtenerDatosLogin;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosTareasRecursos;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by SCIEV on 24/07/2018.
 */

public interface Service {
    @POST(" ")
    Call<RestApiResponse<BEDatosRubroEvaluacionProceso>> flst_ObtenerRubroEvaluacionProceso(@Body RequestBody body);
    @POST(" ")
    Call<RestApiResponse<BEDatosRubroEvaluacionProceso>> flst_ObtenerRubroEvaluacionProceso(@Body ApiRetrofit.ApiRequestBody<ParametroLogin> body);
    @POST(" ")
    Call<RestApiResponse<BEObtenerDatosLogin>> flst_ObtenerDatosLogin(@Body ApiRetrofit.ApiRequestBody<ParametroLogin> body);
    @POST(" ")
    Call<RestApiResponse<GEDatosSilaboEventoEnvio>> flst_ObtenerSilaboEvento(@Body ApiRetrofit.ApiRequestBody<ParametroLogin> body);
    @POST(" ")
    Call<RestApiResponse<BEDatosEvaluacionResultado>> flst_ObtenerRubroEvaluacionResultado(@Body ApiRetrofit.ApiRequestBody<ParametroLogin> body);
    @POST(" ")
    Call<RestApiResponse<BEDatosEnvioTipoNota>> flst_ObtenerTipoNota(@Body ApiRetrofit.ApiRequestBody<ParametroLogin> body);
    @POST(" ")
    Call<RestApiResponse<BEDatosEnvioMensajeria>> flst_ObtenerMensajeria(@Body ApiRetrofit.ApiRequestBody<ParametroLogin> body);
    @POST(" ")
    Call<RestApiResponse<BEDatosEnvioHorario>> flst_ObtenerHorario(@Body ApiRetrofit.ApiRequestBody<ParametroLogin> body);
    @POST(" ")
    Call<RestApiResponse<BEDatosEnvioGrupo>> flst_ObtenerGrupo(@Body ApiRetrofit.ApiRequestBody<ParametroLogin> body);
    @POST(" ")
    Call<RestApiResponse<BEDatosEnvioAsistencia>> flst_ObtenerAsistencia(@Body ApiRetrofit.ApiRequestBody<ParametroLogin> body);
    @POST(" ")
    Call<RestApiResponse<BEDatosCargaAcademica>> flst_ObtenerCargaAcademica(@Body ApiRetrofit.ApiRequestBody<ParametroLogin> body);
    @POST(" ")
    Call<RestApiResponse<Long>> fins_ListarSesion_FechaActualizacion(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData> body);
    @POST(" ")
    Call<RestApiResponse<Long>> fins_ListarRubroEvaluacionProceso_FechaActualizacion(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData> body);
    @POST(" ")
    Call<RestApiResponse<Long>> fins_ListarGrupoEquipo_FechaActualizacion(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData> body);
    @POST(" ")
    Call<RestApiResponse<BEDatosSesionAprendizaje>> flst_ObtenerSesionByUnidad(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<GEDatosRubroEvaluacionProceso>> fins_ListarRubroEvaluacionProcesoBYCALENDARIOPERIODO(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDatosEnvioGrupo>> fins_ListarGruposBYUsuario(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDatosCargaAcademica>> flst_ObtenerCalendario_Acad_Per_PerDet(@Body ApiRetrofit.ApiRequestBody<ParametroChangeData> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<Persona>> flst_ObtenerPersona(@Body ApiRetrofit.ApiRequestBody<ParametroChangeUser> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<AdminService>> f_BuscarUsuarioCent(@Body ApiRetrofit.ApiRequestBody<ParametroChangeAdminService> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<Usuario>> fobj_ObtenerUsuario(@Body ApiRetrofit.ApiRequestBody<ParametroChangeUser> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<List<Persona>>> flst_ObtenerPersonaByCorreoGmail(@Body ApiRetrofit.ApiRequestBody<ParametroChangeUser> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<GEDatosEnvioAsistencia>> fins_ListarEnvioAsistencia(@Body ApiRetrofit.ApiRequestBody<ParametrosImportar<BEVariables>> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BERespuesta>> fins_GuardarGrupoEquipo_Android(@Body ApiRetrofit.ApiRequestBody<ParametrosExportar<BEDatosEnvioGrupo>> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDatosEnvioGrupo>> fins_ListarGrupoEquipo_Equipo_EquiIntegrante(@Body ApiRetrofit.ApiRequestBody<ParametrosImportar<BEVariables>> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BERespuesta>> fins_GuardarMensajesProcesoAndroid(@Body ApiRetrofit.ApiRequestBody<ParametrosExportar<BEDatosEnvioMensajeria>> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDatosEnvioTipoNota>> fins_ListarTipoNotas(@Body ApiRetrofit.ApiRequestBody<ParametrosImportar<BEVariables>> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDatosEvaluacionResultado>> fins_ListarRubroEvaluacionResultado(@Body ApiRetrofit.ApiRequestBody<ParametrosImportar<BEVariables>> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BERespuesta>> fins_GuardarSesion_Android(@Body ApiRetrofit.ApiRequestBody<ParametrosExportar<BEDatosSesionAprendizaje>> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEDatosSesionAprendizaje>> fins_ListarSesiones(@Body ApiRetrofit.ApiRequestBody<ParametrosImportar<BEVariables>> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BERespuesta>> fins_GuardarTareRecursosAndroid(@Body ApiRetrofit.ApiRequestBody<ParametrosExportar<GEDatosTareasRecursos>> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<GEDatosTareasRecursos>> fins_ListarTareRecursos(@Body ApiRetrofit.ApiRequestBody<ParametrosImportar<BEVariables>> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BERespuesta>> fins_GuardarAsistencia_Android(@Body ApiRetrofit.ApiRequestBody<ParametrosExportar<GEDatosEnvioAsistencia>> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BERespuesta>> fins_GuardarRubroEvaluacionProcesoAndroid(@Body ApiRetrofit.ApiRequestBody<ParametrosExportar<GEDatosRubroEvaluacionProceso>> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<GEDatosRubroEvaluacionProceso>> fins_ListarRubroEvaluacionProceso(@Body ApiRetrofit.ApiRequestBody<ParametrosImportar<BEVariables>> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BEObtenerDatosLogin>> flst_Obtenerlistado_Personas(@Body ApiRetrofit.ApiRequestBody<ParametroLogin> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BERespuesta>> fins_GuardarMensajeria(@Body ApiRetrofit.ApiRequestBody<ParametrosExportar<BEDatosEnvioMensajeria>> apiRequestBody);
    @POST(" ")
    @Streaming
    Call<ResponseBody> flst_ObtenerRubroEvaluacionProceso2(@Body ApiRetrofit.ApiRequestBody<ParametroLogin> apiRequestBody);
    @POST(" ")
    Call<RestApiResponse<BERespuesta>> fins_GuardarEntidades_Global(@Body ApiRetrofit.ApiRequestBody<ParametrosExportarGlobal> apiRequestBody);
    @POST(" ")
    Call<ResponseBody> fins_GuardarEntidades_Global2(@Body ApiRetrofit.ApiRequestBody<ParametrosExportarGlobal> apiRequestBody);
    @POST(" ")
    @Multipart
    Call<String> uploadFile(@Part() MultipartBody.Part file, @Part("name") RequestBody name,  @Part("optionTipos") RequestBody tipo1, @Part("option") RequestBody tipo2);
    @Streaming
    @GET
    Call<ResponseBody> downloadFileByUrl(@Url String fileUrl);
}
