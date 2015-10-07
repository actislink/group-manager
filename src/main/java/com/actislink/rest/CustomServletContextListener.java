package com.actislink.rest;

import com.actislink.modules.UserModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class CustomServletContextListener extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {

        return Guice.createInjector(new ServletModule() {

            @Override
            protected void configureServlets() {

                super.configureServlets();

                // Configuring Jersey via Guice:
                ResourceConfig resourceConfig = new PackagesResourceConfig("com/actislink/rest");
                for (Class<?> resource : resourceConfig.getClasses()) {
                    bind(resource);
                }
                serve("/rest/*").with(GuiceContainer.class);
            }
        }, new UserModule());
    }

}
