package be.rubus.jaxrs.secure.workshop;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DigestCalculator {

    public static String calculateDigest(String message) throws NoSuchAlgorithmException {
        return calculateDigest(message.getBytes(Charset.forName("UTF-8")));
    }

    public static String calculateDigest(byte[] message) throws NoSuchAlgorithmException {
        byte[] digest = MessageDigest.getInstance("SHA-256").digest(message);
        return "SHA256=" + Base64.getEncoder().encodeToString(digest);

    }
}
