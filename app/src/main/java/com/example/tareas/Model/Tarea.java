package com.example.tareas.Model;

import java.io.Serializable;

public class Tarea implements Serializable {
    private int id;
    private String title;
    private String description;
    private String dueDate;
    private int status;

    public Tarea(int id, String title, String description, String dueDate, int status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    public int getId() {
        return id;
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

    public String toString(){
        return "Tarea{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", status=" + status +
                '}';
    }

}
