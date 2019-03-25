package com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.data;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.campoTematicoDao.CompetenciaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.curso.CursoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.indicadorDao.IndicadorDao;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.CursoCustom;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.IndicadorQuery;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.Cell;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.ColorNota;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.Column;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.CompetenciaCell;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.ImagenColumn;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.InfoRubroUi;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.NotaCell;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.NotaColumn;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.Row;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.TextoColum;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class InfoRubroDataLocalSource implements InfoRubroDataSource {

    CursoDao cursoDao;
    CompetenciaDao competenciaDao;
    IndicadorDao indicadorDao;


    private String TAG= InfoRubroDataLocalSource.class.getSimpleName();
    public InfoRubroDataLocalSource(CursoDao cursoDao, CompetenciaDao competenciaDao, IndicadorDao indicadorDao) {
        this.cursoDao = cursoDao;
        this.competenciaDao=competenciaDao;
        this.indicadorDao=indicadorDao;
    }

    @Override
    public void getInfoRubro(String idEvaluacionProceso, int cargaCursoId, SucessCallback<InfoRubroUi> infoRubroUiSucessCallback) {
        Log.d(TAG, "idEvaluacionProceso "+ idEvaluacionProceso);
        Log.d(TAG, "cargaCursoId "+ cargaCursoId);

     //curso
        List<Integer>integerList= new ArrayList<>();
        integerList.add(cargaCursoId);
        List<CursoCustom> cursoCustomList= cursoDao.obtenerPorCargaCursos(integerList);
        InfoRubroUi infoRubroUi= new InfoRubroUi();
        for(CursoCustom curso:cursoCustomList ){
            CursoUi cursoUi= new CursoUi();
            cursoUi.setNombre(curso.getNombre());
            cursoUi.setPeriodo(curso.getPeriodo());
            cursoUi.setSeccion(curso.getSeccion());
            infoRubroUi.setCursoUi(cursoUi);
        }
     //alumno
        EvaluacionProcesoC evaluacionProcesoC= SQLite.select().from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.evaluacionProcesoId.withTable()
                        .eq(idEvaluacionProceso)).querySingle();
        if(evaluacionProcesoC!=null){
            Log.d(TAG, "evaluacionProcesoC "+evaluacionProcesoC.getAlumnoId());
            Persona persona= SQLite.select().from(Persona.class)
                    .where(Persona_Table.personaId.withTable()
                            .eq(evaluacionProcesoC.getAlumnoId())).querySingle();
            if(persona!=null){
                Log.d(TAG, "ALMNO "+ persona.getNombres());
                AlumnoUi alumnoUi= new AlumnoUi();
                alumnoUi.setNombre(persona.getNombres());
                alumnoUi.setApellidos(persona.getApellidos());
                alumnoUi.setUrlpicture(persona.getUrlPicture());
                alumnoUi.setIdalumno(persona.getPersonaId());
                infoRubroUi.setAlumnoUi(alumnoUi);
            }

      //nombre rubro
            RubroEvaluacionProcesoC rubroEvaluacionProcesoC= SQLite.select().from(RubroEvaluacionProcesoC.class)
            .where(RubroEvaluacionProcesoC_Table.key.withTable()
                    .eq(evaluacionProcesoC.getRubroEvalProcesoId())).querySingle();
            if(rubroEvaluacionProcesoC!=null){
                infoRubroUi.setNombreRubro(rubroEvaluacionProcesoC.getTitulo());

      //indicadores
                //cuando es rubro

                //cuando es rubrica

                List<RubroEvalRNPFormulaC> formulaList = SQLite.select()
                        .from(RubroEvalRNPFormulaC.class)
                        .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.eq(rubroEvaluacionProcesoC.getRubroEvalProcesoId()))
                        .queryList();

                List<Row> rows= new ArrayList<>();
                List<List<Cell>> cellListList= new ArrayList<>();

                List<Column> columns= new ArrayList<>();
                double escalaMaxAcumulada=0.0;
                TipoNotaC tipoNotaC= null;


                for(RubroEvalRNPFormulaC rubroEvalRNPFormulaC:formulaList ){


                    columns.clear();
                    List<String> rubroEvalProcesoKeyList= new ArrayList<>();
                    rubroEvalProcesoKeyList.add(rubroEvalRNPFormulaC.getRubroEvaluacionSecId());
                    CompetenciaCell competenciaCell= new CompetenciaCell();
                    TreeMap<Integer, Competencia> competenciaList = competenciaDao.getCompetenciaRubro(rubroEvalProcesoKeyList);
                    for (Map.Entry<Integer, Competencia> entry : competenciaList.entrySet()) {
                        Competencia itemCompetencia = entry.getValue();
                       switch (itemCompetencia.getTipoId()){
                           case Competencia.COMPETENCIA_ENFQ:
                               competenciaCell.setTipo(CompetenciaCell.Tipo.ENFOQUE);
                               break;
                           case  Competencia.COMPETENCIA_TRANS:
                               competenciaCell.setTipo(CompetenciaCell.Tipo.TRANSVERSAL);
                               break;
                           default:
                               competenciaCell.setTipo(CompetenciaCell.Tipo.BASE);
                               break;
                       }
                    }
                    List<Cell> cells= new ArrayList<>();

                    List<IndicadorQuery> indicadorUiList = indicadorDao.getIcdsporRubroProceso(rubroEvalProcesoKeyList);
                 //   Log.d(TAG, "indicadorQuery size " + indicadorUiList.size());
                    for (IndicadorQuery indicadorQuery : indicadorUiList) {
                        Row row = new Row();
                     //   Log.d(TAG, "indicadorQuery alias " + indicadorQuery.getAlias());
                        if(!indicadorQuery.getAlias().isEmpty()) row.setContenido(indicadorQuery.getAlias());
                        else row.setContenido(indicadorQuery.getTitulo());
                        rows.add(row);
                           // Log.d(TAG, "indicadorQuery tipo " + indicadorQuery.getTipoId());
                            switch (indicadorQuery.getTipoId()) {
                                case Icds.TIPO_HACER:
                                    competenciaCell.setTipoIndicador(CompetenciaCell.TipoIndicador.HACER);
                                    break;
                                case Icds.TIPO_SABER:
                                    competenciaCell.setTipoIndicador(CompetenciaCell.TipoIndicador.SABER);
                                    break;
                                case Icds.TIPO_SER:
                                    competenciaCell.setTipoIndicador(CompetenciaCell.TipoIndicador.SER);
                                    break;
                                default:
                                    competenciaCell.setTipoIndicador(CompetenciaCell.TipoIndicador.DEFAULT);;
                                    break;
                            }

                    }
                    cells.add(competenciaCell);
                    EvaluacionProcesoC evaluacionProcesoCrubro=null;
                    //rubros
                    RubroEvaluacionProcesoC rubro= SQLite.select().from(RubroEvaluacionProcesoC.class)
                            .where(RubroEvaluacionProcesoC_Table.key.withTable()
                            .eq(rubroEvalRNPFormulaC.getRubroEvaluacionSecId())).querySingle();
                    if(rubro!=null){
                        tipoNotaC=SQLite.select().from(TipoNotaC.class)
                                .where(TipoNotaC_Table.tipoNotaId.withTable()
                                .eq(rubro.getTipoNotaId())).querySingle();
                        if(tipoNotaC!=null){
                            EscalaEvaluacion escalaEvaluacionRub= SQLite.select().from(EscalaEvaluacion.class)
                                    .where(EscalaEvaluacion_Table.escalaEvaluacionId.withTable()
                                    .eq(tipoNotaC.getEscalaEvaluacionId())).querySingle();
                            if(escalaEvaluacionRub!=null){
                                escalaMaxAcumulada += escalaEvaluacionRub.getValorMaximo() - escalaEvaluacionRub.getValorMinimo();
                            }
                          evaluacionProcesoCrubro= SQLite.select().from(EvaluacionProcesoC.class)
                          .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.withTable()
                                  .eq(rubro.getKey())).
                                          and(EvaluacionProcesoC_Table.alumnoId.withTable()
                                                  .eq(evaluacionProcesoC.getAlumnoId())).querySingle();

                        }

                        //columnas
                        NotaColumn notaColumn= new NotaColumn();
                        notaColumn.setContenido("NF");
                        columns.add(notaColumn);

                        List<ValorTipoNotaC>valorTipoNotaCList= SQLite.select().from(ValorTipoNotaC.class)
                                .where(ValorTipoNotaC_Table.tipoNotaId.withTable()
                                        .eq(tipoNotaC.getTipoNotaId())).
                                        orderBy(ValorTipoNotaC_Table.limiteSuperior.desc()).queryList();

                        int count=0;
                        for(ValorTipoNotaC valorTipoNotaC: valorTipoNotaCList){
                            count++;
                            switch (tipoNotaC.getTipoId()){
                                case TipoNotaC.SELECTOR_ICONOS:
                                    ImagenColumn imagenColumn= new ImagenColumn();
                                    imagenColumn.setContenido(valorTipoNotaC.getIcono());
                                    columns.add(imagenColumn);

                                    break;
                                case  TipoNotaC.SELECTOR_VALORES:
                                    TextoColum textoColum= new TextoColum();
                                    textoColum.setValorNumerico(valorTipoNotaC.getTitulo());
                                    textoColum.setColorNota(setColor(count));
                                    columns.add(textoColum);
                                    break;
                            }
                            NotaCell notaCell= new NotaCell();
                            notaCell.setColor(setColor(count));
                            notaCell.setContenido(valorTipoNotaC.getAlias());

                            if(evaluacionProcesoCrubro!=null){
                                if(!evaluacionProcesoCrubro.getValorTipoNotaId().isEmpty()){
                                    if(valorTipoNotaC.getValorTipoNotaId().equals(evaluacionProcesoCrubro.getValorTipoNotaId())){
                                       // Log.d(TAG, "VALORITPO "+ valorTipoNotaC.getAlias());
                                        notaCell.setSelected(true);
                                    }
                                }
                            }
                            cells.add(notaCell);
                            //calcular nivel de logro
                            if(!evaluacionProcesoC.getValorTipoNotaId().isEmpty()){
                                if(valorTipoNotaC.getKey().equals(evaluacionProcesoC.getValorTipoNotaId()))infoRubroUi.setLogro(valorTipoNotaC.getAlias());
                            }
                        }
                        cellListList.add(cells);
                    }

                }
                //escala
                double nota= evaluacionProcesoC.getNota();
                Log.d(TAG, "nota "+ nota);
                if(tipoNotaC!=null){
                    EscalaEvaluacion escalaEvaluacion= SQLite.select()
                            .from(EscalaEvaluacion.class)
                            .where(EscalaEvaluacion_Table.escalaEvaluacionId.withTable()
                                    .eq(tipoNotaC.getEscalaEvaluacionId()))
                            .querySingle();
                    if(escalaEvaluacion!=null){
                        if (nota <= escalaEvaluacion.getValorMinimo()) {
                            nota = escalaEvaluacion.getValorMinimo();
                        }
                        double puntos= Utils.transformacionInvariante(escalaEvaluacion.getValorMinimo(), escalaEvaluacion.getValorMaximo(), nota,0,escalaMaxAcumulada);
//                        Log.d(TAG, "puntos calculados "+ puntos);
//                        Log.d(TAG, "puntos acumulados "+ escalaMaxAcumulada);
                        infoRubroUi.setPuntos(Math.round(puntos) + "/" + Math.round(escalaMaxAcumulada));
                        NumberFormat percentInstance = NumberFormat.getPercentInstance();
                        double percent = puntos / escalaMaxAcumulada;
                        infoRubroUi.setDesempenio(percentInstance.format(percent));

                    }

                }

                infoRubroUi.setRows(rows);
                infoRubroUi.setCellListList(cellListList);
                infoRubroUi.setColumns(columns);
            }
        }
        infoRubroUiSucessCallback.onLoad(true, infoRubroUi);
    }
    public  ColorNota setColor(int count ){
        switch (count){
            case 1:
                return  ColorNota.AZUL;
            case 2:
                return  ColorNota.VERDE;

            case 3:
                return  ColorNota.ANARANJADO;

            case 4:
                return  ColorNota.ROJO;

            default:
                return  ColorNota.DEFAULT;
        }
    }
}
