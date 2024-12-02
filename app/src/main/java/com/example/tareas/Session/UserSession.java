package com.example.tareas.Session;

public class UserSession {
    private static UserSession instance;
    private int userId;

    private UserSession() {
        this.userId = -1;
    }

    public static synchronized UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void logout() {
        this.userId = -1;
    }

    public boolean isLoggedIn() {
        return userId != -1;
    }
}
