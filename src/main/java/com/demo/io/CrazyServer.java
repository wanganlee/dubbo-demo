package com.demo.io;

import com.demo.net.SocketExecutors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

/**
 * @author developer.wang
 * @date 2020/3/24
 */
public class CrazyServer {

    public static final CrazyItMap<String, PrintStream> clients = new CrazyItMap<>();

    public static void main(String[] args) {
        CrazyServer server = new CrazyServer();
        server.init();
    }

    private void init() {
        // 建立监听的ServerSocket
        try (ServerSocket serverSocket = new ServerSocket(CrazyConstant.PORT)) {
            // 采用死循环来不断地接收来自客户端的请求
            while (true) {
                Socket socket = serverSocket.accept();
                SocketExecutors.serverExecutors().submit(new ServerThread(socket));
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(String.format("服务器启动失败，是否端口：%s 已经被占用？", CrazyConstant.PORT));
        }

    }

    static final class ServerThread implements Runnable {

        private Socket socket;

        ServerThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader reader = null;
            PrintStream printStream = null;
            String line;

            try {
                // 获取该Socket对应的输入流
                reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                // 获取该Socket对应的输出流
                printStream = new PrintStream(this.socket.getOutputStream());

                while (Objects.nonNull(line = reader.readLine())) {
                    if (line.startsWith(CrazyItProtocol.USER_ROUND) && line.endsWith(CrazyItProtocol.USER_ROUND)) {// 用户登录
                        // 获取真实信息
                        String userName = this.getRealMsg(line);
                        if (clients.map().containsKey(userName)) {
                            System.out.println("重复");
                            printStream.println(CrazyItProtocol.NAME_REP);
                        } else {
                            System.out.println("成功");
                            printStream.println(CrazyItProtocol.LOGIN_SUCCESS);
                            clients.map().put(userName, printStream);
                        }
                    } else if (line.startsWith(CrazyItProtocol.PRIVATE_ROUND) && line.endsWith(CrazyItProtocol.PRIVATE_ROUND)) {// 私聊信息，只向特定的输出流发送
                        // 得到真实信息
                        String userAndMsg = this.getRealMsg(line);
                        // 以特定分隔符分割字符串，前半部分是私聊用户，后半部分是聊天信息
                        String userName = userAndMsg.split(CrazyItProtocol.SPLIT_SIGN)[0];
                        String msg = userAndMsg.split(CrazyItProtocol.SPLIT_SIGN)[1];
                        clients.map().get(userName).println(String.format("%s 悄悄对你说：%s", clients.getKeyByValue(printStream), msg));
                    } else { // 公聊，向每个Socket发送
                        // 得到真实信息
                        String realMsg = this.getRealMsg(line);
                        clients.valueSet().parallelStream().filter(Objects::nonNull).forEach(o ->
                                o.println(String.format("%s 说： %s", clients.getKeyByValue(o), realMsg)));
                    }
                }
            } catch (IOException e) {
                // 捕获到异常后，表明该Socket对应的客户端已经出现问题，所以程序将其对应的输出流从map中删除
                try {
                    if (Objects.nonNull(reader)) {
                        reader.close();
                    }
                    if (Objects.nonNull(printStream)) {
                        printStream.close();
                    }
                    if (Objects.nonNull(this.socket)) {
                        this.socket.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        /**
         * 将读到的内容去掉前后的协议字符，恢复成真实数据
         */
        private String getRealMsg(String msg) {
            return msg.substring(CrazyItProtocol.PROTOCOL_LENGTH, msg.length() - CrazyItProtocol.PROTOCOL_LENGTH);
        }
    }

}
