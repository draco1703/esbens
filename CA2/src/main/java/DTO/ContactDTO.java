package DTO;

import java.util.List;

/**
 *
 * @author Esben
 */
public class ContactDTO {

    public int id;
    public String fName;
    public String lName;
    public String email;
    public List<PhoneDTO> phones;

    public ContactDTO() {
    }

    public ContactDTO(PersonDTO person) {
        this.id = person.id;
        this.fName = person.fName;
        this.lName = person.lName;
        this.email = person.email;
        this.phones = person.phones;
    }
}
