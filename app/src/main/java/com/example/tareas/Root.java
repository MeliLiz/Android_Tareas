package com.example.tareas;

import android.os.Bundle;


public class Root extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root);

        setupToolbarAndDrawer();

        if (savedInstanceState == null){
            displayFragment(new HomeFragment());
        }
    }
}
