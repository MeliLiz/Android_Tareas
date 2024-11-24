package com.example.tareas;

public class Tarea {
    private String title;
    private String description;
    private String dueDate;
    private int status;

    public Tarea(String title, String description, String dueDate, int status) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public int getStatus() {
        return status;
    }
}
