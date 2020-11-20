
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.Base64;


public class VerifSignn {
  public static final String PRIVATE_KEY_FILE = "C:/Users/megaw/Desktop/Dev/JAVA/signature_numerique/signature/test/privateKey.key";
  public static final String PUBLIC_KEY_FILE = "C:/Users/megaw/Desktop/Dev/JAVA/signature_numerique/signature/test/publicKey.key";

  public static void main(String[] args) throws Exception {
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
      System.out.print(chaine);
      byte[] message = chaine.getBytes("UTF-8");
      ObjectInputStream inputStream = null;
      // [B@59fa1d9b
      inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
      final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
      Signature signature = Signature.getInstance("SHA1withDSA");
      byte[] message2 = "Hello0 world".getBytes();

    //   signature.initSign(privateKey, new SecureRandom());
    //   signature.update(message);
    //   byte[] signatureBytes = signature.sign();
    //   System.out.println(signatureBytes);
      // inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
      // final PublicKey publicKey = (PublicKey) inputStream.readObject();
      // signature.initVerify(publicKey);
      // signature.update(message2);
      // [B@4501b7af
      String sign = "[B@4501b7af";
       String encoded = Base64.getEncoder().encodeToString(sign.getBytes());
      byte[] decoded = Base64.getDecoder().decode(encoded);
      System.out.println(signature.verify(Base64.getDecoder().decode(sign)));

    } catch (Exception e) {

      e.printStackTrace();
    }

  }
}
