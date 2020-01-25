/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import DTO.*;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Esben
 */
@Entity
public class CityInfo implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private int zip;
    private String city;

    public CityInfo() {
    }

    public CityInfo(int zip, String city) {
        this.zip = zip;
        this.city = city;
    }
    
    public CityInfo(CityInfoDTO info) {
        this.id = info.id;
        this.zip = info.zip;
        this.city = info.city;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public int getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    
}
