package com.example.poli_mobile.poli_mobile.ui;

/**
 * Created by TEKUSPC4 on 31/12/15.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.poli_mobile.R;
import com.example.poli_mobile.poli_mobile_entidades.AutoevalacionDocente;
import com.example.poli_mobile.poli_mobile_entidades.ProgramacionParcial;

import java.util.List;

/**
 * Created by venkataprasad on 02-01-2015.
 */
public class AutoEvalAdapter extends RecyclerView.Adapter<AutoEvalAdapter.ViewHolder> {

    private static List<AutoevalacionDocente> dataSet;

    public AutoEvalAdapter(List<AutoevalacionDocente> events) {
        dataSet = events;
    }

    private static final int TYPE_TREND = 0;
    private static final int TYPE_FOOTER = 1;

    @Override
    public AutoEvalAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.list_autoevaluacion, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(AutoEvalAdapter.ViewHolder viewHolder, int i) {

        final AutoevalacionDocente fp = dataSet.get(i);
        viewHolder.tvMateria.setText(fp.getNombreMateria());
        viewHolder.tvCodigoMateria.setText(fp.getCodigoMateria());
        viewHolder.tvSemestre.setText(fp.getSemestre());
        viewHolder.tvGrupo.setText(fp.getGrupoMateria());
        viewHolder.tvAnio.setText(fp.getAnio());
        viewHolder.tvNota.setText(fp.getNotaMateria());
        viewHolder.feed = fp;
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvMateria, tvCodigoMateria, tvGrupo, tvAnio, tvSemestre, tvNota;
        public AutoevalacionDocente feed;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            tvNota = (TextView) itemLayoutView.findViewById(R.id.tvNota);
            tvSemestre = (TextView) itemLayoutView.findViewById(R.id.tvSemestre);
            tvMateria = (TextView) itemLayoutView.findViewById(R.id.tvNombreMateria);
            tvGrupo = (TextView) itemLayoutView.findViewById(R.id.tvGrupo);
            tvAnio = (TextView) itemLayoutView.findViewById(R.id.tvAnio);
            tvCodigoMateria = (TextView) itemLayoutView.findViewById(R.id.tvCodigoMateria);

        }

    }
}

