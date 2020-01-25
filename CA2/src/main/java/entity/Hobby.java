/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import DTO.*;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Esben
 */
@Entity
public class Hobby implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name;
    private String description;
    
    @ManyToMany(mappedBy = "hobbies", cascade = CascadeType.PERSIST)
    private List<Person> peopleWithHobby;

    public Hobby() {
    }

    public Hobby(String name, String description) {
        this.name = name;
        this.description = description;
    }

    Hobby(HobbyDTO hobby) {
        this.id = hobby.id;
        this.name = hobby.name;
        this.description = hobby.description;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Person> getPeopleWithHobby() {
        return peopleWithHobby;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPeopleWithHobby(List<Person> peopleWithHobby) {
        this.peopleWithHobby = peopleWithHobby;
    }
    
    
}
