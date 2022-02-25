package com.xubo.druid.encrypt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * @Author xubo
 * @Date 2022/2/25 14:43
 */
public class Security {


    /**
     * AES对称算法加解密、RSA非对称算法加验签、加解密、生成公私钥
     */
    /**
     * AES对称算法 加密 （密钥长度为16位）
     *
     * @param inputStr 明文
     * @param password 密钥
     */
    public static String encryptByAES(String inputStr, String password) throws Exception {
        byte[] byteData = inputStr.getBytes();
        byte[] bytePassword = password.getBytes();
        return new String(Base64.encodeBase64(encryptByAES(byteData, bytePassword)), "utf-8");
    }

    private static byte[] encryptByAES(byte[] data, byte[] pwd) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/pkcs5padding");
        SecretKeySpec keySpec = new SecretKeySpec(pwd, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] ret = cipher.doFinal(data);
        return ret;
    }

    /**
     * 生成16位AES随机密钥
     */
    public static String getAESRandomKey() {
        Random random = new Random();
        long value = random.nextLong();
        return String.format("%016x", value);
    }
    //=================================================================================================

    /**
     * AES对称算法 解密
     *
     * @param inputStr 密文
     * @param password 密钥
     */
    public static String decryptByAES(String inputStr, String password) throws Exception {
        byte[] byteData = Base64.decodeBase64(inputStr.getBytes("utf-8"));
        byte[] bytePassword = password.getBytes();
        return new String(decryptByAES(byteData, bytePassword), "utf-8");
    }

    private static byte[] decryptByAES(byte[] data, byte[] pwd) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec keySpec = new SecretKeySpec(pwd, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] ret = cipher.doFinal(data);
        return ret;
    }

    //=================================================================================================

    /**
     * RSA非对称算法 私钥加签
     *
     * @param inputStr   明文
     * @param privateKey 私钥
     */
    public static String signByRSAPrivateKey(String inputStr, String privateKey) throws Exception {
        byte[] bs = Base64.encodeBase64(signByPrivateKey(inputStr.getBytes(), getPrivateKeyFromString(privateKey)));
        return new String(bs, "utf-8");
    }

    private static byte[] signByPrivateKey(byte[] data, PrivateKey privateKey) throws Exception {
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initSign(privateKey);
        sig.update(data);
        byte[] ret = sig.sign();
        return ret;
    }

    private static PrivateKey getPrivateKeyFromString(String base64String) throws InvalidKeySpecException, NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] bt = Base64.decodeBase64(base64String.getBytes("utf-8"));
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bt);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        return privateKey;
    }
    //=================================================================================================

    /**
     * RSA非对称算法 公钥验签
     *
     * @param inputStr  明文
     * @param publicKey 公钥
     * @param sign      签名值
     */
    public static boolean verifySignByRSAPublicKey(String inputStr, String publicKey, String sign) throws Exception {
        return verifyByPublicKey(inputStr.getBytes(), getPublicKeyFromString(publicKey), Base64.decodeBase64(sign.getBytes("utf-8")));
    }

    private static boolean verifyByPublicKey(byte[] data, PublicKey publicKey, byte[] signature) throws Exception {
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initVerify(publicKey);
        sig.update(data);
        boolean ret = sig.verify(signature);
        return ret;
    }

    private static PublicKey getPublicKeyFromString(String base64String) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
        byte[] bt = Base64.decodeBase64(base64String.getBytes("utf-8"));
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bt);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
        return publicKey;
    }

    //=================================================================================================

    /**
     * RSA非对称算法 公钥加密 （要加密的数据不能超过127 bytes）
     *
     * @param inputStr  明文
     * @param publicKey 公钥
     */
    public static String encryptByRSAPublicKey(String inputStr, String publicKey) throws Exception {
        PublicKey key = getPublicKeyFromString(publicKey);
        byte[] bt = Base64.encodeBase64(encryptByRSA(inputStr.getBytes(), key));
        return new String(bt, "utf-8");
    }

    private static byte[] encryptByRSA(byte[] input, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] output = cipher.doFinal(input);
        return output;
    }

    //=================================================================================================

    /**
     * RSA非对称算法 私钥解密
     *
     * @param inputStr   密文
     * @param privateKey 私钥
     */
    public static String decryptByRSAPrivateKey(String inputStr, String privateKey) throws Exception {
        PrivateKey key = getPrivateKeyFromString(privateKey);
        return new String(decryptByRSA(Base64.decodeBase64(inputStr.getBytes("utf-8")), key));
    }

    private static byte[] decryptByRSA(byte[] input, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] output = cipher.doFinal(input);
        return output;
    }

    //=================================================================================================

    /**
     * 生成公私钥对
     *
     * @param filePath 生成文件路径
     */
    @SuppressWarnings("static-access")
    public static void getKeyPair(String filePath) {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = keyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGenerator.initialize(1024, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        String publicKeyString = Base64.encodeBase64String(publicKey.getEncoded());
        String privateKeyString = Base64.encodeBase64String(privateKey.getEncoded());

//		String publicKeyString = Base64.encode(publicKey.getEncoded());
//		String privateKeyString = Base64.encode(privateKey.getEncoded());
        try {
            BufferedWriter publicbw = new BufferedWriter(new FileWriter(new File(filePath + "/publicKey.keystore")));
            BufferedWriter privatebw = new BufferedWriter(new FileWriter(new File(filePath + "/privateKey.keystore")));
            publicbw.write(publicKeyString);
            privatebw.write(privateKeyString);
            publicbw.flush();
            publicbw.close();
            privatebw.flush();
            privatebw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //=================================================================================================

    /**
     * 从文件中读取公钥或私钥
     *
     * @param filePath 文件路径
     * @return 公钥或私钥
     */
    public static String readKeyFromFile(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //=================================================================================================

    public static void main(String[] args) throws Exception {
        //生成公私钥文件
        //getKeyPair("C:\\Users\\Desktop\\公私钥");
        //System.out.println("=================================================================================================");

        //从文件中读取公私钥
        String publicKey = readKeyFromFile("C:\\Users\\Desktop\\公私钥\\publicKey.keystore");
        String privateKey = readKeyFromFile("C:\\Users\\Desktop\\公私钥\\privateKey.keystore");
        System.out.println("从文件中读取的公钥：" + publicKey);
        System.out.println("从文件中读取的私钥：" + privateKey);
        System.out.println("=================================================================================================");

        //AES加密
        String inputStr = "{\"errorCode\":\"000000\",\"errorMsg\":\"交易成功\",\"ID\":\"19e4befd_5f56_4e9e_bacc_39e2ae718ae5\",\"name\":\"张三\"}";
        System.out.println("明文数据：" + inputStr);
        String password = getAESRandomKey();
        System.out.println("AES随机16位密钥：" + password);
        String str = encryptByAES(inputStr, password);
        System.out.println("AES加密后的数据：" + str);
        System.out.println("=================================================================================================");

        //AES解密
        System.out.println("AES解密前的数据：" + str);
        System.out.println("AES随机16位密钥：" + password);
        System.out.println("AES解密后的数据：" + decryptByAES(str, password));
        System.out.println("=================================================================================================");

        //RSA私钥加签
        System.out.println("明文数据：" + inputStr);
//		String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKhSigc/B91zzs3LDKnL3bdwX7A8ah0K0RGYsvV+TTvl8pZ+t+bEilYUMCjjFdBfSbimvx/C2VR4ojWXiGxi5zuGYHBvVETvDlzjRInHlrH6JCXpe9GFQYMEAoWPoPM3BoUHpiK+hbT6ulzMqZFv5mtWkn1BC8mLZbcWVY2VdyTzAgMBAAECgYABqL6fj6l+L1QMh0Hasp2+3yayUAYjvyWMbNrmvJX6Tyvy+9DIP4u1iYFoBcUDRs0tOseLgpot0PqYWKQJnXf2BCUSQkFZLG+lTWCPRbB4dlxeX4unjWSItOivDLCtupdqsBmlT1DxNBxGmP1iSj75UJI9vQG8b3lTmgE71r8kgQJBAN3+AXGSsZGz67TGwmCMgsqa8d5gmNIW35Ouf+PVPRGUBndaBKPd4nuWqc4ExWYT0jsuY6OKdO1EVFzePfllQWECQQDCG7wjKaDse9VuvoMkkNxt0dQtLQP5q8mz8CzBTYbhn/TZFN8cJAy6ydu08StLGuVful4TA4hyoiX2KbN7L4LTAkEA2iveDVmnbFQQTg2dUTgm/qoDFZOH/cn/F2xDQo36w8DgDgKJVuclxGLRJamou2a1kfZIdJ0Jx3oFE3RUIjwyYQJAGoCW62YfpqoxtfElDrUIo6XmhlSDGm+uFndQZQQkymGAmLMU007IvigZa1cSRirh7nQPrCDrXFtvO3DuJH+UKQJAKiQLlRuxihH7lrTiOmXYMVo5cD3T8L4Hz+B2AFABtI6WDZWSn+XZUvcpdwPSE0v+FG9W0mbZur15fPBTiXIk1g==";
        System.out.println("RSA私钥：" + privateKey);
        String sign = signByRSAPrivateKey(inputStr, privateKey);
        System.out.println("RSA私钥加签：" + sign);
        System.out.println("=================================================================================================");

        //RSA公钥验签
        System.out.println("明文数据：" + inputStr);
//		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCoUooHPwfdc87Nywypy923cF+wPGodCtERmLL1fk075fKWfrfmxIpWFDAo4xXQX0m4pr8fwtlUeKI1l4hsYuc7hmBwb1RE7w5c40SJx5ax+iQl6XvRhUGDBAKFj6DzNwaFB6YivoW0+rpczKmRb+ZrVpJ9QQvJi2W3FlWNlXck8wIDAQAB";
        System.out.println("RSA公钥：" + publicKey);
        System.out.println("签名值：" + sign);
        System.out.println("RSA公钥验签：" + verifySignByRSAPublicKey(inputStr, publicKey, sign));
        System.out.println("=================================================================================================");

        //RSA公钥加密
        System.out.println("RSA加密前明文：" + password);
        System.out.println("RSA公钥：" + publicKey);
        String encryptStr = encryptByRSAPublicKey(password, publicKey);
        System.out.println("RSA公钥加密后数据：" + encryptStr);
        System.out.println("=================================================================================================");

        //RSA私钥解密
        System.out.println("RSA解密前密文：" + encryptStr);
        System.out.println("RSA私钥：" + privateKey);
        System.out.println("RSA私钥解密后数据：" + decryptByRSAPrivateKey(encryptStr, privateKey));
        System.out.println("=================================================================================================");

    }


}
