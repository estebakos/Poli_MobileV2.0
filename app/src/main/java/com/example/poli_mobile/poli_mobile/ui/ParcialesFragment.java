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
import com.example.poli_mobile.poli_mobile.listeners.ParcialListener;
import com.example.poli_mobile.poli_mobile.network.NetworkManager;
import com.example.poli_mobile.poli_mobile.utilidades.AppContext;
import com.example.poli_mobile.poli_mobile_entidades.CitaMedica;
import com.example.poli_mobile.poli_mobile_entidades.ProgramacionParcial;

import java.util.List;

/**
 * Created by TEBAN on 8/04/2016.
 */
public class ParcialesFragment extends Fragment implements ParcialListener {

    private RecyclerView recyclerView;
    private ParcialesAdapter parcialesAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_parciales, container, false);
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
        NetworkManager.getNManagerInstance().setParcialListener(this);
        NetworkManager.getNManagerInstance().obtenerParciales();
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
    public void ParcialListo(List<ProgramacionParcial> lCita) {
        if(lCita != null && lCita.size() >0){
            parcialesAdapter = new ParcialesAdapter(lCita);
            recyclerView.setAdapter(parcialesAdapter);
        }
    }

    @Override
    public void ParcialFallido() {

    }
}
