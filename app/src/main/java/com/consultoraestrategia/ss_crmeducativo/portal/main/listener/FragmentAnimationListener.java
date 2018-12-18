package com.consultoraestrategia.ss_crmeducativo.portal.main.listener;

import android.support.v4.app.Fragment;
import android.view.animation.Animation;

public interface FragmentAnimationListener {

    void onAnimationStart(Fragment fragment, Animation animation, boolean enter );
    void onAnimationEnd(Fragment fragment,Animation animation, boolean enter);
}
