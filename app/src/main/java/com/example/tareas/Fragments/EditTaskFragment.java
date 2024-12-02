package com.example.tareas.Fragments;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.se.omapi.Session;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import com.example.tareas.Commons.Functions;
import com.example.tareas.Model.Tarea;
import com.example.tareas.R;
import com.example.tareas.Model.Task;
import com.example.tareas.DB.UserBDManager;
import com.example.tareas.Session.UserSession;

import java.util.Calendar;

public class EditTaskFragment extends Fragment{

    private EditText title;
    private EditText description;
    private EditText date;
    private RadioGroup status;
    private Button edit;
    private Tarea taskToEdit;
    private Functions fun = new Functions();
    private int editing = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.edit_task_fragment, container, false);

        if(!UserSession.getInstance().isLoggedIn()) {
            return null;
        }

        UserBDManager userBdd = new UserBDManager(requireContext());
        taskToEdit = (Tarea) getArguments().getSerializable("task");

        if(taskToEdit == null){
            return null;
        }

        title = view.findViewById(R.id.titulo_tarea);
        description = view.findViewById(R.id.description_task);
        date = view.findViewById(R.id.dd);
        status = view.findViewById(R.id.radioGroup2);
        status.setEnabled(false);
        for (int i = 0; i < status.getChildCount(); i++) {
            status.getChildAt(i).setEnabled(false);
        }
        edit = view.findViewById(R.id.editar);
        updateButtonState();


        title.setText(taskToEdit.getTitle());
        description.setText(taskToEdit.getDescription());
        date.setText(taskToEdit.getDueDate());
        switch (taskToEdit.getStatus()){
            case 3:
                status.check(R.id.prioridad_alta_rb);
                break;
            case 2:
                status.check(R.id.prioridad_media_rb);
                break;
            case 1:
                status.check(R.id.prioridad_baja_rb);
                break;
        }

        date.setOnClickListener(v -> fun.showDatePicker(requireContext(), date));

        ImageView editTitle = view.findViewById(R.id.btn_edit_titulo);
        ImageView editDescription = view.findViewById(R.id.btn_edit_descripcion);
        ImageView editDate = view.findViewById(R.id.btn_edit_due_date);
        ImageView editStatus = view.findViewById(R.id.btn_edit_status);

        editTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title.isEnabled()){
                    title.setText(taskToEdit.getTitle());
                    title.setEnabled(false);
                    editing -= 1;
                    Log.d("editing", editing + "");
                } else {
                    title.setEnabled(true);
                    editing += 1;
                    Log.d("editing", editing + "");
                }
                updateButtonState();
            }
        });

        editDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(description.isEnabled()){
                    description.setText(taskToEdit.getDescription());
                    description.setEnabled(false);
                    editing -= 1;
                    Log.d("editing", editing + "");
                }else {
                    description.setEnabled(true);
                    editing += 1;
                    Log.d("editing", editing + "");
                }
                updateButtonState();
            }
        });

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(date.isEnabled()){
                    date.setText(taskToEdit.getDueDate());
                    date.setEnabled(false);
                    editing -= 1;
                    Log.d("editing", editing + "");
                } else {
                    date.setEnabled(true);
                    editing += 1;
                    Log.d("editing", editing + "");
                }
                updateButtonState();
            }
        });

        editStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status.isEnabled()){
                    for (int i = 0; i < status.getChildCount(); i++) {
                        status.getChildAt(i).setEnabled(false);
                    }
                    status.setEnabled(false);
                    editing -= 1;
                    Log.d("editing", editing + "");
                } else {
                    status.setEnabled(true);
                    for (int i = 0; i < status.getChildCount(); i++) {
                        status.getChildAt(i).setEnabled(true);
                    }
                    editing += 1;
                    Log.d("editing", editing + "");
                }
                updateButtonState();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("editing", editing + "");
                UserBDManager bdManager = new UserBDManager(requireContext());
                bdManager.openForWrite();
                int status1 = status.getCheckedRadioButtonId();
                if(status1 == R.id.prioridad_alta_rb){
                    status1 = 3;
                } else if(status1 == R.id.prioridad_media_rb){
                    status1 = 2;
                } else if(status1 == R.id.prioridad_baja_rb){
                    status1 = 1;
                }
                Boolean res = bdManager.updateTask(taskToEdit.getId(),new Task(
                        title.getText().toString(),
                        description.getText().toString(),
                        date.getText().toString(),
                        status1,
                        UserSession.getInstance().getUserId()
                ));
                if(res){
                    Toast toast = Toast.makeText(requireContext(), "Tarea editada correctamente", Toast.LENGTH_SHORT);
                    toast.show();
                }
                bdManager.close();

                editing = 0;
                title.setEnabled(false);
                description.setEnabled(false);
                date.setEnabled(false);
                for (int i = 0; i < status.getChildCount(); i++) {
                    status.getChildAt(i).setEnabled(false);
                }
                status.setEnabled(false);
            }
        });

        return view;
    }

    private void updateButtonState() {
        if (editing == 0) {
            edit.setEnabled(false);
        } else {
            edit.setEnabled(true);
        }
    }
}
