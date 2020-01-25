/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import generator.Generator;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET; 
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Esben
 */
@Path("data")
public class PersonGenResource {

    @Context
    private UriInfo context;
    
    private Generator gen = new Generator(1, 70);


    @GET
    @Path("/{count}/{idInc}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("count") int count, @PathParam("idInc") int idIncrement) {
        return gen.getRandom(count, idIncrement);
    }

}
