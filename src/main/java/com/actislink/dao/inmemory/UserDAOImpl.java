package com.actislink.dao.inmemory;

import java.util.HashSet;
import java.util.Set;

import com.actislink.dao.AlreadyExistException;
import com.actislink.dao.UserDAO;
import com.actislink.model.UserState;

public class UserDAOImpl implements UserDAO {

    private Set<UserState> set = new HashSet<UserState>();

    @Override
    public synchronized void create(UserState userState) throws AlreadyExistException {
        if (set.contains(userState)) {
            throw new AlreadyExistException();
        }
        set.add(userState);
    }

}
