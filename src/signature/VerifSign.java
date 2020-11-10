package signature;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class VerifSign {

    public static final String ALGORITHM = "DSA";
    public static final String PRIVATE_KEY_FILE = "bossPrivate.key";
    public static final String PUBLIC_KEY_FILE = "bossPublic.key";

    public static void main(String[] args) throws Exception {
        String chaine = "";
        String fichier = "DSA.txt";
        ObjectInputStream inputStream = null;
        final String text = "Text to be encrypted ";
       
        // Encrypt the string using the public key
        inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
        final PublicKey publicKey = (PublicKey) inputStream.readObject();

        inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
        final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
        Signature sign = Signature.getInstance("SHA256withDSA");
        sign.initSign(privateKey);
        byte[] bytes = text.getBytes();

        sign.update(bytes);
        byte[] signature = sign.sign();
        try {
            InputStream ips = new FileInputStream(fichier);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {
                // System.out.println(ligne);
                chaine += ligne + "\n";
            }
            byte[] originalText = "Text to be encrypted ".getBytes();
            sign.initVerify(publicKey);
            sign.update(originalText);
            boolean bool = sign.verify(signature);
            // boolean bool2 = sign2.verify(signature);
            if (bool) {
                System.out.println("Signature verified");
            } else {
                System.out.println("Signature failed");
            }
        } catch (Exception e) {
        }
    }
}
