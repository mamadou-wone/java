package sign;
import java.io.*;
import java.security.*;
import javax.crypto.Cipher;
public class Keygen{
    KeyPairGenerator keyPairGenerator;
    Signature sign;
    KeyPair pair;
    PublicKey publicKey;
    PrivateKey privateKey;
    int keySize;

    public void key() throws Exception{
        keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    }
}
