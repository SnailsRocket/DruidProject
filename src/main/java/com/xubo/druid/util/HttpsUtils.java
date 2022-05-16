package com.xubo.druid.util;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import jodd.util.StringUtil;


/**
 * @Author xubo
 * @Date 2022/5/16 14:36
 */
public final class HttpsUtils {

    public static <T> T Post(String url, Map<String, Object> params, Class<T> objectClass) {
        try {
            HttpURLConnection response = HttpsUtils.httpPost(url, params);
            String responseString = HttpsUtils.GetResponseAsString(response);
            System.out.println("responseString");
            System.out.println(responseString);
            T result = JSON.parseObject(responseString, objectClass);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * /**
     * 把响应流转换为文本
     **/
    private static String GetResponseAsString(HttpURLConnection response) throws IOException {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader br = null;
        String result = "";
        try {
            // 7.获取返回报文
            int responseCode = response.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = response.getInputStream();
                StringBuilder sb = new StringBuilder();
                inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                br = new BufferedReader(inputStreamReader);

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                //返回报文
                result = sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                inputStream.close();
            }

            if (null != inputStreamReader) {
                inputStreamReader.close();
            }
            if (null != br) {
                br.close();
            }
        }
        return result;
    }

    /**
     * 网络请求
     *
     * @param url       请求接口完整地址
     * @param paramsMap 请求参数
     * @return
     * @throws IOException
     */
    public static HttpURLConnection httpPost(String url, Map<String, Object> paramsMap) throws IOException {
        return httpPost(url, paramsMap, null, null);
    }

    /**
     * 网络请求
     *
     * @param url            请求接口完整地址
     * @param paramsMap      请求参数
     * @param connectTimeout 链接建立的超时时间
     * @param readTimeout    响应超时时间，超过此时间不再读取响应
     * @return
     * @throws IOException
     */
    public static HttpURLConnection httpPost(String url, Map<String, Object> paramsMap, Integer connectTimeout, Integer readTimeout) throws IOException {
        BufferedWriter writer = null;
        try {
            connectTimeout = null == connectTimeout ? 30000 : connectTimeout;
            readTimeout = null == readTimeout ? 30000 : readTimeout;
            URL urlConnection = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConnection.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(connectTimeout);
            connection.setReadTimeout(readTimeout);
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
            connection.connect();

            String body = buildRequest(paramsMap);
            writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            writer.write(StringUtil.isBlank(body) ? "" : body);
            return connection;

        } finally {
            if (null != writer) {
                writer.close();
            }
        }

    }

    /**
     * 组装请求参数
     *
     * @param paramsMap 请求参数
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String buildRequest(Map<String, Object> paramsMap) throws UnsupportedEncodingException {
        if (null == paramsMap || paramsMap.size() == 0) {
            return null;
        }
        StringBuffer postData = new StringBuffer();
        boolean hasParam = false;
        for (String key : paramsMap.keySet()) {
            String value = (String) paramsMap.get(key);
            if (StringUtil.isNotBlank(key) && StringUtil.isNotBlank(value)) {
                if (hasParam) {
                    postData.append("&");
                }
                postData.append(key);
                postData.append("=");
                postData.append(URLEncoder.encode(value, "UTF-8"));
                hasParam = true;
            }
        }

        return postData.toString();
    }
}
