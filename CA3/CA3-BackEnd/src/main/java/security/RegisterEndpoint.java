/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import exceptions.AuthenticationException;
import exceptions.GenericExceptionMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import service.UserFacade;

/**
 * REST Web Service
 *
 * @author Esben
 */
@Path("register")
public class RegisterEndpoint {

    @Context
    private UriInfo context;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response Register(String jsonString) throws AuthenticationException {

        JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
        String username = json.get("username").getAsString();
        String password = json.get("password").getAsString();
        //Todo refactor into facade
        try {
            if (!UserFacade.getInstance().checkExist(username)) {
                if (UserFacade.getInstance().register(username, password)) {
                    return Response.ok(new Gson().toJson("sucess")).build();
                }
            } else {
                return Response.ok(new Gson().toJson("Username in use")).build();
            }
        } catch (Exception ex) {
            Logger.getLogger(GenericExceptionMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new AuthenticationException("Invalid username or password! Please try again");
    }
}
