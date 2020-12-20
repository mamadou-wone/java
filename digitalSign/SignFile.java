import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;

public class SignFile extends KeyPairGen {

    public static String PATH = "C:/Users/megaw/Desktop/sign/text.txt";
    public static String PATH2 = "C:/Users/megaw/Desktop/sign/text2.txt";
    public static String getText(String path) {
        String message = "";
        try {
            InputStream ips = new FileInputStream(path);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {
                message += ligne + "\n";
            }

        } catch (Exception e) {
        }
        return message;
    }

    public static byte[] signText(String path)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, IOException {
        byte[] signature = null;
        Signature sign = Signature.getInstance("SHA256withDSA");
        sign.initSign(getPrivateKey());
        byte[] bytes  = getText(path).getBytes();
        sign.update(bytes);
        signature = sign.sign();
        FileWriter myWriter = new FileWriter("C:/Users/megaw/Desktop/sign/DSA.txt");
        myWriter.write(signature.toString());
        myWriter.close();
        return signature;
    }
// My Birthday
    public static boolean verifSign(String path) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException,
            IOException {
        boolean verif = false;
        Signature sign = Signature.getInstance("SHA256withDSA");
        sign.initVerify(getPublicKey());
        byte[] bytes  = getText(path).getBytes();
        sign.update(bytes);
        verif = sign.verify(signText(PATH));
        return verif;
    }  
}
