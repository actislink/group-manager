package com.actislink.model;

public class UserInfo {
    private final String name;
    private final String description;

    public UserInfo(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
