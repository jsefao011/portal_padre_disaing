package com.consultoraestrategia.ss_crmeducativo.portal.main.view;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentListener;
import com.consultoraestrategia.ss_crmeducativo.portal.caso.ui.CasoFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.colegioCalendario.ColegioCalendarioFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.colegioEvento.ColegioEventoFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.estudianteCursos.EstudianteCursos;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.estudianteEstadoCuenta.EstudianteEstadoCuenta;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.estudianteRubro.EstudianteRubroFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.estudianteTarea.EstudianteTareaFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.demo.fragmentTofragment.cabecera.FragmentCabecera;
import com.consultoraestrategia.ss_crmeducativo.portal.familia.ui.FamiliaFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.main.MainPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.main.MainPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.main.adapter.MenuAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.main.data.sourse.MainRepository;
import com.consultoraestrategia.ss_crmeducativo.portal.main.data.sourse.local.MainLocaDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.main.dialogAlumnoList.DialogALumnoList;
import com.consultoraestrategia.ss_crmeducativo.portal.main.dialogAlumnoList.adapter.AdapterAlumnoList;
import com.consultoraestrategia.ss_crmeducativo.portal.main.domain.usecase.GetPadreMentor;
import com.consultoraestrategia.ss_crmeducativo.portal.main.domain.usecase.GetProgramaEducativoList;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.HijoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.ItemMenuUI;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.ProgramaEducativoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.main.listener.MenuListener;
import com.consultoraestrategia.ss_crmeducativo.portal.main.login.Login;
import com.consultoraestrategia.ss_crmeducativo.portal.wrapper.MainParametrosGlobales;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuItem;
import com.shehabic.droppy.DroppyMenuPopup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class Main extends BaseActivity<MainView, MainPresenter> implements MainView, MenuListener, BaseFragmentListener, AdapterAlumnoList.Listener {
    //http://pruebas.consultoraestrategia.com/FotosCata/184/01102018121442_27-ELIANE.JPG\"\n" +

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    MenuAdapter menuAdapter;
    @BindView(R.id.nav_bar_rc_menu)
    RecyclerView navBarRcMenu;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.nav_bar_letra_profile)
    TextView navBarLetraProfile;
    @BindView(R.id.nav_bar_imagen_profile)
    CircleImageView navBarImagenProfile;
    @BindView(R.id.nav_bar_letra_profile_hijo)
    TextView navBarLetraProfileHijo;
    @BindView(R.id.nav_bar_imagen_profile_hijo)
    CircleImageView navBarImagenProfileHijo;
    @BindView(R.id.txt_programa_educatio)
    TextView txtProgramaEducatio;
    @BindView(R.id.btn_programa_edu)
    ImageView btnProgramaEdu;
    private ActionBarDrawerToggle drawerToggle;
    private Dialog dialogAlumnoList;

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
        MainRepository mainRepository = new MainRepository(new MainLocaDataSource());
        return new MainPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetPadreMentor(mainRepository),
                new GetProgramaEducativoList(mainRepository));
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
        drawerToggle = setupDrawerToggle();
        drawer.addDrawerListener(drawerToggle);
        setupAdapter();
        setupTabMenu();
    }

    private void setupTabMenu() {

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        presenter.onClickBtnInfoColegio();
                        break;
                    case 1:
                        presenter.onClickBtnInfoEstudiante();
                        break;
                    case 2:
                        presenter.onClickBtnInfoFamilia();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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
        switch (id) {
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


    public <T extends Fragment> void getSupportFragmentManager(final Class<T> fragmentClass, Bundle bundle) {
        drawer.closeDrawers();

        try {
            Fragment fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(bundle);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //setExitTransition(new Slide(Gravity.START));
                //setEnterTransition(new Slide(Gravity.START));
                //fragment.setEnterTransition(new Slide(Gravity.LEFT));
            }
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.flContent, fragment).commit();
            //fragmentTransaction.addToBackStack(null);
        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    @Override
    public void initFragmentEstudianteTarea(MainParametrosGlobales mainParametrosGlobales) {
        setTitle("TAREA");
        //getSupportFragmentManager(EstudianteTareaFragment.class);


    }

    @Override
    public void initFragmentEstudianteAsistencia(MainParametrosGlobales mainParametrosGlobales) {
        setTitle("ASISTENCIA");
        //getSupportFragmentManager(FragmentCabecera.class);
    }

    @Override
    public void initFragmentEstudianteConducta(MainParametrosGlobales mainParametrosGlobales) {
        setTitle("COMPORTAMIENTO");
        getSupportFragmentManager(CasoFragment.class, mainParametrosGlobales.getBundle());
    }

    @Override
    public void initFragmentEstudianteEstadoCuenta(MainParametrosGlobales mainParametrosGlobales) {
        setTitle("ESTADO DE CUENTA");
        //getSupportFragmentManager(EstudianteEstadoCuenta.class);
    }

    @Override
    public void initFragmentEstudianteCurso(MainParametrosGlobales mainParametrosGlobales) {
        setTitle("CURSOS");
        //getSupportFragmentManager(EstudianteCursos.class);
    }

    @Override
    public void initFragmentColegioEvento(MainParametrosGlobales mainParametrosGlobales) {
        setTitle("EVENTO");
        //getSupportFragmentManager(ColegioEventoFragment.class);
    }

    @Override
    public void initFragmentFamiliaActualizarPerfil(MainParametrosGlobales mainParametrosGlobales) {
        setTitle("ACTUALIZAR PERFIL");
        //getSupportFragmentManager(FamiliaFragment.class);
    }

    @Override
    public void initFragmentFamiliaInfografia(MainParametrosGlobales mainParametrosGlobales) {

    }

    @Override
    public void initFragmentColegioCalendario(MainParametrosGlobales mainParametrosGlobales) {
        setTitle("CALENDARIO");
        //getSupportFragmentManager(ColegioCalendarioFragment.class);
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
    public void initFragmentEstudianteRubros(MainParametrosGlobales mainParametrosGlobales) {
        setTitle("RUBROS");
        //getSupportFragmentManager(EstudianteRubroFragment.class);
    }

    @Override
    public void initFragmentFamiliaPerfilFamiliar(MainParametrosGlobales mainParametrosGlobales) {
        setTitle("P. FAMILIAR");
        //getSupportFragmentManager(EstudianteRubroFragment.class);
    }

    @Override
    public void initFragmentColegioDirectorio(MainParametrosGlobales mainParametrosGlobales) {
        setTitle("AGENDA");
        //getSupportFragmentManager(EstudianteRubroFragment.class);
    }

    @Override
    public void setLogoPadre(final char etiqueta, String url_imagen) {
        Glide.with(this)
                .load(url_imagen)
                .apply(new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        navBarLetraProfile.setText(etiqueta);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        navBarLetraProfile.setText("");
                        return false;
                    }
                })
                .into(navBarImagenProfile);
    }

    @Override
    public void setLogoHijo(final char etiqueta, String url_imagen) {
        Glide.with(this)
                .load(url_imagen)
                .apply(new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        navBarLetraProfileHijo.setText(etiqueta);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        navBarLetraProfileHijo.setText("");
                        return false;
                    }
                })
                .into(navBarImagenProfileHijo);
    }

    @Override
    public void setUnknowHijo() {
        navBarLetraProfileHijo.setText("?");
    }

    @Override
    public void showFragmentListHijoSelected(List<HijoUi> hijoUiList) {
        dialogAlumnoList = DialogALumnoList.showDialog(this, hijoUiList, this);
        dialogAlumnoList.show();
    }

    @Override
    public void setCalendarioPeriodo(String nombre) {
        txtProgramaEducatio.setText(nombre);
    }

    @Override
    public void showPopListProgramaEduc(final List<ProgramaEducativoUi> programaEducativoUiList) {
        Log.d(getTag(),"cantidad: " + programaEducativoUiList.size());
        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(btnProgramaEdu.getContext(), btnProgramaEdu);
        droppyBuilder.triggerOnAnchorClick(false);
        // Add normal items (text only)
        for (ProgramaEducativoUi programaEducativoUi : programaEducativoUiList) {
            droppyBuilder.addMenuItem(new DroppyMenuItem(programaEducativoUi.getNombre(), R.drawable.ic_programa_educativo));
        }
        ///droppyBuilder.addMenuItem(new DroppyMenuItem("Secundaria", R.drawable.ic_programa_educativo));
        // Set Callback handler
        droppyBuilder.setOnClick(new DroppyClickCallbackInterface() {
            @Override
            public void call(View v, int posicion) {
                try {
                    presenter.onSelectedProgramaEduca(programaEducativoUiList.get(posicion));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        droppyBuilder.build().show();
    }

    @Override
    public void onMenuSelected(ItemMenuUI itemMenuUI) {
        presenter.onMenuSelected(itemMenuUI);
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


    @OnClick(R.id.nav_bar_imagen_profile_hijo)
    public void onClickedHijo() {
        presenter.onClickHijo();
    }

    @Override
    public void onClickSelectedHijo(HijoUi hijoUi) {
        dialogAlumnoList.hide();
        presenter.onClickSelectedHijo(hijoUi);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @OnClick(R.id.btn_programa_edu)
    public void onViewClickedProgramaEducativo() {
        presenter.onClickedProgramaEducativo();
    }

}
