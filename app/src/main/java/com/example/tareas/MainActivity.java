package com.example.tareas;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuración del Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configuración del DrawerLayout y NavigationView
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Toggle para abrir y cerrar el Drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Button btnGoHome = findViewById(R.id.agregar_tarea_bt);
        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para abrir HomeActivity
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // Inflar el menú de tres puntos en el Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflar el archivo menu_main.xml
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Log.d("MainActivity", "Ajustes seleccionado en el menú de tres puntos");
            Toast.makeText(this, "Ajustes", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.action_about) {
            Log.d("MainActivity", "Acerca de seleccionado en el menú de tres puntos");
            Toast.makeText(this, "Acerca de", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.action_logout) {
            Log.d("MainActivity", "Cerrar sesión seleccionado en el menú de tres puntos");
            Toast.makeText(this, "Cerrar sesión", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.profile) {
            Log.d("MainActivity", "Perfil seleccionado en el menú de tres puntos");
            Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.view_homeworks) {
            Log.d("MainActivity", "Ver tareas seleccionado del drawer menu");
            Toast.makeText(this, "Ver tareas", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.view_resources) {
            Log.d("MainActivity", "Ver recursos seleccionado del drawer menu");
            Toast.makeText(this, "Ver recursos", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.add_homework) {
            Log.d("MainActivity", "Asignar tarea seleccionado del drawer menu");
            Toast.makeText(this, "Asignar tarea", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.edit_homework) {
            Log.d("MainActivity", "Editar tareas seleccionado del drawer menu");
            Toast.makeText(this, "Editar tareas", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("MainActivity", "Elemento no reconocido seleccionado");
            Toast.makeText(this, "Elemento no reconocido", Toast.LENGTH_SHORT).show();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
