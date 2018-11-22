package be.rubus.jaxrs.secure.workshop.order;

import be.rubus.jaxrs.secure.workshop.token.TokenVerifier;
import be.rubus.jaxrs.secure.workshop.user.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/order")
@ApplicationScoped
public class OrderController {

    @Inject
    private TokenVerifier verifier;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response sendOrder(@HeaderParam("Authorization") String authorizationHeader, Order order) {
        User user = verifier.verifyToken(authorizationHeader);
        if (!order.getUserId().equals(user.getId())) {
            return Response.status(401).entity("userId doesn't match").build();
        }
        return Response.accepted("Order accepted").build();
    }

}
