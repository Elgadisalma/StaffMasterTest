package org.example.staffmaster2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.staffmaster2.config.EntityManagerFactorySingleton;
import org.example.staffmaster2.entity.Employee;

import java.util.List;

public class EmployeeDao {
    EntityManagerFactory emf = EntityManagerFactorySingleton.getEntityManagerFactory();

    public void addEmployee(Employee employee) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(em != null) {
                em.close();
            }
        }
    }

    public void updateEmployee(Employee employee) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            em.merge(employee);

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

    public Employee getEmployeeById(long id) {
        EntityManager em = null;
        Employee employee = null;
        try {
            em = emf.createEntityManager();
            employee = em.find(Employee.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return employee;
    }

    public void deleteEmployee(long id) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Employee employee = em.find(Employee.class, id);
            if (employee != null) {
                em.remove(employee);
            }
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

    public List<Employee> getEmployees() {
        EntityManager em = null;
        List<Employee> employees = null;
        try {
            em = emf.createEntityManager();
            employees = em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return employees;
    }



}