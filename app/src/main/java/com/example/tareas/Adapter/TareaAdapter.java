package com.example.tareas.Adapter;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tareas.BaseActivity;
import com.example.tareas.DB.UserBDManager;
import com.example.tareas.Fragments.EditTaskFragment;
import com.example.tareas.Model.Tarea;
import com.example.tareas.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.TareaViewHolder> {

    private List<Tarea> tareaList;
    private boolean showButtons;

    // Constructor
    public TareaAdapter(List<Tarea> tareaList, boolean showButtons) {
        this.tareaList = tareaList;
        this.showButtons = showButtons;
    }

    // ViewHolder
    public static class TareaViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvDueDate;
        ImageButton btnEditTask, btnDeleteTask;
        LinearLayout btnContainer;

        public TareaViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_task_title);
            tvDescription = itemView.findViewById(R.id.tv_task_description);
            tvDueDate = itemView.findViewById(R.id.tv_due_date);
            btnContainer = itemView.findViewById(R.id.btn_container); // Contenedor de botones
            btnEditTask = btnContainer.findViewById(R.id.btn_edit_task);
            btnDeleteTask = btnContainer.findViewById(R.id.btn_delete_task);
        }
    }

    @Override
    public TareaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task, parent, false);
        return new TareaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TareaViewHolder holder, int position) {
        Tarea tarea = tareaList.get(position);

        holder.tvTitle.setText(tarea.getTitle());
        holder.tvDescription.setText(tarea.getDescription());
        holder.tvDueDate.setText("Fecha límite: " + tarea.getDueDate());

        // Mostrar u ocultar botones según el valor de showButtons
        if (showButtons) {
            holder.btnContainer.setVisibility(View.VISIBLE);
            Fragment editTaskFragment = new EditTaskFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("task", tarea);
            editTaskFragment.setArguments(bundle);

            holder.btnEditTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BaseActivity parent = (BaseActivity) holder.itemView.getContext();
                    parent.displayFragment(editTaskFragment);
                }
            });

            holder.btnDeleteTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Show dialog
                    showDeleteConfirmationDialog(tarea.getId(), holder.itemView.getContext());
                }
            });

        } else {
            holder.btnContainer.setVisibility(View.GONE);
        }

        // Change color (due date) depending on the due date
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date date = sdf.parse(tarea.getDueDate());
            Date currentDate = new Date();
            if (tarea.getStatus() == 1) {
                holder.tvDueDate.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.black));
            } else if (date != null && date.before(currentDate)) {
                holder.tvDueDate.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.red));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return tareaList.size();
    }

    private void showDeleteConfirmationDialog(int taskId, Context context) {
        new AlertDialog.Builder(context)
                .setTitle("Confirmar eliminación")
                .setMessage("¿Estás seguro de que deseas eliminar esta tarea?")
                .setPositiveButton("Eliminar", (dialog, which) -> {
                    deleteTask(taskId, context);
                    Toast.makeText(context, "Tarea eliminada", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancelar", (dialog, which) -> {
                    dialog.dismiss();
                })
                .create()
                .show();
    }

    private void deleteTask(int taskId, Context context) {
        UserBDManager userBdd = new UserBDManager(context);
        userBdd.openForWrite();
        userBdd.deleteTask(taskId);
        userBdd.close();
        tareaList.removeIf(tarea -> tarea.getId() == taskId);
        notifyDataSetChanged();
    }

}
