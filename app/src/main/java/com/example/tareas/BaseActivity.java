package com.example.tareas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Método para configurar la Toolbar y Drawer
    protected void setupToolbarAndDrawer() {
        // Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configurar DrawerLayout
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Configurar el toggle del Drawer
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
            displayFragment(new ConfigFragment());

        } else if (id == R.id.action_about) {
            displayFragment(new AboutFragment());

        } else if (id == R.id.action_logout) {
            Intent intent = new Intent(BaseActivity.this, MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.profile) {
            displayFragment(new ProfileFragment());
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.view_homeworks) {
            displayFragment(new HomeFragment());
        } /*else if (id == R.id.view_resources) {
            Log.d("MainActivity", "Ver recursos seleccionado del drawer menu");
            Toast.makeText(this, "Ver recursos", Toast.LENGTH_SHORT).show();
        } */ else if (id == R.id.add_homework) {
            displayFragment(new AddHomeworkFragment());
        } else if (id == R.id.edit_homework) {
            displayFragment(new HomeFragment());
        } else {
            Log.d("MainActivity", "Elemento no reconocido seleccionado");
            Toast.makeText(this, "Elemento no reconocido", Toast.LENGTH_SHORT).show();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void displayFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

}
