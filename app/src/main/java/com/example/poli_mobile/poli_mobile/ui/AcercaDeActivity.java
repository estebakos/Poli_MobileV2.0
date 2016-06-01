package com.example.poli_mobile.poli_mobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.poli_mobile.R;
import com.example.poli_mobile.poli_mobile.listeners.AcercaDeListener;
import com.example.poli_mobile.poli_mobile.network.NetworkManager;
import com.example.poli_mobile.poli_mobile.utilidades.AppContext;
import com.example.poli_mobile.poli_mobile.utilidades.ApplicationSession;
import com.example.poli_mobile.poli_mobile_entidades.*;
import com.example.poli_mobile.poli_mobile_entidades.AcercaDe;

import java.util.List;

/**
 * Created by TEBAN on 8/04/2016.
 */
public class AcercaDeActivity extends Fragment implements AcercaDeListener {

    private TextView tvDni, tvHimno, tvMision, tvVision, tvEscudo, tvBandera, tvPresentacion, tvHistoria;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvBandera = (TextView) view.findViewById(R.id.tvBandera);
        tvDni = (TextView) view.findViewById(R.id.tvDni);
        tvMision = (TextView) view.findViewById(R.id.tvMision);
        tvEscudo = (TextView) view.findViewById(R.id.tvEscudo);
        tvHimno = (TextView) view.findViewById(R.id.tvHimno);
        tvPresentacion = (TextView) view.findViewById(R.id.tvPresentacion);
        tvVision = (TextView) view.findViewById(R.id.tvVision);
        tvHistoria = (TextView) view.findViewById(R.id.tvHistoria);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_acerca_de, container, false);    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetworkManager.getNManagerInstance().setAcercaDeListener(this);
        NetworkManager.getNManagerInstance().getAcercaDe();
    }

    @Override
    public void AcercaDeListo(List<AcercaDe> lAcercaDe) {
        if(lAcercaDe != null && lAcercaDe.size() >0)
        {
            AcercaDe acercaDe = lAcercaDe.get(0);
            tvHistoria.setText(acercaDe.getHistoria());
            tvPresentacion.setText(acercaDe.getPresentacion());
            tvHimno.setText(acercaDe.getHimnoInstitucion());
            tvEscudo.setText(acercaDe.getEscudo());
            tvBandera.setText(acercaDe.getBanderaInstitucion());
            tvDni.setText(acercaDe.getDniUniversidad());
            tvMision.setText(acercaDe.getMision());
            tvVision.setText(acercaDe.getVision());
        }
    }

    @Override
    public void AcercaDeFallido() {

    }
}
