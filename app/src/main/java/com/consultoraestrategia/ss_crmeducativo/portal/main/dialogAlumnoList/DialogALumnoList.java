package com.consultoraestrategia.ss_crmeducativo.portal.main.dialogAlumnoList;
import android.app.Activity;
import android.app.Dialog;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.portal.main.dialogAlumnoList.adapter.AdapterAlumnoList;
import com.consultoraestrategia.ss_crmeducativo.portal.main.dialogAlumnoList.adapter.DialogAlumnoColumnCountProvider;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.HijoUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.List;

public class DialogALumnoList {

    public static Dialog showDialog(Activity activity, List<HijoUi> hijoUiList,AdapterAlumnoList.Listener listener){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_alumno_selected);
        RecyclerView recyclerView = (RecyclerView)dialog.findViewById(R.id.rc_alumnos);

        AutoColumnGridLayoutManager autoColumnGridLayoutManager = new AutoColumnGridLayoutManager(dialog.getContext(), OrientationHelper.VERTICAL, false);
        DialogAlumnoColumnCountProvider columnCountProvider = new DialogAlumnoColumnCountProvider(dialog.getContext());
        autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider);
        recyclerView.setLayoutManager(autoColumnGridLayoutManager);
        recyclerView.setAdapter(new AdapterAlumnoList(hijoUiList, listener));

        /*TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        text.setText(msg);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });*/

        return dialog;

    }
}
