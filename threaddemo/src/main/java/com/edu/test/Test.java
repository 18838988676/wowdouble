package com.edu.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {
    public static void main(String[] args) throws Exception{
        String url = "http://localhost:7001/nkjdpt/dwrInitController/initSendMsg";
        System.out.println(System.currentTimeMillis());
        BufferedReader is = null;
        HttpURLConnection conn = null;
        String returnValue = "";
        StringBuffer sb = new StringBuffer();
        try {
            conn = (HttpURLConnection) (new URL(url)).openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            conn.setConnectTimeout(1000);
            conn.setReadTimeout(3000);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);

            is = new BufferedReader(new InputStreamReader(// 获取借口返回的输出流
                    conn.getInputStream(), "utf-8"));
            String ret = "";
            while ((ret = is.readLine()) != null) {
                sb.append(ret);
            }
            returnValue = new String(sb.toString().getBytes(), "utf-8");// 设置返回值的编码
            System.out.println(System.currentTimeMillis()+returnValue);
        } catch (Exception e) {
            e.printStackTrace();
            throw  e ;
        } finally {
            try {
                if (null != is) {
                    is.close();
                    is = null;
                }
                if (null != conn) {
                    ((HttpURLConnection) conn).disconnect();
                    conn = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw  e ;
            }
        }
    }

}
