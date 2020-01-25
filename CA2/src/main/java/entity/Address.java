/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import DTO.*;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.*;

/**
 *
 * @author Esben
 */
@Entity
public class Address implements Serializable {
        private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String street;
    private String additionalInfo;
    
    @ManyToOne
    private CityInfo cityInfo;

    public Address() {
    }

    public Address(String address, String additionalInfo) {
        this.street = address;
        this.additionalInfo = additionalInfo;
    }

    Address(AddressDTO address) {
        this.id = address.id;
        this.street = address.street;
        this.additionalInfo = address.additionalInfo;
    }


    public Integer getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
    }
    
    
}
