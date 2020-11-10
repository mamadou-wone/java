package signature;
import java.io.*;
import java.security.PrivateKey;
import java.security.Signature;

public class Sign {
    public static final String PRIVATE_KEY_FILE = "bossPrivate.key";


    public static void main(String[] args) throws  Exception{
        try {
            final String originalText = "Text to be encrypted ";
            ObjectInputStream inputStream = null;
            
            inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
            final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
            
            Signature sign = Signature.getInstance("SHA256withDSA");
            sign.initSign(privateKey);
            byte[] bytes = originalText.getBytes();

            sign.update(bytes);
            byte[] signature = sign.sign();
            FileWriter myWriter = new FileWriter("DSA.txt");
            myWriter.write(signature.toString());
            myWriter.close();

            System.out.println(signature);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }
}
