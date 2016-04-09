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
import com.example.poli_mobile.poli_mobile_entidades.CitaMedica;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by venkataprasad on 02-01-2015.
 */
public class CitasAdapter extends RecyclerView.Adapter<CitasAdapter.ViewHolder> {

    private static List<CitaMedica> dataSet;

    public CitasAdapter(List<CitaMedica> events) {
        dataSet = events;
    }

    private static final int TYPE_TREND = 0;
    private static final int TYPE_FOOTER = 1;

    @Override
    public CitasAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.list_cita_medica, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(CitasAdapter.ViewHolder viewHolder, int i) {

        final CitaMedica fp = dataSet.get(i);
        viewHolder.tvNombre.setText(fp.getNom_medico() + " " + fp.getApe_medico());
        viewHolder.tvHora.setText(fp.getHora());
        viewHolder.tvFecha.setText(fp.getDia());
        viewHolder.tvConsultorio.setText(fp.getUbi_consultorio());
        viewHolder.tvDescripcion.setText(fp.getDescripcion());

        switch (fp.getEstado())
        {
            case "1":
                viewHolder.tvEstado.setText("Asignada");
                viewHolder.vEstado.setBackgroundColor(Color.GREEN);
                break;
            case "2":
                viewHolder.tvEstado.setText("Cancelada");
                viewHolder.vEstado.setBackgroundColor(Color.RED);
                break;
            case "3":
                viewHolder.tvEstado.setText("Finalizada");
                viewHolder.vEstado.setBackgroundColor(Color.BLUE);
                break;
            default:
                viewHolder.tvEstado.setText("Asignada");
                viewHolder.vEstado.setBackgroundColor(Color.GREEN);

        }
        viewHolder.feed = fp;
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNombre, tvDescripcion, tvConsultorio, tvFecha, tvEstado, tvHora;
        public View vEstado;
        public CitaMedica feed;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            tvConsultorio = (TextView) itemLayoutView.findViewById(R.id.tvConsultorio);
            tvDescripcion = (TextView) itemLayoutView.findViewById(R.id.tvDescripcion);
            tvEstado = (TextView) itemLayoutView.findViewById(R.id.tvEstado);
            tvFecha = (TextView) itemLayoutView.findViewById(R.id.tvFecha);
            tvNombre = (TextView) itemLayoutView.findViewById(R.id.tvNombreMedico);
            tvHora = (TextView) itemLayoutView.findViewById(R.id.tvHora);
            vEstado = (View) itemLayoutView.findViewById(R.id.vEstado);

        }

    }
}

