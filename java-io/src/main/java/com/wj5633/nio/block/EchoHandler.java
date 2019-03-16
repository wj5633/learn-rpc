package com.wj5633.nio.block;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/3/6 1:59
 * @description
 */

public class EchoHandler implements Runnable {

    private SocketChannel socketChannel;

    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    public EchoHandler(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        try {
            System.out.println("EchoHandler is running.");
            while (socketChannel.read(buffer) != -1) {
                buffer.flip();
                socketChannel.write(buffer);
                if (buffer.hasRemaining()) {
                    buffer.compact();
                } else {
                    buffer.clear();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
