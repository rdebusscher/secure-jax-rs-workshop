package be.rubus.jaxrs.secure.workshop;

import be.rubus.jaxrs.secure.workshop.exception.UnexpectedException;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import org.tomitribe.auth.signatures.Signature;
import org.tomitribe.auth.signatures.Verifier;

import javax.ws.rs.core.MultivaluedMap;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SignatureVerification {
    public boolean verify(String uri, String method, MultivaluedMap<String, String> headers) {

    }

    private Map<String, String> toHeadersMap(MultivaluedMap<String, String> headers) {

    }

    private PublicKey readPublicKey() throws IOException, JOSEException, ParseException {

    }

}
