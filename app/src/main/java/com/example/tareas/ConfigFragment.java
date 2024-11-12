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

import com.example.tareas.OptionAdapter;
import com.example.tareas.R;

import java.util.Arrays;
import java.util.List;

public class ConfigFragment extends Fragment {

    private RecyclerView recyclerView;
    private OptionAdapter adapter;
    private List<String> optionsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.config_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Obtén los datos del array de recursos
        optionsList = Arrays.asList(getResources().getStringArray(R.array.options));

        // Configura el adapter con la lista
        adapter = new OptionAdapter(optionsList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
