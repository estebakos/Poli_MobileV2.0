package com.example.poli_mobile.poli_mobile.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.poli_mobile.R;
import com.example.poli_mobile.poli_mobile.utilidades.ApplicationSession;
import com.example.poli_mobile.poli_mobile_entidades.Facultad;
import com.example.poli_mobile.poli_mobile_entidades.Programa;

/**
 * Created by TEBAN on 8/04/2016.
 */
public class ProgramaActivity extends AppCompatActivity {

    private TextView tvNombre, tvEmail, tvMision, tvVision, tvModalidad, tvCreditos, tvPresentacion, tvPerfil, tvContacto, tvPlan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programa);
        tvContacto = (TextView) findViewById(R.id.tvContacto);
        tvCreditos = (TextView) findViewById(R.id.tvCreditos);
        tvMision = (TextView) findViewById(R.id.tvMision);
        tvNombre = (TextView) findViewById(R.id.tvName);
        tvModalidad = (TextView) findViewById(R.id.tvModalidad);
        tvPerfil = (TextView) findViewById(R.id.tvPerfil);
        tvVision = (TextView) findViewById(R.id.tvVision);
        tvPresentacion = (TextView) findViewById(R.id.tvPresentacion);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvPlan = (TextView) findViewById(R.id.tvPlan);

        if(ApplicationSession.getPrograma() != null)
        {
            Programa programa = ApplicationSession.getPrograma();
            tvPerfil.setText(programa.getPerfil_Profesional());
            tvPlan.setText(programa.getPlan_estudios());
            tvVision.setText(programa.getVision());
            tvEmail.setText(programa.getEmail());
            tvNombre.setText(programa.getNom_programa());
            tvMision.setText(programa.getMision());
            tvModalidad.setText(programa.getModalidad());
            tvContacto.setText(programa.getContacto());
            tvCreditos.setText(programa.getCreditos());
            tvPresentacion.setText(programa.getPresentacion());
        }
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
