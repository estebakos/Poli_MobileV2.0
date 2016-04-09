package com.example.poli_mobile.poli_mobile.ui;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.poli_mobile.R;
import com.example.poli_mobile.poli_mobile.utilidades.AppContext;
import com.example.poli_mobile.poli_mobile.utilidades.ApplicationSession;
import com.example.poli_mobile.poli_mobile_entidades.CalendarioAcademico;
import com.example.poli_mobile.poli_mobile_entidades.Facultad;

import java.util.List;

/**
 * Created by TEKUSPC4 on 24/09/15.
 */
public class CalendarioListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static List<CalendarioAcademico> facultades;
    private int type;

    CalendarioListAdapter(List<CalendarioAcademico> facultades) {
        this.facultades = facultades;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i > 0) {
            View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.card_calendario_list_view, viewGroup, false);
            DeviceViewHolder dvh = new DeviceViewHolder(itemLayoutView);
            return dvh;
        } else {
            View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.card_calendario_title_list_view, viewGroup, false);
            DeviceTitleViewHolder dvh = new DeviceTitleViewHolder(itemLayoutView);
            return dvh;
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder deviceViewHolder, final int i) {
        if (deviceViewHolder instanceof DeviceViewHolder) {
            DeviceViewHolder viewHolder = (DeviceViewHolder) deviceViewHolder;
            final CalendarioAcademico fp = (CalendarioAcademico) facultades.get(i);
            viewHolder.tvActividad.setText(fp.getActividad());
            viewHolder.tvFecha.setText(fp.getFecha());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return facultades.size();
    }

    public static class DeviceViewHolder extends RecyclerView.ViewHolder {
        public TextView tvActividad , tvFecha;

        DeviceViewHolder(final View itemView) {
            super(itemView);
            tvActividad = (TextView) itemView.findViewById(R.id.tvActividad);
            tvFecha = (TextView) itemView.findViewById(R.id.tvFecha);
        }
    }

    public static class DeviceTitleViewHolder extends RecyclerView.ViewHolder {
        public TextView tvFacultad;
        public CardView cvfacultad;

        DeviceTitleViewHolder(final View itemView) {
            super(itemView);
            tvFacultad = (TextView) itemView.findViewById(R.id.tvFacultadNombre);
            cvfacultad = (CardView) itemView.findViewById(R.id.cvFacultades);
        }
    }

}
