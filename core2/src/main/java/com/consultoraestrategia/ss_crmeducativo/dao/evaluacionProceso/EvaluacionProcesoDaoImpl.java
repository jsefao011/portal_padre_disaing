package com.consultoraestrategia.ss_crmeducativo.dao.evaluacionProceso;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.escalaEvaluacionDao.EscalaEvaluacionDao;
import com.consultoraestrategia.ss_crmeducativo.dao.tipoNotaDao.TipoNotaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionDao.TiposDao;
import com.consultoraestrategia.ss_crmeducativo.dao.personaDao.PersonaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvalRNPFormula.RubroEvalRNPFormulaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosRubroEvaluacionProceso.local.BEDatosRubroEvaluacionProcesoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kike on 14/05/2018.
 */

public class EvaluacionProcesoDaoImpl extends BaseDaoImpl<EvaluacionProcesoC,EvaluacionProcesoC_Table> implements EvaluacionProcesoDao {

    private static EvaluacionProcesoDao mInstance;
    private PersonaDao personaDao;
    private RubroEvalRNPFormulaDao rubroEvalRNPFormulaDao;
    private EscalaEvaluacionDao escalaEvaluacionDao;
    private TipoNotaDao tipoNotaDao;
    private TiposDao tiposDao;
    private final static String TAG = EvaluacionProcesoDaoImpl.class.getSimpleName();

    public EvaluacionProcesoDaoImpl(PersonaDao personaDao, RubroEvalRNPFormulaDao rubroEvalRNPFormulaDao, EscalaEvaluacionDao escalaEvaluacionDao, TipoNotaDao tipoNotaDao, TiposDao tiposDao) {
        this.personaDao = personaDao;
        this.rubroEvalRNPFormulaDao = rubroEvalRNPFormulaDao;
        this.escalaEvaluacionDao = escalaEvaluacionDao;
        this.tipoNotaDao = tipoNotaDao;
        this.tiposDao = tiposDao;
    }

    public static EvaluacionProcesoDao getInstance(PersonaDao personaDao, RubroEvalRNPFormulaDao rubroEvalRNPFormulaDao, EscalaEvaluacionDao escalaEvaluacionDao, TipoNotaDao tipoNotaDao, TiposDao tiposDao) {
        if (mInstance == null) {
            mInstance = new EvaluacionProcesoDaoImpl(personaDao, rubroEvalRNPFormulaDao, escalaEvaluacionDao, tipoNotaDao, tiposDao);
        }
        return mInstance;
    }

    @Override
    public List<EvaluacionProcesoC> getListaEvaluacionProceso(String rubroProcesoKey,int personaKey) {
        return  SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(rubroProcesoKey))
                .and(EvaluacionProcesoC_Table.alumnoId.is(personaKey))
                .queryList();
    }

    @Override
    public EvaluacionProcesoC getEvaluacionProceso(String rubroProcesoKey, int personaKey) {
        return SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(rubroProcesoKey))
                .and(EvaluacionProcesoC_Table.alumnoId.eq(personaKey))
                .orderBy(EvaluacionProcesoC_Table.fechaAccion.desc()
                )
                .querySingle();
    }

    @Override
    public List<EvaluacionProcesoC> getListaEvaluacionProcesoRubroFormula(String rubroProcesKey, int personaKey) {
        return  SQLite.select()
                .from(EvaluacionProcesoC.class)
                .innerJoin(RubroEvalRNPFormulaC.class)
                .on(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable().eq(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable()))
                .innerJoin(RubroEvaluacionProcesoC.class)
                .on(RubroEvaluacionProcesoC_Table.key.withTable().eq(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable()))
                .where(EvaluacionProcesoC_Table.alumnoId.withTable().is(Integer.valueOf(personaKey)))
                .and(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable().is(rubroProcesKey))
                .queryList();
    }

    @Override
    protected Class<EvaluacionProcesoC> getEntityClass() {
        return EvaluacionProcesoC.class;
    }

    @Override
    protected Class<EvaluacionProcesoC_Table> getTableclass() {
        return EvaluacionProcesoC_Table.class;
    }

    public boolean crearEvaluacionProceso(String rubroEvalProcesoId ,int cargaCursoId){
        boolean success = true;
        try {
            List<Persona> personaList = personaDao.getAlumnosDeCargaCurso(cargaCursoId);
            for (Persona persona : personaList) {
                EvaluacionProcesoC evaluacionProceso = new EvaluacionProcesoC();
                evaluacionProceso.setRubroEvalProcesoId(rubroEvalProcesoId);
                evaluacionProceso.setAlumnoId(persona.getPersonaId());
                evaluacionProceso.setSyncFlag(EvaluacionProcesoC.FLAG_ADDED);
                boolean successEvalProcesoAlumn = evaluacionProceso.save();
                if (!successEvalProcesoAlumn) throw new Error("Error creating eval proceso for alumn!!!");
            }
        }catch (Exception e){
            success = false;
        }

        return success;
    }

    @Override
    public boolean crearEvaluacionProcesoFormula(String rubroEvalProcesoId, int cargaCursoId) {
        boolean success = true;
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        databaseWrapper.beginTransaction();
            List<Persona> personaList = personaDao.getAlumnosDeCargaCurso(cargaCursoId);
            boolean successEvalProcesoAlumn = false;
            boolean successEvaluacion = false;
            for (Persona persona : personaList) {
                EvaluacionProcesoC evaluacionProceso = new EvaluacionProcesoC();
                evaluacionProceso.setRubroEvalProcesoId(rubroEvalProcesoId);
                evaluacionProceso.setAlumnoId(persona.getPersonaId());
                evaluacionProceso.setSyncFlag(EvaluacionProcesoC.FLAG_ADDED);
                successEvalProcesoAlumn = evaluacionProceso.save();
                if (!successEvalProcesoAlumn){
                    success = false;
                    break;
                }
                successEvaluacion = evaluarRubroFormulaPersona(rubroEvalProcesoId, persona.getPersonaId());
                if (!successEvaluacion){
                    success = false;
                    break;
                }
            }
            if(success)databaseWrapper.setTransactionSuccessful();
            databaseWrapper.endTransaction();
        return success;
    }

    @Override
    public boolean evaluarRubroFormulaPersona(String rubroEvalProcesoId, int personaId){
        boolean success = true;
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();

            int TIPO_FORMULA_MEDIA_PONDERADA = 413;
            int TIPO_FORMULA_MEDIA_ARITMETICA = 414;
            int TIPO_FORMULA_SUMA = 415;
            int TIPO_FORMULA_MAXIMO = 416;
            int TIPO_FORMULA_MINIMO = 417;
            int TIPO_FORMULA_MODA = 479;

            RubroEvaluacionProcesoC rubroFormula = SQLite.select()
                    .from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProcesoC_Table.key.eq(rubroEvalProcesoId))
                    .querySingle();
                rubroFormula.setSyncFlag(BaseEntity.FLAG_UPDATED);
                rubroFormula.save();


            EvaluacionProcesoC evaluacionProcesoC = getEvaluacionProceso(rubroEvalProcesoId,personaId);

            EscalaEvaluacion escalaEvaluacion = escalaEvaluacionDao.getEscalaEvalPorTipoNota(rubroFormula.getTipoNotaId());
            Log.d(TAG, "escalaEvaluacion "+ escalaEvaluacion.getNombre());
            List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList = rubroEvalRNPFormulaDao.getListaRubroEvalRNPFormula(rubroEvalProcesoId);
            Log.d(TAG, "rubroEvalRNPFormulaCList "+ rubroEvalRNPFormulaCList.size());
            Log.d(TAG, "personaId "+ personaId);
            double notasFinales = 0D;
            if (rubroFormula.getTipoFormulaId() == TIPO_FORMULA_MEDIA_PONDERADA) {
                notasFinales = initMediaPonderada(escalaEvaluacion, rubroEvalRNPFormulaCList, personaId);
            } else if (rubroFormula.getTipoFormulaId() == TIPO_FORMULA_MEDIA_ARITMETICA) {
                notasFinales = initMediaArimetica(escalaEvaluacion, rubroEvalRNPFormulaCList, personaId);
            } else if (rubroFormula.getTipoFormulaId() == TIPO_FORMULA_SUMA) {
                notasFinales = initFormulaSuma(escalaEvaluacion, rubroEvalRNPFormulaCList, personaId);
            } else if (rubroFormula.getTipoFormulaId() == TIPO_FORMULA_MAXIMO) {
                notasFinales = initFormulaMaximo(escalaEvaluacion, rubroEvalRNPFormulaCList, personaId);
            } else if (rubroFormula.getTipoFormulaId() == TIPO_FORMULA_MINIMO) {
                notasFinales = initFormulaMinima(escalaEvaluacion, rubroEvalRNPFormulaCList, personaId);
            } else if (rubroFormula.getTipoFormulaId() == TIPO_FORMULA_MODA) {
                notasFinales = initFormulaModa(escalaEvaluacion, rubroEvalRNPFormulaCList, personaId);
            }

            //*region redondeo

                Tipos tipos = tiposDao.get(rubroFormula.getValorRedondeoId());
                Log.d(TAG,"tipos "+ tipos.getNombre() );
                if(tipos!=null && tipos.getTipoId() != 425){
                    double valorRedondeo = Double.valueOf(tipos.getNombre());
                    BigDecimal bigDecimal  = new BigDecimal(notasFinales);
                    long iPart = bigDecimal.longValue();
                    double fraccion = bigDecimal.remainder(BigDecimal.ONE).doubleValue();
                    //0.45  >= 0.25
                    if(fraccion > valorRedondeo){
                        notasFinales = iPart + 1D;
                    }else {
                        notasFinales = iPart;
                    }
                    Log.d(TAG,"Tansfromacion"+notasFinales );
                }


            //*endregion redondeo

            ValorTipoNotaC valorTipoNota = initTipoNotaValor(rubroFormula.getTipoNotaId(),notasFinales);
            evaluacionProcesoC.setNota(notasFinales);
            if(valorTipoNota != null)evaluacionProcesoC.setValorTipoNotaId(valorTipoNota.getValorTipoNotaId());
            evaluacionProcesoC.setCalendarioPeriodoId(rubroFormula.getCalendarioPeriodoId());
            evaluacionProcesoC.setSyncFlag(EvaluacionProcesoC.FLAG_UPDATED);
            evaluacionProcesoC.save();

            databaseWrapper.setTransactionSuccessful();
        } catch (Exception error){
            error.printStackTrace();
            success = false;
        }finally {
            databaseWrapper.endTransaction();
        }
        return success;
    }

    @Override
    public List<EvaluacionProcesoC> getEvaluacionProcesoRubrica(List<String> rubroEvalProcesoKeyList, List<Integer> personIdList) {
        return SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.in(rubroEvalProcesoKeyList))
                .and(EvaluacionProcesoC_Table.alumnoId.in(personIdList))
                .orderBy(EvaluacionProcesoC_Table.fechaAccion.desc())
                .queryList();
    }

    @Override
    public List<EvaluacionProcesoC> getEvaluacionProcesoRubricaEquipo(List<String> rubroEvalProcesoKeyList, List<String> equipoRubroKeys) {
        return SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.in(rubroEvalProcesoKeyList))
                .and(EvaluacionProcesoC_Table.equipoId.in(equipoRubroKeys))
                .orderBy(EvaluacionProcesoC_Table.fechaAccion.desc())
                .queryList();
    }

    private double initMediaPonderada(EscalaEvaluacion escalaEvaluacionPadre, List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList, int personaId) {
        double notaAcumulacion = 0;
        double notaPesoAcumulacion = 0;
        double pesoTotal = 0;
        double notass = 0;

        for (RubroEvalRNPFormulaC rnpFormula : rubroEvalRNPFormulaCList) {
            double notaValue = 0;
            try {
                EvaluacionProcesoC evaluacionProcesoC = getEvaluacionProceso(rnpFormula.getRubroEvaluacionSecId(), personaId);
                EscalaEvaluacion escalaEvaluacionAsociados = escalaEvaluacionDao.getEscalaEvalPorRubrosAsociados(rnpFormula.getRubroEvaluacionSecId());

                if (escalaEvaluacionAsociados == null) return 0D;
                //1.- valor de los hijos
                if (escalaEvaluacionAsociados != null && escalaEvaluacionPadre != null) {
                    notaValue= Utils.transformacionInvariante(escalaEvaluacionAsociados.getValorMinimo(), escalaEvaluacionAsociados.getValorMaximo(),
                            evaluacionProcesoC.getNota(), escalaEvaluacionPadre.getValorMinimo(), escalaEvaluacionPadre.getValorMaximo());
                }

                double pesoActual = rnpFormula.getPeso();
                notaAcumulacion += notaValue;
                notaPesoAcumulacion += notaValue * pesoActual;
                pesoTotal += pesoActual;
                notass = notaPesoAcumulacion / pesoTotal;
            }catch (Exception e){e.getStackTrace();
            e.getCause();}

        }
        return notass;
    }

    private double initMediaArimetica(EscalaEvaluacion escalaEvaluacionPadre, List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList, int personaId) {
        double notaAcumulacion = 0;
        double notass = 0;
        for (RubroEvalRNPFormulaC rnpFormula : rubroEvalRNPFormulaCList) {
            double notaValue = 0;
            EscalaEvaluacion escalaEvaluacionAsociados = escalaEvaluacionDao.getEscalaEvalPorRubrosAsociados(rnpFormula.getRubroEvaluacionSecId());
            EvaluacionProcesoC evaluacionProcesoC = getEvaluacionProceso(rnpFormula.getRubroEvaluacionSecId(), personaId);
            //1.- valor de los hijos
            if (escalaEvaluacionAsociados != null && escalaEvaluacionPadre != null) {
                notaValue = Utils.transformacionInvariante(escalaEvaluacionAsociados.getValorMinimo(), escalaEvaluacionAsociados.getValorMaximo(),
                        evaluacionProcesoC.getNota(), escalaEvaluacionPadre.getValorMinimo(), escalaEvaluacionPadre.getValorMaximo());
            }
            notaAcumulacion += notaValue;
            notass = notaAcumulacion / rubroEvalRNPFormulaCList.size();
        }
        return notass;
    }

    private double initFormulaSuma(EscalaEvaluacion escalaEvaluacionPadre, List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList, int personaId) {
        double notass = 0;
        for (RubroEvalRNPFormulaC rnpFormula : rubroEvalRNPFormulaCList) {
            double notaValue = 0;
            EscalaEvaluacion escalaEvaluacionAsociados = escalaEvaluacionDao.getEscalaEvalPorRubrosAsociados(rnpFormula.getRubroEvaluacionSecId());
            EvaluacionProcesoC evaluacionProcesoC = getEvaluacionProceso(rnpFormula.getRubroEvaluacionSecId(), personaId);
            //1.- valor de los hijos
            if (escalaEvaluacionAsociados != null && escalaEvaluacionPadre != null) {
                notaValue = Utils.transformacionInvariante(escalaEvaluacionAsociados.getValorMinimo(), escalaEvaluacionAsociados.getValorMaximo(),
                        evaluacionProcesoC.getNota(), escalaEvaluacionPadre.getValorMinimo(), escalaEvaluacionPadre.getValorMaximo());
            }

            notass += notaValue;
        }
        return notass;
    }

    private double initFormulaMinima(EscalaEvaluacion escalaEvaluacionPadre, List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList, int personaId) {
        double minimoValor = 0;
        double notass = 0;
        List<Double> list = new ArrayList<>();
        for (RubroEvalRNPFormulaC rnpFormula : rubroEvalRNPFormulaCList) {
            double notaValue = 0;
            EscalaEvaluacion escalaEvaluacionAsociados = escalaEvaluacionDao.getEscalaEvalPorRubrosAsociados(rnpFormula.getRubroEvaluacionSecId());
            EvaluacionProcesoC evaluacionProcesoC = getEvaluacionProceso(rnpFormula.getRubroEvaluacionSecId(), personaId);
            //1.- valor de los hijos
            if (escalaEvaluacionAsociados != null && escalaEvaluacionPadre != null) {
                notaValue = Utils.transformacionInvariante(escalaEvaluacionAsociados.getValorMinimo(), escalaEvaluacionAsociados.getValorMaximo(),
                        evaluacionProcesoC.getNota(), escalaEvaluacionPadre.getValorMinimo(), escalaEvaluacionPadre.getValorMaximo());
            }
            double notaValues = notaValue;
            list.add(notaValues);
            minimoValor = Collections.min(list);
            Log.d(TAG, "SIZEDOUBLE: " + list.size());
            notass = minimoValor;
        }

        return notass;
    }

    private double initFormulaMaximo(EscalaEvaluacion escalaEvaluacionPadre, List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList, int personaId) {
        double maximoValor = 0;
        double notass = 0;
        List<Double> list = new ArrayList<>();
        for (RubroEvalRNPFormulaC rnpFormula : rubroEvalRNPFormulaCList) {
            double notaValue = 0;

            EscalaEvaluacion escalaEvaluacionAsociados = escalaEvaluacionDao.getEscalaEvalPorRubrosAsociados(rnpFormula.getRubroEvaluacionSecId());
            EvaluacionProcesoC evaluacionProcesoC = getEvaluacionProceso(rnpFormula.getRubroEvaluacionSecId(), personaId);
            //1.- valor de los hijos
            if (escalaEvaluacionAsociados != null && escalaEvaluacionPadre != null) {
                notaValue = Utils.transformacionInvariante(escalaEvaluacionAsociados.getValorMinimo(), escalaEvaluacionAsociados.getValorMaximo(),
                        evaluacionProcesoC.getNota(), escalaEvaluacionPadre.getValorMinimo(), escalaEvaluacionPadre.getValorMaximo());
            }
            double notaValuea = notaValue;
            list.add(notaValuea);
            maximoValor = Collections.max(list);
            Log.d(TAG, "SIZEDOUBLE: " + list.size());
            notass = maximoValor;
        }
        return notass;
    }

    private double initFormulaModa(EscalaEvaluacion escalaEvaluacionPadre, List<RubroEvalRNPFormulaC> rubroEvalRNPFormulaCList, int personaId) {
        Integer integer[];
        double notass = 0;
        for (RubroEvalRNPFormulaC rnpFormula : rubroEvalRNPFormulaCList) {
            double notaValue = 0;
            EscalaEvaluacion escalaEvaluacionAsociados = escalaEvaluacionDao.getEscalaEvalPorRubrosAsociados(rnpFormula.getRubroEvaluacionSecId());
            EvaluacionProcesoC evaluacionProcesoC = getEvaluacionProceso(rnpFormula.getRubroEvaluacionSecId(), personaId);
            //1.- valor de los hijos
            if (escalaEvaluacionAsociados != null && escalaEvaluacionPadre != null) {
                notaValue = Utils.transformacionInvariante(escalaEvaluacionAsociados.getValorMinimo(), escalaEvaluacionAsociados.getValorMaximo(),
                        evaluacionProcesoC.getNota(), escalaEvaluacionPadre.getValorMinimo(), escalaEvaluacionPadre.getValorMaximo());
            }
            int notaRedondeada = (int) Math.round(notaValue);
            //  aDoubleMModa = new Double[]{notaValue};
            integer = new Integer[]{notaRedondeada};
            notass = Utils.moda(integer);
        }
        return notass;
    }

    private ValorTipoNotaC initTipoNotaValor(String tipoNotaId, double notass) {
        ValorTipoNotaC valorTipoNota = null;
        try {
            TipoNotaC tipoNotaC = tipoNotaDao.getTipoNotaConValores(tipoNotaId);
            List<ValorTipoNotaC> valorTipoNotaListRubroFinales = tipoNotaC.getValorTipoNotaList();
            switch (tipoNotaC.getTipoId()) {
                case TipoNotaC.SELECTOR_NUMERICO:
                    break;
                case TipoNotaC.SELECTOR_VALORES:
                    for (ValorTipoNotaC itemValorTipoNota : valorTipoNotaListRubroFinales) {
                        if (itemValorTipoNota.getLimiteInferior() <= notass && itemValorTipoNota.getLimiteSuperior() >= notass) {
                            valorTipoNota = itemValorTipoNota;
                        }
                    }
                    break;
                case TipoNotaC.SELECTOR_ICONOS:
                    for (ValorTipoNotaC itemValorTipoNota : valorTipoNotaListRubroFinales) {
                        if (itemValorTipoNota.getLimiteInferior() <= notass && itemValorTipoNota.getLimiteSuperior() >= notass) {
                            valorTipoNota = itemValorTipoNota;
                        }
                    }
                    break;
                default:
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return valorTipoNota;
    }
    public  boolean f_mediaDesviacionEstandar(String key) {
        boolean status=false;
        long count =SQLite.selectCountOf().from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.formulaSinc.eq(true)).count();
        Log.d(TAG, "evaluacionProcesoCList "+ count);
        if(count!=0){
          status= f_mediaDesviacionEstandarFormula(key);
          }
      else status=true;
       return status;
    }

    public  boolean f_mediaDesviacionEstandarFormula(String key) {
        List<Double>listNotas= new ArrayList<>();
        double media=0.0;
        List<EvaluacionProcesoC>evaluciones= SQLite.select().from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(key)).
                        queryList();

        for(EvaluacionProcesoC e: evaluciones){
            media+=e.getNota()/evaluciones.size();
            listNotas.add(e.getNota());
        }
        if(evaluciones!=null) {
            Log.d(TAG, "media " + media);
            double stdDev = Utils.stdDev(media, listNotas);
            Log.d(TAG, "desviacion " + stdDev);

            RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select().from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProcesoC_Table.key.withTable().eq(key)).querySingle();
            Log.d(TAG, "rubroEvaluacionProcesoC "+ rubroEvaluacionProcesoC.key);
            if (rubroEvaluacionProcesoC == null) return false;
            rubroEvaluacionProcesoC.setPromedio(Utils.formatearDecimales(media, 2));
            rubroEvaluacionProcesoC.setDesviacionEstandar(Utils.formatearDecimales(stdDev, 2));
            rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
            rubroEvaluacionProcesoC.update();
            return true;
        }
        return false;
    }
    public void onUpdateEvaluacionFormula() {
        Log.d(getClass().getSimpleName(), "onUpdateEvaluacionFormula");
        try {
            List<EvaluacionProcesoC> evaluacionProcesoCList = SQLite.select()
                    .from(EvaluacionProcesoC.class)
                    .where(EvaluacionProcesoC_Table.formulaSinc.eq(true))
                    .queryList();

            List<String> rubrosAsociadosIdList = new ArrayList<>();
            for (EvaluacionProcesoC evaluacionProcesoC : evaluacionProcesoCList)rubrosAsociadosIdList.add(evaluacionProcesoC.getRubroEvalProcesoId());
            Log.d(BEDatosRubroEvaluacionProcesoLocalDataSource.class.getSimpleName(),"size: " + evaluacionProcesoCList.size());

            IProperty[] parametros = Utils.f_allcolumnTable(RubroEvalRNPFormulaC_Table.ALL_COLUMN_PROPERTIES);
            List<RubroEvalRNPFormulaC> rubroFormulaList = SQLite.select(parametros)
                    .from(RubroEvalRNPFormulaC.class)
                    .innerJoin(RubroEvaluacionProcesoC.class)
                    .on(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.withTable()
                            .eq(RubroEvaluacionProcesoC_Table.key.withTable()))
                    .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionSecId.withTable().in(rubrosAsociadosIdList))
                    .and(RubroEvaluacionProcesoC_Table.tipoFormulaId.withTable().notEq(0))
                    .groupBy(parametros)
                    .queryList();

            Log.d(BEDatosRubroEvaluacionProcesoLocalDataSource.class.getSimpleName(),"rubroFormulaList size: " + rubroFormulaList.size());

            Log.d(BEDatosRubroEvaluacionProcesoLocalDataSource.class.getSimpleName(),"rubroFormulaList: " + rubroFormulaList.size());

            List<String> listIdRubrosActualizados = new ArrayList<>();
            for (EvaluacionProcesoC itemEvaluacionProcesoC: evaluacionProcesoCList){
                RubroEvalRNPFormulaC rubroEvalRNPFormulaC = null;
                for (RubroEvalRNPFormulaC itemRubroEvalRNPFormulaC: rubroFormulaList){
                    if(itemEvaluacionProcesoC.getRubroEvalProcesoId().equals(itemRubroEvalRNPFormulaC.getRubroEvaluacionSecId())){
                        rubroEvalRNPFormulaC = itemRubroEvalRNPFormulaC;
                        break;
                    }
                }

                if(rubroEvalRNPFormulaC != null){

                    boolean success = evaluarRubroFormulaPersona(rubroEvalRNPFormulaC.getRubroEvaluacionPrimId(),itemEvaluacionProcesoC.getAlumnoId() );
                    if(success){
                        //Log.d(EvaluacionFormulaLocal.class.getSimpleName(),"success: " + success);
                        int poscion = listIdRubrosActualizados.indexOf(rubroEvalRNPFormulaC.getRubroEvaluacionPrimId());
                        if(poscion!=-1)listIdRubrosActualizados.add(rubroEvalRNPFormulaC.getRubroEvaluacionPrimId());
                    }
                }
                itemEvaluacionProcesoC.setFormulaSinc(false);
                itemEvaluacionProcesoC.save();
            }

            for (String itemId: listIdRubrosActualizados){
                RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                        .from(RubroEvaluacionProcesoC.class)
                        .where(RubroEvaluacionProcesoC_Table.key.eq(itemId))
                        .querySingle();
                rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                rubroEvaluacionProcesoC.save();
            }
            for(RubroEvalRNPFormulaC rubroformula : rubroFormulaList){
                Log.d(BEDatosRubroEvaluacionProcesoLocalDataSource.class.getSimpleName(), "rubroformula id  "+ rubroformula.getRubroEvaluacionPrimId());
                f_mediaDesviacionEstandarFormula(rubroformula.getRubroEvaluacionPrimId());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
