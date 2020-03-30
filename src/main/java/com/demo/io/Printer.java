package com.demo.io;

import java.io.FileReader;
import java.io.FileWriter;

/**
 * Printer.
 *
 * @author developer.wang
 * @date 2020/3/23
 */
public class Printer {

    private static final String FILE_PATH = "/Users/wanganle/workspace/dubbo-demo/src/main/java/com/demo/io/Printer.java";

    private static final String TARGET_PATH = "printer-demo.txt";


    public static void main(String[] args) {
        char[] content = new char[32];
        int hasRead;
        try (FileReader reader = new FileReader(FILE_PATH);
             FileWriter writer = new FileWriter(TARGET_PATH)) {

            while ((hasRead = reader.read(content)) != -1) {
                writer.write(content, 0, hasRead);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        // byte[] content = new byte[1024];
        // int read;
        //
        // try (FileInputStream inputStream = new FileInputStream(FILE_PATH);
        //      FileOutputStream outputStream = new FileOutputStream(TARGET_PATH);) {
        //
        //     while (-1 != (read = inputStream.read(content))) {
        //         outputStream.write(content, 0, read);
        //     }
        //
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }


    }
}
