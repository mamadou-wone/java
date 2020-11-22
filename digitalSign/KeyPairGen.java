import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;

/**
 * KeyPairGen
 */
public class KeyPairGen {

    public static final String ALGORITHME = "DSA";
    public static final String PRIVATE_KEYS_FILE ="C:/Users/megaw/Desktop/Dev/JAVA/signature_numerique/signature/digitalSign/privateKey.key";
    public static final String PUBLIC_KEY_FILE = "C:/Users/megaw/Desktop/Dev/JAVA/signature_numerique/signature/digitalSign/publicKey.key";
  /**
     * Generate key which contains a pair of private and public key using 1024
     * bytes. Store the set of keys in Prvate.key and Public.key files.
     * 
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void generateKeyPair(){
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHME);
            keyGen.initialize(1024);
            final KeyPair key = keyGen.generateKeyPair();

            File privateKeyFile = new File(PRIVATE_KEYS_FILE);
            File publicKeyFile = new File(PUBLIC_KEY_FILE);

            if (privateKeyFile.getParentFile() != null) {
                privateKeyFile.getParentFile().mkdirs();
            }
            privateKeyFile.createNewFile();

            if (publicKeyFile.getParentFile() != null) {
                publicKeyFile.getParentFile().mkdirs();
            }
            publicKeyFile.createNewFile();


            DSAPrivateKey privateKey = (DSAPrivateKey) key.getPrivate();
            DSAPublicKey publicKey = (DSAPublicKey) key.getPublic();

            ObjectOutputStream publicKeyOS = new ObjectOutputStream(new FileOutputStream(publicKeyFile));
            publicKeyOS.writeObject(key.getPublic());
            publicKeyOS.close();

            // // Saving the Private key in a file
            ObjectOutputStream privateKeyOS = new ObjectOutputStream(new FileOutputStream(privateKeyFile));
            privateKeyOS.writeObject(key.getPrivate());
            privateKeyOS.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DSAPrivateKey getPrivateKey(){
        DSAPrivateKey privateKey = null;
        ObjectInputStream inputStream = null;
      try {
       
          inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEYS_FILE));
          privateKey = (DSAPrivateKey) inputStream.readObject();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return privateKey;
    }


    public static DSAPublicKey getPublicKey(){
        DSAPublicKey publicKey = null;
        ObjectInputStream inputStream = null;
      try {
       
          inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
          publicKey = (DSAPublicKey) inputStream.readObject();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return publicKey;
    }


}