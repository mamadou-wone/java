package frame;

import java.io.*;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.util.Scanner;

public class Sign {
    public static final String PRIVATE_KEY_FILE = "C:/Users/megaw/Desktop/Dev/JAVA/signature_numerique/signature/src/frame/privateKey.key";
    public static final String PUBLIC_KEY_FILE = "C:/Users/megaw/Desktop/Dev/JAVA/signature_numerique/signature/src/frame/publicKey.key";

    public static void main(String[] args) throws  Exception{
        String chaine = "";
        try {
            // final String originalText = "Assane l'ingénieur ";
            InputStream ips = new FileInputStream("C:/Users/megaw/Desktop/Dev/JAVA/signature_numerique/signature/src/frame/hach.txt");
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {
                chaine += ligne + "\n";
            }
            System.out.print(chaine);
            ObjectInputStream inputStream = null;
            
            inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
            final DSAPrivateKey privateKey = (DSAPrivateKey) inputStream.readObject();
            

            inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
            final DSAPublicKey publicKey = (DSAPublicKey) inputStream.readObject();
            
            Signature sign = Signature.getInstance("SHA256withDSA");
            sign.initSign(privateKey);
            byte[] bytes = chaine.getBytes("utf-8");

            sign.update(bytes);
            byte[] signature = sign.sign();
            FileWriter myWriter = new FileWriter("C:/Users/megaw/Desktop/Dev/JAVA/signature_numerique/signature/src/frame/DSA.txt");
            myWriter.write(signature.toString());
            myWriter.close();
            sign.initVerify(publicKey);
            sign.update(bytes);
            boolean bool = sign.verify(signature);
            System.out.println(signature.toString().equals("[B@3532ec19"));
            System.out.println("La signature de l'emprunte est : "+signature +" "+ "et le verification "+bool);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
