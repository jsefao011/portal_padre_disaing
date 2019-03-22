package com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.adapters.holder;

import android.support.constraint.ConstraintLayout;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.consultoraestrategia.ss_crmeducativo.portal.rubroInformacion.entities.ImagenColumn;
import com.consultoraestrategia.ss_crmeducativo_portal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by SCIEV on 8/03/2018.
 */

public class SelectorIconosRowViewHolder extends ColumnHeaderViewHolder<ImagenColumn>
{
    @BindView(R.id.imgResultado)
    CircleImageView imgResultado;
    @BindView(R.id.root)
    ConstraintLayout root;
    public SelectorIconosRowViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void bind(ImagenColumn imagenColumn) {

        Glide.with(itemView.getContext())
                .load(imagenColumn.getContenido())
                .apply(new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_error_outline_black))
                .into(imgResultado);
    }
}
