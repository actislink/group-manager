package com.actislink.model;

import java.time.Instant;

public class GroupUserItem {
    private UserId userId;
    private Instant joinInstant;

    public GroupUserItem(UserId userId) {
        this.userId = userId;
        joinInstant = Instant.now();
    }

    public UserId getUserId() {
        return userId;
    }

    public Instant getJoinInstant() {
        return joinInstant;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
        GroupUserItem other = (GroupUserItem) obj;
        if (userId == null) {
            if (other.userId != null) {
                return false;
            }
        } else if (!userId.equals(other.userId)) {
            return false;
        }
        return true;
    }


}
