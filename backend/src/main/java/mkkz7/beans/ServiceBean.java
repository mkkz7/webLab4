package mkkz7.beans;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import mkkz7.db.IUserDAO;
import mkkz7.entities.User;

@Stateless
public class ServiceBean {
    @EJB
    private IUserDAO userDAO;

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
}
