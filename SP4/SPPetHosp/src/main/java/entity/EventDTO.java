package entity;

import java.util.Date;

/**
 *
 * @author Esben
 */
public class EventDTO{
    public int id;
    public String event;
    public String remark;
    public Date date;
    public PetDTO pet;

    public EventDTO(Event sourceEvent, boolean withPet) {
        this.id = sourceEvent.getId();
        this.event = sourceEvent.getEvent();
        this.remark = sourceEvent.getRemark();
        this.date = sourceEvent.getDate();
        if (withPet) {
            this.pet = new PetDTO(sourceEvent.getPet(), true, false);
        }
    }
}
