package com.actislink.model;

import java.time.Instant;

public class UserItem {
    private String name;
    private Instant joinInstant;

    public UserItem(String name, Instant joinInstant) {
        this.name = name;
        this.joinInstant = joinInstant;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return joinInstant + " " + name;
    }
}
