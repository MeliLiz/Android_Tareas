package com.example.tareas;

import android.os.Bundle;
import android.util.Log;


public class Root extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root);

        setupToolbarAndDrawer();

        if (savedInstanceState == null){
            int user_id = UserSession.getInstance().getUserId();
            Log.d("Root", "User ID: " + user_id);
            if(user_id != -1){
                displayFragment(new HomeFragment());
            }
        }
    }
}
