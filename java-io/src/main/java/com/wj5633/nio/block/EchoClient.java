package com.wj5633.nio.block;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/3/6 2:02
 * @description
 */

public class EchoClient {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.wrap("Hello world!".getBytes());

        CharBuffer charBuffer;
        CharsetDecoder decoder = Charset.defaultCharset().newDecoder();

        try (SocketChannel socketChannel = SocketChannel.open()) {
            if (socketChannel.isOpen()) {
                socketChannel.configureBlocking(true);
                socketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 128 * 1024);
                socketChannel.setOption(StandardSocketOptions.SO_SNDBUF, 128 * 1024);
                socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
                socketChannel.setOption(StandardSocketOptions.SO_LINGER, 5);

                socketChannel.connect(new InetSocketAddress("127.0.0.1", 8085));

                if (socketChannel.isConnected()) {

                    socketChannel.write(byteBuffer);
                    ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                    while (socketChannel.read(buffer) != -1) {

                        buffer.flip();
                        charBuffer = decoder.decode(buffer);
                        System.out.println(charBuffer.toString());

                        if (buffer.hasRemaining()) {
                            buffer.compact();
                        } else {
                            buffer.clear();
                        }
                    }
                }
            } else {
                throw new RuntimeException("connection cannot be established!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


