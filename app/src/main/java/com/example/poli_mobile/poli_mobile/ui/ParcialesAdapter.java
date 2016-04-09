package com.example.poli_mobile.poli_mobile.ui;

/**
 * Created by TEKUSPC4 on 31/12/15.
 */

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.poli_mobile.R;
import com.example.poli_mobile.poli_mobile_entidades.ProgramacionParcial;

import java.util.List;

/**
 * Created by venkataprasad on 02-01-2015.
 */
public class ParcialesAdapter extends RecyclerView.Adapter<ParcialesAdapter.ViewHolder> {

    private static List<ProgramacionParcial> dataSet;

    public ParcialesAdapter(List<ProgramacionParcial> events) {
        dataSet = events;
    }

    private static final int TYPE_TREND = 0;
    private static final int TYPE_FOOTER = 1;

    @Override
    public ParcialesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.list_parciales, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ParcialesAdapter.ViewHolder viewHolder, int i) {

        final ProgramacionParcial fp = dataSet.get(i);
        viewHolder.tvMateria.setText(fp.getCodigoMateria());
        viewHolder.tvHora.setText(fp.getHoraParcial());
        viewHolder.tvFecha.setText(fp.getDiaParcial());
        viewHolder.tvGrupo.setText(fp.getGrupoMateria());
        viewHolder.tvSede.setText(fp.getSedeParcial());
        viewHolder.tvAula.setText(fp.getAulaParcial());
        viewHolder.feed = fp;
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvMateria, tvSede, tvGrupo, tvFecha, tvAula, tvHora;
        public ProgramacionParcial feed;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            tvFecha = (TextView) itemLayoutView.findViewById(R.id.tvFecha);
            tvHora = (TextView) itemLayoutView.findViewById(R.id.tvHora);
            tvMateria = (TextView) itemLayoutView.findViewById(R.id.tvMateria);
            tvGrupo = (TextView) itemLayoutView.findViewById(R.id.tvGrupo);
            tvSede = (TextView) itemLayoutView.findViewById(R.id.tvSede);
            tvAula = (TextView) itemLayoutView.findViewById(R.id.tvAula);

        }

    }
}

