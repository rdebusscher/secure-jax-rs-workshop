package be.rubus.jaxrs.secure.workshop;

import be.rubus.jaxrs.secure.workshop.exception.UnexpectedException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;

@Provider
public class SignatureContainerRequestFilter implements ContainerRequestFilter {


    private SignatureVerification signatureVerification = new SignatureVerification();

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
    }

    private String getEntityBody(ContainerRequestContext requestContext) {

    }

    private static String read(InputStream input) {

    }

}
