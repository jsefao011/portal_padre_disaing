package com.consultoraestrategia.ss_crmeducativo.portal.main.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.transition.Slide;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentListener;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.ui.CasoFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.colegioCalendario.ColegioCalendarioFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.colegioEvento.ColegioEventoFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.estudianteAsistencia.EstudianteAsistenciaFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.estudianteCursos.EstudianteCursos;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.estudianteEstadoCuenta.EstudianteEstadoCuenta;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.estudianteRubro.EstudianteRubroFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.estudianteTarea.EstudianteTareaFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.estudianteconducta.EstudianteConductaFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.cabecera.FragmentCabecera;
import com.consultoraestrategia.ss_crmeducativo.portal.main.MainPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.main.MainPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.main.adapter.MenuAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.main.listener.MenuListener;
import com.consultoraestrategia.ss_crmeducativo.portal.main.login.Login;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.view.TareasFragment;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class Main extends BaseActivity<MainView, MainPresenter> implements MainView, MenuListener, BaseFragmentListener {
    //http://pruebas.consultoraestrategia.com/FotosCata/184/01102018121442_27-ELIANE.JPG\"\n" +
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    MenuAdapter menuAdapter;
    @BindView(R.id.nav_bar_rc_menu)
    RecyclerView navBarRcMenu;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_bar_imagen_profile_hijo)
    CircleImageView navBarImagenProfileHijo;
    private ActionBarDrawerToggle drawerToggle;
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
        return new MainPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
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
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        setTitle("TAREA");
        drawerToggle = setupDrawerToggle();
        drawer.addDrawerListener(drawerToggle);
        setupAdapter();

        Glide.with(this)
                .load("http://pruebas.consultoraestrategia.com/FotosCata/184/01102018121442_27-ELIANE.JPG")
                .apply(new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(navBarImagenProfileHijo);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,  R.string.navigation_drawer_close);
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
        switch (id){
            case R.id.action_settings:
                return true;
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
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
    public void showMenuList(List<Object> objects) {

        Log.d("MainExampĺe", "showMenuList");
        menuAdapter.setLista(objects);
    }

    @Override
    public void MenuViewNotifyDataSetChanged() {
        menuAdapter.notifyDataSetChanged();
    }



    public <T extends Fragment> void getSupportFragmentManager(final Class<T> fragmentClass) {
        drawer.closeDrawers();
        drawer.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create a new fragment and specify the fragment to show based on nav item clicked

                try {

                    Fragment fragment = (Fragment) fragmentClass.newInstance();

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        //setExitTransition(new Slide(Gravity.START));
                        //setEnterTransition(new Slide(Gravity.START));
                        fragment.setEnterTransition(new Slide(Gravity.LEFT));
                    }

                    // Insert the fragment by replacing any existing fragment

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.flContent, fragment).commit();
                    fragmentTransaction.addToBackStack(null);

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        },300);


    }

    @Override
    public void initFragmentEstudianteTarea() {
        setTitle("TAREA");
        getSupportFragmentManager(EstudianteTareaFragment.class);


    }

    @Override
    public void initFragmentEstudianteAsistencia() {
        setTitle("ASISTENCIA");
        getSupportFragmentManager(FragmentCabecera.class);
    }

    @Override
    public void initFragmentEstudianteConducta() {
        setTitle("COMPORTAMIENTO");
        getSupportFragmentManager(CasoFragment.class);
    }

    @Override
    public void initFragmentEstudianteEstadoCuenta() {
        setTitle("ESTADO DE CUENTA");
        getSupportFragmentManager(EstudianteEstadoCuenta.class);
    }

    @Override
    public void initFragmentEstudianteCurso() {
        setTitle("CURSOS");
        getSupportFragmentManager(EstudianteCursos.class);
    }

    @Override
    public void initFragmentColegioEvento() {
        setTitle("EVENTO");
        getSupportFragmentManager(ColegioEventoFragment.class);
    }

    @Override
    public void initFragmentColegioCalendario() {
        setTitle("CALENDARIO");
        getSupportFragmentManager(ColegioCalendarioFragment.class);
    }

    @Override
    public void starLoginActivity() {
        Intent intent = new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void initFragmentEstudianteRubros() {
        setTitle("RUBROS");
        getSupportFragmentManager(EstudianteRubroFragment.class);
    }

    @Override
    public void onMenuSelected(Object o) {
        presenter.onMenuSelected(o);
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

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
