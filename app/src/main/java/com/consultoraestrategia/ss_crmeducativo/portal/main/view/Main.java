package com.consultoraestrategia.ss_crmeducativo.portal.main.view;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.portal.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.portal.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.colegioCalendario.ColegioCalendarioFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.colegioEvento.ColegioEventoFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.estudianteAsistencia.EstudianteAsistenciaFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.estudianteCursos.EstudianteCursos;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.estudianteEstadoCuenta.EstudianteEstadoCuenta;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.estudianteTarea.EstudianteTareaFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.estudianteconducta.EstudianteConductaFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.main.MainPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.main.MainPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.main.adapter.MenuAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.main.listener.MenuListener;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main extends BaseActivity<MainView, MainPresenter> implements MainView, MenuListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    MenuAdapter menuAdapter;
    @BindView(R.id.nav_bar_rc_menu)
    RecyclerView navBarRcMenu;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @Override
    protected String getTag() {
        return getClass().getSimpleName();
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenterImpl(new UseCaseHandler(), getResources());
    }

    @Override
    protected MainView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle("TAREA");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        setupAdapter();
    }

    private void setupAdapter() {
        menuAdapter = new MenuAdapter(new ArrayList<Object>(), this);
        navBarRcMenu.setLayoutManager(new LinearLayoutManager(this));
        navBarRcMenu.setAdapter(menuAdapter);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return toolbar;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showImportantMessage(CharSequence message) {

    }

    @Override
    public void showFinalMessage(CharSequence message) {

    }

    @Override
    public void showListSingleChooser(String title, List<Object> items, int positionSelected) {

    }

    @Override
    public void showMultiChoiseDialog(String title, CharSequence[] items, boolean[] checkedItems) {

    }

    @Override
    public void showMenuList(List<Object> objects) {

        Log.d("MainExampĺe", "showMenuList");
        menuAdapter.setLista(objects);
    }

    @Override
    public void MenuViewNotifyDataSetChanged() {
        menuAdapter.notifyDataSetChanged();
    }

    @Override
    public void initFragmentEstudianteTarea() {
        toolbar.setTitle("TAREA");
        drawer.postDelayed(new Runnable() {
            @Override
            public void run() {
                EstudianteTareaFragment fragment = new EstudianteTareaFragment();

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.root, fragment, fragment.getTag())
                        .commitNow();
            }
        },300);


    }

    @Override
    public void initFragmentEstudianteAsistencia() {
        toolbar.setTitle("ASISTENCIA");
        drawer.postDelayed(new Runnable() {
            @Override
            public void run() {
                EstudianteAsistenciaFragment fragment = new EstudianteAsistenciaFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.root, fragment, fragment.getTag())
                        .commitNow();

            }
        },300);
    }

    @Override
    public void initFragmentEstudianteConducta() {
        toolbar.setTitle("COMPORTAMIENTO");
        drawer.postDelayed(new Runnable() {
            @Override
            public void run() {
                EstudianteConductaFragment fragment = new EstudianteConductaFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.root, fragment, fragment.getTag())
                        .commitNow();

            }
        },300);
    }

    @Override
    public void initFragmentEstudianteEstadoCuenta() {
        toolbar.setTitle("ESTADO DE CUENTA");
        drawer.postDelayed(new Runnable() {
            @Override
            public void run() {
                EstudianteEstadoCuenta fragment = new EstudianteEstadoCuenta();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.root, fragment, fragment.getTag())
                        .commitNow();

            }
        },300);
    }

    @Override
    public void initFragmentEstudianteCurso() {
        toolbar.setTitle("CURSOS");
        drawer.postDelayed(new Runnable() {
            @Override
            public void run() {
                EstudianteCursos fragment = new EstudianteCursos();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.root, fragment, fragment.getTag())
                        .commitNow();

            }
        },300);
    }

    @Override
    public void initFragmentColegioEvento() {
        toolbar.setTitle("EVENTO");
        drawer.postDelayed(new Runnable() {
            @Override
            public void run() {
                ColegioEventoFragment fragment = new ColegioEventoFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.root, fragment, fragment.getTag())
                        .commitNow();

            }
        },300);
    }

    @Override
    public void initFragmentColegioCalendario() {
        toolbar.setTitle("CALENDARIO");
        drawer.postDelayed(new Runnable() {
            @Override
            public void run() {
                ColegioCalendarioFragment fragment = new ColegioCalendarioFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.root, fragment, fragment.getTag())
                        .commitNow();

            }
        },300);
    }

    @Override
    public void onMenuSelected(Object o) {
        presenter.onMenuSelected(o);
        drawer.closeDrawer(GravityCompat.START);
    }

    @OnClick({R.id.btn_info_estudiante, R.id.btn_info_colegio, R.id.btn_info_familia})
    public void onViewClicked(View view) {
        Log.d("MainExampĺe", "onViewClicked");
        switch (view.getId()) {
            case R.id.btn_info_estudiante:
                presenter.onClickBtnInfoEstudiante();
                break;
            case R.id.btn_info_colegio:
                presenter.onClickBtnInfoColegio();
                break;
            case R.id.btn_info_familia:
                presenter.onClickBtnInfoFamilia();
                break;
        }
    }

}
