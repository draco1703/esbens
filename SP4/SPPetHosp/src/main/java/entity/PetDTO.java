package entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Esben
 */
public class PetDTO {

    public int id;
    public String name;
    public Date birth;
    public Date death;
    public String species;
    
    public OwnerDTO owner;
    public Collection<EventDTO> events = new ArrayList();

    public PetDTO(Pet SourcePet, boolean withOwner, boolean withEvents) {
        this.id = SourcePet.getId();
        this.name = SourcePet.getName();
        this.birth = SourcePet.getBirth();
        if(SourcePet.getDeath() != null){
            this.death = SourcePet.getDeath();
        }
        this.species = SourcePet.getSpecies();
        if (withOwner) {
            this.owner = new OwnerDTO(SourcePet.getOwner(), false);
        }
        if (withEvents) {
            this.events = SourcePet.getEventCollectionDTO();
        }
    }
}
