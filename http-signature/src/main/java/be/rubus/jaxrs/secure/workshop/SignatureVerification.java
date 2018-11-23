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
        String signatureHeader = headers.getFirst("Signature");

        Signature signature = Signature.fromString(signatureHeader);

        try {
            // We should use the signature.getKeyId() to determine which key needs to be used.
            Verifier verifier = new Verifier(readPublicKey(), signature);
            return verifier.verify(method, uri, toHeadersMap(headers));
        } catch (IOException | JOSEException | ParseException | NoSuchAlgorithmException | SignatureException e) {
            throw new UnexpectedException(e);
        }
    }

    private Map<String, String> toHeadersMap(MultivaluedMap<String, String> headers) {
        Map<String, String> result = new HashMap<>();
        for (String key : headers.keySet()) {
            result.put(key, headers.getFirst(key));
        }
        return result;
    }

    private PublicKey readPublicKey() throws IOException, JOSEException, ParseException {
        FileInputStream inputStream = new FileInputStream("/Users/rubus/rsa.pub.json");
        String fileContent = new Scanner(inputStream).useDelimiter("\\Z").next();
        inputStream.close();
        return RSAKey.parse(fileContent).toPublicKey();
    }

}
