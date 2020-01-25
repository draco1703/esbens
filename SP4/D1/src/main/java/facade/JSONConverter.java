package facade;

import com.google.gson.*;
import entity.*;
import java.util.List;


/**
 *
 * @author Esben
 */
public class JSONConverter {
    
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public Person getPersonFromJson(String js) {
        return gson.fromJson(js, Person.class);
    }

    public String getJSONFromPerson(Person p) {
        return gson.toJson(p);
    }

    public String getJSONFromPersons(List<Person> persons) {
        return gson.toJson(persons);
    }
}
