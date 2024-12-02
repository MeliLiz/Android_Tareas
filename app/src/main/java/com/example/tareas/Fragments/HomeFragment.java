package com.example.tareas.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tareas.R;
import com.example.tareas.Model.Tarea;
import com.example.tareas.Adapter.TareaAdapter;
import com.example.tareas.Model.Task;
import com.example.tareas.DB.UserBDManager;
import com.example.tareas.Session.UserSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private TareaAdapter adapter;
    private List<Tarea> tareaList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        if (!UserSession.getInstance().isLoggedIn()) {
            return null;
        }

        recyclerView = view.findViewById(R.id.recyclerViewTareas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        UserBDManager userBdd = new UserBDManager(requireContext());
        userBdd.openForRead();
        ArrayList<Task> tasks = userBdd.getUserPendingTasks(UserSession.getInstance().getUserId());
        Log.d("HomeFragment", "Tareas del usuario: " + tasks);
        userBdd.close();

        Collections.sort(tasks, new Comparator<Task>(){
            @Override
            public int compare(Task tarea1, Task tarea2){
                return tarea1.compareTo(tarea2);
            }
        });

        if(tasks.isEmpty()){
            TextView noTasksText = view.findViewById(R.id.noTasksText);
            noTasksText.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

        tareaList = new ArrayList<>();
        for (Task task : tasks) {
            tareaList.add(new Tarea(task.getId(), task.getTitle(), task.getDescription(), task.getDueDate(), task.getStatus()));
        }

        adapter = new TareaAdapter(tareaList, true);
        recyclerView.setAdapter(adapter);

        return view;
    }

}
