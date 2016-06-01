package com.example.poli_mobile.poli_mobile.ui;

/**
 * Created by TEKUSPC4 on 31/12/15.
 */

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
import com.example.poli_mobile.poli_mobile_entidades.ProgramacionParcial;
import com.example.poli_mobile.poli_mobile_entidades.asesoriaAcademica;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by venkataprasad on 02-01-2015.
 */
public class AsesoriaAdapter extends RecyclerView.Adapter<AsesoriaAdapter.ViewHolder> {

    private static List<asesoriaAcademica> dataSet, total;

    public AsesoriaAdapter(List<asesoriaAcademica> events, List<asesoriaAcademica> total) {
        dataSet = events;
        this.total = total;
    }

    private static final int TYPE_TREND = 0;
    private static final int TYPE_FOOTER = 1;

    @Override
    public AsesoriaAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.list_asesoria, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(AsesoriaAdapter.ViewHolder viewHolder, int i) {
        final asesoriaAcademica fp = dataSet.get(i);
        viewHolder.tvMateria.setText(fp.getNombreMateria());
        viewHolder.tvCodigoMateria.setText(fp.getCodigoMateria());
        viewHolder.tvCreditos.setText(fp.getCreditos());
        viewHolder.cdAsesoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<asesoriaAcademica> listAsesoria = new ArrayList<asesoriaAcademica>();
                for(asesoriaAcademica a : total)
                {
                    if(a.getCodigoMateria().equals(fp.getCodigoMateria()))
                    {
                        listAsesoria.add(a);
                    }
                }
                ApplicationSession.setAsesoria(listAsesoria);
                Intent intent = new Intent(AppContext.getContext() , AsesoriaActivity.class);
                AppContext.getContext().startActivity(intent);
            }
        });
        viewHolder.feed = fp;
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvMateria, tvCodigoMateria, tvCreditos;
        public asesoriaAcademica feed;
        public CardView cdAsesoria;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            tvCodigoMateria = (TextView) itemLayoutView.findViewById(R.id.tvCodigoMateria);
            tvMateria = (TextView) itemLayoutView.findViewById(R.id.tvAsignatura);
            tvCreditos = (TextView) itemLayoutView.findViewById(R.id.tvCreditos);
            cdAsesoria = (CardView) itemLayoutView.findViewById(R.id.cdAsesoria);
        }

    }
}

