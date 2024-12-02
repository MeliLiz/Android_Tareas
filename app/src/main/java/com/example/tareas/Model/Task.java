package com.example.tareas.Model;

import android.util.Log;

import java.util.Date;

public class Task {
    private int id;
    private String title;
    private String description;
    private String dueDate;
    private int status;
    private int userId;


    public Task(String title, String description, String dueDate, int status, int userId) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.userId = userId;
    }

    public Task(int id, String title, String description, String dueDate, int status, int userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.userId = userId;
    }

    // Getters y setters

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

   public int getUserId(){
        return userId;
   }

   public void setId(int id){
        this.id = id;
   }

   public void setTitle(String title){
        this.title = title;
   }

   public void setDescription(String description){
        this.description = description;
   }

   public void setDueDate(String dueDate){
        this.dueDate = dueDate;
   }

   public void setStatus(int status){
        this.status = status;
   }

   public void setUserId(int userId){
        this.userId = userId;
   }

   @Override
   public String toString(){
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", status=" + status +
                ", userId=" + userId +
                '}';
   }

    public int compareTo(Task other) {
        // convert the str duedate to date and compare them
       Date date1 = new Date(this.dueDate);
       Date date2 = new Date(other.dueDate);
       if(date1.compareTo(date2) != 0){
           return date1.compareTo(date2);
       }
       if(this.status != other.status){
           return other.status - this.status; // The higher the status, the higher the priority
       }
       return this.title.compareTo(other.title);
   }

    public Boolean equals(Task other){
        return this.title.equals(other.title) &&
                this.description.equals(other.description) &&
                this.dueDate.equals(other.dueDate) && this.status == other.status &&
                this.userId == other.userId;
    }
}
