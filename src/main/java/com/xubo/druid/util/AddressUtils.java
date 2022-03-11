package com.xubo.druid.util;

import com.alibaba.fastjson.JSON;
import org.springframework.http.ResponseEntity;

import java.io.*;

/**
 * @Author xubo
 * @Date 2022/3/10 14:01
 */
public class AddressUtils {

    public static void main(String[] args) {
        /*File file = new File("E:\\xubo\\note\\privoce\\privoce3.txt");
        InputStreamReader reader = null;
        StringBuffer sb = new StringBuffer();
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            int temp;
            while ((temp  = reader.read()) != -1) {
                if(((char)temp) != 'r') {
                    sb.append(temp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Object parse = JSON.parse(sb.toString());
        System.out.println(parse);*/


        File file=new File("E:\\xubo\\note\\privoce\\privoce3.txt");
        StringBuffer sb = new StringBuffer();
        try {
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),"UTF-8");//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    //System.out.println(lineTxt);
                    sb.append(lineTxt);
                }
                read.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sb.length());
        String s = formatStr(sb.toString());
        Object parse = JSON.parse(s);
        System.out.println(parse);
    }


    public static String formatStr(String str) {
        int i = str.lastIndexOf("{");
        int j = str.lastIndexOf("}");
        /*if(i==-1||j==-1){
            br.setErrno(400);
            br.setData(str);
            br.setErrmsg("转换失败");
            return ResponseEntity.ok(br);
        }*/
        String substring = str.substring(i, j + 1);
        System.out.println("截取字符串："+substring);
        return substring;
    }



}
