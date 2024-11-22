package com.example.tareas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tareas.db";
    private static final int DATABASE_VERSION = 1;

    // Tables
    public static final String TABLE_USERS = "users";
    public static final String TABLE_TASKS = "tasks";

    //Columns of the users table
    public static final String COLUMN_ID_USER = "id";
    public static final String COLUMN_NAME_USER = "name";
    public static final String COLUMN_EMAIL_USER = "email";
    public static final String COLUMN_USERNAME_USER = "username";
    public static final String COLUMN_PASSWORD_USER = "password";

    //Columns of the tasks table
    public static final String COLUMN_ID_TASK = "id";
    public static final String COLUMN_TITLE_TASK = "title";
    public static final String COLUMN_DESCRIPTION_TASK = "description";
    public static final String COLUMN_DUE_DATE_TASK = "due_date";
    public static final String COLUMN_USER_ID_TASK = "user_id";
    public static final String COLUMN_STATUS_TASK = "status";

    // Create tables
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "(" +
            COLUMN_ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME_USER + " TEXT NOT NULL, " +
            COLUMN_EMAIL_USER + " TEXT NOT NULL, " +
            COLUMN_USERNAME_USER + " TEXT NOT NULL, " +
            COLUMN_PASSWORD_USER + "TEXT" + ");";

    private static final String CREATE_TABLE_TASKS = "CREATE TABLE " + TABLE_TASKS + "(" +
            COLUMN_ID_TASK + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TITLE_TASK + " TEXT NOT NULL, " +
            COLUMN_DESCRIPTION_TASK + " TEXT, " +
            COLUMN_DUE_DATE_TASK + " TEXT, " +
            COLUMN_USER_ID_TASK + " INTEGER NOT NULL, " +
            COLUMN_STATUS_TASK + " TEXT" + ");";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_TASKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
      db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
      db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
      onCreate(db);
    }


}
