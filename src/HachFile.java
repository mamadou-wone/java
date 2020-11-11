import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.Scanner;

public class HachFile {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter le nom du fichier");
        String msg = sc.nextLine();
        MessageDigest md = MessageDigest.getInstance("MD5");
       
        String chaine = "";
        try {
            InputStream ips = new FileInputStream(msg + ".txt");
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
        FileWriter myWriter = new FileWriter("hach.txt");
        myWriter.write(sb.toString());
        myWriter.close();
    }
}
