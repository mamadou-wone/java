package secret;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.security.PrivateKey;

import javax.crypto.Cipher;

public class DecryptMsg {
    public static final String ALGORITHM = "RSA";
    public static final String PRIVATE_KEY_FILE = "bossPrivate.key";
    public static final String PUBLIC_KEY_FILE = "bossPublic.key";

    public static String decrypt(byte[] text, PrivateKey key) {
        byte[] dectyptedText = null;
        try {
            // get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance(ALGORITHM);

            // decrypt the text using the private key
            cipher.init(Cipher.DECRYPT_MODE, key);
            dectyptedText = cipher.doFinal(text);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new String(dectyptedText);
    }


    public static void main(String[] args) {
        String chaine = "";
        try {
            ObjectInputStream inputStream = null;
            inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
            final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
            InputStream cipherMsg = new FileInputStream("bossCipher.txt");
            InputStreamReader readText = new InputStreamReader(cipherMsg);
            BufferedReader br = new BufferedReader(readText);
            String ligne;
            while ((ligne = br.readLine()) != null) {
                // System.out.println(ligne);
                chaine += ligne + "\n";
            }
            final byte[] cipherText = chaine.getBytes(); 
            // System.out.println(chaine.getBytes());
            final String plainText = decrypt(cipherText, privateKey);
            System.out.println(plainText);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
            
        }
    }
}
