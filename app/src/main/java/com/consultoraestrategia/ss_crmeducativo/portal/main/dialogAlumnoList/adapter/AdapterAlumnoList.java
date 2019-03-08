package com.consultoraestrategia.ss_crmeducativo.portal.main.dialogAlumnoList.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.HijoUi;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterAlumnoList extends RecyclerView.Adapter<AdapterAlumnoList.Holder> {

    private List<HijoUi> hijoUiList = new ArrayList<>();
    private Listener listener;

    public AdapterAlumnoList(List<HijoUi> hijoUiList, Listener listener) {
        this.hijoUiList.clear();
        this.hijoUiList.addAll(hijoUiList);
        this.listener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alumno_selected, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(hijoUiList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return hijoUiList.size();
    }

    public static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.hijo_etiqueta)
        TextView hijoEtiqueta;
        @BindView(R.id.hijo_imagen)
        CircleImageView hijoImagen;
        @BindView(R.id.hijo_nombre)
        TextView hijoNombre;
        private Listener listener;
        private HijoUi hijoUi;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bind(final HijoUi hijoUi, Listener listener) {
            this.listener = listener;
            this.hijoUi = hijoUi;
            hijoNombre.setText(hijoUi.getNombre());
            Glide.with(itemView.getContext())
                    .load(hijoUi.getUrl_imagen())
                    .apply(new RequestOptions()
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            hijoImagen.setImageDrawable(null);
                            hijoEtiqueta.setText(hijoUi.getEtiqueta());
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            hijoEtiqueta.setText("");
                            return false;
                        }
                    })
                    .into(hijoImagen);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                default:
                    listener.onClickSelectedHijo(hijoUi);
                    break;
            }
        }
    }

    public interface Listener {
        void onClickSelectedHijo(HijoUi hijoUi);
    }
}
