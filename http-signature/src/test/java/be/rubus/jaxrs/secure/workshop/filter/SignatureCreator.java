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
        Signature signature = new Signature("key-id", "rsa-sha256", null, "(request-target)", "date", "digest");
        try {
            Key key = readPrivateKey();
            Signer signer = new Signer(key, signature);

            URIInfo uriInfo = (URIInfo) context.getProperty(URIInfo.class.getName());

            Signature realSignature = signer.sign(uriInfo.getMethod(), uriInfo.getPath(), getHeaders(context.getHeaders()));
            return realSignature.toString();
        } catch (IOException | JOSEException | ParseException e) {
            throw new UnexpectedException(e);
        }
    }

    private Map<String, String> getHeaders(MultivaluedMap<String, Object> headers) {
        Map<String, String> result = new HashMap<>();
        result.put("digest", headers.getFirst("digest").toString());
        result.put("date", headers.getFirst("Date").toString());
        return result;
    }

    private PrivateKey readPrivateKey() throws IOException, JOSEException, ParseException {


        FileInputStream inputStream = new FileInputStream("/Users/rubus/rsa.priv.json");
        String fileContent = new Scanner(inputStream).useDelimiter("\\Z").next();
        inputStream.close();
        return RSAKey.parse(fileContent).toPrivateKey();

    }

}
