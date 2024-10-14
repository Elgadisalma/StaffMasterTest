package org.example.staffmaster2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.staffmaster2.entity.Employee;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        try {
//            entityManager.getTransaction().begin();
//            Employee employee = new Employee();
//            employee.setName("test");
//            entityManager.persist(employee);
//            entityManager.getTransaction().commit();
//        }finally {
//            entityManager.close();
//        }
    }
}

