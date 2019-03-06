package com.consultoraestrategia.ss_crmeducativo.portal.demo.infoRubro;

import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.portal.demo.infoRubro.ui.InfoRubroView;


/**
 * Created by Jse on 15/09/2018.
 */

public interface InfoRubroPresenter {
    void setExtras(Bundle extras);

    void attachView(InfoRubroView view);

    void onCreate();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onBackPressed();

    void onViewCreated();

    void onDestroyView();
}
