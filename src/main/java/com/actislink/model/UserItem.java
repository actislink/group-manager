package com.actislink.model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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
        return String.format("%s %s",
                DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.systemDefault()).format(joinInstant), name);
    }
}
