package com.example.poli_mobile.poli_mobile.ui;

import java.util.ArrayList;
import java.util.List;

import com.example.poli_mobile.R;
import com.example.poli_mobile.R.layout;
import com.example.poli_mobile.poli_mobile.listeners.FacultadesListener;
import com.example.poli_mobile.poli_mobile.network.NetworkManager;
import com.example.poli_mobile.poli_mobile.utilidades.AppContext;
import com.example.poli_mobile.poli_mobile_entidades.Facultad;
import com.example.poli_mobile.poli_mobile_entidades.Noticia;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FacultadesFragment extends android.support.v4.app.Fragment implements FacultadesListener {
    private FacultadesListAdapter adapter;
    private NetworkManager nManager;
    private FacultadesListener facultadesListener;
    private RecyclerView recyclerView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView)view.findViewById(R.id.rvFacultades);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(AppContext.getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(layout.fragment_facultades, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nManager = NetworkManager.getNManagerInstance();
        nManager.setFacultadesListener(this);
        nManager.getFacultades();
    }

    @Override
    public void onFacultades(List<Facultad> facultades) {
        adapter = new FacultadesListAdapter(facultades);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFacultadesError() {

    }
}
