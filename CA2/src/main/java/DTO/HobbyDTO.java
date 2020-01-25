package DTO;

import entity.*;

/**
 *
 * @author Esben
 */
public class HobbyDTO {

    public int id;
    public String name;
    public String description;

    public HobbyDTO(Hobby hobby) {
        this.id = hobby.getId();
        this.name = hobby.getName();
        this.description = hobby.getDescription();
    }
}
