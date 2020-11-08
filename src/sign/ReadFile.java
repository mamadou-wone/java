package sign;
import java.io.*;
public class ReadFile extends DigitalSign{
    String chaine = "";
    String fichier;

    void read(String fileName){
        try{
            InputStream ips=new FileInputStream(fileName); 
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);
            String ligne;
            while ((ligne=br.readLine())!=null){
             System.out.println(ligne);
             chaine+=ligne+"\n";
            //  System.out.println(chaine);
            }
           //  byte[] bytes = chaine.getBytes();
            br.close(); 
           }  
           catch (Exception e){
            System.out.println("Text = " +e.toString());
           }
    }
}
