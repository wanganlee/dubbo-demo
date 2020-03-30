package com.demo.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author developer.wang
 * @date 2020/3/22
 */
public class DemoServer {

    private static final CopyOnWriteArrayList<Socket> SOCKETS = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8088);
            while (true) {
                // 此代码会阻塞，将一直等待别人的连接
                Socket socket = serverSocket.accept();
                SOCKETS.add(socket);
                SocketExecutors.serverExecutors().submit(new ServerThread(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static final class ServerThread implements Runnable {

        private final Socket socket;
        private final BufferedReader reader;

        ServerThread(Socket socket) throws IOException {
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        }

        @Override
        public void run() {
            System.out.println(String.format("server thread: [%s] start handling...", Thread.currentThread().getName()));
            String line;
            try {
                while (Objects.nonNull(line = this.reader.readLine())) {
                    String finalLine = line;
                    // 遍历socket列表中的每个socket，将读取到的内容向每个socket发送一次
                    SOCKETS.stream().filter(Objects::nonNull).forEach(o -> {
                        try {
                            System.out.println(String.format("server receive: %s, now start to send to client", finalLine));
                            PrintStream printStream = new PrintStream(o.getOutputStream());
                            printStream.println(finalLine);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } catch (IOException e) {
                // 如果捕获到异常，则表明该socket对应的客户端已经关闭，删除该socket
                e.printStackTrace();
                SOCKETS.remove(socket);
            }
            System.out.println(String.format("server thread: [%s] end handling...", Thread.currentThread().getName()));
        }
    }
}
