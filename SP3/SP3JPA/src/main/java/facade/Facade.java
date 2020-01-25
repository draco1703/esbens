/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Esben
 */
public class Facade {

    EntityManagerFactory emf;

    public Facade() {
    }

    public Facade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sp3jpa");
        return emf.createEntityManager();
    }

    public void createCustomer(String name, String email) {
        EntityManager em = getEntityManager();
        Customer cust = new Customer(name, email);
        try {
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    public Customer findCustomerByID(int id) {
        EntityManager em = getEntityManager();
        Customer cust = new Customer();
        try {
            cust = em.createNamedQuery("Customer.findByID", Customer.class).setParameter("id", id).getSingleResult();
        } finally {
            em.close();
        }
        return cust;
    }

    public Customer findCustomerByName(String name) {
        EntityManager em = getEntityManager();
        Customer cust = new Customer();
        try {
            cust = em.createNamedQuery("Customer.findByName", Customer.class).setParameter("costumer_name", name).getSingleResult();
        } finally {
            em.close();
        }
        return cust;
    }

    public Customer findCustomerByEmail(String email) {
        EntityManager em = getEntityManager();
        Customer cust = new Customer();
        try {
            cust = em.createNamedQuery("Customer.findByEmail", Customer.class).setParameter("email", email).getSingleResult();
        } finally {
            em.close();
        }
        return cust;
    }

    public List<Customer> getAllCustomers() {
        EntityManager em = getEntityManager();
        List<Customer> custList = new ArrayList();
        try {
            custList = em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
        } finally {
            em.close();
        }
        return custList;
    }

    public List<ItemType> getAllItemTypes() {
        EntityManager em = getEntityManager();
        List<ItemType> typeList = new ArrayList();
        try {
            typeList = em.createNamedQuery("ItemType.findAll", ItemType.class).getResultList();
        } finally {
            em.close();
        }
        return typeList;
    }

    public ItemType findItemTypeByName(String name) {
        EntityManager em = getEntityManager();
        ItemType item = new ItemType();
        try {
            item = em.createNamedQuery("ItemType.findByItemName", ItemType.class).setParameter("item_name", name).getSingleResult();
        } finally {
            em.close();
        }
        return item;
    }

    public List<ItemOrder> getAllItemOrders() {
        EntityManager em = getEntityManager();
        List<ItemOrder> orderList = new ArrayList();
        try {
            orderList = em.createNamedQuery("ItemOrder.findAll").getResultList();
        } finally {
            em.close();
        }
        return orderList;
    }

    public List<OrderLine> getAllOrderLines() {
        EntityManager em = getEntityManager();
        List<OrderLine> lineList = new ArrayList();
        try {
            lineList = em.createNamedQuery("OrderLine.findAll").getResultList();
        } finally {
            em.close();
        }
        return lineList;
    }

    public void createEmptyOrder(Customer cust) {
        EntityManager em = getEntityManager();
        ItemOrder order = new ItemOrder();
        cust.addOrder(order);
        try {
            em.getTransaction().begin();
            em.persist(order);
            em.merge(cust);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void createOrder(Customer cust, ArrayList<OrderLine> orderLines) {
        EntityManager em = getEntityManager();
        ItemOrder order = new ItemOrder(orderLines);
        cust.addOrder(order);
        try {
            em.getTransaction().begin();
            em.persist(order);
            em.merge(cust);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
//    public void addOrderToCustomer(Customer cust) {
//        EntityManager em = getEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.persist(order);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }
    public ItemOrder findOrderByID(int id) {
        EntityManager em = getEntityManager();
        ItemOrder order = new ItemOrder();
        try {
            order = em.createNamedQuery("ItemOrder.findById", ItemOrder.class).setParameter("order_id", id).getSingleResult();
        } finally {
            em.close();
        }
        return order;
    }

    public void createItemType(String name, String description, int price) {
        EntityManager em = getEntityManager();
        ItemType type = new ItemType(name, description, price);
        try {
            em.getTransaction().begin();
            em.persist(type);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void createOrderLine(ItemOrder order, int quantity, ItemType item) {
        EntityManager em = getEntityManager();
        OrderLine OL = new OrderLine(quantity, item);
        order.addOrderLine(OL);
        try {
            em.getTransaction().begin();
            em.persist(OL);
            em.merge(order);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
