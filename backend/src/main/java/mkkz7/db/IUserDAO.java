package mkkz7.db;

import jakarta.ejb.Local;
import mkkz7.db.entities.User;

@Local
public interface IUserDAO {
    void save(User user);
    User findById(long id);
    void delete(User user);
    void clear();
    User findByUsername(String username);
}
