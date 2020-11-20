import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Signature;

import javax.crypto.Cipher;

/**
 * Sign
 */
public class Sign {

    public static final String PRIVE_KEY_FILE = "C:/Users/megaw/Desktop/Dev/JAVA/signature_numerique/signature/test/privateKey.key";

    public static void main(String[] args) throws Exception {
        try {
            ObjectInputStream inputStream = null;

            inputStream = new ObjectInputStream(new FileInputStream(PRIVE_KEY_FILE));
            final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
            byte[] messageBytes = "Maimouna".getBytes();
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageHash = md.digest(messageBytes);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] digitalSignature = cipher.doFinal(messageHash);
            System.out.println(digitalSignature.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}