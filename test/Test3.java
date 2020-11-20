
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.Base64;

import javax.crypto.Cipher;

public class Test3 {
    public static final String PATH = "C:/Users/megaw/Desktop/Dev/JAVA/signature_numerique/signature/test/";
    public static final String PRIVATE_KEY_FILE = "C:/Users/megaw/Desktop/Dev/JAVA/signature_numerique/signature/test/privateKey.key";
    public static final String PUBLIC_KEY_FILE = "C:/Users/megaw/Desktop/Dev/JAVA/signature_numerique/signature/test/publicKey.key";

    public static void SignText(byte[] text, PrivateKey key) {
        try {
            Signature signature = Signature.getInstance("SHA256withDSA");
            signature.initSign(key);
            signature.update(text);
            byte[] digitalSignature = signature.sign();
            // Files.write(Paths.get(PATH + "digital_signature_2"), digitalSignature);
            FileWriter myWriter = new FileWriter(
                    "C:/Users/megaw/Desktop/Dev/JAVA/signature_numerique/signature/test/sign.txt");
            myWriter.write(digitalSignature.toString());
            myWriter.close();
            System.out.println(digitalSignature);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void verifSignature(byte[] signText, PublicKey key) {
        try {
            Signature signature = Signature.getInstance("SHA256withDSA");
            signature.initVerify(key);
            boolean isCorrect = signature.verify(signText);
            System.out.println(isCorrect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        byte[] messageBytes = Files.readAllBytes(Paths.get(PATH + "test.txt"));
        String chaine = "";
        try {
            InputStream ips = new FileInputStream(
                    "C:/Users/megaw/Desktop/Dev/JAVA/signature_numerique/signature/test/test.txt");
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {
                // System.out.println(ligne);
                chaine += ligne + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(chaine);
        ObjectInputStream inputStream = null;
        inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
        final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
        // System.out.println(privateKey);
        SignText(chaine.getBytes("utf-8"), privateKey);

        inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
        final PublicKey publicKey = (PublicKey) inputStream.readObject();
        String signText = "";
        try {
            InputStream ips = new FileInputStream(
                    "C:/Users/megaw/Desktop/Dev/JAVA/signature_numerique/signature/test/sign.txt");
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {
                // System.out.println(ligne);
                signText += ligne + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println(signText);
        byte[] receivedSignature = Files.readAllBytes(Paths.get(PATH +
        "sign.txt"));
        verifSignature(receivedSignature, publicKey);

    }
}
