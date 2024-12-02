package com.example.tareas.Fragments;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tareas.BaseActivity;
import com.example.tareas.Commons.Functions;
import com.example.tareas.R;
import com.example.tareas.Model.Task;
import com.example.tareas.DB.UserBDManager;
import com.example.tareas.Session.UserSession;

import java.util.Calendar;

public class AddHomeworkFragment extends Fragment{

    private EditText title;
    private EditText description;
    private EditText date;
    private RadioGroup status;
    private Button add;
    private Functions fun = new Functions();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.add_task_fragment, container, false);

        if(!UserSession.getInstance().isLoggedIn()) {
            return null;
        }

        UserBDManager userBdd = new UserBDManager(requireContext());

        title = view.findViewById(R.id.titulo_text_field);
        description = view.findViewById(R.id.description_task);
        date = view.findViewById(R.id.dd);
        status = view.findViewById(R.id.radioGroup2);
        add = view.findViewById(R.id.agregar_tarea_bt);

        date.setOnClickListener(v -> fun.showDatePicker(requireContext(), date)); // Set a listener to pick the date

        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String titulo = title.getText().toString();
                String descripcion = description.getText().toString();
                String fecha = date.getText().toString();
                int status1 = status.getCheckedRadioButtonId();

                if(titulo.isEmpty() || descripcion.isEmpty() || fecha.isEmpty() || status1 == -1){
                    Toast.makeText(getContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else{
                    if(status1 == R.id.prioridad_alta_rb){
                        status1 = 3;
                    } else if(status1 == R.id.prioridad_media_rb){
                        status1 = 2;
                    } else if(status1 == R.id.prioridad_baja_rb){
                        status1 = 1;
                    }
                    Task task = new Task(titulo, descripcion, fecha, status1, UserSession.getInstance().getUserId());
                    userBdd.openForWrite();
                    userBdd.insertTask(task);
                    userBdd.close();
                    Toast.makeText(getContext(), "Tarea agregada con éxito", Toast.LENGTH_SHORT).show();
                    BaseActivity parent = (BaseActivity) getActivity();
                    if (parent != null) {
                        parent.displayFragment(new HomeFragment());
                    } else{
                        title.setText("");
                        description.setText("");
                        date.setText("");
                        status.clearCheck();
                    }
                }
            }
        });

        return view;
    }
}
