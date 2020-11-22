import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

public class Main extends SignFile {
    public static void main(String[] args)
            throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, IOException {
        generateKeyPair();
        // signText(PATH);
        System.out.println(verifSign(PATH2));
    }
}
