package org.example.staffmaster2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.staffmaster2.config.EntityManagerFactorySingleton;
import org.example.staffmaster2.entity.Conge;

import java.util.List;

public class CongeDao {
    EntityManagerFactory emf = EntityManagerFactorySingleton.getEntityManagerFactory();


    public void addConge(Conge conge) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(conge);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(em != null) {
                em.close();
            }
        }
    }


    public List<Conge> getConges() {
        EntityManager em = null;
        List<Conge> conges = null;
        try {
            em = emf.createEntityManager();
            conges = em.createQuery("SELECT e FROM Conge e", Conge.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return conges;
    }

    public Conge getCongeById(Long id) {
        EntityManager em = null;
        Conge conge = null;
        try {
            em = emf.createEntityManager();
            conge = em.find(Conge.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return conge;
    }
}
