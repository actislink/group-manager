package com.actislink.modules;

import com.actislink.bussines.GroupManagerFactory;
import com.actislink.bussines.UserManager;
import com.actislink.dao.GroupDAO;
import com.actislink.dao.UserDAO;
import com.actislink.dao.inmemory.GroupDAOImpl;
import com.actislink.dao.inmemory.UserDAOImpl;
import com.actislink.model.GroupId;
import com.actislink.service.GroupService;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class UserModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserDAO.class).to(UserDAOImpl.class);
        bind(UserManager.class).toInstance(new UserManager());

        bind(GroupDAO.class).to(GroupDAOImpl.class);
        bind(GroupService.class).toInstance(new GroupService());

        install(new FactoryModuleBuilder()
                .implement(GroupId.class, GroupId.class)
                .build(GroupManagerFactory.class));
    }

}
