package facade;

import entity.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Esben
 */
public class PersonFacade implements IPersonFacade {

    private EntityManagerFactory emf;

    
    public PersonFacade() {
    }

    public PersonFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf) {
        //Persistence.createEntityManagerFactory("sp4d123");
        this.emf = emf;
    }

    @Override
    public Person addPerson(Person p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return p;
    }

    @Override
    public Person deletePerson(int id) {
        EntityManager em = emf.createEntityManager();
        Person person = new Person();
        try {
            person = em.find(Person.class, id);
            em.getTransaction().begin();
            em.remove(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return person;
    }

    @Override
    public Person getPerson(int id) {
        EntityManager em = emf.createEntityManager();
        Person person = new Person();
        try {
            person = em.find(Person.class, id);
        } finally {
            em.close();
        }
        return person;
    }

    @Override
    public List<Person> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        List<Person> people = new ArrayList();
        try {
            people = em.createNamedQuery("Person.findAll").getResultList();
        } finally {
            em.close();
        }
        return people;
    }

    @Override
    public Person editPerson(Person p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.createQuery("UPDATE Person p SET p.firstName = ?1, p.lastName = ?2, p.phone = ?3 WHERE p.id = ?4")
                    .setParameter(1, p.getFirstName())
                    .setParameter(2, p.getLastName())
                    .setParameter(3, p.getPhone())
                    .setParameter(4, p.getId())
                    .executeUpdate();
        } finally {
            em.close();
        }
        return p;
    }

}
