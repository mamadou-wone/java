import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.Scanner;

public class Hach {
    public static void main(String[] args) throws Exception {
        // String password = "123456789";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter some text");
        String msg = sc.nextLine();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(msg.getBytes());

        byte byteData[] = md.digest();
      
        // convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        // c4c8e2fe5b7ae9d699d17cbf7e64e1ec
        FileWriter myWriter = new FileWriter("hach.txt");
        myWriter.write(sb.toString());
        myWriter.close();
        System.out.println("En format hexa : " + sb.toString());
    }
}