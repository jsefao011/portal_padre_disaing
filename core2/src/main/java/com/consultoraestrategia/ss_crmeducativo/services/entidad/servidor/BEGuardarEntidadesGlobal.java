package com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor;

import com.consultoraestrategia.ss_crmeducativo.services.entidad.BEDatosServidor;

public class BEGuardarEntidadesGlobal extends BEDatosServidor {

    public GEDatosRubroEvaluacionProceso rubroEvaluacionProceso;
    public GEDatosTareasRecursos tareaRecursos;
    public BEDatosSesionAprendizaje sesionAprendizaje;
    public GEDatosEnvioAsistencia asistencia;
    public BEDatosEnvioGrupo grupo;
    public BEDatosEnvioMensajeria mensajeria;
    public BEDatosCasos casos;
    public int usuarioId;

    public GEDatosRubroEvaluacionProceso getRubroEvaluacionProceso() {
        return rubroEvaluacionProceso;
    }

    public void setRubroEvaluacionProceso(GEDatosRubroEvaluacionProceso rubroEvaluacionProceso) {
        this.rubroEvaluacionProceso = rubroEvaluacionProceso;
    }

    public GEDatosTareasRecursos getTareaRecursos() {
        return tareaRecursos;
    }

    public void setTareaRecursos(GEDatosTareasRecursos tareaRecursos) {
        this.tareaRecursos = tareaRecursos;
    }

    public BEDatosSesionAprendizaje getSesionAprendizaje() {
        return sesionAprendizaje;
    }

    public void setSesionAprendizaje(BEDatosSesionAprendizaje sesionAprendizaje) {
        this.sesionAprendizaje = sesionAprendizaje;
    }

    public GEDatosEnvioAsistencia getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(GEDatosEnvioAsistencia asistencia) {
        this.asistencia = asistencia;
    }

    public BEDatosEnvioGrupo getGrupo() {
        return grupo;
    }

    public void setGrupo(BEDatosEnvioGrupo grupo) {
        this.grupo = grupo;
    }

    public BEDatosEnvioMensajeria getMensajeria() {
        return mensajeria;
    }

    public void setMensajeria(BEDatosEnvioMensajeria mensajeria) {
        this.mensajeria = mensajeria;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public BEDatosCasos getCasos() {
        return casos;
    }

    public void setCasos(BEDatosCasos casos) {
        this.casos = casos;
    }

    @Override
    public String toString() {
        return "BEGuardarEntidadesGlobal{" +
                "rubroEvaluacionProceso=" + rubroEvaluacionProceso +
                ", tareaRecursos=" + tareaRecursos +
                ", sesionAprendizaje=" + sesionAprendizaje +
                ", asistencia=" + asistencia +
                ", grupo=" + grupo +
                ", mensajeria=" + mensajeria +
                ", usuarioId=" + usuarioId +
                ", fecha_servidor=" +  fecha_servidor+
                '}';
    }
}
