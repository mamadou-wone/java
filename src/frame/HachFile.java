package frame;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;

public class HachFile {
    
    public void hachFile(String path)throws Exception{
        MessageDigest md = MessageDigest.getInstance("MD5");
       
        String chaine = "";
        try {
            InputStream ips = new FileInputStream(path);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {
                // System.out.println(ligne);
                chaine += ligne + "\n";
            }
            // System.out.print(chaine);
        } catch (Exception e) {
        
            e.printStackTrace();
        }
        // System.out.println(chaine.getBytes());
        md.update(chaine.getBytes());

        byte byteData[] = md.digest();
        // convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        System.out.println("En format hexa : " + sb.toString());
        FileWriter myWriter = new FileWriter("C:/Users/megaw/Desktop/Dev/JAVA/signature_numerique/signature/src/hach.txt");
        myWriter.write(sb.toString());
        myWriter.close();
    }
    // public static void main(String[] args) throws Exception {
       
    // }
}
