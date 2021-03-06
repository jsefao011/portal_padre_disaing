package com.consultoraestrategia.ss_crmeducativo.base.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DialogTitle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.consultoraestrategia.ss_crmeducativo.base.filterChooser.CallbackFilterChooserBottomDialog;
import com.consultoraestrategia.ss_crmeducativo.base.filterChooser.FilterChooserBottomSheetDialog;
import com.consultoraestrategia.ss_crmeducativo.core2.R;

import java.util.List;

/**
 * Created by @stevecampos on 15/01/2018.
 */

public abstract class BaseActivity<V extends BaseView<P>, P extends BasePresenter<V>> extends AppCompatActivity implements BaseView<P> {

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
        this.progressBar = getProgressBar();
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

    public void showImportantMessage(CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setTitle(R.string.dialog_title);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    /*PARA ELIMINAR!!!*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_base_activity, menu);
        return true;
    }

    public void showFinalMessage(CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        TextView text= new TextView(getActivity());
        text.setPadding(20,20,20,0);
        text.setText(message);
        text.setGravity(Gravity.CENTER);
        builder.setView(text)
                .setTitle(R.string.dialog_title)
                .setCancelable(false)
                .setPositiveButton(R.string.global_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showFinalMessageAceptCancel(final CharSequence message, final CharSequence messageTittle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        TextView text= new TextView(getActivity());
        text.setPadding(30,30,30,0);
        text.setText(message);
        text.setGravity(Gravity.CENTER);
        builder.setView(text)
                .setTitle(messageTittle)
                .setCancelable(false)
                .setNegativeButton(R.string.global_btn_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(R.string.global_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.onCLickAcceptButtom();
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Crea un Diálogo con una lista de selección simple
     */
    public void showListSingleChooser(String dialogTitle, final List<Object> items, int positionSelected) {
        if (items.isEmpty()) return;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        int size = items.size();
        final CharSequence[] singleItems = new CharSequence[size];

        for (int i = 0; i < size; i++) {
            singleItems[i] = items.get(i).toString();
        }

        if (positionSelected >= items.size()) {
            positionSelected = -1;
        }

        builder.setTitle(dialogTitle)
                .setSingleChoiceItems(singleItems, positionSelected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(getTag(), "setSingleChoiceItems onClick i: " + which);
                    }
                })
                .setPositiveButton(R.string.global_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(getTag(), "setPositiveButton onClick i: " + which);
                        int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                        if (selectedPosition != -1) {
                            Object objectSelected = items.get(selectedPosition);
                            presenter.onSingleItemSelected(objectSelected, selectedPosition);
                        }
                        dialog.dismiss();
                    }
                }).setNegativeButton(R.string.global_btn_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(getTag(), "setNegativeButton onClick i: " + which);
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



    public void showListFilterChooser(String titulo, Enum[] enums, Enum anEnum, CallbackFilterChooserBottomDialog callbackFilter) {

        FilterChooserBottomSheetDialog dialogFragment= new FilterChooserBottomSheetDialog();
        dialogFragment.setDialogTitle(titulo);
        dialogFragment.setItems(enums, anEnum);
        dialogFragment.setCallbackFilter(callbackFilter);
        dialogFragment.show(getSupportFragmentManager(), dialogFragment.getTag());
    }


}
