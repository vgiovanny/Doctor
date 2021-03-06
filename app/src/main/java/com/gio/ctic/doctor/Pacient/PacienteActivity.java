package com.gio.ctic.doctor.Pacient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gio.ctic.doctor.NewHistoryActivity;
import com.gio.ctic.doctor.Otros.Doctor;
import com.gio.ctic.doctor.R;

import java.util.ArrayList;

public class PacienteActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private PacienteAdapter mPAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList results;
    Doctor doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);
        Intent i = getIntent();

        doctor= i.getParcelableExtra("doctor_login");

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPAdapter = new PacienteAdapter(getDataSet());
        mRecyclerView.setAdapter(mPAdapter);

    }


    private ArrayList<Paciente> getDataSet() {
        results = new ArrayList<>();
        results.add(new Paciente("001", "Gio Mondragon", "72223822",doctor.getId_doc()));
        results.add(new Paciente("002","Carlo Castro", "09412478",doctor.getId_doc()));
        results.add(new Paciente("003", "Sheper Derek", "72355196",doctor.getId_doc()));

        return results;
    }

    public void Lanza(int pos){
        Paciente paciente = (Paciente) results.get(pos);
        Intent intent = new Intent(this, NewHistoryActivity.class);
        intent.putExtra("pacient_selected", paciente);
        startActivity(intent);
    }

    public void onResume() {
        super.onResume();

        ( mPAdapter).setOnItemClickListener(new PacienteAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Lanza(position);
            }
        });

    }
}
