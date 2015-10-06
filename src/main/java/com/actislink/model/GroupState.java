package com.actislink.model;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

public class GroupState {
    private GroupId id;
    private Instant creationInstant;
    private List<GroupUserItem> members = new LinkedList<GroupUserItem>();

    public GroupState(String name) {
        creationInstant = Instant.now();
        id = new GroupId(name);
    }

    public Instant getCreationDate() {
        return creationInstant;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        GroupState other = (GroupState) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    public GroupId getId() {
        return id;
    }

    public List<GroupUserItem> getMembers() {
        return members;
    }
}
