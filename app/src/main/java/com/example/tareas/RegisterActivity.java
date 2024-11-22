package com.example.tareas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_user_register);

        DBManager dbManager = new DBManager(this);


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

                if(nombre.isEmpty() || email.isEmpty() || usuario.isEmpty() || pass.isEmpty()) {
                    return;
                } else{
                    dbManager.open();
                    dbManager.insertUser(nombre, email, usuario, pass);
                    dbManager.close();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }
        });

    }
}
