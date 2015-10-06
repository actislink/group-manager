package com.actislink.rest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.actislink.bussines.UserManager;
import com.actislink.dao.AlreadyExistException;
import com.actislink.model.UserCreation;

@Path("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Inject
    private UserManager userManager;

    @GET
    public Response create(@QueryParam("username") String username, @QueryParam("desc") String desc,
            @Context HttpServletRequest req) {
        UserCreation userCreation = new UserCreation(username);
        userCreation.setDescription(desc);

        try {
            userManager.createUser(userCreation);

            HttpSession session = req.getSession(true);
            session.setAttribute("username", username);

            return Response.status(200).entity(username + " " + desc).build();
        } catch (AlreadyExistException e) {
            LOGGER.warn("", e);
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}
