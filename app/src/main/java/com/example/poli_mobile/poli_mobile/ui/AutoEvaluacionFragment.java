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
import com.example.poli_mobile.poli_mobile.listeners.AutoEvalListener;
import com.example.poli_mobile.poli_mobile.network.NetworkManager;
import com.example.poli_mobile.poli_mobile.utilidades.AppContext;
import com.example.poli_mobile.poli_mobile_entidades.AutoevalacionDocente;

import java.util.List;

/**
 * Created by TEBAN on 8/04/2016.
 */
public class AutoEvaluacionFragment extends Fragment implements AutoEvalListener {

    private RecyclerView recyclerView;
    private AutoEvalAdapter autoEvalAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_eval_docentes, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvEvalDocentes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(AppContext.getContext()));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetworkManager.getNManagerInstance().setAutoEvalListener(this);

        NetworkManager.getNManagerInstance().obtenerAutoEvaluacion();
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
    public void AutoEvalLista(List<AutoevalacionDocente> lCita) {
        if(lCita != null && lCita.size() >0){
            autoEvalAdapter = new AutoEvalAdapter(lCita);
            recyclerView.setAdapter(autoEvalAdapter);
        }
    }

    @Override
    public void AutoEvalProblema() {

    }
}
