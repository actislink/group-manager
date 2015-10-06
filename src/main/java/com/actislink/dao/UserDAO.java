package com.actislink.dao;

import com.actislink.model.UserId;
import com.actislink.model.UserInfo;
import com.actislink.model.UserState;

public interface UserDAO {

    void create(UserState userState) throws AlreadyExistException;

    UserInfo loadById(UserId id);
}
