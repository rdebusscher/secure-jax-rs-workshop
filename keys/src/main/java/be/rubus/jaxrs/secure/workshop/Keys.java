package be.rubus.jaxrs.secure.workshop;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateCrtKey;

public class Keys {

    public static void main(String[] args) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();

        RSAPrivateCrtKey privateKey = (RSAPrivateCrtKey) kp.getPrivate();

        System.out.println("P=" + privateKey.getPrimeExponentP());
        System.out.println("Q=" + privateKey.getPrimeExponentQ());

    }
}
