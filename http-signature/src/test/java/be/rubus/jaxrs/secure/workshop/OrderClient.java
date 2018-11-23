package be.rubus.jaxrs.secure.workshop;

import be.rubus.jaxrs.secure.workshop.filter.SignatureInterceptor;
import be.rubus.jaxrs.secure.workshop.filter.SignatureWriterInterceptor;
import be.rubus.jaxrs.secure.workshop.order.Order;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class OrderClient {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        client.register(SignatureWriterInterceptor.class);
        client.register(SignatureInterceptor.class);

        Order order = newOrder();

        Response response = client.target("http://localhost:8080/protection/resources")
                .path("order")
                .request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(order, MediaType.APPLICATION_JSON_TYPE));

        System.out.println(response.getStatus());
    }

    private static Order newOrder() {
        Order result = new Order();
        result.setUserId(123L);
        result.setProducts(new long[]{13, 53, 81});
        result.setAddress("The delivery Address");
        return result;
    }
}
