package com.example.tareas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private TareaAdapter adapter;
    private List<Tarea> tareaList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar la vista del fragmento
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        // Inicializar RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewTareas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Crear lista de tareas
        tareaList = new ArrayList<>();
        tareaList.add(new Tarea("Tarea 1", "Descripción de la tarea", "20/11/2024"));
        tareaList.add(new Tarea("Tarea 2", "Descripción de la tarea", "22/11/2024"));
        tareaList.add(new Tarea("Tarea 3", "Descripción de la tarea", "24/11/2024"));

        // Configurar el adaptador
        adapter = new TareaAdapter(tareaList);
        recyclerView.setAdapter(adapter);

        // Devolver la vista completa con RecyclerView inicializado
        return view;
    }
}
