import java.io.*;
import java.security.*;

class GenSig {

    public static void main(String[] args) {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        /* Generate a DSA signature */
        KeyPair pair = keyGen.generateKeyPair();
PrivateKey priv = pair.getPrivate();
PublicKey pub = pair.getPublic();
        if (args.length != 1) {
            System.out.println("Usage: GenSig nameOfFileToSign");
        }
        else try {

        // the rest of the code goes here

        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
        }
    }
}