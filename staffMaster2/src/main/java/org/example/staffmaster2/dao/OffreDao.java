package org.example.staffmaster2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.staffmaster2.config.EntityManagerFactorySingleton;
import org.example.staffmaster2.entity.Employee;
import org.example.staffmaster2.entity.Offre;

import java.util.List;

public class OffreDao {
    EntityManagerFactory emf = EntityManagerFactorySingleton.getEntityManagerFactory();

    public void addOffre(Offre offre) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(offre);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(em != null) {
                em.close();
            }
        }
    }


    public List<Offre> getOffres() {
        EntityManager em = null;
        List<Offre> offres = null;
        try {
            em = emf.createEntityManager();
            offres = em.createQuery("SELECT e FROM Offre e", Offre.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return offres;
    }

    public Offre getOffreById(Long id) {
        EntityManager em = null;
        Offre offre = null;
        try {
            em = emf.createEntityManager();
            offre = em.find(Offre.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return offre;
    }



}
