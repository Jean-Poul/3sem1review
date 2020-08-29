/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jplm
 */
public class MakeTestData {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        BankCustomer c1 = new BankCustomer("J-P", "L-M", "1234", 999.99, 1, "Good customer");
        BankCustomer c2 = new BankCustomer("Joe", "Potato", "5678", 2.50, 99, "Investigate");
        try {
            em.getTransaction().begin();
            em.persist(c1);
            em.persist(c2);
            em.getTransaction().commit();
            //Verify that customers are managed and has been given a database id
            System.out.println(c1.getId());
            System.out.println(c2.getId());

        } finally {
            em.close();
            emf.close();
        }
        System.out.println(c1.toString());
        System.out.println(c2.toString());
    }
}
