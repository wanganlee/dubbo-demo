package com.demo.net;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * URLEncoder, URLDecoder
 *
 * @author developer.wang
 * @date 2020/3/22
 * @see java.net.URLEncoder
 * @see java.net.URLDecoder
 */
public class URLCodeDemo {

    private static final String ENC = StandardCharsets.UTF_8.toString();

    public static void main(String[] args) {
        String keyWord = "疯狂Java讲义";
        try {
            // 将普通字符串转换为application/x-www-form-urlencoded字符串
            String encode = URLEncoder.encode(keyWord, ENC);
            System.out.println(String.format("encode: %s", encode));

            // 将application/x-www-form-urlencoded字符串转换为普通字符串
            System.out.println(String.format("decode: %s", URLDecoder.decode(encode, ENC)));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        // char c = '汪';
        // System.out.println(c);
        // System.out.println(Character.getNumericValue(c));
        // System.out.println(Character.toString(c));
    }
}
