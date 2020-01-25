package DTO;

import entity.*;

/**
 *
 * @author Esben
 */
public class AddressDTO {

    public int id;
    public String street;
    public String additionalInfo;
    public CityInfoDTO cityInfo;

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
        if (address.getCityInfo() != null) {
            this.cityInfo = new CityInfoDTO(address.getCityInfo());
        } else {
            this.cityInfo = null;
        }
    }
}
