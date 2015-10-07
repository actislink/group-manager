package com.actislink.model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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
        return String.format("CreationDate: %s\nName: %s\nMembers:\n\t%s",
                DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.systemDefault()).format(creationInstant), name,
                Joiner.on("\n\t").join(members));
    }
}
