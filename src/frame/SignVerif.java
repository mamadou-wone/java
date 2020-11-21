package frame;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;

/**
 * SignVerif
 */
public class SignVerif {
    public static final String PATH = "C:/Users/megaw/Desktop/Dev/JAVA/signature_numerique/signature/src/frame/";
    public static final String PRIVATE_KEY_FILE = PATH + "privateKey.key";
    public static final String PUBLIC_KEY_FILE = PATH + "publicKey.key";

    public static String getMessage(String path) {
        String chaine = "";
        try {
            InputStream ips = new FileInputStream(PATH + path);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {
                chaine += ligne + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chaine;
    }

    public static byte[] signText(byte[] message, DSAPrivateKey privateKey) throws Exception {
        byte[] signature = null;
        try {
            Signature sign = Signature.getInstance("SHA256withDSA");
            sign.initSign(privateKey);
            sign.update(message);
            signature = sign.sign();
            FileWriter myWriter = new FileWriter(PATH + "DSA3.txt");
            myWriter.write(signature.toString());
            myWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signature;
    }

    public static boolean verifSnature(byte[] message, byte[] signature, DSAPublicKey publicKey) throws Exception {
        boolean bool = false;
        try {
            Signature sign = Signature.getInstance("SHA256withDSA");
            sign.initVerify(publicKey);
            sign.update(message);
            bool = sign.verify(signature);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return bool;
    }

    public static void displayFrame(boolean bool) {
        if (bool) {
            System.out.println("Valide");
        } else {
            System.out.println("No Valide");
        }
    }

    public static DSAPrivateKey getPrivateKey() {
         DSAPrivateKey privateKey = null;
        try {
            ObjectInputStream inputStream = null;

            inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
            privateKey = (DSAPrivateKey) inputStream.readObject();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return privateKey;
    }

    public static DSAPublicKey getPublicKey() {
        DSAPublicKey publicKey = null;
       try {
           ObjectInputStream inputStream = null;

           inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
           publicKey = (DSAPublicKey) inputStream.readObject();
       } catch (Exception e) {
          e.printStackTrace();
       }
       return publicKey;
   }
    // 
    
}