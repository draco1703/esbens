package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Random;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import service.*;

@Path("starwars")
public class StarwarsResource {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    DataService DS = new DataService();

    @Context
    private UriInfo context;

    public StarwarsResource() {
    }

    //PEOPLE

    @GET
    @RolesAllowed({"user", "admin"})
    @Path("people/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeopleById(@PathParam("id") int id) {
        return Response.ok().entity(gson.toJson(DS.getPerson(id))).build();
    }
    
    @GET
//    @RolesAllowed({"user", "admin"})
    //Commented out to allow this call for anyone. Getting a specific person will be a members only feature
    @Path("people/random")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRandomPerson() {
        return Response.ok().entity(gson.toJson(DS.getPerson(new Random().nextInt(87) + 1))).build();
    }
}
