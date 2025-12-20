package mkkz7.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.ws.rs.POST;
import mkkz7.entities.PointResult;

import java.util.List;

public class PointDAO implements IPointDAO{
    @Override
    public void save(EntityManager em, PointResult result){
        em.persist(result);
    }

    @Override
    public PointResult find(EntityManager em, long id){
        return em.find(PointResult.class, id);
    }

    @Override
    public List<PointResult> getAll(EntityManager em){
        return em.createQuery("select points from PointResult points", PointResult.class)
                .getResultList();
    }

    @Override
    public void delete(EntityManager em, PointResult result){
        em.remove(result);
    }

    @Override
    public void clear(EntityManager em){
        Query query = em.createQuery("delete from PointResult point");
        query.executeUpdate();
    }
}
