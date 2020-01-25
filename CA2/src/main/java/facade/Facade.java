/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.persistence.*;
import entity.*;
import DTO.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Esben
 */
public class Facade {

    private EntityManagerFactory emf;

    public Facade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEM() {
        return emf.createEntityManager();
    }

    public List<ContactDTO> getAllContactDTO() {
        List<PersonDTO> arr;
        ArrayList<ContactDTO> res = new ArrayList();
        arr = getAllPeopleDTO();
        for (PersonDTO dto : arr) {
            res.add(new ContactDTO(dto));
        }
        return res;
    }

    public List<PersonDTO> getAllPeopleDTO() {
        EntityManager em = getEM();
        try {
            TypedQuery<PersonDTO> q = em.createQuery("Select new DTO.PersonDTO(p) from Person p", PersonDTO.class);
            List<PersonDTO> people = q.getResultList();
            return people;
        } finally {
            em.close();
        }
    }
    
    public List<Person> getAllPeople() {
        EntityManager em = getEM();
        try {
            TypedQuery<Person> q = em.createQuery("Select p from Person p", Person.class);
            List<Person> people = q.getResultList();
            return people;
        } finally {
            em.close();
        }
    }

    public PersonDTO getPersonDTO(int id) {
        EntityManager em = getEM();
        try {
            Person person = em.find(Person.class, id);
            if (person != null) {
                PersonDTO p = new PersonDTO(person);
                return p;
            } else {
                return null;
            }
        } finally {
            em.close();
        }
    }

    public PersonDTO getPersonDTOByPhoneNumber(int number) {
        EntityManager em = getEM();
        try {
            TypedQuery<PersonDTO> q = em.createQuery("SELECT new DTO.PersonDTO(ph.person) FROM Phone ph WHERE ph.number = :number", PersonDTO.class)
                    .setParameter("number", number);
            PersonDTO res = (PersonDTO) q.getSingleResult();
            if (res == null) {
                return null;
            }
            return res;
        } finally {
            em.close();
        }
    }

    public List<PersonDTO> getPeopleDTOByHobby(String name) {
        EntityManager em = getEM();
        try {
            TypedQuery<PersonDTO> q = em.createQuery("SELECT DISTINCT new DTO.PersonDTO(p) FROM Person p LEFT JOIN p.hobbies h WHERE h.name = :name", PersonDTO.class)
                    .setParameter("name", name);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<PersonDTO> getPeopleDTOByCity(String city) {
        EntityManager em = getEM();
        try {
            TypedQuery<PersonDTO> q = em.createQuery("SELECT DISTINCT new DTO.PersonDTO(p) FROM Person p LEFT JOIN p.address.cityInfo c WHERE c.city= :city", PersonDTO.class)
                    .setParameter("city", city);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getPersonDTOCountFromHobby(String name) {
        return getPeopleDTOByHobby(name).size();
    }

    public PersonDTO createPerson(Person p) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return new PersonDTO(p);
        } finally {
            em.close();
        }
    }

    public PersonDTO createPerson(PersonDTO p) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            Person person = new Person(p);
            em.persist(person);
            em.getTransaction().commit();
            return new PersonDTO(person);
        } finally {
            em.close();
        }
    }
    
    public Phone createPhone(Phone p) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    public PersonDTO editPerson(PersonDTO p) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.merge(new Person(p));
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }
    
    public PersonDTO editPerson(Person p) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            return new PersonDTO(p);
        } finally {
            em.close();
        }
    }

    public void deletePerson(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            Person p = em.find(Person.class, id);
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Hobby> getAllHobbies() {
        EntityManager em = getEM();
        try {
            TypedQuery<Hobby> q = em.createQuery("SELECT h FROM Hobby h", Hobby.class);
            List<Hobby> hobbies = q.getResultList();
            return hobbies;
        } finally {
            em.close();
        }
    }
    
    public List<PhoneDTO> getAllPhonesDTO() {
        EntityManager em = getEM();
        try {
            TypedQuery<PhoneDTO> q = em.createQuery("SELECT new DTO.PhoneDTO(ph) FROM Phone ph", PhoneDTO.class);
            List<PhoneDTO> hobbies = q.getResultList();
            return hobbies;
        } finally {
            em.close();
        }
    }
    
    public List<HobbyDTO> getAllHobbiesDTO() {
        EntityManager em = getEM();
        try {
            TypedQuery<HobbyDTO> q = em.createQuery("SELECT new DTO.HobbyDTO(h) FROM Hobby h", HobbyDTO.class);
            List<HobbyDTO> hobbies = q.getResultList();
            return hobbies;
        } finally {
            em.close();
        }
    }

    public List<CityInfoDTO> getAllCityInfoDTO() {
        EntityManager em = getEM();
        try {
            TypedQuery<CityInfoDTO> q = em.createQuery("SELECT new DTO.CityInfoDTO(c) FROM CityInfo c", CityInfoDTO.class);
            List<CityInfoDTO> cityInfos = q.getResultList();
            return cityInfos;
        } finally {
            em.close();
        }
    }

    public void addHobbyToPerson(int id, int hid) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            Person p = em.find(Person.class, id);
            Hobby h = em.find(Hobby.class, hid);
            p.getHobbies().add(h);
            em.merge(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void addPhoneToPerson(int phoneID, int personID) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            Person p = em.find(Person.class, personID);
            Phone ph = em.find(Phone.class, phoneID);
            ph.setPerson(p);
            em.merge(ph);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public void addAddressToPerson(Address addr, int personID) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            Person p = em.find(Person.class, personID);
            p.setAddress(addr);
            em.merge(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void addHobbyToPerson(Hobby hobby, int personID) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            Person p = em.find(Person.class, personID);
            p.getHobbies().add(hobby);
            hobby.getPeopleWithHobby().add(p);
            em.merge(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public HobbyDTO getHobbyDTOByName(String hobbyName) {
        EntityManager em = getEM();
        try {
            HobbyDTO res = em.find(HobbyDTO.class, hobbyName);
            if (res == null) {
                return null;
            }
            return res;
        } finally {
            em.close();
        }
    }

    public CityInfoDTO getCityInfoDTOFromZip(int zip) {
        EntityManager em = getEM();
        try {
            CityInfoDTO res = em.find(CityInfoDTO.class, zip);
            if (res == null) {
                return null;
            }
            return res;
        } finally {
            em.close();
        }
    }
}
