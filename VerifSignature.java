import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;

import java.util.Scanner;

public class VerifSignature {
   public static void main(String args[]) throws Exception{
      //Creating KeyPair generator object
      KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");
	      
      //Initializing the key pair generator
      keyPairGen.initialize(2048);
	      
      //Generate the pair of keys
      KeyPair pair = keyPairGen.generateKeyPair();
      
      //Getting the privatekey from the key pair
      PrivateKey privKey = pair.getPrivate();

      //Creating a Signature object
      Signature sign = Signature.getInstance("SHA256withDSA");
      Signature sign2 = Signature.getInstance("SHA256withDSA");

      //Initializing the signature
      sign.initSign(privKey);
      byte[] bytes = "Hello how are you".getBytes();
      

      sign2.initSign(privKey);
      byte[] btes = "Hello how r you".getBytes();
      //Adding data to the signature
      sign.update(bytes);
      sign2.update(btes);
      //Calculating the signature
      byte[] signature = sign.sign();      
      byte[] signature2 = sign2.sign();
      //Initializing the signature
      sign.initVerify(pair.getPublic());
      sign.update(bytes);

      sign2.initVerify(pair.getPublic());
      sign2.update(btes);
      
      //Verifying the signature
      boolean bool = sign.verify(signature);
    //   boolean bool2 = sign2.verify(signature);
      if(bool) {
         System.out.println("Signature verified");   
      } else {
         System.out.println("Signature failed");
      }
   }
}