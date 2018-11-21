package be.rubus.jaxrs.secure.workshop;

import java.nio.charset.Charset;
import java.security.*;
import java.util.Base64;

public class SignRSA {

    public static void main(String[] args) throws Exception {
        KeyPair kp = generateRSAPair();
        String message = "Welcome to JCON 2018";
        byte[] signature = createSignature(message, kp.getPrivate());

        System.out.println("Signature (Base64) " + Base64.getEncoder().encodeToString(signature));

        System.out.println("Verification " + verifySignature(message, kp.getPublic(), signature));

        System.out.println("Verification (other message) " + verifySignature(message.toLowerCase(), kp.getPublic(), signature));

        KeyPair otherKp = generateRSAPair();
        System.out.println("Verification (other Key) " + verifySignature(message, otherKp.getPublic(), signature));

    }
    private static KeyPair generateRSAPair() throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        return kpg.generateKeyPair();

    }

    private static byte[] createSignature(String message, PrivateKey privateKey) throws Exception {
        Signature createSignature = Signature.getInstance("SHA256withRSA");
        createSignature.initSign(privateKey);
        createSignature.update(message.getBytes(Charset.forName("UTF-8")));
        return createSignature.sign();

    }

    private static boolean verifySignature(String message, PublicKey publicKey, byte[] signature) throws Exception {
        Signature verifySignature = Signature.getInstance("SHA256withRSA");
        verifySignature.initVerify(publicKey);
        verifySignature.update(message.getBytes(Charset.forName("UTF-8")));

        return verifySignature.verify(signature);

    }
}
