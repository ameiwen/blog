package com.blog.utils;

import com.google.common.hash.Hashing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;

public class AlgorithmUtil {
    private static Logger logger = LogManager.getLogger(AlgorithmUtil.class);
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public AlgorithmUtil() {
    }

    public static byte[] encryptDESToByte(String content, String key) {
        byte[] bytes = null;

        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
            cipher.init(1, loadDesKey(key), iv);
            bytes = cipher.doFinal(content.getBytes("UTF-8"));
        } catch (Exception var5) {
            logger.error(var5);
        }

        return bytes;
    }

    public static byte[] decryptDESToByte(String hexContent, String key) {
        byte[] bytes = null;

        try {
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
            byte[] source = hex2byte(hexContent);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(2, loadDesKey(key), iv);
            bytes = cipher.doFinal(source);
        } catch (Exception var6) {
            logger.error(var6);
        }

        return bytes;
    }

    public static String encryptDESToString(String content, String key) {
        byte[] bytes = null;

        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
            cipher.init(1, loadDesKey(key), iv);
            bytes = cipher.doFinal(content.getBytes("UTF-8"));
        } catch (Exception var5) {
            logger.error(var5);
        }

        return bytes2hex(bytes);
    }

    public static String decryptDESToString(String hexContent, String key) {
        byte[] bytes = null;

        try {
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
            byte[] source = hex2byte(hexContent);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(2, loadDesKey(key), iv);
            bytes = cipher.doFinal(source);
        } catch (Exception var6) {
            logger.error(var6);
        }

        return bytes == null ? "" : new String(bytes);
    }

    public static byte[] encryptAESToByte(String source, String key) {
        byte[] bytes = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, loadKeyAES(key.getBytes("utf-8")), new IvParameterSpec(key.getBytes("utf-8")));
            bytes = cipher.doFinal(source.getBytes("utf-8"));
        } catch (Exception var4) {
            logger.error(var4);
        }

        return bytes;
    }

    public static byte[] decryptAESToByte(String source, String key) {
        byte[] bytes = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, loadKeyAES(key.getBytes("utf-8")), new IvParameterSpec(key.getBytes("utf-8")));
            bytes = cipher.doFinal(hex2byte(source));
        } catch (Exception var4) {
            logger.error(var4);
        }

        return bytes;
    }

    public static String encryptAESToString(String source, String key) {
        byte[] bytes = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, loadKeyAES(key.getBytes("utf-8")), new IvParameterSpec(key.getBytes("utf-8")));
            bytes = cipher.doFinal(source.getBytes("utf-8"));
        } catch (Exception var4) {
            logger.error(var4);
        }

        return bytes2hex(bytes);
    }

    public static String decryptAESToString(String source, String key) {
        byte[] bytes = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, loadKeyAES(key.getBytes("utf-8")), new IvParameterSpec(key.getBytes("utf-8")));
            bytes = cipher.doFinal(hex2byte(source));
        } catch (Exception var4) {
            logger.error(var4);
        }

        return bytes == null ? "" : new String(bytes);
    }

    public static String md5(String s) {
        return Hashing.md5().hashString(s, UTF_8).toString();
    }
    //验证密码长度>=6且<32加密2次,=32加密1次
    public static String convertToPassword(String s){
        if(s.length()>=6 && s.length()<32){
            return md5(md5(s));
        }
        if(s.length()==32){
            return md5(s);
        }
        return null;
    }

    public static String sha256(String s) {
        return Hashing.sha256().hashString(s, UTF_8).toString();
    }

    public static String sha512(String s) {
        return Hashing.sha512().hashString(s, UTF_8).toString();
    }

    public static String bytes2hex(byte[] bytes) {
        if (bytes == null) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            byte[] arr$ = bytes;
            int len$ = bytes.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                byte b = arr$[i$];
                String temp = Integer.toHexString(b & 255);
                if (temp.length() == 1) {
                    sb.append("0");
                }

                sb.append(temp);
            }

            return sb.toString();
        }
    }

    public static byte[] hex2byte(String hex) {
        byte[] digest = new byte[hex.length() / 2];

        for(int i = 0; i < digest.length; ++i) {
            String byteString = hex.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte)byteValue;
        }

        return digest;
    }

    private static SecretKey loadDesKey(String key) {
        SecretKey secretKey = null;

        try {
            byte[] keyBytes = key.getBytes("UTF-8");
            DESKeySpec dks = new DESKeySpec(keyBytes);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            secretKey = keyFactory.generateSecret(dks);
        } catch (Exception var5) {
            logger.error(var5);
        }

        return secretKey;
    }

    private static SecretKey loadKeyAES(byte[] bytes) {
        return new SecretKeySpec(bytes, "AES");
    }

    public static void main(String[] args) throws Exception {
    }
}
