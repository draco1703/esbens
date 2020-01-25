/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import entity.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author Esben
 */
public class DBPopulate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ca3PU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        Role adminRole = new Role("admin");
        User admin = new User("admin", "fatmin");
        admin.addRole(adminRole);
        em.persist(admin);
        em.persist(adminRole);
        
        
        
        Role userRole = new Role("user");
        User norm = new User("notmin", "regularguy");
        norm.addRole(userRole);
        em.persist(norm);
        em.persist(userRole);
        
        em.getTransaction().commit();
        em.close();

    }
    
}
