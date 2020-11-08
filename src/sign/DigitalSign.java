package sign;

import java.io.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Scanner;

public class DigitalSign {
    public static void main(String args[]) throws Exception {

        // ReadFile readFile = new ReadFile();
        // readFile.read("mounass.txt");
        // Accepting text from user
        // Scanner sc = new Scanner(System.in);
        // System.out.println("Enter some text");
        // String msg = sc.nextLine();

        // // Creating KeyPair generator object
        // KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");

        // // Initializing the key pair generator
        // keyPairGen.initialize(2048);

        // // Generate the pair of keys
        // KeyPair pair = keyPairGen.generateKeyPair();

        // // Getting the private key from the key pair
        // PrivateKey privKey = pair.getPrivate();

        // // Creating a Signature object
        // Signature sign = Signature.getInstance("SHA256withDSA");

        // // Initialize the signature
        // sign.initSign(privKey);
        // byte[] bytes = "msg".getBytes();

        // // Adding data to the signature
        // sign.update(bytes);

        // // Calculating the signature
        // byte[] signature = sign.sign();
        // FileOutputStream sigfos = new FileOutputStream("sign.txt");
        // sigfos.write(signature);

        // sigfos.close();

        // sign.initVerify(pair.getPublic());
        // sign.update(bytes);

        // byte[] key = privKey.getEncoded();
        // FileOutputStream keyfos = new FileOutputStream("mounass.txt");
        // keyfos.write(key);

        // keyfos.close();
        // // Printing the signature
        // System.out.println("Digital signature for given text: " + new String(signature, "UTF8"));
        // System.out.println("Text Claire " + msg);
    }
}
