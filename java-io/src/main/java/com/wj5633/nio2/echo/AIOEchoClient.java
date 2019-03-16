package com.wj5633.nio2.echo;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/3/6 22:30
 * @description
 */

public class AIOEchoClient {

    public static void main(String[] args) {
        int port = 8080;
        String host = "127.0.0.1";
        new Thread(new AsyncEchoClientHandler(host, port)).start();
    }
}
