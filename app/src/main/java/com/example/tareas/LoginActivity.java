package com.example.tareas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class LoginActivity extends AppCompatActivity {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button login = findViewById(R.id.ingresar);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText user = findViewById(R.id.user);
                EditText pass = findViewById(R.id.pass);

                String userText = user.getText().toString();
                String passText = pass.getText().toString();

                if(userText.equals("admin") && passText.equals("123")){
                    Intent intent = new Intent(LoginActivity.this, Root.class);
                    startActivity(intent);
                }

            }
        });

    }
}
