package com.example.tareas;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
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

import java.util.Calendar;

public class AddHomeworkFragment extends Fragment{

    private EditText title;
    private EditText description;
    private EditText date;
    private RadioGroup status;
    private Button add;

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

        date.setOnClickListener(v -> showDatePicker()); // Set a listener to pick the date

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
                    title.setText("");
                    description.setText("");
                    date.setText("");
                    status.clearCheck();
                }
            }
        });

        return view;
    }

    private void showDatePicker() {
        //get the current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Show the datepicker
        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Update the selected date in the EditText
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                date.setText(selectedDate);
            }
        }, year, month, day);

        datePickerDialog.show();
    }



}
