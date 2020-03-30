package com.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Objects;

/**
 * NIO Server.
 *
 * @author developer.wang
 * @date 2020/3/25
 */
public class NServer {

    private static final String HOST_NAME = "127.0.0.1";
    private static final int PORT = 8080;

    public static void main(String[] args) {
        new NServer().init();
    }

    private void init() {
        try {
            // charset
            Charset charset = Charset.forName("UTF-8");

            // 通过open()方法来打开一个实例，并绑定到指定IP地址及端口号的serverSocketChannel
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open().bind(new InetSocketAddress(HOST_NAME, PORT));
            // 设置以非阻塞模式工作
            serverSocketChannel.configureBlocking(false);

            // 用于检测所有channel状态的selector
            Selector selector = Selector.open();

            // 注册channel到selector上
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            // 获取需要进行io处理的channel
            while (selector.select() > 0) {
                for (SelectionKey selectionKey : selector.selectedKeys()) {
                    // 移除正在处理的key
                    selector.selectedKeys().remove(selectionKey);

                    // 如果SelectionKey对应的channel包含客户端的连接请求
                    if (selectionKey.isAcceptable()) {
                        // 调用accept方法接收连接，产生服务器端的SocketChannel
                        SocketChannel accept = serverSocketChannel.accept();
                        // 设置成非阻塞模式
                        accept.configureBlocking(false);
                        // 将该SocketChannel也注册到selector上
                        accept.register(selector, SelectionKey.OP_READ);
                        // 将SelectionKey对应的channel设置成准备接收其他请求
                        selectionKey.interestOps(SelectionKey.OP_ACCEPT);
                    }

                    // 如果SelectionKey对应的channel有数据需要读取
                    if (selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();

                        if (!channel.isConnected()) {
                            System.out.println("the connect is closed!");
                            return;
                        }

                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        StringBuilder content = new StringBuilder();
                        try {
                            // 开始读取数据
                            while (channel.read(buffer) > 0) {
                                buffer.flip();
                                content.append(charset.decode(buffer));
                            }
                            System.out.println(String.format("读取到的数据：%s", content.toString()));
                            // 将SelectionKey对应的channel设置成准备下一次读取
                            selectionKey.interestOps(SelectionKey.OP_READ);
                        } catch (IOException e) {
                            e.printStackTrace();
                            // 从Selector中删除指定的SelectionKey
                            selectionKey.cancel();
                            if (Objects.nonNull(selectionKey.channel())) {
                                selectionKey.channel().close();
                            }
                        }
                        // 如果聊天信息不为空，则遍历该selector里注册的所有SelectionKey，并将内容写入该channel中
                        if (content.toString().length() > 0) {
                            for (SelectionKey key : selector.keys()) {
                                if (key.channel() instanceof SocketChannel) {
                                    SocketChannel socketChannel = (SocketChannel) key.channel();
                                    socketChannel.write(charset.encode(content.toString()));
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
