package com.wj5633.nio;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/3/6 20:13
 * @description
 */

public class NIO2EchoServer {

    public static void main(String[] args) {
        int port =8080;

        NIO2EchoServerHandler server = new NIO2EchoServerHandler(port);
        new Thread(server).start();
    }
}
