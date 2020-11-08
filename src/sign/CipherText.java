package sign;

import java.io.*;
import java.security.*;
import java.util.Scanner;

import javax.crypto.Cipher;

public class CipherText {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter some text");
        String msg = sc.nextLine();
        // Creating a Signature object
        Signature sign = Signature.getInstance("SHA256withRSA");

        // Creating KeyPair generator object
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

        // Initializing the key pair generator
        keyPairGen.initialize(2048);
        // Generate the pair of keys
        KeyPair pair = keyPairGen.generateKeyPair();

        // Getting the public key from the key pair
        PublicKey publicKey = pair.getPublic();

        // Creating a Cipher object
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        // Initializing a Cipher object
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        // Add data to the cipher
        byte[] input = msg.getBytes();
        cipher.update(input);

        // encrypting the data
        byte[] cipherText = cipher.doFinal();
        System.out.println(new String(cipherText, "UTF8"));

        // try {
        // FileWriter myWriter = new FileWriter("cipherTest.txt");
        // myWriter.write(new String(cipherText, "UTF8"));
        // myWriter.close();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        byte[] privKey = pair.getPrivate().getEncoded();
        FileOutputStream decryptKey = new FileOutputStream("privateKey.txt");
        decryptKey.write(privKey);

        byte[] pubKey = pair.getPublic().getEncoded();
        FileOutputStream cipherKey = new FileOutputStream("publicKey.txt");
        cipherKey.write(pubKey);
        // Initializing the same cipher for decryption
        cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());

        FileOutputStream privateKey = new FileOutputStream("cipher.txt");
        privateKey.write(cipherText);
        // Decrypting the text
        byte[] decipheredText = cipher.doFinal(cipherText);
        System.out.println(new String(decipheredText));
    }

}
