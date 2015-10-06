package com.actislink.bussines;

import javax.inject.Inject;

import com.actislink.dao.GroupDAO;
import com.actislink.model.GroupId;
import com.actislink.model.UserId;

public class GroupManager {

    private GroupId id;
    @Inject
    private GroupDAO groupDAO;
    
    public GroupManager(GroupId id){
        this.id = id;
    }

    public void addUser(UserId userId) {
        groupDAO.addMember(id, userId);
    }
}
