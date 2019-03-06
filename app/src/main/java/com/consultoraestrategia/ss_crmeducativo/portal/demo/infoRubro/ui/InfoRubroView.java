package com.consultoraestrategia.ss_crmeducativo.portal.demo.infoRubro.ui;



import com.consultoraestrategia.ss_crmeducativo.portal.demo.infoRubro.InfoRubroPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.infoRubro.entities.Alumno;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.infoRubro.entities.Cell;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.infoRubro.entities.Column;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.infoRubro.entities.Row;

import java.util.List;

/**
 * Created by Jse on 15/09/2018.
 */

public interface InfoRubroView {
    void setPresenter(InfoRubroPresenter presenter);

    void showTableView(List<List<Cell>> cellListList, List<Column> columnList, List<Row> rowList, String nombreTabla);

    void setPuntos(String puntos);

    void setAlumno(Alumno alumno);

    void setDesempenio(String desempenio);

    void setNota(String nota);

    void setLogro(String logro);

    void setNombreRubrica(String nombreRubrica);

    void setNombreCurso(String nombreCurso);
}
