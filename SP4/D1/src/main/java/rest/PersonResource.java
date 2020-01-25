/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entity.*;
import facade.IPersonFacade;
import facade.JSONConverter;
import facade.PersonFacade;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
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

    JSONConverter JSON = new JSONConverter();

    private final IPersonFacade facade = new PersonFacade(Persistence.createEntityManagerFactory("sp4d123"));

    @Context
    private UriInfo context;

    public PersonResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok().entity(JSON.getJSONFromPersons(facade.getAllPersons())).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        return Response.ok().entity(JSON.getJSONFromPerson(facade.getPerson(id))).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(String json) {
        Person person = JSON.getPersonFromJson(json);
        person = facade.addPerson(person);
        return Response.ok().entity(JSON.getJSONFromPerson(person)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response put(@PathParam("id") int id, String json) {
        Person person = JSON.getPersonFromJson(json);
        person.setId(id);
        person = facade.editPerson(person);
        return Response.ok().entity(JSON.getJSONFromPerson(person)).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        Person person = facade.deletePerson(id);
        return Response.ok().entity(JSON.getJSONFromPerson(person)).build();
    }
}
