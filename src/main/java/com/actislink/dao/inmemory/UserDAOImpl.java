package com.actislink.dao.inmemory;

import java.util.HashMap;
import java.util.Map;

import com.actislink.dao.AlreadyExistException;
import com.actislink.dao.UserDAO;
import com.actislink.model.UserId;
import com.actislink.model.UserInfo;
import com.actislink.model.UserState;
import com.google.inject.Singleton;

@Singleton
public class UserDAOImpl implements UserDAO {

    private Map<UserId, UserState> map = new HashMap<UserId, UserState>();

    @Override
    public synchronized void create(UserState userState) throws AlreadyExistException {
        if (map.keySet().contains(userState.getId())) {
            throw new AlreadyExistException();
        }
        map.put(userState.getId(), userState);
    }

    @Override
    public UserInfo loadById(UserId id) {
        UserState state = map.get(id);
        UserInfo userInfo = null;
        if (state != null) {
            userInfo = new UserInfo(state.getId().getId(), state.getDescription());
        }
        return userInfo;
    }

}
