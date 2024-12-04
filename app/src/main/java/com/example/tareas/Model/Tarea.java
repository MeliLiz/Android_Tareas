package com.example.tareas.Model;

import java.io.Serializable;
import org.json.JSONObject;

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


    public String toJson() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);
            jsonObject.put("title", title);
            jsonObject.put("description", description);
            jsonObject.put("dueDate", dueDate);
            jsonObject.put("status", status);
            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
