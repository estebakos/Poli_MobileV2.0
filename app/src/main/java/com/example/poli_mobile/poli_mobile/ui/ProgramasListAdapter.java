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
import com.example.poli_mobile.poli_mobile_entidades.Programa;

import java.util.List;

/**
 * Created by TEKUSPC4 on 24/09/15.
 */
public class ProgramasListAdapter extends RecyclerView.Adapter<ProgramasListAdapter.DeviceViewHolder> {

    public static List<Programa> facultades;


    ProgramasListAdapter(List<Programa> facultades) {
        this.facultades = facultades;
    }

    @Override
    public ProgramasListAdapter.DeviceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.card_programas_list_view, viewGroup, false);
        DeviceViewHolder dvh = new DeviceViewHolder(itemLayoutView);
        return dvh;
    }


    @Override
    public void onBindViewHolder(ProgramasListAdapter.DeviceViewHolder deviceViewHolder, final int i) {
        deviceViewHolder.tvPrograma.setText(facultades.get(i).getNom_programa());
        deviceViewHolder.cvfacultad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplicationSession.setPrograma(facultades.get(i));
                Intent intent = new Intent(AppContext.getContext(), ProgramaActivity.class);
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
        public TextView tvPrograma;
        public CardView cvfacultad;

        DeviceViewHolder(final View itemView) {
            super(itemView);
            tvPrograma = (TextView) itemView.findViewById(R.id.tvProgramaNombre);
            cvfacultad = (CardView) itemView.findViewById(R.id.cvFacultades);
        }
    }

}
