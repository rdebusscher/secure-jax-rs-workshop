package be.rubus.jaxrs.secure.workshop;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Base64;

public class JWTTest {

    public static void main(String[] args) throws Exception {


        // Try to break it



    }

    private static byte[] createSecret() {


    }

    private static String createJWT(String message, byte[] sharedSecret) throws JOSEException {


    }

    private static Payload verifyJWT(String jwt, byte[] sharedSecret) throws JOSEException, ParseException {


    }

    private static String decode(String data) {

    }

}
