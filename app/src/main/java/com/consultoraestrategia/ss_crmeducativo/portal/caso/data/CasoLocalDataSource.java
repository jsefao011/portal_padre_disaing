package com.consultoraestrategia.ss_crmeducativo.portal.caso.data;

import android.text.TextUtils;
import android.util.Log;
import android.widget.LinearLayout;

import com.consultoraestrategia.ss_crmeducativo.dao.curso.CursoDao;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.Caso;
import com.consultoraestrategia.ss_crmeducativo.entities.CasoArchivo;
import com.consultoraestrategia.ss_crmeducativo.entities.CasoArchivo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Caso_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.CursoCustom;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.CasoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.FechaUi;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.TipoHijoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.entities.TipoPadreUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioTipoFileU;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.Operator;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CasoLocalDataSource implements CasoDataSource {
   String TAG= CasoLocalDataSource.class.getSimpleName();
   CursoDao cursoDao;

    public CasoLocalDataSource(CursoDao cursoDao) {
        this.cursoDao = cursoDao;
    }

    @Override
    public void getAlumoCaso(int alumnoId,  int programaEducativoId, SucessCallback<AlumnoUi> alumnoUiSucessCallback) {

        Log.d(TAG, "programaEducativoId   " +programaEducativoId);
        Log.d(TAG, "alumnoId   " +alumnoId);
        AlumnoUi alumnoUi= new AlumnoUi();
        Persona alumno= SQLite.select().from(Persona.class)
                .where(Persona_Table.personaId.withTable().eq(alumnoId)).querySingle();
        alumnoUi.setAlumnoId(alumno.getPersonaId());
        Log.d(TAG, "caso size  " +alumnoId);

        List<Caso>casoList= SQLite.select().from(Caso.class)
                .where(Caso_Table.alumnoId.withTable().eq(alumno.getPersonaId()))
                .and(Caso_Table.programaEducativoId.withTable().eq(programaEducativoId)).queryList();
        int cantidadM=0;
        int cantidadD=0;
        List<CasoUi>casoUiList= new ArrayList<>();
        Log.d(TAG, "caso size  " +casoList.size());
        for(Caso caso: casoList){
            CasoUi casoUi= new CasoUi();
            //traer tipo hijo
            Tipos tipoconducta = SQLite.select(Utils.f_allcolumnTable(Tipos_Table.ALL_COLUMN_PROPERTIES)).from(Tipos.class)
                    .where(Tipos_Table.tipoId.withTable().eq(caso.getTipoCasoId())).querySingle();
            TipoHijoUi tipoHijoUi= new TipoHijoUi();
            if (tipoconducta != null) {
                switch (tipoconducta.getParentId()) {
                    case Tipos.MERITO:
                        tipoHijoUi.setTipoPadre(TipoHijoUi.TipoPadre.MERITO);
                        cantidadM++;
                        break;
                    case Tipos.DEMERITO:

                        tipoHijoUi.setTipoPadre(TipoHijoUi.TipoPadre.DEMERITO);
                        cantidadD++;
                        break;
                }
                tipoHijoUi.setNombre(tipoconducta.getNombre());
                tipoHijoUi.setTipo(tipoconducta.getTipoId());
                casoUi.setTipoHijoUi(tipoHijoUi);
            }


            casoUi.setId(caso.getKey());
            casoUi.setDescripcion(caso.getDescripcion());
            //convertir fecha de caso
            casoUi.setFechaUi(f_fecha_letras(caso.getFechaCaso()));
            //trare curso caso
            List<Integer >integerList= new ArrayList<>();
            integerList.add(caso.getCargaCursoId());
            List<CursoCustom> cursoCustomList= cursoDao.obtenerPorCargaCursos(integerList);
            for(CursoCustom cursoCustom:cursoCustomList){
                CursoUi cursoUi= new CursoUi();
                cursoUi.setId(cursoCustom.getCursoId());
                cursoUi.setNombre(cursoCustom.getNombre());
                casoUi.setCursoUi(cursoUi);
            }
            //trer recursos archivos
            List<CasoArchivo>casoArchivoList= SQLite.select()
                    .from(CasoArchivo.class)
                    .where(CasoArchivo_Table.casoId.withTable().eq(caso.getKey()))
                    .queryList();
            Log.d(TAG, "CAOS LIST "+ casoArchivoList.size());

            List<RepositorioFileUi>repositorioFileUiList= new ArrayList<>();
            for (CasoArchivo casoArchivo : casoArchivoList) {
                RepositorioFileUi archivoUi = new RepositorioFileUi();
                archivoUi.setArchivoId(casoArchivo.getArchivoCasoId());
                archivoUi.setNombreArchivo(casoArchivo.getNombre());
                archivoUi.setNombreRecurso(casoArchivo.getNombre());
                switch (casoArchivo.getTipoId()) {
                    case CasoArchivo.TIPO_IMAGEN:
                        archivoUi.setTipoFileU(RepositorioTipoFileU.IMAGEN);
                        break;
                    case CasoArchivo.TIPO_VIDEO:
                        archivoUi.setTipoFileU(RepositorioTipoFileU.VIDEO);
                        break;
                    case CasoArchivo.TIPO_DOCUMENTO:
                        archivoUi.setTipoFileU(RepositorioTipoFileU.DOCUMENTO);
                        break;
                    case CasoArchivo.TIPO_AUDIO:
                        archivoUi.setTipoFileU(RepositorioTipoFileU.AUDIO);
                        break;
                    case CasoArchivo.TIPO_HOJA_CALCULO:
                        archivoUi.setTipoFileU(RepositorioTipoFileU.HOJA_CALCULO);
                        break;
                    case CasoArchivo.TIPO_DIAPOSITIVA:
                        archivoUi.setTipoFileU(RepositorioTipoFileU.DIAPOSITIVA);
                        break;
                    case CasoArchivo.TIPO_PDF:
                        archivoUi.setTipoFileU(RepositorioTipoFileU.PDF);
                        break;
                }

                archivoUi.setUrl(casoArchivo.getPath());
                archivoUi.setPath(casoArchivo.getLocalPath());
                if (TextUtils.isEmpty(casoArchivo.getLocalPath())) {
                    archivoUi.setEstadoFileU(RepositorioEstadoFileU.SIN_DESCARGAR);
                } else {
                    archivoUi.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
                }
                archivoUi.setSelect(true);
                repositorioFileUiList.add(archivoUi);

            }
            casoUi.setRepositorioFileUiList(repositorioFileUiList);
            casoUiList.add(casoUi);
        }
        alumnoUi.setCasoUiList(casoUiList);
        //Tipos padres
        List<TipoPadreUi>tipoPadreUiList= new ArrayList<>();
        if(casoList.size()>0){
            TipoPadreUi tipoPadreUiMerito= new TipoPadreUi();
            tipoPadreUiMerito.setNombre("Merito");
            tipoPadreUiMerito.setCantidad(cantidadM);
            tipoPadreUiMerito.setPorcentaje(cantidadM * 100 / casoList.size());
            tipoPadreUiList.add(tipoPadreUiMerito);
            TipoPadreUi tipoPadreUiDemerito= new TipoPadreUi();
            tipoPadreUiDemerito.setNombre("Demerito");
            tipoPadreUiDemerito.setCantidad(cantidadD);
            tipoPadreUiDemerito.setPorcentaje(cantidadD * 100 / casoList.size());
            tipoPadreUiList.add(tipoPadreUiDemerito);

        }
        alumnoUi.setTipoPadreUiList(tipoPadreUiList);
        alumnoUiSucessCallback.onLoad(true, alumnoUi);

    }

    @Override
    public void updateSucessDowload(String archivoId, String path, CallbackSuccess callback) {
        CasoArchivo archivo = SQLite.select()
                .from(CasoArchivo.class)
                .where(CasoArchivo_Table.key.eq(archivoId))
                .querySingle();
        if (archivo != null) {
            archivo.setLocalPath(path);
            boolean success = archivo.save();
            callback.onLoad(success);
        } else {
            callback.onLoad(false);
        }
    }

    public  FechaUi f_fecha_letras(long timesTamp) {

        FechaUi fechaUi= new FechaUi();
        String[] vobj_days = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
        String[] vobj_Meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        Date date = new Date(timesTamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        fechaUi.setDia(dayOfMonth);
        fechaUi.setDiaSemana( vobj_days[dayOfWeek - 1]);
        fechaUi.setMes(vobj_Meses[month]);
        return fechaUi;
    }
}
