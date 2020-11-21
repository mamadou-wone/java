import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Signature;

public class Boss {
public static final String PATH = "C:/Users/megaw/Desktop/Dev/JAVA/signature_numerique/signature/test/";
public static void main(String[] args) throws Exception {
    byte[] message = "Hello world".getBytes();

    KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
    keyPairGen.initialize(1024, new SecureRandom());
    KeyPair keyPair = keyPairGen.generateKeyPair();

    Signature signature = Signature.getInstance("SHA1withRSA");

    signature.initSign(keyPair.getPrivate(), new SecureRandom());
    signature.update(message);
    byte[] signatureBytes = signature.sign();
    // System.out.println(ConvertionHelper.bytesToHex(signatureBytes));

    signature.initVerify(keyPair.getPublic());
    signature.update(message);
    System.out.println(signature.verify(signatureBytes));
  }
}
