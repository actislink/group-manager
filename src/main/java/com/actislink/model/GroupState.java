package com.actislink.model;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class GroupState {
    private GroupId id;
    private Instant creationInstant;
    private Set<GroupUserItem> members = new HashSet<GroupUserItem>();

    private Duration maxFreq;
    private Instant lastAccessInstant;

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

    public Set<GroupUserItem> getMembers() {
        return members;
    }

    public Duration getMaxFreq() {
        return maxFreq;
    }

    public void setMaxFreq(Duration maxFreq) {
        this.maxFreq = maxFreq;
    }

    public Instant getLastAccessInstant() {
        return lastAccessInstant;
    }

    public void setLastAccessInstant(Instant lastAccessInstant) {
        this.lastAccessInstant = lastAccessInstant;
    }
}
