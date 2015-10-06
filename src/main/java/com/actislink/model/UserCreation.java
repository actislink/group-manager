package com.actislink.model;

public class UserCreation {
    private String username;
    private String description;

    public UserCreation(String username) {
        super();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
