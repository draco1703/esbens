/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import DTO.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Esben
 */
@Entity
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String fName;
    private String lName;

    @JoinColumn(name = "PERSON_ID")
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Phone> phones;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address address;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Hobby> hobbies;

    public Person(String email, String firstName, String lastName) {
        this.email = email;
        this.fName = firstName;
        this.lName = lastName;
        this.hobbies = new ArrayList();
        this.phones = new ArrayList();
    }

    public Person() {
    }

    public Person(PersonDTO person) {
        this.id = person.id;
        this.fName = person.fName;
        this.lName = person.lName;
        this.email = person.email;
        if (person.phones != null) {
            for (PhoneDTO phone : person.phones) {
                this.phones.add(new Phone(phone));
            }
        }
        if (person.hobbies != null) {
            this.hobbies = new ArrayList();
            for (HobbyDTO hobby : person.hobbies) {
                this.hobbies.add(new Hobby(hobby));
            }
        }
        if (person.address != null) {
            this.address = new Address(person.address);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public Address getAddress() {
        return address;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public void addHobby(Hobby hobby) {
        this.hobbies.add(hobby);
    }

}
