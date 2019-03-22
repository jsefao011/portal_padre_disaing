package com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.data;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.campoTematicoDao.CompetenciaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.curso.CursoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.indicadorDao.IndicadorDao;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
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
import com.raizlabs.android.dbflow.sql.language.SQLite;

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
            Persona persona= SQLite.select().from(Persona.class)
                    .where(Persona_Table.personaId.withTable()
                            .eq(evaluacionProcesoC.getAlumnoId())).querySingle();
            if(persona!=null){
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



                for(RubroEvalRNPFormulaC rubroEvalRNPFormulaC:formulaList ){
                    List<Cell> cells= new ArrayList<>();
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

                    List<IndicadorQuery> indicadorUiList = indicadorDao.getIcdsporRubroProceso(rubroEvalProcesoKeyList);
                    for (IndicadorQuery indicadorQuery : indicadorUiList) {
                        Row row = new Row();
                        row.setContenido(indicadorQuery.getAlias());
                        rows.add(row);
                            Log.d(TAG, "indicadorQuery tipo " + indicadorQuery.getTipoId());
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
                    //columnas
                    NotaColumn notaColumn= new NotaColumn();
                    notaColumn.setContenido("NF");
                    columns.add(notaColumn);

                    TipoNotaC tipoNotaC= SQLite.select().from(TipoNotaC.class)
                            .where(TipoNotaC_Table.tipoNotaId.withTable()
                                    .eq(rubroEvaluacionProcesoC.getTipoNotaId())).querySingle();
                    if(tipoNotaC!=null){
                        List<ValorTipoNotaC>valorTipoNotaCList= SQLite.select().from(ValorTipoNotaC.class)
                                .where(ValorTipoNotaC_Table.tipoNotaId.withTable()
                                        .eq(tipoNotaC.getTipoNotaId())).
                                        orderBy(ValorTipoNotaC_Table.limiteSuperior.desc()).queryList();

                     int count=0;
                        for(ValorTipoNotaC valorTipoNotaC: valorTipoNotaCList){
                            count++;
                            Log.d(TAG, "valores  "+ valorTipoNotaC.getAlias());
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
                            if(valorTipoNotaC.getValorTipoNotaId().equals( evaluacionProcesoC.getValorTipoNotaId())){
                                notaCell.setSelected(true);

                            }
                            cells.add(notaCell);
                        }

                    }
                    cellListList.add(cells);
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
