package com.actislink.rest;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

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

import com.actislink.dao.InternalException;
import com.actislink.model.GroupCreation;
import com.actislink.model.GroupId;
import com.actislink.model.GroupInfo;
import com.actislink.model.UserId;
import com.actislink.service.GroupService;
import com.google.common.base.Joiner;

@Path("/group")
public class GroupController {
    @Inject
    private GroupService groupService;

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupController.class);

    @GET
    public Response create(@QueryParam("name") String name, @QueryParam("maxFreq") long maxFreq, @Context HttpServletRequest req) {
        try {
            UserId userId = getCurrentUser(req.getSession());
            groupService.createGroup(new GroupCreation(name, Duration.of(maxFreq, ChronoUnit.SECONDS)), userId);

            return Response.status(200).build();
        } catch (InternalException e) {
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
        } catch (InternalException e) {
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
        return Response.status(200).entity(Joiner.on("<br/>").join(groupService.listAll())).build();
    }

    @GET
    @Path("{name}")
    public Response display(@PathParam("name") String group) {
        GroupInfo info = groupService.manage(new GroupId(group)).get();
        if (info == null) {
            return Response.status(404).build();
        }
        return Response.status(200).entity(info.toString()).build();
    }
}
