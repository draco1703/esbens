package entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Esben
 */
public class PersonDTO {

    public int id;
    public String firstName;
    public String lastName;
    public String phone;
    public List<AddressDTO> addresses;

    public PersonDTO(Person source) {
        this.id = source.getId();
        this.firstName = source.getFirstName();
        this.lastName = source.getLastName();
        this.phone = source.getPhone();
        this.addresses = mapAddressToDTO(source.getAddressList());
    }
    
    private ArrayList<AddressDTO> mapAddressToDTO(List<Address> addressList){
        ArrayList<AddressDTO> newList = new ArrayList<>();
        for (Address address : addressList) {
            newList.add(new AddressDTO(address));
        }
        return newList;
    }
}
