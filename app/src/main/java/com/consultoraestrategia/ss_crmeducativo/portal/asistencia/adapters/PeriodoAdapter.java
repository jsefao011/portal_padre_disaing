package com.consultoraestrategia.ss_crmeducativo.portal.asistencia.adapters;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.lib.verticalTextView.VerticalTextView;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.asistencia.listener.PeriodoListener;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 21/10/2017.
 */

public class PeriodoAdapter extends RecyclerView.Adapter<PeriodoAdapter.ViewHolder> {

    public static String TAG = PeriodoAdapter.class.getSimpleName();

    private List<PeriodoUi> periodoUiList;
    private PeriodoListener listener;


    public PeriodoAdapter(List<PeriodoUi> periodoUiList, PeriodoListener listener) {
        this.periodoUiList = periodoUiList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_tab_periodo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(periodoUiList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return periodoUiList.size();
    }

    public void setPeriodoList(List<PeriodoUi> periodoList) {
        this.periodoUiList.clear();
        this.periodoUiList.addAll(periodoList);
        notifyDataSetChanged();
    }


    public void togglePeriodo(PeriodoUi oldPeriodo, PeriodoUi periodoSelected) {
        int positionOld = periodoUiList.indexOf(oldPeriodo);
        int positionNew = periodoUiList.indexOf(periodoSelected);
        if(oldPeriodo!=null)oldPeriodo.setStatus(false);
        periodoSelected.setStatus(true);
        if(positionOld!=-1)periodoUiList.set(positionOld, oldPeriodo);
        if(positionNew!=-1)periodoUiList.set(positionNew, periodoSelected);
        notifyItemChanged(positionOld);
        notifyItemChanged(positionNew);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.root)
        ConstraintLayout root;
        @BindView(R.id.textTitle)
        VerticalTextView textTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final PeriodoUi periodo, final PeriodoListener listener ){
            final boolean isSelected = periodo.isStatus();
            LayerDrawable shape = (LayerDrawable) ContextCompat.getDrawable(itemView.getContext(),R.drawable.border_radius_left);
            final GradientDrawable gradientDrawable = (GradientDrawable) shape.findDrawableByLayerId(R.id.shape);

            int colorFondo = ContextCompat.getColor(itemView.getContext(),R.color.colorPrimary);

            Log.d(TAG,"periodoName : "+ periodo.toString());
            if (isSelected) {
                gradientDrawable.setStroke(2, colorFondo);
                textTitle.setTextColor(colorFondo);
                gradientDrawable.setColor(ContextCompat.getColor(itemView.getContext(),R.color.md_white_1000));
            } else {
                gradientDrawable.setColor(colorFondo);
                gradientDrawable.setStroke(2,colorFondo);
                textTitle.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_white_1000));
            }
            Log.d(TAG,"periodoName : "+ periodo.getTipoName());
            root.setBackground(shape);
            textTitle.setText(periodo.getTipoName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onPeriodoSelected(periodo);
                    }
                }
            });

        }
    }


}
