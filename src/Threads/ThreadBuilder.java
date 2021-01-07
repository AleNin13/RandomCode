package Threads;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


public class ThreadBuilder {
    public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        return number.toString(32);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InterruptedException {
        StringBuilder w = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            int n;
            do{
                n = new Random().nextInt(127);
            }while (n<33);
            w.append((char) n);
        }
        MessageDigest ps = MessageDigest.getInstance("SHA-256");
        byte[] hash = ps.digest(w.toString().getBytes(StandardCharsets.UTF_8));
        System.out.println(w);
        System.out.println(toHexString(hash));
        System.out.println(w);
        System.out.println(toHexString(ps.digest("Carlo".getBytes(StandardCharsets.UTF_8))));
        int i=11;
        while (i>0){
            System.out.print("Hai " + --i + " secondi per salvarti la password\r");
            Thread.sleep(1000);
        }
    }
}
