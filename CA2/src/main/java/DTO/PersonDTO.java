package DTO;

import entity.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Esben
 */
public class PersonDTO {

    public int id;
    public String fName;
    public String lName;
    public String email;
    public List<PhoneDTO> phones = new ArrayList();
    public List<HobbyDTO> hobbies = new ArrayList();
    public AddressDTO address;

    public PersonDTO() {
    }

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.fName = person.getfName();
        this.lName = person.getlName();
        this.email = person.getEmail();
        
        this.phones = new ArrayList();
        for (Phone phone : person.getPhones()) {
            this.phones.add(new PhoneDTO(phone));
        }
        
        this.hobbies = new ArrayList();
        for (Hobby hobby : person.getHobbies()) {
            this.hobbies.add(new HobbyDTO(hobby));
        }
        
        if (person.getAddress() != null) {
            this.address = new AddressDTO(person.getAddress());
        } else {
            this.address = null;
        }
    }
}
