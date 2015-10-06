package com.actislink.model;

import java.time.Instant;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

public class GroupInfo {
    private String name;
    private Instant creationInstant;
    private ImmutableList<UserItem> members;

    public GroupInfo(String name, Instant creationInstant, ImmutableList<UserItem> members) {
        this.name = name;
        this.creationInstant = creationInstant;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return creationInstant + " " + name + " " + Joiner.on(", ").join(members);
    }
}
