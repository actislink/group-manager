package com.actislink.dao.inmemory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.actislink.dao.AlreadyExistException;
import com.actislink.dao.GroupDAO;
import com.actislink.model.GroupId;
import com.actislink.model.GroupState;
import com.actislink.model.GroupUserItem;
import com.actislink.model.UserId;
import com.google.inject.Singleton;

@Singleton
public class GroupDAOImpl implements GroupDAO {

    private Map<GroupId, GroupState> map = new HashMap<GroupId, GroupState>();

    @Override
    public synchronized void create(GroupState groupState) throws AlreadyExistException {
        if (map.keySet().contains(groupState.getId())) {
            throw new AlreadyExistException();
        }
        map.put(groupState.getId(), groupState);
    }

    @Override
    public GroupState loadById(GroupId id) {
        return map.get(id);
    }

    @Override
    public List<GroupState> listAll() {
        return new LinkedList<GroupState>(map.values());
    }

    @Override
    public void join(GroupId groupId, UserId userId) {
        GroupState groupState = map.get(groupId);
        if (groupState == null) {
            throw new IllegalArgumentException();
        }
        groupState.getMembers().add(new GroupUserItem(userId));
    }

    @Override
    public void leave(GroupId groupId, UserId userId) {
        GroupState groupState = map.get(groupId);
        if (groupState == null) {
            throw new IllegalArgumentException();
        }
        groupState.getMembers().remove(new GroupUserItem(userId));
    }

}
