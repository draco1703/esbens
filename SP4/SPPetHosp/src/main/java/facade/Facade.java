/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.*;
import java.util.ArrayList;
import java.util.Date;
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
        return emf.createEntityManager();
    }

//    public List<Pet> getAllPets() {
//        EntityManager em = getEntityManager();
//        List<Pet> pets = new ArrayList();
//        List<PetDTO> petsProcessed = new ArrayList();
//        try {
//            pets = em.createNamedQuery("Pet.findAll", Pet.class).getResultList();
//        } finally {
//            em.close();
//        }
//        for (Pet pet : pets) {
//            petsProcessed.add(new PetDTO(pet, true, true));
//        }
//        return pets;
//    }
    
    public List<PetDTO> getAllPets() {
        EntityManager em = getEntityManager();
        List<Pet> pets = new ArrayList();
        List<PetDTO> petsProcessed = new ArrayList();
        try {
            pets = em.createNamedQuery("Pet.findAll").getResultList();
        } finally {
            em.close();
        }
        for (Pet pet : pets) {
            petsProcessed.add(new PetDTO(pet, true, false));
        }
        return petsProcessed;
    }

    public List<PetDTO> getAllLivingPets() {
        EntityManager em = getEntityManager();
        List<Pet> pets = new ArrayList();
        List<PetDTO> petsProcessed = new ArrayList();
        try {
            pets = em.createNamedQuery("Pet.findByDeath").setParameter("death", null).getResultList();
        } finally {
            em.close();
        }
        for (Pet pet : pets) {
            petsProcessed.add(new PetDTO(pet, true, true));
        }
        return petsProcessed;
    }

    public List<PetDTO> getAllPetsWithEvent(Date date) {
        EntityManager em = getEntityManager();
        List<Event> events = new ArrayList();
        List<PetDTO> pets = new ArrayList();
        try {
            events = em.createNamedQuery("Event.findByDate").setParameter("date", date).getResultList();
        } finally {
            em.close();
        }

        for (Event event : events) {
            pets.add(new PetDTO(event.getPet(), true, true));
        }
        return pets;
    }

}
