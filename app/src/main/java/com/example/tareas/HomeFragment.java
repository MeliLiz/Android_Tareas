package com.example.tareas;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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
        ArrayList<Task> tasks = userBdd.getUsertasks(UserSession.getInstance().getUserId());
        Log.d("HomeFragment", "Tareas del usuario: " + tasks);
        userBdd.close();

        if(tasks.isEmpty()){
            TextView noTasksText = view.findViewById(R.id.noTasksText);
            noTasksText.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

        tareaList = new ArrayList<>();
        for (Task task : tasks) {
            tareaList.add(new Tarea(task.getTitle(), task.getDescription(), task.getDueDate()));
        }

        adapter = new TareaAdapter(tareaList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
