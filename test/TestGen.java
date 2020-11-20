// package com.jmdoudoux.test.securite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class TestGen {
  public static final String PRIVATE_KEY_FILE = "C:/Users/megaw/Desktop/Dev/JAVA/signature_numerique/signature/test/privateKey.key";
  public static final String PUBLIC_KEY_FILE = "C:/Users/megaw/Desktop/Dev/JAVA/signature_numerique/signature/test/publicKey.key";

  public static void main(String[] argv) throws Exception {
   try {
    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
    keyGen.initialize(1024);
    KeyPair keypair = keyGen.genKeyPair();
    PrivateKey privateKey = keypair.getPrivate();
    // System.out.println(privateKey);
    PublicKey publicKey = keypair.getPublic();
    // System.out.println(publicKey);
    File privateKeyFile = new File(PRIVATE_KEY_FILE);
    File publicKeyFile = new File(PUBLIC_KEY_FILE);
    if (privateKeyFile.getParentFile() != null) {
      privateKeyFile.getParentFile().mkdirs();
  }
  privateKeyFile.createNewFile();

  if (publicKeyFile.getParentFile() != null) {
      publicKeyFile.getParentFile().mkdirs();
  }
  publicKeyFile.createNewFile();

  ObjectOutputStream publicKeyOS = new ObjectOutputStream(new FileOutputStream(publicKeyFile));
  publicKeyOS.writeObject(publicKey);
  publicKeyOS.close();

  // Saving the Private key in a file
  ObjectOutputStream privateKeyOS = new ObjectOutputStream(new FileOutputStream(privateKeyFile));
  privateKeyOS.writeObject(privateKey);
  privateKeyOS.close();

   } catch (Exception e) {
     e.printStackTrace();
   }
  }
}
