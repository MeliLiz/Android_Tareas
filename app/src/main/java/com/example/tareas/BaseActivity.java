package com.example.tareas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout drawer;

    private static final String PREFS_NAME = "app_prefs";
    private static final String NIGHT_MODE_KEY = "night_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Método para configurar la Toolbar y Drawer
    protected void setupToolbarAndDrawer() {
        // Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#FFFFFF\">" + getString(R.string.appNname) + "</font>"));

        // Configurar DrawerLayout
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Configurar el toggle del Drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
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
            UserSession.getInstance().logout();
            Log.d("MainActivity", UserSession.getInstance().getUserId() + "Logout");
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            preferences.edit().putInt("user_id", -1).apply();
            Intent intent = new Intent(BaseActivity.this, MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.night_mode) {
            SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            boolean nightMode = preferences.getBoolean(NIGHT_MODE_KEY, false);

            if (nightMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }

            preferences.edit().putBoolean(NIGHT_MODE_KEY, !nightMode).apply();
            recreate();

        }  else if (id == R.id.profile) {
            displayFragment(new ProfileFragment());
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.view_homeworks) {
            displayFragment(new HomeFragment());
        } else if (id == R.id.view_completed) {
            displayFragment(new CompletedTasksFragment());
        } else if (id == R.id.add_homework) {
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
