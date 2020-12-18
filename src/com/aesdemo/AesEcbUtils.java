package com.aesdemo;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AesEcbUtils {
    private static String key = "abdsf6465sdfsf5d";

    public static byte[] encrypt(byte[] plain) {
        try {
            SecretKeySpec sKeyS = new SecretKeySpec(key.getBytes("utf-8"), "AES");
            Cipher cipherE = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipherE.init(Cipher.ENCRYPT_MODE, sKeyS);
            byte[] payPwdEnc = cipherE.doFinal(plain);
            return payPwdEnc;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new byte[0];
        }
    }

    public static byte[] encrypt(String paraString) {
        String src = paraString + "hello";
        try {
            return encrypt(src.getBytes("utf-8"));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new byte[0];
        }
    }

    public static byte[] decrypt(byte[] cipherb) {
        try {
            SecretKeySpec sKeyS = new SecretKeySpec(key.getBytes("utf-8"), "AES");
            Cipher cipherD = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipherD.init(Cipher.DECRYPT_MODE, sKeyS);
            byte[] decBytes = cipherD.doFinal(cipherb);
            return decBytes;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new byte[0];
        }
    }

}
