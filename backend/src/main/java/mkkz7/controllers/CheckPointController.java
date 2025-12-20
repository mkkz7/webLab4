package mkkz7.controllers;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import mkkz7.beans.ControllerBean;

@Path("/checkpoint")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CheckPointController {
    @EJB
    private ControllerBean controllerBean;

    @GET
    public String getM(){
        return null;
    }

    @POST
    public String checkPoint(){
        return null;
    }

}
