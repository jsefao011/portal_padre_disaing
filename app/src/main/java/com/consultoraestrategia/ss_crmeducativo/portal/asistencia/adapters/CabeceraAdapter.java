package com.consultoraestrategia.ss_crmeducativo.portal.asistencia.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.adapters.holders.CabeceraHolderDetalle;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.adapters.holders.CabeceraHolderGeneral;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.entities.ValorAsistenciaGeneralUi;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.entities.ValorAsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.List;

public class CabeceraAdapter extends RecyclerView.Adapter{

    List<Object> objectList;
    public static final  int DETALLE=0, GENERAL=1;

    public CabeceraAdapter(List<Object> objectList) {
        this.objectList = objectList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case GENERAL:
                return new CabeceraHolderGeneral(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cuerpo_caso, parent, false));
            default :
                return new CabeceraHolderDetalle(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cuerpo_caso, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Object object= objectList.get(position);
        switch (holder.getItemViewType()){
            case GENERAL:
                if(object instanceof ValorAsistenciaGeneralUi)
                ((CabeceraHolderGeneral) holder).bind((ValorAsistenciaGeneralUi)object);
                break;
             default :
                 if(object instanceof ValorAsistenciaUi)
                     ((CabeceraHolderDetalle) holder).bind((ValorAsistenciaUi)object);
                 break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object object=objectList.get(position);
        if(object instanceof ValorAsistenciaGeneralUi)return GENERAL;
        else return DETALLE;

    }

    @Override
    public int getItemCount() {
        return objectList.size();


    }
    public void setObjectList(List<Object>objectList){
        this.objectList.clear();
        this.objectList.addAll(objectList);
        notifyDataSetChanged();
    }
}
