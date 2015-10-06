package com.actislink.dao;

import com.actislink.model.UserState;

public interface UserDAO {

    void create(UserState userState) throws AlreadyExistException;
}
