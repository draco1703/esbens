package rest;

import DTO.*;
import com.google.gson.*;
import entity.Person;
import facade.Facade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Esben
 */
@Path("person")
public class PersonResource {

    @Context
    private UriInfo context;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ca2pu");
    private final Facade fc = new Facade(emf);

    public PersonResource() {
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeople() {
        List<PersonDTO> personDTOs = fc.getAllPeopleDTO();
        return Response.ok(gson.toJson(personDTOs)).build();
    }
    
    @GET
    @Path("contact/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContacts() {
        List<ContactDTO> DTOs = fc.getAllContactDTO();
        return Response.ok(gson.toJson(DTOs)).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") int id) throws Exception {
        PersonDTO personDTO = fc.getPersonDTO(id);
        return Response.ok(gson.toJson(personDTO)).build();
    }
    
    @GET
    @Path("contact/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContact(@PathParam("id") int id) throws Exception {
        ContactDTO contactDTO = new ContactDTO(fc.getPersonDTO(id));
        return Response.ok(gson.toJson(contactDTO)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("phone/{num}")
    public Response getPeopleByPhoneNumber(@PathParam("num") int number) {
        PersonDTO person = fc.getPersonDTOByPhoneNumber(number);
        return Response.ok(gson.toJson(person)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("hobby/{name}")
    public Response getPeopleByHobby(@PathParam("name") String name) {
        List<PersonDTO> persons = fc.getPeopleDTOByHobby(name);
        return Response.ok(gson.toJson(persons)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("hobby/{name}/count")
    public Response getCountByHobby(@PathParam("name") String name) {
        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("count", fc.getPersonDTOCountFromHobby(name));
        return Response.ok(gson.toJson(jsonObj)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("bycity/{name}")
    public Response getPeopleByCity(@PathParam("name") String name) {
        List<PersonDTO> persons = fc.getPeopleDTOByCity(name);
        return Response.ok(gson.toJson(persons)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPerson(String json) {
        PersonDTO personDTO = gson.fromJson(json, PersonDTO.class);
        personDTO = fc.editPerson(personDTO);
        return Response.ok().entity(gson.toJson(personDTO)).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response deleteJson(@PathParam("id") int id) {
        fc.deletePerson(id);
        return Response.ok(gson.toJson(true)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPerson(String json) {
        PersonDTO personDTO = gson.fromJson(json, PersonDTO.class);
        fc.createPerson(personDTO);
        return Response.ok(gson.toJson(personDTO)).build();
    }
    
    @GET
    @Path("city/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCities() {
        List<CityInfoDTO> DTOs = fc.getAllCityInfoDTO();
        return Response.ok(gson.toJson(DTOs)).build();
    }
}
