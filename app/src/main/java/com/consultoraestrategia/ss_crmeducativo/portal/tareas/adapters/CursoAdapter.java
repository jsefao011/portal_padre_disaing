package com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.portal.tareas.adapters.holders.CursoHolder;
import com.consultoraestrategia.ss_crmeducativo.portal.tareas.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.List;

import butterknife.BindView;

public class CursoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<CursoUi> cursoUiList;


    public CursoAdapter(List<CursoUi> cursoUiList) {
        this.cursoUiList = cursoUiList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CursoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_curso_tarea, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CursoUi cursoUi = cursoUiList.get(position);
        ((CursoHolder) holder).bind(cursoUi);
    }

    @Override
    public int getItemCount() {
        return cursoUiList.size();
    }

    public void setObjectList(List<CursoUi> cursoUiList) {
        this.cursoUiList.clear();
        this.cursoUiList.addAll(cursoUiList);
        notifyDataSetChanged();
    }
}
