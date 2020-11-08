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

        System.out.println("En format hexa : " + sb.toString());

        // convertir le tableau de bits en une format hexadécimal - méthode 2
        // StringBuffer hexString = new StringBuffer();
        // for (int i = 0; i < byteData.length; i++) {
        //     String hex = Integer.toHexString(0xff & byteData[i]);
        //     if (hex.length() == 1)
        //         hexString.append('0');
        //     hexString.append(hex);
        // }
        // System.out.println("En format hexa : " + hexString.toString());
    }
}