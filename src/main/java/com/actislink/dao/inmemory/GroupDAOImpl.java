package com.actislink.dao.inmemory;

import java.util.HashMap;
import java.util.Map;

import com.actislink.dao.AlreadyExistException;
import com.actislink.dao.GroupDAO;
import com.actislink.model.GroupId;
import com.actislink.model.GroupInfo;
import com.actislink.model.GroupState;
import com.actislink.model.UserId;

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
    public GroupInfo loadById(GroupId id) {
        GroupState state = map.get(id);
        GroupInfo info = null;
        if (state != null) {
            info = new GroupInfo(state.getId().getId());
        }
        return info;
    }

    @Override
    public void addMember(GroupId groupId, UserId userId) {
        map.get(groupId).getMembers().add(userId);
    }

}
