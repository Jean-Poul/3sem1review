/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import entities.Employee;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jplm
 */
public class main {
    
     public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Employee("Kurt", "Address 1", 1));
            em.persist(new Employee("Hanne", "Address 2", 2));
            em.persist(new Employee("Jan", "Address 3", 3));
            em.persist(new Employee("Irene", "Address 4", 4));
            em.persist(new Employee("Tian", "Address 5", 5));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
     }
}
