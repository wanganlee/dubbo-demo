package com.demo.io;

import com.demo.net.SocketExecutors;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Objects;

/**
 * @author developer.wang
 * @date 2020/3/24
 */
public class CrazyClient {

    private Socket socket;
    private BufferedReader brServer;
    private BufferedReader keyIn;
    private PrintStream printStream;

    public static void main(String[] args) {
        CrazyClient client = new CrazyClient();
        client.init();
        client.readAndSend();
    }

    private void init() {

        try {
            // 初始化代表键盘的输入流
            this.keyIn = new BufferedReader(new InputStreamReader(System.in));
            // 连接到服务器端
            this.socket = new Socket("127.0.0.1", CrazyConstant.PORT);
            // 获取该Socket对应的输入流和输出流
            this.printStream = new PrintStream(this.socket.getOutputStream());
            this.brServer = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            String tip = "";
            while (true) {
                String userName = JOptionPane.showInputDialog(String.format("%s 输入用户名", tip));
                // 在用户输入用户名前后增加协议字符串后发送
                this.printStream.println(String.format("%s%s%s", CrazyItProtocol.USER_ROUND, userName, CrazyItProtocol.USER_ROUND));
                // 读取服务器端的响应
                String result = this.brServer.readLine();
                // 如果用户名重复，则开始下次循环
                if (CrazyItProtocol.NAME_REP.equals(result)) {
                    tip = "用户名重复！请重写输入";
                    continue;
                }
                // 如果服务器端返回登录成功，则结束循环
                if (CrazyItProtocol.LOGIN_SUCCESS.equals(result)) {
                    break;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(String.format("服务异常！%s", e));
            this.closeResource();
        }

        SocketExecutors.clientExecutors().submit(new ClientThread(this.brServer));
    }

    /**
     * 读取键盘输出，并发送给服务器端
     */
    private void readAndSend() {
        try {
            String line;

            while (Objects.nonNull(line = this.keyIn.readLine())) {
                // 如果发送的信息中有冒号，且以//开头，则认为是想发送私聊信息
                if (line.indexOf(":") > 0 && line.startsWith("//")) {
                    line = line.substring(2);
                    this.printStream.println(CrazyItProtocol.PRIVATE_ROUND + line.split(":")[0] + CrazyItProtocol.SPLIT_SIGN + line.split(":")[1]
                            + CrazyItProtocol.PRIVATE_ROUND);
                } else {
                    this.printStream.println(CrazyItProtocol.MSG_ROUND + line + CrazyItProtocol.MSG_ROUND);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(String.format("服务处理异常！%s", e));
            System.exit(1);
        }
    }

    private void closeResource() {
        try {
            if (Objects.nonNull(this.keyIn)) {
                this.keyIn.close();
            }

            if (Objects.nonNull(this.brServer)) {
                this.brServer.close();
            }
            if (Objects.nonNull(this.printStream)) {
                this.printStream.close();
            }
            if (Objects.nonNull(this.socket)) {
                this.socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static final class ClientThread implements Runnable {

        private final BufferedReader reader;

        ClientThread(BufferedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            String line;
            try {
                // 打印从服务器端读取到的信息             `````````
                while (Objects.nonNull(line = this.reader.readLine())) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (Objects.nonNull(this.reader)) {
                        this.reader.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
