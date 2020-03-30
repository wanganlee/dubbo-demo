package com.demo.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Objects;

/**
 * @author developer.wang
 * @date 2020/3/22
 */
public class DemoClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8088);
            // 开启线程不断地读取来自服务器端的数据
            SocketExecutors.clientExecutors().submit(new ClientThread(socket));

            PrintStream printStream = new PrintStream(socket.getOutputStream());
            String line;
            // 不断地读取键盘输入
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (Objects.nonNull(line = reader.readLine())) {
                // 将用户的键盘输入内容写入socket对应的输出流
                printStream.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static final class ClientThread implements Runnable {

        private final Socket socket;
        private final BufferedReader reader;

        ClientThread(Socket socket) throws IOException {
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        }

        @Override
        public void run() {
            System.out.println(String.format("client thread: [%s] start handling...", Thread.currentThread().getName()));
            String content;
            try {
                // 不断地读取socket输入流中的内容，并打印出来
                while (Objects.nonNull(content = this.reader.readLine())) {
                    System.out.println(String.format("client receive: %s", content));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("client thread: [%s] end handling...", Thread.currentThread().getName()));
        }
    }
}
