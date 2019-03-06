package com.consultoraestrategia.ss_crmeducativo.portal.main.login;

import android.content.Intent;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.login.ui.LoginActivity;
import com.consultoraestrategia.ss_crmeducativo.portal.main.view.Main;

public class Login extends LoginActivity {

    private String TAG = Login.class.getSimpleName();

    @Override
    public void goToActivity(int idUsuario) {
        Log.d(TAG, "idUsuario : " + idUsuario);
        Intent intent = new Intent(this, Main.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
