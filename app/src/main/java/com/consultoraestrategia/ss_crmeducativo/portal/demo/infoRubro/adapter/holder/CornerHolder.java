package com.consultoraestrategia.ss_crmeducativo.portal.demo.infoRubro.adapter.holder;


import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 2/07/2018.
 */

public class CornerHolder {
    private View view;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.root)
    LinearLayout root;

    public CornerHolder(View view) {
        this.view = view;
        ButterKnife.bind(this, view);
    }

    public void bind(String titulo) {
        txtTitle.setText(titulo);
    }
}
