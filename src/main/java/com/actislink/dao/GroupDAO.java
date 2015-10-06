package com.actislink.dao;

import com.actislink.model.GroupId;
import com.actislink.model.GroupInfo;
import com.actislink.model.GroupState;
import com.actislink.model.UserId;

public interface GroupDAO {

    void create(GroupState groupState) throws AlreadyExistException;

    GroupInfo loadById(GroupId id);

    void addMember(GroupId groupId, UserId userId);
}
