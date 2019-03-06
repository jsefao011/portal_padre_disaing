package com.consultoraestrategia.ss_crmeducativo.portal.demo.estudianteRubro;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;

import com.consultoraestrategia.ss_crmeducativo.portal.demo.infoRubro.ui.IndoRubroFragment;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class EstudianteRubroFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.tab_host)
    TabHost tabHost;
    @BindView(R.id.btn_imagen3)
    ImageView btnImagen;
    @BindView(R.id.btn_imagen4)
    ImageView btnImagen2;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_estudiante_rubro, container, false);
        unbinder = ButterKnife.bind(this, view);
        btnImagen.setOnClickListener(this);
        btnImagen2.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(getClass().getSimpleName(), "onViewCreated");
        setupTabHost();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setupTabHost() {

        tabHost.setup();
        TabHost.TabSpec spec = tabHost.newTabSpec("Rubros del curso");
        spec.setContent(R.id.tab_one_container);
        spec.setIndicator("Rubros del curso");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Lista de rubros");
        spec.setContent(R.id.tab_two_container);
        spec.setIndicator("Lista de rubros");
        tabHost.addTab(spec);

        tabHost.getTabWidget().getChildAt(0).getBackground().setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
        tabHost.getTabWidget().getChildAt(1).getBackground().setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(getClass().getSimpleName(), "onDestroyView");
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_imagen:
                showInfoRubro();
                break;
            case R.id.btn_imagen2:
                showInfoRubro();
                break;
        }
    }

    private void showInfoRubro() {
        IndoRubroFragment indoRubroFragment = IndoRubroFragment.newInstance("{\n" +
                "  \"apellido\": \"Sandra\",\n" +
                "  \"cells\": [\n" +
                "    {\n" +
                "      \"itemCellList\": [\n" +
                "        {\n" +
                "          \"competenciaId\": 347,\n" +
                "          \"content\": \"25\",\n" +
                "          \"select\": false,\n" +
                "          \"urlImg\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"competenciaId\": 0,\n" +
                "          \"content\": \"x\",\n" +
                "          \"select\": true,\n" +
                "          \"urlImg\": \"http://192.168.1.151/SistemaAcademico/Images/Expresiones/Muy Contento.png\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"competenciaId\": 0,\n" +
                "          \"content\": \"\",\n" +
                "          \"select\": false,\n" +
                "          \"urlImg\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"competenciaId\": 0,\n" +
                "          \"content\": \"\",\n" +
                "          \"select\": false,\n" +
                "          \"urlImg\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"competenciaId\": 0,\n" +
                "          \"content\": \"\",\n" +
                "          \"select\": false,\n" +
                "          \"urlImg\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"competenciaId\": 0,\n" +
                "          \"content\": \"\",\n" +
                "          \"select\": false,\n" +
                "          \"urlImg\": \"\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"itemCellList\": [\n" +
                "        {\n" +
                "          \"competenciaId\": 347,\n" +
                "          \"content\": \"25\",\n" +
                "          \"select\": false,\n" +
                "          \"urlImg\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"competenciaId\": 0,\n" +
                "          \"content\": \"x\",\n" +
                "          \"select\": true,\n" +
                "          \"urlImg\": \"http://192.168.1.151/SistemaAcademico/Images/Expresiones/Muy Contento.png\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"competenciaId\": 0,\n" +
                "          \"content\": \"\",\n" +
                "          \"select\": false,\n" +
                "          \"urlImg\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"competenciaId\": 0,\n" +
                "          \"content\": \"\",\n" +
                "          \"select\": false,\n" +
                "          \"urlImg\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"competenciaId\": 0,\n" +
                "          \"content\": \"\",\n" +
                "          \"select\": false,\n" +
                "          \"urlImg\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"competenciaId\": 0,\n" +
                "          \"content\": \"\",\n" +
                "          \"select\": false,\n" +
                "          \"urlImg\": \"\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"itemCellList\": [\n" +
                "        {\n" +
                "          \"competenciaId\": 347,\n" +
                "          \"content\": \"25\",\n" +
                "          \"select\": false,\n" +
                "          \"urlImg\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"competenciaId\": 0,\n" +
                "          \"content\": \"\",\n" +
                "          \"select\": false,\n" +
                "          \"urlImg\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"competenciaId\": 0,\n" +
                "          \"content\": \"x\",\n" +
                "          \"select\": true,\n" +
                "          \"urlImg\": \"http://192.168.1.151/SistemaAcademico/Images/Expresiones/Contento.png\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"competenciaId\": 0,\n" +
                "          \"content\": \"\",\n" +
                "          \"select\": false,\n" +
                "          \"urlImg\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"competenciaId\": 0,\n" +
                "          \"content\": \"\",\n" +
                "          \"select\": false,\n" +
                "          \"urlImg\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"competenciaId\": 0,\n" +
                "          \"content\": \"\",\n" +
                "          \"select\": false,\n" +
                "          \"urlImg\": \"\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"itemCellList\": [\n" +
                "        {\n" +
                "          \"competenciaId\": 349,\n" +
                "          \"content\": \"25\",\n" +
                "          \"select\": false,\n" +
                "          \"urlImg\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"competenciaId\": 0,\n" +
                "          \"content\": \"\",\n" +
                "          \"select\": false,\n" +
                "          \"urlImg\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"competenciaId\": 0,\n" +
                "          \"content\": \"x\",\n" +
                "          \"select\": true,\n" +
                "          \"urlImg\": \"http://192.168.1.151/SistemaAcademico/Images/Expresiones/Contento.png\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"competenciaId\": 0,\n" +
                "          \"content\": \"\",\n" +
                "          \"select\": false,\n" +
                "          \"urlImg\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"competenciaId\": 0,\n" +
                "          \"content\": \"\",\n" +
                "          \"select\": false,\n" +
                "          \"urlImg\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"competenciaId\": 0,\n" +
                "          \"content\": \"\",\n" +
                "          \"select\": false,\n" +
                "          \"urlImg\": \"\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"columna\": [\n" +
                "    {\n" +
                "      \"nombreComp\": \"COMPRUEBA\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"nombreComp\": \"Comprueba expresiónes\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"nombreComp\": \"comparación de números\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"nombreComp\": \"IT\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"desempenio\": \"85%\",\n" +
                "  \"fila\": [\n" +
                "    {\n" +
                "      \"content\": \"NF\",\n" +
                "      \"img\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"content\": \"\",\n" +
                "      \"img\": \"http://192.168.1.151/SistemaAcademico/Images/Expresiones/Muy Contento.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"content\": \"\",\n" +
                "      \"img\": \"http://192.168.1.151/SistemaAcademico/Images/Expresiones/Contento.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"content\": \"\",\n" +
                "      \"img\": \"http://192.168.1.151/SistemaAcademico/Images/Expresiones/Neutral.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"content\": \"\",\n" +
                "      \"img\": \"http://192.168.1.151/SistemaAcademico/Images/Expresiones/Triste.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"content\": \"\",\n" +
                "      \"img\": \"http://192.168.1.151/SistemaAcademico/Images/Expresiones/Muy Triste.png\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"logro\": \"Muy Contento\",\n" +
                "  \"nombre\": \"Echevarria Loza\",\n" +
                "  \"nombreCursoGradoSeccion\": \" MATEMATICA Cuarto : Jacop\",\n" +
                "  \"nombreRubrica\": \"Desarrollo de ejercicios de Conjuntos\",\n" +
                "  \"nota\": \"\",\n" +
                "  \"puntos\": \"68/80\",\n" +
                "  \"urlImg\": \"http://pruebas.consultoraestrategia.com/FotosCata/184/01102018121442_27-ELIANE.JPG\"\n" +
                "}");
        indoRubroFragment.show(getFragmentManager(), IndoRubroFragment.class.getSimpleName());
    }
}
