package com.example.signupforms;

public class User {
    private String fullName;
    private String username;
    private String email;
    private String password;
    private String gender;

    public User(String fullName, String username, String email, String password, String gender){
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }
}
