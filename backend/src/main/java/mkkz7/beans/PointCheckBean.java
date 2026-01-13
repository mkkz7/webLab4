package mkkz7.beans;


import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import mkkz7.db.entities.PointResult;
import mkkz7.db.entities.User;
import mkkz7.exceptions.PointValidationException;
import mkkz7.utils.*;
import mkkz7.utils.DTO.PointDTO;
import mkkz7.utils.DTO.PointResultDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Stateless
public class PointCheckBean {
    @EJB
    AreaCheckBean areaCheckBean;

    @EJB
    ServiceBean serviceBean;
    PointValidator validator = new PointValidator();

    public void delete(PointResultDTO point){
        serviceBean.deletePoint(point);
    }

    public PointResult checkHit(PointDTO pointDTO, User user) throws PointValidationException {
        long startTime = System.nanoTime();
        PointResult pointResult;

        if(Objects.equals(pointDTO.getSource(), "form")){
            validator.validateFromGraph((int) pointDTO.getX(), (int) pointDTO.getY(), pointDTO.getR());
            pointResult = areaCheckBean.createResponse((int) pointDTO.getX(), (int) pointDTO.getY(), pointDTO.getR(), startTime);
        }else if(Objects.equals(pointDTO.getSource(), "graph")){
            validator.validateFromGraph(pointDTO.getX(), pointDTO.getY(), pointDTO.getR());
            pointResult = areaCheckBean.createResponse(pointDTO.getX(), pointDTO.getY(), pointDTO.getR(), startTime);
        }else{
            throw new PointValidationException("Wrong query source!");
        }

        pointResult.setUser(user);
        serviceBean.savePoint(pointResult);

        return pointResult;
    }

    public List<PointResultDTO> getPointsByUserId(User user){
        List<PointResultDTO> points = new ArrayList<PointResultDTO>();
        List<PointResult> result = serviceBean.getPointsByUser(user.getId());
        result.forEach(pointResult -> {
            PointResultDTO point = PointResultDTO.builder()
                    .x(pointResult.getX())
                    .y(pointResult.getY())
                    .r(pointResult.getR())
                    .hit(pointResult.isHit())
                    .execTime(pointResult.getExecTime())
                    .user_id(pointResult.getUser().getId()).build();
            points.add(point);
        });

        return points;
    }

    public List<PointResultDTO> getAllPoints(){
        List<PointResultDTO> points = new ArrayList<PointResultDTO>();
        List<PointResult> result = serviceBean.getAllPoints();

        result.forEach(pointResult -> {
            PointResultDTO point = PointResultDTO.builder()
                    .id(pointResult.getId())
                    .x(pointResult.getX())
                    .y(pointResult.getY())
                    .r(pointResult.getR())
                    .hit(pointResult.isHit())
                    .execTime(pointResult.getExecTime())
                    .user_id(pointResult.getUser().getId())
                    .username(pointResult.getUser().getUsername()).build();
            points.add(point);
        });

        return points;
    }

}
