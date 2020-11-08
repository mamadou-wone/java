package sign;

import java.io.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Scanner;

public class DigitalSign {
    public static void main(String args[]) throws Exception {
        String chaine = "";
        String fichier = "hach.txt";
        // lecture du fichier texte
        try {
            InputStream ips = new FileInputStream(fichier);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {
                // System.out.println(ligne);
                chaine += ligne + "\n";

            }

            // Creating KeyPair generator object
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");

            // Initializing the key pair generator
            keyPairGen.initialize(2048);

            // Generate the pair of keys
            KeyPair pair = keyPairGen.generateKeyPair();

            // Getting the private key from the key pair
            PrivateKey privKey = pair.getPrivate();

            // Creating a Signature object
            Signature sign = Signature.getInstance("SHA256withDSA");

            // Initialize the signature
            sign.initSign(privKey);
            byte[] bytes = chaine.getBytes();

            // Adding data to the signature
            sign.update(bytes);

            // Calculating the signature
            byte[] signature = sign.sign();
            FileOutputStream sigfos = new FileOutputStream("sign.txt");
            sigfos.write(signature);

            sigfos.close();

            sign.initVerify(pair.getPublic());
            sign.update(bytes);

            // Printing the signature
            System.out.println("Digital signature for given text: " + new String(signature, "UTF8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
