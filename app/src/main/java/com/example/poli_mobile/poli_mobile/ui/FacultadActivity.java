package com.example.poli_mobile.poli_mobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.poli_mobile.R;
import com.example.poli_mobile.poli_mobile.utilidades.AppContext;
import com.example.poli_mobile.poli_mobile.utilidades.ApplicationSession;
import com.example.poli_mobile.poli_mobile_entidades.Facultad;

/**
 * Created by TEBAN on 8/04/2016.
 */
public class FacultadActivity extends AppCompatActivity {

    private TextView tvNombre, tvGeneralidades, tvMision, tvVision, tvValores, tvFundamentos, tvPrincipios;
    private Button btnProgramas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultad);
        tvFundamentos = (TextView) findViewById(R.id.tvFundamentos);
        tvGeneralidades = (TextView) findViewById(R.id.tvGeneralidades);
        tvMision = (TextView) findViewById(R.id.tvMision);
        tvNombre = (TextView) findViewById(R.id.tvName);
        tvPrincipios = (TextView) findViewById(R.id.tvPrincipios);
        tvValores = (TextView) findViewById(R.id.tvValores);
        tvVision = (TextView) findViewById(R.id.tvVision);
        btnProgramas = (Button) findViewById(R.id.btnProgramas);

        if(ApplicationSession.getFacultad() != null)
        {
            Facultad facultad = ApplicationSession.getFacultad();
            tvFundamentos.setText(facultad.getFundamentos());
            tvValores.setText(facultad.getValores());
            tvVision.setText(facultad.getVision());
            tvPrincipios.setText(facultad.getPrincipios());
            tvNombre.setText(facultad.getNom_facultad());
            tvMision.setText(facultad.getMision());
            tvGeneralidades.setText(facultad.getGeneralidades());
            ApplicationSession.setProgramas(facultad.getProgramas());
        }

        btnProgramas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppContext.getContext(), ProgramasActivity.class);
                AppContext.getContext().startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
