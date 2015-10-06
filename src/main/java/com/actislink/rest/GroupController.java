package com.actislink.rest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
import com.google.common.base.Joiner;

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

            GroupId groupId = new GroupId(groupCreation.getName());
            UserId userId = getCurrentUser(req.getSession());

            groupService.manage(groupId).addUser(userId);

            return Response.status(200).entity("ok").build();
        } catch (AlreadyExistException e) {
            LOGGER.warn("", e);
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    private UserId getCurrentUser(HttpSession session) {
        return new UserId(session.getAttribute("username").toString());
    }

    @GET
    @Path("join/{group}")
    public Response join(@PathParam("group") String group, @Context HttpServletRequest req) {
        try {
            groupService.manage(new GroupId(group)).join(getCurrentUser(req.getSession()));
            return Response.status(200).build();
        } catch (IllegalArgumentException e) {
            LOGGER.warn("", e);
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("leave/{group}")
    public Response leave(@PathParam("group") String group, @Context HttpServletRequest req) {
        try {
            groupService.manage(new GroupId(group)).leave(getCurrentUser(req.getSession()));
            return Response.status(200).build();
        } catch (IllegalArgumentException e) {
            LOGGER.warn("", e);
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("list")
    public Response list() {
        return Response.status(200).entity(Joiner.on("\n").join(groupService.listAll())).build();
    }
}
