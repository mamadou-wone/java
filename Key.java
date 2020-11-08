import java.io.*;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Scanner;
import java.security.SecureRandom;
import java.security.spec.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Key {

    public static PrivateKey get(String filename) throws Exception {

        byte[] keyBytes = Files.readAllBytes(Paths.get(filename));

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair keypair = keyGen.genKeyPair();
        PrivateKey privateKey = keypair.getPrivate();
        PublicKey publicKey = keypair.getPublic();

            get("keyTes");
        // System.out.println( new String(publicKey.getEncoded(), "UTF8"));
        // System.out.println(publicKey);
        // byte[] key = privateKey.getEncoded();
        // FileOutputStream keyfos = new FileOutputStream("keyTes.txt");
        // keyfos.write(key);

        // keyfos.close();
    }
}
