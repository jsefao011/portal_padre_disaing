package com.consultoraestrategia.ss_crmeducativo.portal.tareas.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.portal.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.portal.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters.FragmentAdapter;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.presenter.TareasPresenter;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.presenter.TareasPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.view.TabsTareaLista.TabsTareasLista;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class TareasFragment extends Fragment implements TareasView{


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = TareasFragment.class.getSimpleName();
    Unbinder unbinder;
    TareasPresenter presenter;
    @BindView(R.id.tab_tareas)
    TabLayout tabTareas;
    @BindView(R.id.vp_Tareas)
    ViewPager vpTareas;



    public static TareasFragment newInstance(String param1, String param2) {
        TareasFragment fragment = new TareasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        //fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_tareas, container, false);
        unbinder = ButterKnife.bind(this, view);
        initPresenter();
        return view;
    }



    private void initPresenter() {

        presenter= new TareasPresenterImpl(new UseCaseHandler());
        setPresenter(presenter);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapterViewPager();
        presenter.onCreate();
    }


    private void setupAdapterViewPager() {

       final Bundle args = new Bundle();
        final FragmentAdapter fragmentAdapter = new FragmentAdapter(getFragmentManager());
        fragmentAdapter.addFragment(TabsTareasLista.newInstance(args), "Tareas por curso");
        fragmentAdapter.addFragment(TabsTareasLista.newInstance(args), "Tareas General");
        vpTareas.setOffscreenPageLimit(5);
        vpTareas.setAdapter(fragmentAdapter);
        tabTareas.setupWithViewPager(vpTareas);
        vpTareas.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
             args.putInt("tipotarea",position );

             //   if (presenter != null)presenter.onPageChanged(fragmentAdapter.getItem(position) != null ? fragmentAdapter.getItem(position).getClass() : null);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    public void setPresenter(TareasPresenter presenter) {
        presenter.attachView(this);
        presenter.onCreate();
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
}
