package com.actislink.bussines;

import javax.inject.Inject;

import com.actislink.dao.AlreadyExistException;
import com.actislink.dao.GroupDAO;
import com.actislink.model.GroupCreation;
import com.actislink.model.GroupId;
import com.actislink.model.GroupState;
import com.actislink.model.UserId;

public class GroupManager {

    @Inject
    private GroupDAO groupDAO;

    public void createGroup(GroupCreation groupCreation) throws AlreadyExistException {
        GroupState state = new GroupState(groupCreation.getName());

        groupDAO.create(state);
    }

    public void addUser(GroupId groupId, UserId userId) {
        groupDAO.addMember(groupId, userId);
    }
}
