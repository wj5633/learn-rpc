package com.wj5633.rmi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/2/28 0:18
 * @description
 */

public class CustomerSocketFactory extends RMISocketFactory {
    public Socket createSocket(String host, int port) throws IOException {
        return new Socket(host, port);
    }

    public ServerSocket createServerSocket(int port) throws IOException {
        if (port == 0) {
            port = 8501;
        }
        System.out.println("rmi notify port: " + port);
        return new ServerSocket(port);
    }
}
