package mkkz7.beans;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import mkkz7.db.IPointDAO;
import mkkz7.db.IUserDAO;
import mkkz7.db.entities.PointResult;
import mkkz7.db.entities.User;
import mkkz7.exceptions.PointIsNotExistException;
import mkkz7.utils.DTO.PointResultDTO;

import java.util.List;

@Stateless
public class ServiceBean {
    @EJB
    private IUserDAO userDAO;

    @EJB
    private IPointDAO pointDAO;

    public void register(User user){
        userDAO.save(user);
    }

    public User findByUsername(String username){
        try {
            return userDAO.findByUsername(username);
        }catch (Exception e){
            return null;
        }
    }

    public User findById(long id){
        try {
            return userDAO.findById(id);
        }catch (Exception e){
            return null;
        }
    }

    public void savePoint(PointResult pointResult){
        pointDAO.save(pointResult);
    }

    public List<PointResult> getAllPoints(){
        try {
            return pointDAO.getAll();
        }catch (Exception e){
            return null;
        }
    }

    public List<PointResult> getPointsByUser(long id){
        try {
            return pointDAO.getPointsByUser(id);
        }catch (Exception e){
            return null;
        }
    }

    public void deletePoint(PointResultDTO point){
        try{
            PointResult pointResult = pointDAO.find(point.getId());
            if (pointResult == null){
                throw new PointIsNotExistException("Point is not found!");
            }
            pointDAO.delete(pointResult);
        }catch(Exception e){

        }
    }

    public void deletePoints(){
        try {
            pointDAO.clear();
        }catch (Exception e){

        }
    }
}
