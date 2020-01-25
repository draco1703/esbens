/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import facade.Facade;
import javax.persistence.Persistence;

/**
 *
 * @author Esben
 */
public class tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Facade facade = new Facade(Persistence.createEntityManagerFactory("pethospu"));
        System.out.println(facade.getAllPets());
    }
    
}
