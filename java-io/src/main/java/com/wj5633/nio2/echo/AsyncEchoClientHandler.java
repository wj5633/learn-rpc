package com.wj5633.nio2.echo;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/3/6 22:31
 * @description
 */

public class AsyncEchoClientHandler implements Runnable, CompletionHandler<Void, AsyncEchoClientHandler> {

    private AsynchronousSocketChannel client;
    private String host;
    private int port;
    private CountDownLatch latch;

    public AsyncEchoClientHandler(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            client = AsynchronousSocketChannel.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);

        client.connect(new InetSocketAddress(host, port), this, this);
        try {
            latch.await();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void completed(Void result, AsyncEchoClientHandler attachment) {
        // 准备写入服务端的数据
        byte[] req = "Hello world!".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();

        client.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                if (attachment.hasRemaining()) {
                    client.write(attachment, attachment, this);
                } else {
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    client.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {
                            attachment.flip();

                            byte[] bytes = new byte[attachment.remaining()];
                            attachment.get(bytes);

                            String body;
                            try {
                                body = new String(bytes);
                                System.out.println("echo content is: " + body);

                                latch.countDown();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {
                            close();
                        }
                    });
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                close();
            }
        });
    }

    @Override
    public void failed(Throwable exc, AsyncEchoClientHandler attachment) {
        close();
    }

    public void close() {
        try {
            client.close();
            latch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
