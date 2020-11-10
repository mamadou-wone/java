package secret;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class CipherMsg {
    public static final String ALGORITHM = "RSA";
    public static final String PRIVATE_KEY_FILE = "bossPrivate.key";
    public static final String PUBLIC_KEY_FILE = "bossPublic.key";

    public static byte[] encrypt(String text, PublicKey key) {
        byte[] cipherText = null;
        try {
            // get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            // encrypt the plain text using the public key
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipherText = cipher.doFinal(text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    public static void main(String[] args) {
        try {

            final String originalText = "Text to be encrypted ";
            ObjectInputStream inputStream = null;

            // Encrypt the string using the public key
            inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
            final PublicKey publicKey = (PublicKey) inputStream.readObject();
            final byte[] cipherText = encrypt(originalText, publicKey);
            System.out.println("Encrypted Text: " + cipherText.toString());

            FileOutputStream textCipher = new FileOutputStream("bossCipher.txt");
            textCipher.write(cipherText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
