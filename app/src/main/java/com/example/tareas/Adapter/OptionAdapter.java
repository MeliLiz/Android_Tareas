package com.example.tareas.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tareas.R;

import java.util.List;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.ViewHolder> {

    private final List<String> options;

    // Constructor que recibe la lista de opciones
    public OptionAdapter(List<String> options) {
        this.options = options;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout personalizado para cada elemento
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.config_option, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Obtén el elemento actual de la lista
        String option = options.get(position);

        // Vincula los datos con las vistas
        holder.optionText.setText(option);
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    // Clase ViewHolder que referencia los elementos del layout
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView optionText;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Asocia el TextView del layout personalizado
            optionText = itemView.findViewById(R.id.option_text);
        }
    }
}
