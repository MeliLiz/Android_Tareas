package com.example.tareas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private final DBHelper dbHelper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insertUser(String name, String email, String username, String password) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME_USER, name);
        values.put(DBHelper.COLUMN_EMAIL_USER, email);
        values.put(DBHelper.COLUMN_USERNAME_USER, username);
        values.put(DBHelper.COLUMN_PASSWORD_USER, password);
        db.insert(DBHelper.TABLE_USERS, null, values);
    }

    public void insertTask(String title, String description, String dueDate, int userId, String status) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_TITLE_TASK, title);
        values.put(DBHelper.COLUMN_DESCRIPTION_TASK, description);
        values.put(DBHelper.COLUMN_DUE_DATE_TASK, dueDate);
        values.put(DBHelper.COLUMN_USER_ID_TASK, userId);
        values.put(DBHelper.COLUMN_STATUS_TASK, status);
        db.insert(DBHelper.TABLE_TASKS, null, values);
    }

    public Cursor getUsers(){
        return db.query(DBHelper.TABLE_USERS, null, null, null, null, null, null);
    }

    public Cursor getUserTasks(int userId){
        return db.query(DBHelper.TABLE_TASKS, null,
                DBHelper.COLUMN_USER_ID_TASK + " = ?",
                new String[]{String.valueOf(userId)}, null, null, null);
    }


}
