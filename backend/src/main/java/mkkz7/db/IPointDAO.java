package mkkz7.db;

import jakarta.ejb.Local;
import jakarta.persistence.EntityManager;
import jdk.jfr.Label;
import mkkz7.entities.PointResult;

import java.util.List;

@Local
public interface IPointDAO {
    void save(EntityManager em, PointResult result);
    PointResult find(EntityManager em, long id);
    List<PointResult> getAll(EntityManager em);
    void delete(EntityManager em, PointResult result);
    void clear(EntityManager em);
}
