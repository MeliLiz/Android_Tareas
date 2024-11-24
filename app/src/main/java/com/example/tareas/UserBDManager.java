package com.example.tareas;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class UserBDManager {
    private static final int VERSION = 3;
    private static final String NOMBRE_BD = "tareas.db";

    private SQLiteDatabase bdd;
    private UserBD users;

    // Constructor que inicializa UserBD con el contexto
    public UserBDManager(Context context) {
        users = new UserBD(context, NOMBRE_BD, null, VERSION);
    }

    public void openForWrite() {
        bdd = users.getWritableDatabase();
    }

    public void openForRead() {
        bdd = users.getReadableDatabase();
    }

    public void close() {
        bdd.close();
    }

    public SQLiteDatabase getBDD() {
        return bdd;
    }

    // Methods for users

    public long insertUser(User user) {
        ContentValues values = new ContentValues();
        Log.d(user.toString(), "User");
        values.put("nombre", user.getName());
        values.put("email", user.getEmail());
        values.put("usuario", user.getUsername());
        values.put("password", user.getPassword());
        return bdd.insert(UserBD.TABLA_USUARIOS, null, values);
    }

    public User getUser(String username, String password){
        User user = null;
        Cursor cursor = bdd.query("usuario", new String[] {"id", "nombre", "email", "usuario", "password"}, "usuario = ? AND password = ?", new String[] {username, password}, null, null, null);
        if (cursor.moveToFirst()) {
            user = new User(
                    cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    cursor.getString(cursor.getColumnIndexOrThrow("email")),
                    cursor.getString(cursor.getColumnIndexOrThrow("usuario")),
                    cursor.getString(cursor.getColumnIndexOrThrow("password"))
            );
            user.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
        }
        cursor.close();
        return user;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        Cursor cursor = bdd.query("usuario", new String[] {"id", "nombre", "email", "usuario", "password"}, null, null, null, null, "id");

        while (cursor.moveToNext()) {
            User user = new User(
                    cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    cursor.getString(cursor.getColumnIndexOrThrow("email")),
                    cursor.getString(cursor.getColumnIndexOrThrow("usuario")),
                    cursor.getString(cursor.getColumnIndexOrThrow("password"))
            );
            users.add(user);
        }

        cursor.close();
        return users;
    }

    // Methods for tasks
    public long insertTask(Task task) {
        ContentValues values = new ContentValues();
        values.put("titulo", task.getTitle());
        values.put("descripcion", task.getDescription());
        values.put("fecha_vencimiento", task.getDueDate());
        values.put("estado", task.getStatus());
        values.put("id_usuario", task.getUserId());
        return bdd.insert("tarea", null, values);
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        Cursor cursor = bdd.query("tarea", new String[] {"id", "titulo", "descripcion", "fecha_vencimiento", "estado", "id_usuario"}, null, null, null, null, "id");

        while (cursor.moveToNext()) {
            Task task = new Task(
                    cursor.getString(cursor.getColumnIndexOrThrow("titulo")),
                    cursor.getString(cursor.getColumnIndexOrThrow("descripcion")),
                    cursor.getString(cursor.getColumnIndexOrThrow("fecha_vencimiento")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("estado")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("id_usuario"))
            );
            tasks.add(task);
        }

        cursor.close();
        return tasks;
    }

    public ArrayList<Task> getUsertasks(int userId) {
        ArrayList<Task> tasks = new ArrayList<>();
        Cursor cursor = bdd.query("tarea", new String[] {"id", "titulo", "descripcion", "fecha_vencimiento", "estado", "id_usuario"}, "id_usuario = ?", new String[] {String.valueOf(userId)}, null, null, "id");

        while (cursor.moveToNext()) {
            Task task = new Task(
                    cursor.getString(cursor.getColumnIndexOrThrow("titulo")),
                    cursor.getString(cursor.getColumnIndexOrThrow("descripcion")),
                    cursor.getString(cursor.getColumnIndexOrThrow("fecha_vencimiento")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("estado")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("id_usuario"))
            );
            tasks.add(task);
        }
        cursor.close();
        return tasks;
    }
}
