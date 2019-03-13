package com.consultoraestrategia.ss_crmeducativo.portal.contactos.cabecera;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.base.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragment;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.LifecycleImpl;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.ViewPagerItemListener;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.ViewpagerAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.data.source.ContactosRepository;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.data.source.local.ContactosLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.detalleCompaneros.DetalleCompanerosFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.detalleDirectivos.DetalleDirectivosFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.detalleProfesores.DetalleProfesoresFragment;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.domain.usecase.GetAdministrativo;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.domain.usecase.GetCompaneros;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.domain.usecase.GetDocentes;
import com.consultoraestrategia.ss_crmeducativo.portal.contactos.listener.ContactoListener;
import com.consultoraestrategia.ss_crmeducativo_portal.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

public class ContactosFragment extends BaseFragment<ContactosView, ContactosPresenter, ContactoListener> implements ContactosView,LifecycleImpl.LifecycleListener {

    private static final String TAG = ContactosFragment.class.getSimpleName();

    @BindView(R.id.tab_contactos)
    BottomNavigationViewEx tabContactos;
    @BindView(R.id.vp_contactos)
    ViewPager vpContactos;
    ContactosPresenter presenter;


    @Override
    protected String getLogTag() {
        return ContactosFragment.class.getSimpleName();
    }

    @Override
    protected ContactosPresenter getPresenter() {
        ContactosRepository contactosRepository = new ContactosRepository(new ContactosLocalDataSource());
        presenter = new ContactosPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(), new GetAdministrativo(contactosRepository), new GetCompaneros(contactosRepository), new GetDocentes(contactosRepository));
        return presenter;
    }

    @Override
    protected ContactosView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_contactos, container, false);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return null;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapterViewPager();
        setHasOptionsMenu(true);
        //presenter.onCreate();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"onActivityCreated "+ (presenter==null));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void setupAdapterViewPager() {
        DetalleCompanerosFragment detalleCompanerosFragment = new DetalleCompanerosFragment();
        DetalleDirectivosFragment detalleDirectivosFragment = new DetalleDirectivosFragment();
        DetalleProfesoresFragment detalleProfesoresFragment = new DetalleProfesoresFragment();
        ViewpagerAdapter fragmentAdapter = new ViewpagerAdapter(getChildFragmentManager(),2  ,this);
        fragmentAdapter.addFragment(detalleCompanerosFragment, "Companeros");
        fragmentAdapter.addFragment(detalleDirectivosFragment, "Directores");
        fragmentAdapter.addFragment(detalleProfesoresFragment, "Profesores");
        vpContactos.setOffscreenPageLimit(3);
        vpContactos.setAdapter(fragmentAdapter);
        tabContactos.setupWithViewPager(vpContactos);
        presenter.onAttach(this,detalleProfesoresFragment, detalleDirectivosFragment, detalleCompanerosFragment);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
    }

    @Override
    public void onChildsFragmentViewCreated() {
        Log.d(TAG,"onChildsFragmentViewCreated");
        presenter.onChildsFragmentViewCreated();
    }

    @Override
    public void onFragmentViewCreated(Fragment f, View view, Bundle savedInstanceState) {
        Log.d(TAG,"onFragmentResumed: " + f.getClass().getSimpleName());
    }

    @Override
    public void onFragmentResumed(Fragment f) {
        Log.d(TAG,"onFragmentResumed: " + f.getClass().getSimpleName());
        presenter.onResumen();
    }

    @Override
    public void onFragmentViewDestroyed(Fragment f) {
        Log.d(TAG,"onChildsFragmentViewDestroy"+ f.getClass().getSimpleName());
    }

    @Override
    public void onFragmentActivityCreated(Fragment f, Bundle savedInstanceState) {
//        if(f instanceof ViewPagerItemListener){
//                ViewPagerItemListener<ContactosPresenter> viewPagerItemFragment = (ViewPagerItemListener<ContactosPresenter>)f;
//            viewPagerItemFragment.onAttach(presenter);
//        }

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_contacto, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
               //
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
