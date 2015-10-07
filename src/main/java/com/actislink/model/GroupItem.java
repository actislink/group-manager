package com.actislink.model;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class GroupItem {

    private GroupId id;
    private Instant creationInstant;

    public GroupItem(GroupId id, Instant creationInstant) {
        this.id = id;
        this.creationInstant = creationInstant;
    }

    public GroupId getId() {
        return id;
    }

    public Instant getCreationInstant() {
        return creationInstant;
    }

    @Override
    public String toString() {
        return String.format("%s %s", DateTimeFormatter.RFC_1123_DATE_TIME.format(creationInstant), id);
    }
}
