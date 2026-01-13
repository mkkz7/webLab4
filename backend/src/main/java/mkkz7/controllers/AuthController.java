package mkkz7.controllers;

import jakarta.ejb.EJB;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import mkkz7.beans.AuthBean;
import mkkz7.beans.PollQueue;
import mkkz7.db.entities.User;
import mkkz7.utils.DTO.ResultDTO;
import mkkz7.utils.DTO.UserDTO;

import java.util.Collections;

@Path("/auth")
public class AuthController {
    @EJB
    private AuthBean authBean;

    @EJB
    PollQueue pollQueue;

    @Context
    private HttpServletRequest request;

    @POST
    @Path("/registration")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(UserDTO userDTO){
        authBean.regUser(userDTO);

        User user = authBean.loginUser(userDTO);
        HttpSession session = request.getSession(true);
        session.setAttribute("userId", user.getId());

        return Response.ok(new ResultDTO(true, "User " + user.getUsername() + " registered!")).build();
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(UserDTO userDTO){
        User user = authBean.loginUser(userDTO);
        HttpSession session = request.getSession(true);

        session.setAttribute("userId", user.getId());

        return Response.ok(new ResultDTO(true, "Welcome back " + user.getUsername() + "! ")).build();
    }

    @POST
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logout(){
        HttpSession session = request.getSession(false);

        if(session != null) {
            session.invalidate();
        }

        return Response.ok(new ResultDTO(true, "Logged out.")).build();
    }

    @GET
    @Path("/me")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response me(){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        return Response.ok().build();
    }

    @DELETE
    @Path("/clear")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response clear(){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        authBean.clear();
        pollQueue.clear();
        pollQueue.notifyAllClients(Collections.emptyList());
        return Response.ok().build();
    }
}
