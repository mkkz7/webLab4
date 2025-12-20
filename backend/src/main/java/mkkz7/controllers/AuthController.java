package mkkz7.controllers;

import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import mkkz7.beans.AuthBean;
import mkkz7.utils.ResultDTO;
import mkkz7.utils.UserDTO;

@Path("/auth")
public class AuthController {
    @EJB
    private AuthBean authBean;

    @POST
    @Path("/registration")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(UserDTO userDTO){
        boolean success = authBean.regUser(userDTO);
        if(success){
            return Response.ok().entity(new ResultDTO(true, "User registered!")).build();
        }else{
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ResultDTO(false, "Username already exists"))
                    .build();
        }
    }
}
