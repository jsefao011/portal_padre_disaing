package com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.ui;



import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.InfoRubroPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.Cell;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.Column;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.Row;

import java.util.List;

/**
 * Created by Jse on 15/09/2018.
 */

public interface InfoRubroView extends BaseView<InfoRubroPresenter> {


    void showTableView(List<List<Cell>> cellListList, List<Column> columnList, List<Row> rowList, String nombreTabla);

    void setPuntos(String puntos);

    void setAlumno(AlumnoUi alumnoUi );

    void setDesempenio(String desempenio);

    void setNota(String nota);

    void setLogro(String logro);

    void setNombreRubrica(String nombreRubrica);

    void setNombreCurso(String nombreCurso);
}
