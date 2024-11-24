package com.example.tareas;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_user_register);

        // Inicializar UserBDManager con el contexto
        UserBDManager userBdd = new UserBDManager(this);

        Button registrar = findViewById(R.id.register);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = findViewById(R.id.editTextText3);
                EditText mail = findViewById(R.id.editTextDate2);
                EditText user = findViewById(R.id.editTextText4);
                EditText password = findViewById(R.id.editPassword3);

                String nombre = name.getText().toString();
                String email = mail.getText().toString();
                String usuario = user.getText().toString();
                String pass = password.getText().toString();

                if (nombre.isEmpty() || email.isEmpty() || usuario.isEmpty() || pass.isEmpty()) {
                    userBdd.openForRead();
                    ArrayList<User> users = userBdd.getUsers();
                    ArrayList<Task> tasks = userBdd.getTasks();
                    userBdd.close();

                    // Opcional: Puedes agregar un Log o mensaje para mostrar usuarios recuperados.
                    Log.d("RegisterActivity", "Usuarios registrados: " + users);
                    Log.d("RegisterActivity", "Tareas registradas: " + tasks);
                } else {
                    userBdd.openForWrite();
                    User us = new User(nombre, email, usuario, pass);
                    userBdd.insertUser(us);
                    userBdd.close();

                    Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}

