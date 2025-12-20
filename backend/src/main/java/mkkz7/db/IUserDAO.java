package mkkz7.db;

import jakarta.ejb.Local;
import jakarta.persistence.EntityManager;
import mkkz7.entities.User;

@Local
public interface IUserDAO {
    void save(User user);
    User find(long id);
    void delete(User user);
    void clear();
    User findByUsername(String username);
}
