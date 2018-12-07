package be.rubus.jaxrs.secure.workshop.user;

import be.rubus.jaxrs.secure.workshop.UsernamePassword;
import be.rubus.jaxrs.secure.workshop.token.TokenCreator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/authenticate")
@ApplicationScoped
public class AuthenticationController {

    @Inject
    private UserBoundary userBoundary;

    @Inject
    private TokenCreator tokenCreator;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String doAuthenticate(UsernamePassword usernamePassword) {

    }


}
