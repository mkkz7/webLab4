package mkkz7.db;

import jakarta.ejb.Local;
import mkkz7.db.entities.PointResult;

import java.util.List;

@Local
public interface IPointDAO {
    void save( PointResult result);
    PointResult find(long id);
    List<PointResult> getAll();
    List<PointResult> getPointsByUser(long id);
    void delete(PointResult result);
    void clear();
}
