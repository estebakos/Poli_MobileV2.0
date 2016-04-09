package com.example.poli_mobile.poli_mobile.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.poli_mobile.R;
import com.example.poli_mobile.poli_mobile.utilidades.AppContext;
import com.example.poli_mobile.poli_mobile.utilidades.ApplicationSession;
import com.example.poli_mobile.poli_mobile_entidades.Facultad;

import java.util.List;

/**
 * Created by TEKUSPC4 on 24/09/15.
 */
public class FacultadesListAdapter extends RecyclerView.Adapter<FacultadesListAdapter.DeviceViewHolder> {

    public static List<Facultad> facultades;


    FacultadesListAdapter(List<Facultad> facultades) {
        this.facultades = facultades;
    }

    @Override
    public FacultadesListAdapter.DeviceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.card_facultades_list_view, viewGroup, false);
        DeviceViewHolder dvh = new DeviceViewHolder(itemLayoutView);
        return dvh;
    }


    @Override
    public void onBindViewHolder(FacultadesListAdapter.DeviceViewHolder deviceViewHolder, final int i) {
        deviceViewHolder.tvFacultad.setText(facultades.get(i).getNom_facultad());
        deviceViewHolder.cvfacultad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplicationSession.setFacultad(facultades.get(i));
                Intent intent = new Intent(AppContext.getContext(), FacultadActivity.class);
                AppContext.getContext().startActivity(intent);
            }
        });
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
        public TextView tvFacultad;
        public CardView cvfacultad;

        DeviceViewHolder(final View itemView) {
            super(itemView);
            tvFacultad = (TextView) itemView.findViewById(R.id.tvFacultadNombre);
            cvfacultad = (CardView) itemView.findViewById(R.id.cvFacultades);
        }
    }

}
