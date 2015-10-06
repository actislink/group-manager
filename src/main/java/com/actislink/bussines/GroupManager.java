package com.actislink.bussines;

import javax.inject.Inject;

import com.actislink.dao.GroupDAO;
import com.actislink.model.GroupId;
import com.actislink.model.UserId;
import com.google.inject.assistedinject.Assisted;

public class GroupManager {

    private GroupId id;
    @Inject
    private GroupDAO groupDAO;

    @Inject
    public GroupManager(@Assisted GroupId id) {
        this.id = id;
    }

    public void addUser(UserId userId) {
        groupDAO.addMember(id, userId);
    }

    public void join(UserId id) {
        groupDAO.join(this.id, id);
    }

    public void leave(UserId id) {
        groupDAO.leave(this.id, id);
    }
}
