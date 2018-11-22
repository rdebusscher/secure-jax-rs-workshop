package be.rubus.jaxrs.secure.workshop.token;

import be.rubus.jaxrs.secure.workshop.exception.UnexpectedException;
import be.rubus.jaxrs.secure.workshop.user.User;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import net.minidev.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

import static be.rubus.jaxrs.secure.workshop.token.TokenCreator.ISSUER;

@ApplicationScoped
public class TokenVerifier {

    public User verifyToken(String authorizationHeader) {
        String jwtToken = getJWTToken(authorizationHeader);

        try {
            RSAPublicKey publicKey = (RSAPublicKey) readPublicKey();
            Payload payload = verifyJWT(jwtToken, publicKey);
            JSONObject json = payload.toJSONObject();
            checkClaims(json);
            return createUser(json);
        } catch (IOException | JOSEException | ParseException e) {
            throw new UnexpectedException(e);
        }
    }

    private User createUser(JSONObject json) {
        User result = new User();
        result.setId(json.getAsNumber("id").longValue());
        result.setUserName(json.getAsString("username"));
        result.setUserName(json.getAsString("name"));
        return result;
    }

    private void checkClaims(JSONObject json) {
        String iss = json.getAsString("iss");
        if (!ISSUER.equals(iss)) {
            throw new UnexpectedException("iss has not the expected value");
        }

        long iat = json.getAsNumber("iat").longValue();
        long exp = json.getAsNumber("exp").longValue();
        long now = new Date().getTime();
        if (now < iat || now > exp) {
            throw new UnexpectedException("JWT is not valid (Time frame)");
        }
    }

    private Payload verifyJWT(String jwt, RSAPublicKey publicKey) throws JOSEException, ParseException {

        JWSObject jws = JWSObject.parse(jwt);

        JWSVerifier verifier = new RSASSAVerifier(publicKey);

        if (!jws.verify(verifier)) {
            throw new UnexpectedException("JWT not valid");
        }
        return jws.getPayload();
    }

    private PublicKey readPublicKey() throws IOException, JOSEException, ParseException {
        FileInputStream inputStream = new FileInputStream("/Users/rubus/rsa.pub.json");
        String fileContent = new Scanner(inputStream).useDelimiter("\\Z").next();
        inputStream.close();
        return RSAKey.parse(fileContent).toPublicKey();
    }

    private String getJWTToken(String header) {
        String[] parts = header.split(" ");
        if (parts.length != 2) {
            throw new UnexpectedException("Authorization header value incorrect");
        }
        if (!"Bearer".equals(parts[0])) {
            throw new UnexpectedException("Authorization header value must start with Bearer");
        }

        return parts[1];
    }
}
