/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import enitiy.*;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Esben
 */
public class CustomerFacade {

    EntityManagerFactory emf;

    public CustomerFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Customer getCustomer(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }

    public List<Customer> getCustomers() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void addCustomer(Customer cust) {
        EntityManager em = getEntityManager();
        try {
            em.persist(cust);
        } finally {
            em.close();
        }
    }

    public void deleteCustomer(int id) {
        EntityManager em = getEntityManager();
        try {
            em.createNamedQuery("DELETE FROM Customer c WHERE c.ID = :id").executeUpdate();
        } finally {
            em.close();
        }
    }

    public void updateCustomer(Customer cust) {
        EntityManager em = getEntityManager();
        try {
            Customer c = em.find(Customer.class, cust.getId());
            if (c != null) {
                em.getTransaction().begin();
                c = cust;
                em.persist(c);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
