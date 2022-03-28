package com.xubo.druid.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.security.Security;
import java.util.Date;

//import net.sf.json.JSONObject;

/**
 * @Author xubo
 * @Date 2022/3/3 17:03
 */
public class PayAesUtils {

    public static final String ALGORITHM = "AES";
//     private static final String DEFAULT_KEY = "ECPB2CABCDEFGHIJ";  //16位  ccbftsr
    private static final String DEFAULT_KEY = "ccbftsr";  //16位  ccbftsr
    @SuppressWarnings("restriction")
    public static Key getKey(String strKey) throws Exception {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        while(strKey.length()<16){
            strKey=strKey+"0";
        }
        return getKey(strKey.getBytes());
    }
    /**
     * 从指定字符串生成密钥，密钥所需的字节数组长度为16位 不足16位时后面补0，超出16位只取前16位
     *
     * @param arrBTmp 构成该字符串的字节数组
     * @return 生成的密钥
     * @throws Exception
     */
    private static Key getKey(byte[] arrBTmp) throws Exception {
        // 创建一个空的16位字节数组（默认值为0）
        byte[] arrB = new byte[16];
        // 将原始字节数组转换为16位
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }

        SecretKeySpec key;
        key = new SecretKeySpec(arrB, "AES");
        return key;
    }
    /**
     * 根据密匙进行AES加密
     *
     * @param keyString
     *            密匙
     * @param srcString
     *            要加密的信息
     * @return String 加密后的信息
     */
    public static String encrypt(String keyString, String srcString) throws Exception{
        // 定义要生成的密文
        byte[] cipherByte = null;
        Key key = getKey(keyString);
        // 得到加密/解密器
        Cipher c1 = Cipher.getInstance("AES");
        // 用指定的密钥和模式初始化Cipher对象
        // 参数:(ENCRYPT_MODE, DECRYPT_MODE, WRAP_MODE,UNWRAP_MODE)
        c1.init(Cipher.ENCRYPT_MODE, key);
        // 对要加密的内容进行编码处理,
        cipherByte = c1.doFinal(srcString.getBytes("UTF-8"));

        // 返回密文的十六进制形式
        return byte2hex(cipherByte);
    }

    public static String defaultEncrypt(String srcString) throws Exception{
        return encrypt(DEFAULT_KEY,srcString);
    }

    /**
     * 根据密匙进行AES解密
     *
     * @param keyString
     *            密匙
     * @param encryptedString
     *            要解密的密文
     * @return String 返回解密后信息
     */
    public static String decrypt(String keyString, String encryptedString) throws Exception{
        Key key = getKey(keyString);
        byte[] cipherByte = null;
        // 得到加密/解密器
        Cipher c1 = Cipher.getInstance("AES");
        // 用指定的密钥和模式初始化Cipher对象
        c1.init(Cipher.DECRYPT_MODE, key);
        // 对要解密的内容进行编码处理
        cipherByte = c1.doFinal(hex2byte(encryptedString));

        // return byte2hex(cipherByte);
        return new String(cipherByte, "UTF-8");
    }


    public static String defaultDecrypt(String encryptedString) throws Exception{
        return decrypt(DEFAULT_KEY,encryptedString);
    }


    /**
     * 将二进制转化为16进制字符串
     *
     * @param b
     *            二进制字节数组
     * @return String
     */
    public static String byte2hex(byte[] b) {

        if(null==b){
            return null;
        }

        StringBuffer hs = new StringBuffer("");
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }
        }
        return hs.toString().toUpperCase();
    }
    /**
     * 十六进制字符串转化为2进制
     *
     * @param hex
     * @return
     */
    public static byte[] hex2byte(String hex) throws Exception {
        if (null== hex || hex.length() % 2 != 0) {
            //System.out.println("[ERROR]DESUtil:string to hex is null or invalid length!");
            throw new Exception();
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
            String swap = "" + arr[i++] + arr[i];
            int byteint = Integer.parseInt(swap, 16) & 0xFF;
            b[j] = new Integer(byteint).byteValue();
        }
        return b;
    }
    /*
     * 示例
     */
    public static void main(String[] args ){
        String encryptStr = "F996AB6027EAA8EEF25908F1B8F1467EF9E142BF8BC3DDB44DC873993D12982D9B027F13DA572718FAB626E4D408AB43";
//        String key = "EPQLHJLCNURHNUZA";   // ECPB2CABCDEFGHIJ  ccbftsr
        String key = "ccbftsr";   // ECPB2CABCDEFGHIJ  ccbftsr
        String encryptStr1 = "7FF01629D32F42D6AE82D5B2E505D9FC536FD733F39CFCC0C86C37FE88837092F11DA18835E804D876552132E4951D36D555038714108B94AE4602592904C83F";
        String tranSid = String.valueOf(new Date().getTime());
        String reqTime = String.valueOf(new Date().getTime());
        JSONObject jsonObj = new JSONObject();
        /*jsonObj.put("tranSid", tranSid);
        jsonObj.put("requestTime", reqTime);
//        jsonObj.put("mobileNo", "18550000000");
        jsonObj.put("name", "王五");
        jsonObj.put("idType", "01");
        jsonObj.put("idNumber", "420117");
        jsonObj.put("openId", "1234");
        jsonObj.put("subChannel", "sub");*/
        jsonObj.put("mobile", "18551111112");
        jsonObj.put("userId", "1234567890");
        jsonObj.put("token", "hederfhedh9894325");
        try {
            //加密
            String encryptedString = encrypt(key,jsonObj.toString());
//            System.out.println("http://buy.ccb.com/openLoginInterface.jhtml?channelNo=test1&toUrl=" + URLEncoder.encode("http://buy.ccb.com/client/index.jhtml?aaa=xxx&bbb=xxx", "utf-8") + "&loginParams="+
            System.out.println("http://buy.ccb.com/openLoginInterface.jhtml?channelNo=test1&toUrl=" + URLEncoder.encode("https://mgb.cn/test_shop_jz/#/?p1=jz", "utf-8") + "&loginParams="+
                    encryptedString +"&requestTime="+ reqTime +"&tranSid=" + tranSid);
            System.out.println("加密后的数据： " + encryptedString);
            //解密
            String decryptedString = decrypt(key,encryptedString);
            System.out.println(decryptedString);

            String toUrl = "https%3A%2F%2Fmgb.cn%2Ftest_shop_jz%2F%23%2F%3Fp1%3Djz";
            String decodeURL = URLDecoder.decode(toUrl, "UTF-8");
            System.out.println(decodeURL);

            String result = defaultDecrypt(encryptStr);
            Object parse = JSON.parse(result);
            System.out.println(parse);

            System.out.println("------------");
            String s = defaultDecrypt(encryptStr1);
            System.out.println("ss" + s);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
