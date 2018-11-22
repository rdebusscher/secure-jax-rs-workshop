package be.rubus.jaxrs.secure.workshop;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.ParseException;
import java.util.Base64;
import java.util.Scanner;

public class JWKTest {

    public static void main(String[] args) throws Exception {
        RSAKey rsaKey = generateJWK();

        writePrivateKey(rsaKey);
        writePublicKey(rsaKey);

        PublicKey publicKey = readPublicKey();
        byte[] encrypted = encrypt(publicKey, "Encrypted by JWK based key");
        System.out.println("Encoded message : " + Base64.getEncoder().encodeToString(encrypted));

        PrivateKey privateKey = readPrivateKey();
        byte[] message = decrypt(privateKey, encrypted);
        System.out.println("Original message : " + new String(message));

    }

    private static RSAKey generateJWK() throws JOSEException {
        RSAKeyGenerator generator = new RSAKeyGenerator(2048);
        return generator.generate();

    }

    private static void writePublicKey(RSAKey rsaKey) throws IOException {
        FileWriter writer = new FileWriter("/Users/rubus/rsa.pub.json");
        writer.write(rsaKey.toPublicJWK().toJSONObject().toJSONString());
        writer.close();

    }

    private static void writePrivateKey(RSAKey rsaKey) throws IOException {
        FileWriter writer = new FileWriter("/Users/rubus/rsa.priv.json");
        writer.write(rsaKey.toJSONObject().toJSONString());
        writer.close();

    }

    private static PrivateKey readPrivateKey() throws IOException, JOSEException, ParseException {
        FileInputStream inputStream = new FileInputStream("/Users/rubus/rsa.priv.json");
        String fileContent = new Scanner(inputStream).useDelimiter("\\Z").next();
        inputStream.close();
        return RSAKey.parse(fileContent).toPrivateKey();

    }

    private static PublicKey readPublicKey() throws IOException, JOSEException, ParseException {
        FileInputStream inputStream = new FileInputStream("/Users/rubus/rsa.pub.json");
        String fileContent = new Scanner(inputStream).useDelimiter("\\Z").next();
        inputStream.close();
        return RSAKey.parse(fileContent).toPublicKey();

    }

    private static byte[] encrypt(PublicKey publicKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(message.getBytes());

    }

    private static byte[] decrypt(PrivateKey privateKey, byte[] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(encrypted);
    }


}
