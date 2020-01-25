package entity;

import java.util.Collection;

/**
 *
 * @author Esben
 */
public class OwnerDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private Collection<PetDTO> pets;

    public OwnerDTO(Owner sourceOwner, boolean withPets) {
        this.id = sourceOwner.getId();
        this.firstName = sourceOwner.getFirstName();
        this.lastName = sourceOwner.getLastName();
        this.address = sourceOwner.getAddress();
        if (withPets) {
            this.pets = sourceOwner.getPetCollectionDTO();
        }
    }
}
