package com.example.tareas;

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
}
