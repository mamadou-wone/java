package signature;

import java.io.*;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Scanner;

public class Sign {
    public static final String PRIVE_KEY_FILE = "privateKey.key";
    public static void main(String[] args) throws  Exception{
        String chaine = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter le nom du fichier contenant l'emprunte");
        String msg = sc.nextLine();
        try {
            // final String originalText = "Assane l'ingénieur ";
            InputStream ips = new FileInputStream(msg + ".txt");
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {
                chaine += ligne + "\n";
            }
            // System.out.print(chaine);
            ObjectInputStream inputStream = null;
            
            inputStream = new ObjectInputStream(new FileInputStream(PRIVE_KEY_FILE));
            final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
            
            Signature sign = Signature.getInstance("SHA256withDSA");
            sign.initSign(privateKey);
            byte[] bytes = chaine.getBytes();

            sign.update(bytes);
            byte[] signature = sign.sign();
            FileWriter myWriter = new FileWriter("DSA.txt");
            myWriter.write(signature.toString());
            myWriter.close();
// [B@1d16f93d
            System.out.println("La signature de l'emprunte est : "+signature);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
