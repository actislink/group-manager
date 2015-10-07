package com.actislink.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.actislink.bussines.GroupManager;
import com.actislink.bussines.GroupManagerFactory;
import com.actislink.dao.AlreadyExistException;
import com.actislink.dao.GroupDAO;
import com.actislink.model.GroupCreation;
import com.actislink.model.GroupId;
import com.actislink.model.GroupItem;
import com.actislink.model.GroupState;
import com.actislink.model.UserId;
import com.google.inject.Provides;
import com.google.inject.Singleton;

@Singleton
public class GroupService {

    private Map<GroupId, GroupManager> map = new HashMap<GroupId, GroupManager>();

    @Inject
    private GroupDAO groupDAO;
    @Inject
    private GroupManagerFactory factory;

    public List<GroupItem> listAll() {
        List<GroupItem> list = new ArrayList<GroupItem>();
        for (GroupState groupState : groupDAO.listAll()) {
            list.add(new GroupItem(groupState.getId(), groupState.getCreationDate()));
        }

        return list;
    }

    public void createGroup(GroupCreation groupCreation, UserId creator) throws AlreadyExistException {
        GroupState state = new GroupState(groupCreation.getName());

        groupDAO.create(state);

        GroupId groupId = new GroupId(groupCreation.getName());
        manage(groupId).addUser(creator);
    }

    @Provides
    @Singleton
    public synchronized GroupManager manage(GroupId id) {
        if (!map.containsKey(id)) {
            map.put(id, factory.create(id));
        }

        return map.get(id);
    }

}
