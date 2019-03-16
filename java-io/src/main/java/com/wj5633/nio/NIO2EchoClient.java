package com.wj5633.nio;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/3/6 20:27
 * @description
 */

public class NIO2EchoClient {

    public static void main(String[] args) {
        int port = 8080;

        new Thread(new NIO2ClientHandler("127.0.0.1", port)).start();
    }
}
