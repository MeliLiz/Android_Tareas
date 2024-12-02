package com.example.tareas.Commons;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class Functions {
    public void showDatePicker(Context context, EditText date) {
        //get the current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Show the datepicker
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
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
