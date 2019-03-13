package com.consultoraestrategia.ss_crmeducativo.portal.programahorario.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.consultoraestrategia.ss_crmeducativo.portal.programahorario.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.util.CircularTextView;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CursoHorarioAdapter extends RecyclerView.Adapter<CursoHorarioAdapter.CursoHorarioHolder> {

    private List<CursoUi> cursosUIList;

    public CursoHorarioAdapter(List<CursoUi> cursosUIList) {
        this.cursosUIList = cursosUIList;
    }

    @NonNull
    @Override
    public CursoHorarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_programa_horario_curso, parent, false);
        return new CursoHorarioHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursoHorarioHolder holder, int position) {
        holder.bind(cursosUIList.get(position));
    }

    @Override
    public int getItemCount() {
        return cursosUIList.size();
    }

    public void setList(List<CursoUi> cursoUiList) {
        this.cursosUIList.clear();
        this.cursosUIList.addAll(cursoUiList);
        notifyDataSetChanged();
    }

    public class CursoHorarioHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.position)
        CircularTextView position;
        @BindView(R.id.txt_nombre)
        TextView txtNombre;
        @BindView(R.id.txt_detalle)
        TextView txtDetalle;
        public CursoHorarioHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(CursoUi cursosUI) {
            position.setStrokeWidth(2);
            position.setSolidColor(cursosUI.getColor1());
            position.setStrokeColor(cursosUI.getColor1());
            position.setText(String.valueOf(cursosUI.getPosicion()));
            position.setTextColor(Color.parseColor("#ffffff"));
            txtNombre.setText(cursosUI.getNombre());
            txtDetalle.setText(cursosUI.getDetalle());
        }
    }

}
