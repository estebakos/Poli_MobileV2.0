package com.example.poli_mobile.poli_mobile.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.poli_mobile.R;
import com.example.poli_mobile.R.layout;
import com.example.poli_mobile.poli_mobile.listeners.CalendarioListener;
import com.example.poli_mobile.poli_mobile.listeners.FacultadesListener;
import com.example.poli_mobile.poli_mobile.network.NetworkManager;
import com.example.poli_mobile.poli_mobile.utilidades.AppContext;
import com.example.poli_mobile.poli_mobile_entidades.CalendarioAcademico;
import com.example.poli_mobile.poli_mobile_entidades.Facultad;

import java.util.List;

public class CalendarioFragment extends android.support.v4.app.Fragment implements CalendarioListener {
    private CalendarioListAdapter adapter;
    private NetworkManager nManager;
    private CalendarioListener calendarioListener;
    private RecyclerView recyclerView;
    private TextView tvTitle;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView)view.findViewById(R.id.rvCalendario);
        tvTitle = (TextView) view.findViewById(R.id.tvCalendarDate);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(AppContext.getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(layout.fragment_calendario_academico, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nManager = NetworkManager.getNManagerInstance();
        nManager.setCalendarioListener(this);
        nManager.getCalendario();
    }

    @Override
    public void onCalendario(List<CalendarioAcademico> calendario) {
        if(calendario != null && calendario.size() >0) {
            tvTitle.setText("Calendario Académico Periodo " + calendario.get(0).getAnio() + "-" + calendario.get(0).getSemestre());
            calendario.add(new CalendarioAcademico());
            adapter = new CalendarioListAdapter(calendario);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onCalendarioError() {

    }
}
