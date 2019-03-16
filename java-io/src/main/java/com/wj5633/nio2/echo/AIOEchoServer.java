package com.wj5633.nio2.echo;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/3/6 22:13
 * @description
 */

public class AIOEchoServer {

    public static void main(String[] args) {
        int port =8080;
        AsyncEchoServerHandler timeServer = new AsyncEchoServerHandler(port);

        new Thread(timeServer).start();
    }
}
