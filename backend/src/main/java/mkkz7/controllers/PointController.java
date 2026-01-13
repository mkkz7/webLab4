package mkkz7.controllers;

import jakarta.ejb.EJB;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import mkkz7.beans.AuthBean;
import mkkz7.beans.PointCheckBean;
import mkkz7.db.entities.PointResult;
import mkkz7.db.entities.User;
import mkkz7.utils.DTO.PointDTO;
import mkkz7.utils.DTO.PointResultDTO;
import mkkz7.beans.PollQueue;

import java.util.List;

@Path("/graph")
public class PointController {
    //Можно сделать фильтр вместо постоянной проверки сессии
    @EJB
    private PointCheckBean controllerBean;

    @EJB
    private AuthBean authBean;

    @EJB
    PollQueue pollQueue;

    @Context
    HttpServletRequest request;

    @DELETE
    @Path("/deletepoint")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePoint(PointResultDTO point){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        if(!(session.getAttribute("userId").equals(point.getUser_id()))){
            return Response.status(405).build();
        }
        controllerBean.delete(point);
        List<PointResultDTO> existingPoints = controllerBean.getAllPoints();
        pollQueue.notifyAllClients(existingPoints);
        return Response.ok().build();
    }

    @GET
    @Path("/poll")
    @Produces(MediaType.APPLICATION_JSON)
    public void poll(@Suspended AsyncResponse asyncResponse) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            asyncResponse.resume(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }

        pollQueue.add(asyncResponse);
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAll(){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        List<PointResultDTO> points = controllerBean.getAllPoints();

        return Response.ok(points).build();
    }

    @POST
    @Path("/check")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response checkPoint(PointDTO pointDTO){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        Long userId = (Long) session.getAttribute("userId");
        User user = authBean.findById(userId);
        PointResult result = controllerBean.checkHit(pointDTO, user);
        PointResultDTO dto = PointResultDTO.builder()
                .id(result.getId())
                .x(result.getX())
                .y(result.getY())
                .r(result.getR())
                .hit(result.isHit())
                .execTime(result.getExecTime())
                .user_id(result.getUser().getId()).build();

        List<PointResultDTO> allPoints = controllerBean.getAllPoints();
        pollQueue.notifyAllClients(allPoints);

        return Response.ok(dto).build();
    }
}
