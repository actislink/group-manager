package com.actislink.bussines;

import javax.inject.Inject;

import com.actislink.dao.AlreadyExistException;
import com.actislink.dao.UserDAO;
import com.actislink.model.UserCreation;
import com.actislink.model.UserId;
import com.actislink.model.UserInfo;
import com.actislink.model.UserState;

public class UserManager {

    @Inject
    private UserDAO userDAO;

    public void createUser(UserCreation userCreation) throws AlreadyExistException {
        UserState state = new UserState(userCreation.getUsername());
        state.setDescription(userCreation.getDescription());

        userDAO.create(state);
    }

    public UserInfo get(UserId id) {
        return userDAO.loadById(id);
    }
}
