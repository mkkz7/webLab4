package mkkz7.db;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import mkkz7.entities.User;

import java.util.List;

@Stateless
public class UserDAO implements IUserDAO{
    @PersistenceContext(unitName = "lab4-persistence-unit")
    private EntityManager em;

    @Override
    public void save(User user){
        em.persist(user);
    }

    @Override
    public User find(long id){
        return em.find(User.class, id);
    }

    @Override
    public User findByUsername(String username){
        List<User> users = em.createQuery(
                        "select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();

        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public void delete(User user){
        em.remove(user);
    }

    @Override
    public void clear(){
        Query query = em.createQuery("delete from User user");
        query.executeUpdate();
    }
}
