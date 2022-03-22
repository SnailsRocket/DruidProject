package com.xubo.druid.util;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 使用AES加密与解密
 *
 * @author Wangyaohui
 */
public class AESStrUtilsOrder {
    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 128;
    private final static String ENCODING = "UTF-8";
    /**
     * <p>
     * 加密
     * </p>
     *
     * @param data
     *            需要加密内容
     * @param key
     *            密钥
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(String data, String key) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");

            secureRandom.setSeed(key.getBytes());
            kgen.init(secureRandom);
            //kgen.init(KEY_SIZE, new SecureRandom(key.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, ALGORITHM);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
            byte[] byteContent = data.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content
     *            待解密内容
     * @param password
     *            解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] content, String key) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");

            secureRandom.setSeed(key.getBytes());
            kgen.init(secureRandom);

            //kgen.init(KEY_SIZE, new SecureRandom(key.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, ALGORITHM);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, keySpec);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static String  aesEncypt(String value,String password) {
        System.out.println("加密前：" + value);
        byte[] encryptResult = encrypt(value, password);
        String encryptResultStr = parseByte2HexStr(encryptResult);
        System.out.println("加密后：" + encryptResultStr);
        return encryptResultStr;
    }

    public static  String aesDecypt(String value, String password) {
        byte[] decryptFrom = parseHexStr2Byte(value);
        byte[] decryptResult = decrypt(decryptFrom, password);

        String decryptedStr = new String(decryptResult);
        System.out.println("解密后：" + decryptedStr);
        return decryptedStr;
    }
    public static void main(String[] args) {
        String content = "null";
        String password = "ccbftsr";
        // 加密
        System.out.println("加密前：" + content);
        byte[] encryptResult = encrypt(content, password);
        String encryptResultStr = parseByte2HexStr(encryptResult);
        System.out.println("加密后：" + encryptResultStr);

        // 解密
//        byte[] decryptFrom = parseHexStr2Byte("D37C3FF5DA3C15E39F9429D65A0654367CB35FBED2EE5A10A31A5C88C32C938F6C046887E537018292F7AE579E564684");
        // byte[] decryptFrom = parseHexStr2Byte("7FF01629D32F42D6AE82D5B2E505D9FC536FD733F39CFCC0C86C37FE88837092F11DA18835E804D876552132E4951D36D555038714108B94AE4602592904C83F");
        byte[] decryptFrom = parseHexStr2Byte("A6F2ACE7A270BE73365E268A94BA23E631FD36EF4EDD19254D6295B1E80A25D30B123E72108B5737E36F53572CB86D0A8FB7A3FCB6D69CAF6BD92A4DF733C76B");
        //  A6F2ACE7A270BE73365E268A94BA23E631FD36EF4EDD19254D6295B1E80A25D30B123E72108B5737E36F53572CB86D0A8FB7A3FCB6D69CAF6BD92A4DF733C76B
        byte[] decryptResult = decrypt(decryptFrom, password);
        System.out.println("解密后：" + new String(decryptResult));
    }

}
