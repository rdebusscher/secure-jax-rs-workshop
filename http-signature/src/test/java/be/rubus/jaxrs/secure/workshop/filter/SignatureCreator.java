package be.rubus.jaxrs.secure.workshop.filter;

import be.rubus.jaxrs.secure.workshop.exception.UnexpectedException;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import org.tomitribe.auth.signatures.Signature;
import org.tomitribe.auth.signatures.Signer;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.security.PrivateKey;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class SignatureCreator {


    public String createSignature(WriterInterceptorContext context) {

    }

    private Map<String, String> getHeaders(MultivaluedMap<String, Object> headers) {

    }

    private PrivateKey readPrivateKey() throws IOException, JOSEException, ParseException {



    }

}
