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

import com.actislink.dao.AlreadyExistException;
import com.actislink.model.GroupCreation;
import com.actislink.model.GroupId;
import com.actislink.model.UserId;
import com.actislink.service.GroupService;

@Path("/group")
public class GroupController {
    @Inject
    private GroupService groupService;

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupController.class);

    @GET
    public Response create(@QueryParam("name") String name, @Context HttpServletRequest req) {
        GroupCreation groupCreation = new GroupCreation(name);

        try {
            groupService.createGroup(groupCreation);

            HttpSession session = req.getSession(false);

            GroupId groupId = new GroupId(groupCreation.getName());
            UserId userId = new UserId(session.getAttribute("username").toString());

            groupService.manage(groupId).addUser(userId);

            return Response.status(200).entity("ok").build();
        } catch (AlreadyExistException e) {
            LOGGER.warn("", e);
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}
