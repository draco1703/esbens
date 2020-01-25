package DTO;

import entity.*;

/**
 *
 * @author Esben
 */
public class CityInfoDTO {

    public int id;
    public int zip;
    public String city;

    public CityInfoDTO(CityInfo cityInfo) {
        this.id = cityInfo.getId();
        this.zip = cityInfo.getZip();
        this.city = cityInfo.getCity();
    }
}
