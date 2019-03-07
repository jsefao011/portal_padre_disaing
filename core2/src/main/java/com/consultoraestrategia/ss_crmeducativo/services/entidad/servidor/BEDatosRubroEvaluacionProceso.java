package com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor;

import com.consultoraestrategia.ss_crmeducativo.entities.CriterioRubroEvaluacionC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoCampotematicoC;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.BEDatosServidor;


import java.util.List;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class BEDatosRubroEvaluacionProceso  extends BEDatosServidor {

    private List<RubroEvaluacionProcesoC> rubroEvaluacionProceso;
    private List<RubroEvalRNPFormulaC> rubroEvalProcesoFormula;
    private List<EvaluacionProcesoC> evaluacionProceso;
    private List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> ObtenerRubroEvaluacionProcesoEquipo ;
    private List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> ObtenerRubroEvaluacionProcesoIntegrante;
    private List<EquipoEvaluacionProcesoC> obtenerEquipoEvaluacionProceso;
    private List<RubroEvaluacionProcesoCampotematicoC> rubro_evaluacion_proceso_campotematico;
    private List<CriterioRubroEvaluacionC> obtenerCriterioRubroEvaluacionProceso;

    public BEDatosRubroEvaluacionProceso() {
    }


    public List<RubroEvaluacionProcesoC> getRubroEvaluacionProceso() {
        return rubroEvaluacionProceso;
    }

    public List<RubroEvalRNPFormulaC> getRubroEvalProcesoFormula() {
        return rubroEvalProcesoFormula;
    }

    public List<EvaluacionProcesoC> getEvaluacionProceso() {
        return evaluacionProceso;
    }

    public List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> getObtenerRubroEvaluacionProcesoEquipo() {
        return ObtenerRubroEvaluacionProcesoEquipo;
    }

    public List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> getObtenerRubroEvaluacionProcesoIntegrante() {
        return ObtenerRubroEvaluacionProcesoIntegrante;
    }

    public List<EquipoEvaluacionProcesoC> getObtenerEquipoEvaluacionProceso() {
        return obtenerEquipoEvaluacionProceso;
    }

    public List<RubroEvaluacionProcesoCampotematicoC> getRubro_evaluacion_proceso_campotematico() {
        return rubro_evaluacion_proceso_campotematico;
    }

    public void setRubroEvaluacionProceso(List<RubroEvaluacionProcesoC> rubroEvaluacionProceso) {
        this.rubroEvaluacionProceso = rubroEvaluacionProceso;
    }

    public void setRubroEvalProcesoFormula(List<RubroEvalRNPFormulaC> rubroEvalProcesoFormula) {
        this.rubroEvalProcesoFormula = rubroEvalProcesoFormula;
    }

    public void setEvaluacionProceso(List<EvaluacionProcesoC> evaluacionProceso) {
        this.evaluacionProceso = evaluacionProceso;
    }

    public void setObtenerRubroEvaluacionProcesoEquipo(List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> obtenerRubroEvaluacionProcesoEquipo) {
        ObtenerRubroEvaluacionProcesoEquipo = obtenerRubroEvaluacionProcesoEquipo;
    }

    public void setObtenerRubroEvaluacionProcesoIntegrante(List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> obtenerRubroEvaluacionProcesoIntegrante) {
        ObtenerRubroEvaluacionProcesoIntegrante = obtenerRubroEvaluacionProcesoIntegrante;
    }

    public void setObtenerEquipoEvaluacionProceso(List<EquipoEvaluacionProcesoC> obtenerEquipoEvaluacionProceso) {
        this.obtenerEquipoEvaluacionProceso = obtenerEquipoEvaluacionProceso;
    }

    public void setRubro_evaluacion_proceso_campotematico(List<RubroEvaluacionProcesoCampotematicoC> rubro_evaluacion_proceso_campotematico) {
        this.rubro_evaluacion_proceso_campotematico = rubro_evaluacion_proceso_campotematico;
    }

    public List<CriterioRubroEvaluacionC> getObtenerCriterioRubroEvaluacionProceso() {
        return obtenerCriterioRubroEvaluacionProceso;
    }

    public void setObtenerCriterioRubroEvaluacionProceso(List<CriterioRubroEvaluacionC> obtenerCriterioRubroEvaluacionProceso) {
        this.obtenerCriterioRubroEvaluacionProceso = obtenerCriterioRubroEvaluacionProceso;
    }

}
