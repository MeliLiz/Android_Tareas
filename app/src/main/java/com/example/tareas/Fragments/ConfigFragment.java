package com.example.tareas.Fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tareas.Adapter.OptionAdapter;
import com.example.tareas.LoginActivity;
import com.example.tareas.Model.Task;
import com.example.tareas.R;
import com.example.tareas.Model.Tarea;
import com.example.tareas.Session.UserSession;
import com.example.tareas.Utils.FileUtils;
import com.example.tareas.DB.UserBDManager;

public class ConfigFragment extends Fragment {

    private RecyclerView recyclerView;
    private OptionAdapter adapter;
    private List<String> optionsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.config_fragment, container, false);

        if (!UserSession.getInstance().isLoggedIn()) {
            // Manejar el caso en el que el usuario no esté autenticado
            Log.e("ConfigFragment", "Usuario no autenticado");
            return null;
        }

        int userId = UserSession.getInstance().getUserId(); // Obtener el ID del usuario
        Log.i("ConfigFragment", "Usuario ID: " + userId);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        optionsList = Arrays.asList(getResources().getStringArray(R.array.options));
        adapter = new OptionAdapter(optionsList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OptionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String option) {
                UserBDManager dbManager = new UserBDManager(requireContext());
                if (option.equals("Exportar Tareas")) {
                    dbManager.openForRead();

                    List<Task> tasks = dbManager.getUsertasks(userId); // Obtener las tareas del usuario
                    dbManager.close();

                    for (Task task : tasks) {
                        Log.i("Task Info", task.toString()); // Mostrar la información de cada tarea en el log
                    }
                    if(!tasks.isEmpty()) {
                        String txtContent = FileUtils.exportTasksToTxt(tasks);
                        saveToPublicStorage(txtContent);
                        Toast toast = Toast.makeText(requireContext(), "Archivo guardado en Downloads", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(requireContext(), "SIN TAREAS", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else if (option.equals("Eliminar Todas las tareas")) {
                    // Abrir la base de datos en modo escritura antes de realizar la operación
                    dbManager.openForWrite();

                    // Eliminar todas las tareas del usuario
                    boolean success = dbManager.deleteAllTasksForUser(userId);
                    dbManager.close();

                    if (success) {
                        Toast.makeText(getContext(), "Todas las tareas han sido eliminadas", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "No existen tareas", Toast.LENGTH_SHORT).show();
                    }
                } else if (option.equals("Eliminar Todas las tareas completadas")) {
                    dbManager.openForWrite();

                    // Llamar a una función que elimine solo las tareas con estatus de "completadas" (por ejemplo, status = 3)
                    boolean success = dbManager.deleteCompletedTasksForUser(userId);
                    dbManager.close();

                    if (success) {
                        Toast.makeText(getContext(), "Tareas completadas eliminadas", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "No hay tareas completadas", Toast.LENGTH_SHORT).show();
                    }
                } else if (option.equals("Eliminar Cuenta")) {
                    // Mostrar un cuadro de confirmación antes de eliminar la cuenta
                    new AlertDialog.Builder(requireContext())
                            .setTitle("Confirmar eliminación")
                            .setMessage("¿Estás seguro de que deseas eliminar tu cuenta? Esta acción no se puede deshacer.")
                            .setPositiveButton("Eliminar", (dialog, which) -> {
                                deleteAccount(); // Llama al método para eliminar la cuenta
                            })
                            .setNegativeButton("Cancelar", null)
                            .show();
                }

            }
        });

        return view;
    }

    private void deleteAccount() {
        UserBDManager dbManager = new UserBDManager(requireContext());
        dbManager.openForWrite();

        int userId = UserSession.getInstance().getUserId();

        // Eliminar tareas relacionadas con el usuario
        boolean tasksDeleted = dbManager.deleteAllTasksForUser(userId);

        // Eliminar el usuario de la base de datos
        boolean userDeleted = dbManager.deleteUserById(userId);

        dbManager.close();

        if (userDeleted) {
            // Cerrar sesión
            UserSession.getInstance().logout();
            Toast.makeText(requireContext(), "Cuenta eliminada exitosamente", Toast.LENGTH_SHORT).show();

            // Redirigir a la actividad de Login
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Limpia la pila de actividades
            startActivity(intent);
            requireActivity().finish(); // Finaliza la actividad actual
        } else {
            Toast.makeText(requireContext(), "Error al eliminar la cuenta", Toast.LENGTH_SHORT).show();
        }
    }


    private void saveToPublicStorage(String txtContent) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, "tareas.txt");  // Cambia a tareas.txt
        values.put(MediaStore.MediaColumns.MIME_TYPE, "text/plain");  // MIME tipo de texto plano
        values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS);

        Uri uri = getContext().getContentResolver().insert(MediaStore.Files.getContentUri("external"), values);

        try {
            OutputStream outputStream = getContext().getContentResolver().openOutputStream(uri);
            if (outputStream != null) {
                outputStream.write(txtContent.getBytes());
                outputStream.close();
                Log.i("Archivo guardado", "Archivo guardado en: " + uri.toString());
            }
        } catch (IOException e) {
            Log.e("Error", "Error al guardar el archivo", e);
        }
    }

}
