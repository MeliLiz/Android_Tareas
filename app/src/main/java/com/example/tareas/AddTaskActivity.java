package com.example.tareas;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

public class AddTaskActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);


        setupToolbarAndDrawer();


    }
}
