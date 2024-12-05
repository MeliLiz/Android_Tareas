package com.example.tareas.Utils;

import android.content.Context;
import android.os.Environment;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import com.example.tareas.Model.Tarea;
import com.example.tareas.Model.Task;

import org.json.JSONArray;
import org.json.JSONObject;

public class FileUtils {


    public static String exportTasksToTxt(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();

        for (Task task : tasks) {
            sb.append("ID: ").append(task.getId()).append("\n")
                    .append("Título: ").append(task.getTitle()).append("\n")
                    .append("Descripción: ").append(task.getDescription()).append("\n")
                    .append("Fecha de vencimiento: ").append(task.getDueDate()).append("\n")
                    .append("Estado: ").append(task.getStatus()).append("\n")
                    .append("-----\n");  // Separador entre tareas
        }

        return sb.toString();
    }


    // Guarda contenido en un archivo
    public static void saveToFile(Context context, String fileName, String content) {
        try {
            File directory = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(directory, fileName);

            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();

            System.out.println("Archivo guardado: " + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
