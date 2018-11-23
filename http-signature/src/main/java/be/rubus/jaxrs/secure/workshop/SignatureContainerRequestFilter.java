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
        String entityBody = getEntityBody(requestContext);

        try {
            String digest = DigestCalculator.calculateDigest(entityBody);
            if (!requestContext.getHeaders().getFirst("Digest").equals(digest)) {
                throw new WebApplicationException("Digest verification failed");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new UnexpectedException(e);
        }

        if (!signatureVerification.verify(requestContext.getUriInfo().getRequestUri().getPath(),
                requestContext.getMethod(), requestContext.getHeaders())) {
            throw new WebApplicationException("Signature verification failed");
        }
    }

    private String getEntityBody(ContainerRequestContext requestContext) {
        InputStream in = requestContext.getEntityStream();
        String entity = read(in);

        // To allow normal processing of the entity
        requestContext.setEntityStream(new ByteArrayInputStream(entity.getBytes(Charset.forName("UTF-8"))));
        return entity;
    }

    private static String read(InputStream input) {
        java.util.Scanner s = new java.util.Scanner(input).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
