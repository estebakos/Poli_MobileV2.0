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
import com.example.poli_mobile.poli_mobile.listeners.AsesorialListener;
import com.example.poli_mobile.poli_mobile.network.NetworkManager;
import com.example.poli_mobile.poli_mobile.utilidades.AppContext;
import com.example.poli_mobile.poli_mobile_entidades.asesoriaAcademica;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TEBAN on 8/04/2016.
 */
public class AsesoriaFragment extends Fragment implements AsesorialListener {

    private RecyclerView recyclerView;
    private AsesoriaAdapter asesoriaAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_asesoria, container, false);
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
        NetworkManager.getNManagerInstance().setAsesorialListener(this);
        NetworkManager.getNManagerInstance().obtenerAsesoria();
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
    public void Asesoria(List<asesoriaAcademica> lCita) {
        if (lCita != null && lCita.size() > 0) {
            List<asesoriaAcademica> newAsesoria = new ArrayList<>();
            for (asesoriaAcademica a : lCita) {
                boolean exists = false;
                for(asesoriaAcademica b : newAsesoria)
                {
                    if(a.getCodigoMateria().equals(b.getCodigoMateria()))
                    {
                        exists = true;
                    }
                }
                if(exists == false)
                {
                    newAsesoria.add(a);
                }
            }
            asesoriaAdapter = new AsesoriaAdapter(newAsesoria, lCita);
            recyclerView.setAdapter(asesoriaAdapter);
        }
    }

    @Override
    public void AsesoriaFail() {

    }
}
