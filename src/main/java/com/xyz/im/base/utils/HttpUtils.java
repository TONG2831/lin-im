package com.xyz.im.base.utils;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * Get Post 工具类
 */
public class HttpUtils {

    public static String get(String url, boolean gzip) {
        return link(url, null, "GET", gzip);
    }

    public static String post(String url, String input, boolean gzip) {
        return link(url, input, "POST", gzip);
    }

    public static String link(String url, String input, String method, boolean gzip) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);

            connection.setRequestMethod(method);
            // connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.connect();

            if (input != null) {
                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
                out.append(input);
                out.flush();
                out.close();
            }

            InputStream respond = connection.getInputStream();

            StringBuilder sb = new StringBuilder();

            if (gzip) {
                String s = getGiz(respond);
                sb.append(s);
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(respond, "UTF-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();
            }

            connection.disconnect();
            return sb.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static String getGiz(InputStream in) {
        try {
            GZIPInputStream giz = new GZIPInputStream(in);
            BufferedReader reader = new BufferedReader(new InputStreamReader(giz));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

}
