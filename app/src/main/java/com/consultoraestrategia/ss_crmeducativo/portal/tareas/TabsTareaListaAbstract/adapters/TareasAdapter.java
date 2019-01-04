package com.consultoraestrategia.ss_crmeducativo.portal.tareas.TabsTareaListaAbstract.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CursoTareasUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.List;

public class TareasAdapter extends RecyclerView.Adapter<TareasCursosHolder>  {

    List<CursoTareasUi> cursoTareasUiList;
    String TAG = TareasAdapter.class.getSimpleName();
    Context context;



    public TareasAdapter(List<CursoTareasUi> listarTareasUis, Context context) {
        this.cursoTareasUiList=listarTareasUis;
        this.context=context;
    }


    @NonNull
    @Override
    public TareasCursosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tabla_tarea, parent, false);
        return new TareasCursosHolder(view) ;
    }



    @Override
    public void onBindViewHolder(@NonNull TareasCursosHolder holder, int position) {
        CursoTareasUi cursoTareasUi= cursoTareasUiList.get(position);
        holder.bind( cursoTareasUi , context);
    }

    @Override
    public int getItemCount() {
        return cursoTareasUiList.size();
    }

        public void setCursosList(List<CursoTareasUi> listarTareasUis) {
        this.cursoTareasUiList.clear();
        this.cursoTareasUiList.addAll(listarTareasUis);
        notifyDataSetChanged();
    }


}
