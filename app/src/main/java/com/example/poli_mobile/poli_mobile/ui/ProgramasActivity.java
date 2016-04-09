package com.example.poli_mobile.poli_mobile.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
public class ProgramasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgramasListAdapter programasListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programas);
        recyclerView = (RecyclerView) findViewById(R.id.rvFacultades);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(AppContext.getContext()));

        if (ApplicationSession.getProgramas().size() > 0) {
            programasListAdapter = new ProgramasListAdapter(ApplicationSession.getProgramas());
            recyclerView.setAdapter(programasListAdapter);
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
