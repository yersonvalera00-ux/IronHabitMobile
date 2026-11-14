package com.ironhabit.gym.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ironhabit.gym.R;
import com.ironhabit.gym.data.SocioRepository;

public class SociosActivity extends AppCompatActivity {

    private SocioAdapter adapter;
    private final SocioRepository repo = SocioRepository.getInstancia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socios);

        RecyclerView rv = findViewById(R.id.rvSocios);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SocioAdapter();
        rv.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fabNuevo);
        fab.setOnClickListener(v ->
                startActivity(new Intent(this, RegistroSocioActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume(); // se ejecuta también al volver del formulario
        adapter.setDatos(repo.listar());
    }
}