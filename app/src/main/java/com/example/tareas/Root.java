package com.example.tareas;

import android.os.Bundle;
import android.util.Log;

import com.example.tareas.Fragments.HomeFragment;
import com.example.tareas.Session.UserSession;


public class Root extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root);

        setupToolbarAndDrawer();

        if (savedInstanceState == null){
            int user_id = UserSession.getInstance().getUserId();
            if(user_id != -1){
                displayFragment(new HomeFragment());
            }else{
                Log.e("Root", "No se encontró el usuario");
            }
        }
    }
}
