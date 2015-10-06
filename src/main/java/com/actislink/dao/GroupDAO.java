package com.actislink.dao;

import java.util.List;

import com.actislink.model.GroupId;
import com.actislink.model.GroupState;
import com.actislink.model.UserId;

public interface GroupDAO {

    void create(GroupState groupState) throws AlreadyExistException;

    GroupState loadById(GroupId id);

    List<GroupState> listAll();

    void join(GroupId groupId, UserId userId);

    void leave(GroupId groupId, UserId userId);
}
