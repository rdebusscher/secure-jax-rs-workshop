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
        byte[] secret = createSecret();

        String jwsValue = createJWT("Hello World!", secret);
        System.out.println("JWS = " + jwsValue);

        Payload payload = verifyJWT(jwsValue, secret);
        System.out.println("verified payload " + payload.toString());

        // Try to break it

        String[] parts = jwsValue.split("\\.");
        System.out.println("header " + decode(parts[0]));
        System.out.println("body " + decode(parts[1]));

        String newBody = "Hello JVMConf";
        String tamperedJWT = parts[0] + '.' + Base64.getEncoder().encodeToString(newBody.getBytes(Charset.forName("UTF-8")))
                + '.' + parts[2];

        try {
            verifyJWT(tamperedJWT, secret);
            System.out.println("Very strange that a tampered JWT is accepted by the verification");
        } catch (IllegalArgumentException e) {
            System.out.println("Changing body is detected");
        }

        byte[] otherSecret = createSecret();
        try {
            verifyJWT(jwsValue, otherSecret);
            System.out.println("Very strange that a different secret is accepted by the verification");
        } catch (IllegalArgumentException e) {
            System.out.println("Changing secret is detected");
        }

    }

    private static byte[] createSecret() {
        SecureRandom random = new SecureRandom();
        byte[] sharedSecret = new byte[32]; // == 256 Bit, \ OK for256-bit based hash
        random.nextBytes(sharedSecret);
        return sharedSecret;

    }

    private static String createJWT(String message, byte[] sharedSecret) throws JOSEException {
        JWSSigner signer = new MACSigner(sharedSecret);

        JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload(message));

        jwsObject.sign(signer);

        return jwsObject.serialize();

    }

    private static Payload verifyJWT(String jwt, byte[] sharedSecret) throws JOSEException, ParseException {
        JWSObject jws = JWSObject.parse(jwt);

        JWSVerifier verifier = new MACVerifier(sharedSecret);

        if (!jws.verify(verifier)) {
            throw new IllegalArgumentException("JWT not valid");
        }
        return jws.getPayload();

    }

    private static String decode(String data) {
        return new String(Base64.getDecoder().decode(data));
    }

}
