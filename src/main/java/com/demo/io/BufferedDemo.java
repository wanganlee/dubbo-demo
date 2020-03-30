package com.demo.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * @author developer.wang
 * @date 2020/3/23
 */
public class BufferedDemo {


    public static void main(String[] args) {

        // 将System.in对象转换成Reader对象，并将Reader对象包装成BufferedReader
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            // 采用循环方式来逐行地读取
            while (Objects.nonNull(line = reader.readLine())) {
                if ("exit".equals(line)) {
                    System.exit(1);
                }
                System.out.println(String.format("输入的内容为：%s", line));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
