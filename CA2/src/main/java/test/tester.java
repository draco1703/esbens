/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import DTO.*;
import entity.*;
import facade.Facade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import randomGen.RandomGen;

/**
 *
 * @author Esben
 */
public class tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ca2pu");
        Facade fc = new Facade(emf);

        RandomGen rGen = new RandomGen();

//        List<Person> pers = rGen.generatePerson(50);
//        for(int i = 0; i < 50; i++){
//            fc.createPerson(pers.get(i));
//        }

        List<PersonDTO> ppl = fc.getAllPeopleDTO();
        
        
        List<Phone> ph = rGen.generatePhone(101);
        System.out.println(ph);
        List<Address> addr = rGen.generateAddress(101);
        System.out.println(addr);
        

//
//        rGen.generateRandomFullPeople(1);
        
        
        System.out.println(fc.getAllPeopleDTO());
        System.out.println(fc.getAllContactDTO());
        
    }

}
