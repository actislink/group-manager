package com.actislink.model;

import java.time.Instant;

public class GroupItem {

    private GroupId id;
    private Instant creationInstant;

    public GroupItem(GroupId id, Instant creationInstant) {
        super();
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
        return id + " " + creationInstant;
    }
}
