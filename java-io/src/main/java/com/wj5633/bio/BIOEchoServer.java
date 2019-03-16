package com.wj5633.bio;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/3/6 1:33
 * @description
 */

public class BIOEchoServer {

    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        int port = 8082;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket;
            while (true) {
                socket = serverSocket.accept();
                executor.submit(new BIOEchoServerHandler(socket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
