package be.rubus.jaxrs.secure.workshop;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

public class EncryptRSA {

    public static void main(String[] args) throws Exception {

        KeyPair kp = generateRSAPair();
        byte[] encrypted = encrypt(kp.getPublic(), "Welcome to Workshop at JVMCon Koln");

        System.out.println("Encrypted:" + new String(encrypted));
        System.out.println("Encrypted(Base64):" + Base64.getEncoder().encodeToString(encrypted));

        byte[] original = decrypt(kp.getPrivate(), encrypted);
        System.out.println("decrypted:" + new String(original));

        KeyPair kpOther = generateRSAPair();
        byte[] hacked = decrypt(kpOther.getPrivate(), encrypted);
        System.out.println("decrypted Wrong Key:" + new String(hacked));

    }


    private static KeyPair generateRSAPair() throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        return kpg.generateKeyPair();
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
