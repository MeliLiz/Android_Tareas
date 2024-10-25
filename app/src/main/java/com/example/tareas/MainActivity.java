package com.example.tareas;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
            Log.d("MainActivity", "Settings item selected from three-dot menu");
            return true;
        } else if (id == R.id.action_about) {
            Log.d("MainActivity", "About item selected from three-dot menu");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.view_homeworks) {
            Log.d("MainActivity", "Ver tareas seleccionado del drawer menu");
        } else if (id == R.id.view_resources) {
            Log.d("MainActivity", "Ver recursos seleccionado del drawer menu");
        } else if (id == R.id.add_homework) {
            Log.d("MainActivity", "Asignar tarea seleccionado del drawer menu");
        } else if (id == R.id.edit_homework) {
            Log.d("MainActivity", "Editar tareas seleccionado del drawer menu");
        } else {
            Log.d("MainActivity", "Elemento no reconocido seleccionado");
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
