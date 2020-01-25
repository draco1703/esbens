
package rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("info")
public class InfoResource {

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;
    
    public InfoResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser(){
        return "\"Hello "+ securityContext.getUserPrincipal().getName()+"\"";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        return "\"Hello "+ securityContext.getUserPrincipal().getName()+"\"";
    }
}
