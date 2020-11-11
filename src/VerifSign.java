import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Scanner;

public class VerifSign {

    public static final String PUBLIC_KEY_FILE = "publicKey.key";
    public static final String PRRIVE_KEY_FILE = "privateKey.key";

    public static void main(String[] args) throws Exception {
        String chaine = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter le nom du fichier contenant la signature");
        String msg = sc.nextLine();
        String signText = "";

        try {
            InputStream ips = new FileInputStream("DSA.txt");
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String text;
            while ((text = br.readLine()) != null) {
                // System.out.println(ligne);
                signText += text + "\n";
            }
            // System.out.print("siganture " + signText);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            InputStream ips = new FileInputStream("hach.txt");
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {
                chaine += ligne + "\n";
            }
            // System.out.println(chaine);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ObjectInputStream inputStream = null;
        inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
        final PublicKey publicKey = (PublicKey) inputStream.readObject();

        inputStream = new ObjectInputStream(new FileInputStream(PRRIVE_KEY_FILE));
        final PrivateKey privateKey = (PrivateKey) inputStream.readObject();

        Signature sign = Signature.getInstance("SHA256withDSA");
        sign.initSign(privateKey);
        byte[] bytes = chaine.getBytes();

        // sign.update(bytes);

        byte[] signature = sign.sign();
        sign.initVerify(publicKey);
        sign.update(bytes);

        boolean bool = sign.verify(signature);
        System.out.println(signature);

    }
}
