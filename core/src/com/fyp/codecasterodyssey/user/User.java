package com.fyp.codecasterodyssey.user;

public class User {
    private String username;
    private String learningPath;
    private String quests; 

    public User() {}

    public User(String username) {
        this.username = username;

        UserManager.createUserJSON(this);
    }

    public String getUsername() {
        return username;
    }
    
}
