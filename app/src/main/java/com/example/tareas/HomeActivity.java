package com.example.tareas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TareaAdapter adapter;
    private List<Tarea> tareaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        recyclerView = findViewById(R.id.recyclerViewTareas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Crear lista de tareas
        tareaList = new ArrayList<>();
        tareaList.add(new Tarea("Tarea 1", "Descripción de la tarea", "20/11/2024"));
        tareaList.add(new Tarea("Tarea 2", "Descripción de la tarea", "22/11/2024"));
        tareaList.add(new Tarea("Tarea 3", "Descripción de la tarea", "24/11/2024"));

        // Configurar el adaptador
        adapter = new TareaAdapter(tareaList);
        recyclerView.setAdapter(adapter);

    }
}
