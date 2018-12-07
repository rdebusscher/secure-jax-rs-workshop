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

    }

    private User createUser(JSONObject json) {

    }

    private void checkClaims(JSONObject json) {

    }

    private Payload verifyJWT(String jwt, RSAPublicKey publicKey) throws JOSEException, ParseException {

    }

    private PublicKey readPublicKey() throws IOException, JOSEException, ParseException {

    }

    private String getJWTToken(String header) {

    }
}
