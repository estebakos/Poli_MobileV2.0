package com.example.poli_mobile.poli_mobile.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.poli_mobile.R;
import com.example.poli_mobile.poli_mobile.utilidades.AppContext;
import com.example.poli_mobile.poli_mobile.utilidades.ApplicationSession;
import com.example.poli_mobile.poli_mobile_entidades.Programa;
import com.example.poli_mobile.poli_mobile_entidades.asesoriaAcademica;

/**
 * Created by TEBAN on 8/04/2016.
 */
public class AsesoriaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Asesoria_DtlAdapter asesoriaAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asesoria_dtl);
        recyclerView = (RecyclerView) findViewById(R.id.rvTrends);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(AppContext.getContext()));
        asesoriaAdapter = new Asesoria_DtlAdapter(ApplicationSession.getAsesoria());
        recyclerView.setAdapter(asesoriaAdapter);
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
