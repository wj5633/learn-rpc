package com.wj5633.nio.block;

import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/3/6 1:55
 * @description
 */

public class EchoServer {

    private static ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            if (serverSocketChannel.isOpen()) {
                serverSocketChannel.configureBlocking(true);
                serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
                serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);

                serverSocketChannel.bind(new InetSocketAddress(8085));
                while (true) {
                    try {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        executor.submit(new EchoHandler(socketChannel));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                throw new RuntimeException("server socket channel cannot ne opened!");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
