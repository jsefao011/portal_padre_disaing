package com.consultoraestrategia.ss_crmeducativo.portal.base.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;


import java.util.List;

/**
 * Created by @stevecampos on 15/01/2018.
 */

public abstract class BaseActivity<V extends BaseView<P>, P extends BasePresenter<V>> extends AppCompatActivity implements BaseView<P>  {

    protected abstract String getTag();

    protected abstract AppCompatActivity getActivity();

    protected abstract P getPresenter();

    protected abstract V getBaseView();

    protected abstract Bundle getExtrasInf();

    protected P presenter;

    protected abstract void setContentView();

    protected abstract ViewGroup getRootLayout();

    protected abstract ProgressBar getProgressBar();
    private static final String TAG = BaseActivity.class.getSimpleName();

    /*public static <T extends AppCompatActivity> Intent getStartIntent(Context context, Class<T> tClass) {
        return new Intent(context, tClass);
    }*/

    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getTag(), "onCreate");
        setContentView();
        setupProgressBar();
        setupPresenter();
        if (presenter != null) presenter.onCreate();
    }

    private void setupProgressBar() {
        progressBar = getProgressBar();
    }


    private void setupPresenter() {
        presenter = (P) getLastCustomNonConfigurationInstance();
        if (presenter == null) {
            presenter = getPresenter();
            presenter.setExtras(getExtrasInf());
        }
        setPresenter(presenter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(getTag(), "onStart");
        if (presenter != null) presenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(getTag(), "onResume");
        if (presenter != null) presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(getTag(), "onPause");
        if (presenter != null) presenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(getTag(), "onStop");
        if (presenter != null) presenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(getTag(), "onDestroy");
        if (presenter != null) presenter.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Log.d(getTag(), "onBackPressed");
        if (presenter != null) presenter.onBackPressed();
        super.onBackPressed();
    }

    @Override
    public void setPresenter(P presenter) {
        if (presenter != null) {
            presenter.attachView(getBaseView());
        }
    }

    @Override
    public P onRetainCustomNonConfigurationInstance() {
        return presenter;
    }

    public void showProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    public void showMessage(CharSequence error) {
        Snackbar.make(getRootLayout(), error, Snackbar.LENGTH_LONG).show();
    }


}
