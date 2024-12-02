package com.example.tareas.Model;

public class User {
    private int id;
    private String name;
    private String email;
    private String username;
    private String password;

    public User(String name, String email, String username, String password){
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Boolean equals(User user){
        if (user == null) return false;
        if (user.getName().equals(this.getName()) && user.getEmail().equals(this.getEmail()) && user.getUsername().equals(this.getUsername()) && user.getPassword().equals(this.getPassword())){
            return true;
        } else {
            return false;
        }
    }
}
