package com.actislink.bussines;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import com.actislink.dao.GroupDAO;
import com.actislink.dao.MaxFreqException;
import com.actislink.dao.UserDAO;
import com.actislink.model.GroupId;
import com.actislink.model.GroupInfo;
import com.actislink.model.GroupState;
import com.actislink.model.GroupUserItem;
import com.actislink.model.UserId;
import com.actislink.model.UserItem;
import com.actislink.model.UserState;
import com.google.common.collect.ImmutableList;
import com.google.inject.assistedinject.Assisted;

public class GroupManager {

    private GroupId id;
    @Inject
    private GroupDAO groupDAO;
    @Inject
    private UserDAO userDAO;

    @Inject
    public GroupManager(@Assisted GroupId id) {
        this.id = id;
    }

    private boolean canJoin(GroupState state) {
        return state.getLastAccessInstant() == null
                || Duration.between(state.getLastAccessInstant(), Instant.now()).compareTo(state.getMaxFreq()) > 0;
    }

    public void join(UserId id) throws MaxFreqException {
        GroupState state = groupDAO.loadById(this.id);
        if (canJoin(state)) {
            groupDAO.join(this.id, id);
            state.setLastAccessInstant(Instant.now());
        } else {
            throw new MaxFreqException();
        }
    }

    public void leave(UserId id) {
        groupDAO.leave(this.id, id);
    }

    public GroupInfo get() {
        GroupState state = groupDAO.loadById(id);
        GroupInfo info = null;
        if (state != null) {
            List<UserItem> list = new LinkedList<UserItem>();
            for (GroupUserItem item : state.getMembers()) {
                UserState userState = userDAO.loadById(item.getUserId());
                list.add(new UserItem(userState.getId().getId(), item.getJoinInstant()));
            }

            info = new GroupInfo(state.getId().getId(), state.getCreationDate(), ImmutableList.copyOf(list));
        }
        return info;
    }
}
