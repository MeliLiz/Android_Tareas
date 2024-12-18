package com.example.tareas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.tareas.DB.UserBDManager;
import com.example.tareas.Model.User;
import com.example.tareas.Session.UserSession;

public class LoginActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private static final String PREFS_NAME = "app_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button login = findViewById(R.id.ingresar);
        UserBDManager userBdd = new UserBDManager(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText user = findViewById(R.id.user);
                EditText pass = findViewById(R.id.pass);

                String userText = user.getText().toString();
                String passText = pass.getText().toString();

                userBdd.openForRead();
                User us = userBdd.getUser(userText, passText);
                userBdd.close();

                if(us != null){
                    Toast.makeText(LoginActivity.this, "Bienvenido " + us.getName(), Toast.LENGTH_SHORT).show();
                    SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                    preferences.edit().putInt("user_id", us.getId()).apply();
                    UserSession.getInstance().setUserId(us.getId());
                    Intent intent = new Intent(LoginActivity.this, Root.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
