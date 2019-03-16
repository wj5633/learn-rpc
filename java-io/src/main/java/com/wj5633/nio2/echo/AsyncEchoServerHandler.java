package com.wj5633.nio2.echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/3/6 22:14
 * @description
 */

public class AsyncEchoServerHandler implements Runnable {

    private int port;

    private CountDownLatch latch;

    private AsynchronousServerSocketChannel asynchronousServerSocketChannel;

    public AsyncEchoServerHandler(int port) {
        this.port = port;
        try {
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        doAccept();

        try {
            latch.await();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doAccept() {
        asynchronousServerSocketChannel.accept(this, new CompletionHandler<AsynchronousSocketChannel, AsyncEchoServerHandler>() {

            @Override
            public void completed(AsynchronousSocketChannel result, AsyncEchoServerHandler attachment) {
                attachment.asynchronousServerSocketChannel.accept(attachment, this);

                ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                result.read(buffer, buffer, new ReadCompletionHandler(result));
            }

            @Override
            public void failed(Throwable exc, AsyncEchoServerHandler attachment) {
                exc.printStackTrace();
                attachment.latch.countDown();
            }
        });
    }
}
