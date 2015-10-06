package com.actislink.dao;

import com.actislink.model.UserId;
import com.actislink.model.UserState;

public interface UserDAO {

    void create(UserState userState) throws AlreadyExistException;

    UserState loadById(UserId id);
}
