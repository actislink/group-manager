package com.actislink.model;

import java.time.Instant;

import com.google.common.collect.ImmutableList;

public class GroupInfo {
    private String name;
    private Instant creationInstant;
    private ImmutableList<UserItem> members;

    public GroupInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
