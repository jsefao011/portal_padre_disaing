package com.consultoraestrategia.ss_crmeducativo.services.data.source.base.local;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;

/**
 * Created by SCIEV on 18/05/2018.
 */

public abstract class ServiceLocalDataSource<T> implements ServiceDataSource<T> {

    @Override
    public void getDatosLogin(String usuarioId, ObjectCallBack<T> callBack) {

    }

    @Override
    public void getDatosExportar(ObjectCallBack<T> callBack) {

    }

    @Override
    public void SendDatos(T item, Class<BERespuesta> respuestaClass, DosObjectCallBack<T, BERespuesta> callBack)  {

    }

    @Override
    public void changeEstado(T item, int syncFlag, SuccessCallBack callBack) {

    }

    @Override
    public void saveDatos(T item, SuccessCallBack callBack) {

    }

    @Override
    public <V extends BEVariables> void getDatosImportar(V importar, ObjectCallBack<T> callBack) {

    }
}
