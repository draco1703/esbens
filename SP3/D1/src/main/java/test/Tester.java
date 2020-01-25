package test;

import javax.persistence.*;
import entity.*;
import enums.CustomerType;

public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("day1");
        EntityManager em = emf.createEntityManager();

        Book book;

        em.getTransaction().begin();

        book = new Book();
        book.setTitle("book-5");
        em.persist(book);

        long id = 1;

        Customer cust = em.find(Customer.class, id);
        cust.setcType(CustomerType.RUSTY);

        cust.addHobby("Sleep");
        cust.addPhone("12345678", "Intetsigende..");

        em.persist(cust);

        em.getTransaction().commit();
        em.close();

    }

}
