package com.xubo.druid.encrypt;


import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author xubo
 * @Date 2022/2/25 15:10
 * 直接参考AESUtils 这个工具类
 */
public class GenerateAESCode {

    //算法
    private static String algorithm="AES";
    //自定义密钥，由uuid生成的32位字符串
    /**
     * uuid是128位整数 以16进制展现
     */
    private static String key = UUID.randomUUID().toString().replaceAll("-","");
    /**
     * 生成密钥
     * @return
     * @throws Exception
     */
    public static Key getKey(String strKey) throws Exception{
        /*//创建密钥生成器
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        //初始化密钥
        keyGenerator.init(new SecureRandom(strKey.getBytes()));
        //生成密钥
        SecretKey getKey = keyGenerator.generateKey();
        //System.out.println("生成密钥:"+bytesToHexString(getKey.getEncoded ())+"----"+bytesToHexString(getKey.getEncoded ()).length());
        System.out.println(getKey);
        return getKey;*/
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        while (strKey.length() < 16) {
            strKey = strKey + "0";
        }
        return getKey(strKey.getBytes());
    }

    private static Key getKey(byte[] arrBTmp) throws Exception {
        // 创建一个空的16位字节数组（默认值为0）
        byte[] arrB = new byte[16];
        // 将原始字节数组转换为16位
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        // 生成密钥

        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128, new SecureRandom(arrB));

        SecretKeySpec key;
        key = new SecretKeySpec(arrB, "AES");
        return key;
    }


    public static void main(String[] args) throws Exception {
        Key xubo = getKey("xubo");
        System.out.println(bytesToHexString(xubo.getEncoded()));

        /*byte[] aesKey = generateDesKey(128);
        System.out.println(aesKey.toString());
        List<String> list = new ArrayList<>();
        for (byte b : aesKey) {
            System.out.println(b);
            list.add(String.valueOf(b));
        }
        System.out.println(list);*/
    }

    /**
     * Convert byte[] to hex string
     *
     * @param src byte[] data
     * @return hex string
     */
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static byte[] generateDesKey(int length) throws Exception {
        //实例化
        KeyGenerator kgen = null;
        kgen = KeyGenerator.getInstance("AES");
        //设置密钥长度
        kgen.init(length);
        //生成密钥
        SecretKey skey = kgen.generateKey();
        //返回密钥的二进制编码
        return skey.getEncoded();
    }



}
