package be.rubus.jaxrs.secure.workshop.filter;

import be.rubus.jaxrs.secure.workshop.DigestCalculator;
import be.rubus.jaxrs.secure.workshop.exception.UnexpectedException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;

/**
 *
 */
public class SignatureWriterInterceptor implements WriterInterceptor {

    private SignatureCreator signatureCreator = new SignatureCreator();

    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {

    }

}
