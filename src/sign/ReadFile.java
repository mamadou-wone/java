package sign;

import java.security.MessageDigest;
import java.io.*;

public class ReadFile {
    public static void main(String[] args) throws Exception {
        String chaine = "";
        String fichier = "cipher.txt";
        MessageDigest md = MessageDigest.getInstance("MD5");
        // lecture du fichier texte
        try {
            InputStream ips = new FileInputStream(fichier);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {
                // System.out.println(ligne);
                chaine += ligne + "\n";

            }
            md.update(chaine.getBytes());
            byte byteData[] = md.digest();

            // convertir le tableau de bits en une format hexadécimal - méthode 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            try {
                FileWriter myWriter = new FileWriter("hach.txt");
                myWriter.write(sb.toString());
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("En format hexa : " + sb.toString());

            // System.out.println(chaine);
            br.close();
        } catch (Exception e) {
            System.out.println("Text = " + e.toString());
        }

    }
}
