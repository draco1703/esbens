package entity;

/**
 *
 * @author Esben
 */
public class AddressDTO {

    public int id;
    public String country;
    public String city;
    public String address;

    public AddressDTO(Address from) {
        this.id = from.getId();
        this.country = from.getCountry();
        this.city = from.getCity();
        this.address = from.getAddress();
    }
}
