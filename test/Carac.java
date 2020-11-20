import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Carac {

    public static void main(String[] args) {
        
        try {
            byte[] bytes = "Hello world".getBytes("UTF-8");
            String encoded = Base64.getEncoder().encodeToString(bytes);
            byte[] decoded = Base64.getDecoder().decode(encoded);
            System.out.println(bytes);
            System.out.println(encoded);
            System.out.println(decoded);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//    [B@6a38e57f
    }
}
