package org.example.staffmaster2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.staffmaster2.config.EntityManagerFactorySingleton;
import org.example.staffmaster2.entity.Candidature;
import org.example.staffmaster2.entity.Employee;

import java.util.List;

public class CondidatureDao {
    EntityManagerFactory emf = EntityManagerFactorySingleton.getEntityManagerFactory();

    public void addCondidature(Candidature condidature) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(condidature);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(em != null) {
                em.close();
            }
        }
    }

    public List<Candidature> getCandidatures() {
        EntityManager em = null;
        List<Candidature> candidatures = null;
        try {
            em = emf.createEntityManager();
            candidatures = em.createQuery("SELECT e FROM Candidature e", Candidature.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return candidatures;
    }

    public Candidature getCandidatureById(Long id) {
        EntityManager em = null;
        Candidature condidature = null;
        try {
            em = emf.createEntityManager();
            condidature = em.find(Candidature.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return condidature;
    }

    public void updateCandidature(Candidature candidature) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            em.merge(candidature);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em != null) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
