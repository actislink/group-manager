package com.actislink.bussines;

import com.actislink.model.GroupId;

public interface GroupManagerFactory {

    GroupManager create(GroupId id);
}
