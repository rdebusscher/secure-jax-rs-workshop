package be.rubus.jaxrs.secure.workshop.token;

import be.rubus.jaxrs.secure.workshop.exception.UnexpectedException;
import be.rubus.jaxrs.secure.workshop.user.User;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonObject;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.PrivateKey;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Scanner;

@ApplicationScoped
public class TokenCreator {

    public static final String ISSUER = "http://someservice/verify-user";

    public String createToken(User user) {

    }

    private String createJWT(String message, PrivateKey privateKey) throws JOSEException {

    }

    private String createJSONString(User user) {

    }

    private PrivateKey readPrivateKey() throws IOException, JOSEException, ParseException {

    }

}
