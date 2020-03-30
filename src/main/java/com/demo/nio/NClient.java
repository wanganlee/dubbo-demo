package com.demo.nio;

import com.demo.net.SocketExecutors;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @author developer.wang
 * @date 2020/3/25
 */
public class NClient {

    private static final Charset CHARSET = Charset.forName("UTF-8");
    private static final String HOST_NAME = "127.0.0.1";
    private static final int PORT = 8080;
    private SocketChannel socketChannel;
    private Selector selector;

    public static void main(String[] args) {
        new NClient().init();
    }

    private void init() {
        try {
            this.socketChannel = SocketChannel.open(new InetSocketAddress(HOST_NAME, PORT));
            this.socketChannel.configureBlocking(false);

            this.selector = Selector.open();

            this.socketChannel.register(this.selector, SelectionKey.OP_READ);

            // 启动线程读取服务器端的数据
            SocketExecutors.clientExecutors().submit(new ClientThread(this.selector));

            // 创建键盘输入流
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                // 读取键盘输入
                String line = scanner.nextLine();
                // 将键盘输入的内容输出到socketChannel中
                this.socketChannel.write(CHARSET.encode(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取服务器端数据
     */
    static final class ClientThread implements Runnable {

        private final Selector selector;

        ClientThread(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            try {
                while (this.selector.select() > 0) {
                    for (SelectionKey selectionKey : this.selector.selectedKeys()) {
                        // 删除正在处理的SelectionKey
                        this.selector.selectedKeys().remove(selectionKey);

                        // 只处理该SelectionKey对应的channel中有可读的数据
                        if (!selectionKey.isReadable()) {
                            continue;
                        }

                        // 使用NIO读取channel中的数据
                        StringBuilder content = new StringBuilder();
                        if (selectionKey.channel() instanceof SocketChannel) {
                            SocketChannel channel = (SocketChannel) selectionKey.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);

                            while (channel.read(buffer) > 0) {
                                buffer.flip();
                                content.append(CHARSET.decode(buffer));
                            }
                            System.out.println(String.format("读取到服务器端发送的数据：%s", content.toString()));
                            // 为下一次读取做准备
                            selectionKey.interestOps(SelectionKey.OP_READ);
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
