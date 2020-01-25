package facade;

import entity.*;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Esben
 */
public interface IPersonFacade {
    public void addEntityManagerFactory(EntityManagerFactory emf);
    public Person addPerson(Person p);
    public Person deletePerson(int id);
    public Person getPerson(int id);
    public List<Person> getAllPersons();
    public Person editPerson(Person p);
}
