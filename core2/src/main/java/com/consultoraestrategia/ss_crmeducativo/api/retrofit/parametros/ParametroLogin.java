package com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SCIEV on 26/07/2018.
 */

public class ParametroLogin extends ApiRetrofit.Parameters {
    @SerializedName("vint_UsuarioId")
    int usuarioId;

    public ParametroLogin() {
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
