package mkkz7.db;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import mkkz7.db.entities.PointResult;

import java.util.List;

@Stateless
public class PointDAO implements IPointDAO{
    @PersistenceContext(unitName = "lab4-persistence-unit")
    private EntityManager em;

    @Override
    public void save(PointResult result){
        em.persist(result);
    }

    @Override
    public PointResult find(long id){
        return em.find(PointResult.class, id);
    }

    @Override
    public List<PointResult> getAll(){
        return em.createQuery("select points from PointResult points", PointResult.class)
                .getResultList();
    }

    @Override
    public List<PointResult> getPointsByUser(long id){
        return em.createQuery(
                        "SELECT p FROM PointResult p WHERE p.user.id = :uid", PointResult.class
                )
                .setParameter("uid", id)
                .getResultList();
    }

    @Override
    public void delete(PointResult result){
        em.remove(result);
    }

    @Override
    public void clear(){
        Query query = em.createQuery("delete from PointResult point");
        query.executeUpdate();
    }
}
