/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randomGen;

import entity.*;
import facade.Facade;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Esben
 */
public class RandomGen {

    String[] fNames = {"Reed", "Juliet", "India", "Carla", "Elton", "Steel", "Aladdin", "Sean", "Sarah", "Ashton", "Bertha", "Bevis", "Lawrence", "Yetta", "Samson", "Griffith", "Meghan", "Karen", "Brady", "Serena", "Ferdinand", "Mariam", "Stephanie", "Noel", "Trevor", "Christen", "Nathan", "Chester", "Adria", "Jemima", "Linus", "Elmo", "Jeanette", "Kay", "Lee", "Daniel", "Theodore", "Ariel", "Kelsey", "Hall", "Kristen", "Castor", "Alma", "MacKensie", "Garth", "Ahmed", "Dalton", "Illana", "Dustin", "Jin", "Brady", "Virginia", "Wang", "Cathleen", "Sara", "Jacob", "Mufutau", "Danielle", "Emma", "Emerald", "Baker", "Chelsea", "Russell", "Ray", "Teegan", "Kevyn", "Carla", "Troy", "Evan", "Alice", "Lareina", "Olivia", "Clare", "Fuller", "Uma", "Darrel", "Georgia", "Cassady", "Kameko", "Jordan", "Amanda", "Xenos", "Elliott", "Amber", "Brandon", "Beck", "Chester", "Kirestin", "Rafael", "Keane", "Myles", "Chase", "Dorothy", "Althea", "Jenette", "Melissa", "Guy", "Noble", "Kelly", "Amery"};
    String[] lNames = {"Benton", "Leblanc", "Mcclain", "Williams", "Swanson", "Foster", "Glenn", "Moreno", "Paul", "Lynch", "Kirby", "Valentine", "Bush", "Fulton", "Snow", "Lyons", "Stafford", "Steele", "Harding", "Atkinson", "Hanson", "Reyes", "Slater", "Anderson", "Stark", "Bridges", "Mercer", "Shepard", "Johnston", "Swanson", "Goff", "King", "Greer", "Horne", "Dillon", "Goff", "Rowe", "Mendoza", "Savage", "Leonard", "Francis", "Carson", "Dudley", "Nixon", "Cantu", "Langley", "Wilcox", "Fischer", "Riley", "Lynn", "Vaughan", "Knapp", "Humphrey", "Nixon", "Cardenas", "Conrad", "Glenn", "Carrillo", "Mccarthy", "Ramsey", "Bond", "James", "Mccormick", "Carroll", "Burt", "Pugh", "Mcdonald", "Massey", "Ross", "Little", "Ashley", "Holmes", "Pope", "Vaughan", "Guthrie", "Bean", "Meyers", "Hines", "Hinton", "Klein", "Marshall", "Ross", "Farley", "Finch", "Henderson", "Walls", "Beard", "Hoffman", "Hill", "Fuentes", "Mclaughlin", "Stone", "Herrera", "Booker", "Baldwin", "Navarro", "Franks", "Byers", "Baldwin", "Rice"};
    String[] emailSuffix = {"email.dk", "email.com", "cmail.com", "artjhs.dk", "zetjadt.com"};

    String[] address1 = {"Æble", "Pære", "Kirsebær", "Rosen", "Tulipan", "Krysantemum", "Skov", "Hansens", "Christians"};
    String[] address2 = {"vej", "sti", "gade", "bullevard", "haven"};
    String[] additional = {"2.tv", "2.th", "3sal", "5sal", "stueetagen", "I telten bagved"};

    String[] phoneType = {"Privat mobil", "Arbejds mobil", "Fastnet"};

    String[] hobby = {"Fodbold", "Håndbold", "Basketball", "Tennis", "Squash", "Golf"};
    String[] hobbyDesc = {"Sparken til en bold", "Kasten med en bold", "Kasten med en bold", "Bold & ketcher VS fremmed person", "Bold & ketcher VS væg.", "Før din vræde ud på bolden, ikke din caddie"};

    Random r = new Random();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ca2pu");
    Facade fc = new Facade(emf);

    public RandomGen() {
    }

    private void generateRandomFullPerson(List<Hobby> hobbies) {
        Person pers = generatePerson(1).get(0);
        pers.addHobby(hobbies.get(r.nextInt(hobbies.size())));
        fc.createPerson(pers);
        
        Phone ph = fc.createPhone(generatePhone(1).get(0));
        fc.addPhoneToPerson(ph.getId(), pers.getId());
        
        
        fc.addAddressToPerson(generateAddress(1).get(0), pers.getId());
    }

    public void generateRandomFullPeople(int amount) {
        for (int i = 0; i < amount; i++) {
            generateRandomFullPerson(fc.getAllHobbies());
        }
    }

    public List<Person> generatePerson(int amount) {
        List<Person> pers = new ArrayList();
        for (int i = 0; i < amount; i++) {
            pers.add(personLogic());
        }
        return pers;
    }

    public List<Address> generateAddress(int amount) {
        List<Address> addr = new ArrayList();
        for (int i = 0; i < amount; i++) {
            addr.add(addressLogic());
        }
        return addr;
    }

    public List<Phone> generatePhone(int amount) {
        List<Phone> ph = new ArrayList();
        for (int i = 0; i < amount; i++) {
            ph.add(phoneLogic());
        }
        return ph;
    }

    public void generateHobbies(List<Person> ppl, List<Hobby> hobbies) {
        for (Person pers : ppl) {
            pers.addHobby(hobbies.get(r.nextInt(hobbies.size())));
        }
    }

    private Person personLogic() {
        String fname = fNames[r.nextInt(fNames.length)];
        String lname = lNames[r.nextInt(lNames.length)];
        Person p = new Person(fname + lname + "@" + emailSuffix[r.nextInt(emailSuffix.length)], fname, lname);
        return p;
    }

    private Address addressLogic() {
        String address = address1[r.nextInt(address1.length)] + address2[r.nextInt(address2.length)] + (r.nextInt(150) + 1);
        String additionalInf = "";
        if (r.nextInt(10) == 9) {
            additionalInf = additional[r.nextInt(additional.length)];
        }
        return new Address(address, additionalInf);
    }

    private Phone phoneLogic() {
        return new Phone(r.nextInt(99999999), phoneType[r.nextInt(3)]);
    }

}
