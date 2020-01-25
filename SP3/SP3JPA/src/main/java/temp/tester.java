/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import entity.*;
import facade.Facade;

/**
 *
 * @author Esben
 */
public class tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Facade fc = new Facade();

        //Comment out the ones with create if they are already in DB
         
        //Create Customer
//        fc.createCustomer("person1", "mail@email.com");
//        fc.createCustomer("person2", "snail@email.com");
//        fc.createCustomer("Peter Pedersen", "pp@email.dk");
//        fc.createCustomer("John Doe", "johnd@email.com");
//        fc.createCustomer("Jane Doe", "jado@email.co.uk");
//        fc.createCustomer("P. Erson", "person@email.se");

        //Find a Customer
        System.out.println(fc.findCustomerByName("person1"));
        System.out.println(fc.findCustomerByID(4));
        System.out.println(fc.findCustomerByEmail("pp@email.dk"));

        //Get all customers
        System.out.println(fc.getAllCustomers());

        //Create an Order (insert a few orders) (Orders are always linked to a person.)
//        fc.createEmptyOrder(fc.findCustomerByName("person1"));
//        fc.createEmptyOrder(fc.findCustomerByID(4));
//        fc.createEmptyOrder(fc.findCustomerByEmail("pp@email.dk"));
//        fc.createEmptyOrder(fc.findCustomerByEmail("mail@email.com"));
//        fc.createEmptyOrder(fc.findCustomerByID(6));

        //Find an Order
        ItemOrder o1 = fc.findOrderByID(1);
        ItemOrder o2 = fc.findOrderByID(3);
        ItemOrder o3 = fc.findOrderByID(4);
        System.out.println(o1);
        System.out.println(o2);
        System.out.println(o3);

        //Create an ItemType (insert a few ItemTypes)
//        fc.createItemType("couch", "Big couch", 500);
//        fc.createItemType("Table", "Flat table with four legs", 1500);
//        fc.createItemType("Chair", "The ch is silent, so it's pronunced as \"air\". Which is also the material", 750);
//        fc.createItemType("Rug", "Perfect for rats", 50);

        //Create an OrderLine (insert a few orderLines)
//        fc.createOrderLine(o1, 5, fc.findItemTypeByName("couch"));
//        fc.createOrderLine(o1, 3, fc.findItemTypeByName("Chair"));
//        fc.createOrderLine(o1, 6, fc.findItemTypeByName("Rug"));
//        fc.createOrderLine(o2, 1, fc.findItemTypeByName("Chair"));
//        fc.createOrderLine(o3, 999, fc.findItemTypeByName("Table"));
    }
}
