package com.actislink.modules;

import com.actislink.bussines.UserManager;
import com.actislink.dao.UserDAO;
import com.actislink.dao.inmemory.UserDAOImpl;
import com.actislink.service.GroupService;
import com.google.inject.AbstractModule;

public class UserModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserDAO.class).to(UserDAOImpl.class);
        bind(UserManager.class).toInstance(new UserManager());

    
        bind(GroupService.class).toInstance(new GroupService());
    }

}
