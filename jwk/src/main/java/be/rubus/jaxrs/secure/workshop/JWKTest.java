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


    }

    private static RSAKey generateJWK() throws JOSEException {


    }

    private static void writePublicKey(RSAKey rsaKey) throws IOException {


    }

    private static void writePrivateKey(RSAKey rsaKey) throws IOException {


    }

    private static PrivateKey readPrivateKey() throws IOException, JOSEException, ParseException {


    }

    private static PublicKey readPublicKey() throws IOException, JOSEException, ParseException {


    }

    private static byte[] encrypt(PublicKey publicKey, String message) throws Exception {


    }

    private static byte[] decrypt(PrivateKey privateKey, byte[] encrypted) throws Exception {

    }


}
