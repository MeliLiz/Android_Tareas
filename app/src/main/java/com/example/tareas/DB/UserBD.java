package com.example.tareas.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserBD extends SQLiteOpenHelper {
    public static final String TABLA_USUARIOS = "usuario";
    private static final String COL_ID = "id";
    private static final String COL_NOMBRE = "nombre";
    private static final String COL_EMAIL = "email";
    private static final String COL_USUARIO = "usuario";
    private static final String COL_PASSWORD = "password";

    private static final String CREAR_TABLA_USUARIOS = "CREATE TABLE " + TABLA_USUARIOS + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NOMBRE + " TEXT NOT NULL, " +
            COL_EMAIL + " TEXT NOT NULL, " +
            COL_USUARIO + " TEXT NOT NULL, " +
            COL_PASSWORD + " TEXT" + ");";

    public static final String TABLA_TAREAS = "tarea";
    public static final String COL_ID_TAREA = "id";
    public static final String COL_TITULO = "titulo";
    public static final String COL_DESCRIPCION = "descripcion";
    public static final String COL_FECHA_VENCIMIENTO = "fecha_vencimiento";
    public static final String COL_ESTADO = "estado";
    public static final String COL_ID_USUARIO = "id_usuario";

    private static final String CREAR_TABLA_TAREAS = "CREATE TABLE " + TABLA_TAREAS +
            "(" + COL_ID_TAREA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_TITULO + " TEXT NOT NULL, " +
            COL_DESCRIPCION + " TEXT, " +
            COL_FECHA_VENCIMIENTO + " TEXT, " +
            COL_ESTADO + " TEXT, " +
            COL_ID_USUARIO + " INTEGER NOT NULL, " +
            "FOREIGN KEY(" + COL_ID_USUARIO + ") REFERENCES " + TABLA_USUARIOS + "(" + COL_ID + ")" + ");";

    public UserBD(Context context, String name, SQLiteDatabase.CursorFactory cf, int version){
        super(context, name, cf, version);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREAR_TABLA_USUARIOS);
        db.execSQL(CREAR_TABLA_TAREAS);
    }

    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva){
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_TAREAS);
        onCreate(db);
    }

}
