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
        try {
            PrivateKey privateKey = readPrivateKey();
            String json = createJSONString(user);
            return createJWT(json, privateKey);
        } catch (IOException | JOSEException | ParseException e) {
            throw new UnexpectedException(e);
        }
    }

    private String createJWT(String message, PrivateKey privateKey) throws JOSEException {

        JWSSigner signer = new RSASSASigner(privateKey);

        JWSHeader.Builder headerBuilder = new JWSHeader.Builder(JWSAlgorithm.RS256);
        headerBuilder.keyID("someUniqueKey") // UUID in most cases
                .type(JOSEObjectType.JWT);
        JWSObject jwsObject = new JWSObject(headerBuilder.build(), new Payload(message));

        jwsObject.sign(signer);

        return jwsObject.serialize();
    }

    private String createJSONString(User user) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime exp = now.plusMinutes(30);

        JsonObject json = Json.createObjectBuilder()
                .add("id", user.getId())
                .add("userName", user.getUserName())
                .add("name", user.getName())
                // The following are OpenIdConnect compliant claims.
                .add("iat", now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()) // issued at
                .add("exp", exp.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()) // expiration
                .add("iss", ISSUER)
                .build();

        return json.toString();
    }

    private PrivateKey readPrivateKey() throws IOException, JOSEException, ParseException {
        FileInputStream inputStream = new FileInputStream("/Users/rubus/rsa.priv.json");
        String fileContent = new Scanner(inputStream).useDelimiter("\\Z").next();
        inputStream.close();
        return RSAKey.parse(fileContent).toPrivateKey();
    }

}
