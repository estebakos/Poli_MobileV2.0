package com.example.poli_mobile.poli_mobile.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.poli_mobile.R;
import com.example.poli_mobile.poli_mobile.listeners.CitaListener;
import com.example.poli_mobile.poli_mobile.network.NetworkManager;
import com.example.poli_mobile.poli_mobile.utilidades.AppContext;
import com.example.poli_mobile.poli_mobile_entidades.CitaMedica;

import java.util.List;

/**
 * Created by TEBAN on 8/04/2016.
 */
public class CitasFragment extends Fragment implements CitaListener {

    private RecyclerView recyclerView;
    private CitaListener citaListener;
    private CitasAdapter citasAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cita_medica, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvTrends);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(AppContext.getContext()));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetworkManager.getNManagerInstance().setCitaListener(this);
        NetworkManager.getNManagerInstance().ObtenerCitas();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void CitaLista(List<CitaMedica> lCita) {
        if(lCita != null && lCita.size() >0){
            citasAdapter = new CitasAdapter(lCita);
            recyclerView.setAdapter(citasAdapter);
        }
    }

    @Override
    public void CitaFallida() {

    }
}
