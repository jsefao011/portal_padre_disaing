package com.consultoraestrategia.ss_crmeducativo.portal.main2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.consultoraestrategia.ss_crmeducativo.portal.demo.colegioCalendario.ColegioCalendarioFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.estudianteCursos.EstudianteCursos;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.estudianteEstadoCuenta.EstudianteEstadoCuenta;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.estudianteTarea.EstudianteTareaFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.estudianteconducta.EstudianteConductaFragment;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Main2 extends AppCompatActivity {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        final FragmentManager fragmentManager = getSupportFragmentManager();

        final Fragment fragment1 = new EstudianteEstadoCuenta();
        final Fragment fragment2 = new EstudianteConductaFragment();
        final Fragment fragment3 = new EstudianteCursos();
        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        final Fragment fragment;
                        switch (item.getItemId()) {
                            case R.id.action_favorites:
                                fragment = fragment1;
                                break;
                            case R.id.action_schedules:
                                fragment = fragment2;
                                break;
                            case R.id.action_music:
                            default:
                                fragment = fragment3;
                                break;
                        }
                        bottomNavigation.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                            }
                        },300);

                        return true;
                    }
                });
        // Set default selection
        bottomNavigation.setSelectedItemId(R.id.action_favorites);


    }


}
