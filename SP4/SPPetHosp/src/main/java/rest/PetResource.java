/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.*;
import facade.Facade;
import java.util.Date;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Esben
 */
@Path("pet")
public class PetResource {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Facade facade = new Facade(Persistence.createEntityManagerFactory("pethospu"));

    @Context
    private UriInfo context;

    public PetResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(gson.toJson(facade.getAllPets())).build();
    }

    @GET
    @Path("living")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLiving() {
        return Response.ok(gson.toJson(facade.getAllLivingPets())).build();
    }

    @GET
    @Path("count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCount() {

        int count = facade.getAllPets().size();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("count", count);
        return Response.ok().entity(jsonObject.toString()).build();
    }

    @GET
    @Path("event/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByEventDate(@PathParam("year") long dateLong) {

        Date date = new Date(dateLong);
        return Response.ok(gson.toJson(facade.getAllPetsWithEvent(date))).build();
    }

}
