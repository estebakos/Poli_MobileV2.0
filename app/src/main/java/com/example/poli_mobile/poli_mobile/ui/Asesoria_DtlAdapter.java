package com.example.poli_mobile.poli_mobile.ui;

/**
 * Created by TEKUSPC4 on 31/12/15.
 */

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.poli_mobile.R;
import com.example.poli_mobile.poli_mobile.utilidades.ApplicationSession;
import com.example.poli_mobile.poli_mobile_entidades.asesoriaAcademica;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by venkataprasad on 02-01-2015.
 */
public class Asesoria_DtlAdapter extends RecyclerView.Adapter<Asesoria_DtlAdapter.ViewHolder> {

    private static List<asesoriaAcademica> dataSet;

    public Asesoria_DtlAdapter(List<asesoriaAcademica> events) {
        dataSet = events;
    }

    private static final int TYPE_TREND = 0;
    private static final int TYPE_FOOTER = 1;

    @Override
    public Asesoria_DtlAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.list_asesoria_detalle, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(Asesoria_DtlAdapter.ViewHolder viewHolder, int i) {
        final asesoriaAcademica fp = dataSet.get(i);
        viewHolder.tvNombreMateria.setText(fp.getNombreMateria());
        viewHolder.tvCodigoMateria.setText(fp.getCodigoMateria());
        viewHolder.tvCreditos.setText(fp.getCreditos());
        viewHolder.tvAula.setText(fp.getAula());
        viewHolder.tvDia.setText(fp.getDia());
        viewHolder.tvDocente.setText(fp.getNombreDocente());
        viewHolder.tvGrupo.setText(fp.getGrupoMateria());
        viewHolder.tvMarcacion.setText(fp.getMarcacion());
        viewHolder.tvSede.setText(fp.getSede());
        viewHolder.tvHora.setText(fp.getHorario());
        viewHolder.feed = fp;
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNombreMateria, tvCodigoMateria, tvGrupo, tvDocente, tvDia, tvHora, tvAula, tvSede , tvMarcacion, tvCreditos;
        public asesoriaAcademica feed;
        public CardView cdAsesoria;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            tvAula = (TextView) itemLayoutView.findViewById(R.id.tvAula);
            tvCodigoMateria = (TextView) itemLayoutView.findViewById(R.id.tvCodigoMateria);
            tvCreditos = (TextView) itemLayoutView.findViewById(R.id.tvCreditos);
            tvDia = (TextView) itemLayoutView.findViewById(R.id.tvDia);
            tvDocente = (TextView) itemLayoutView.findViewById(R.id.tvDocente);
            tvGrupo = (TextView) itemLayoutView.findViewById(R.id.tvGrupoMateria);
            tvHora = (TextView) itemLayoutView.findViewById(R.id.tvHora);
            tvMarcacion = (TextView) itemLayoutView.findViewById(R.id.tvMarcacion);
            tvNombreMateria = (TextView) itemLayoutView.findViewById(R.id.tvNombreMateria);
            tvSede = (TextView) itemLayoutView.findViewById(R.id.tvSede);
            cdAsesoria = (CardView) itemLayoutView.findViewById(R.id.cdAsesoria);
        }

    }
}

